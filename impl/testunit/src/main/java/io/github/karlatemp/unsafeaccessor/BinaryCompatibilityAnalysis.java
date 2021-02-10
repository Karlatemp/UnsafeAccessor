package io.github.karlatemp.unsafeaccessor;

import org.junit.jupiter.api.Assertions;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleInfo;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class BinaryCompatibilityAnalysis {
    public static boolean hasAnnotation(List<AnnotationNode> nodes, String type) {
        if (nodes == null || nodes.isEmpty()) return false;
        String type0 = "L" + type + ";";
        for (AnnotationNode node : nodes) {
            if (node != null) {
                if (node.desc.equals(type0) || node.desc.equals(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasAnnotation(MethodNode node, String type) {
        if (node != null) {
            return hasAnnotation(node.invisibleAnnotations, type) || hasAnnotation(node.visibleAnnotations, type);
        }
        return false;
    }

    public static boolean hasAnnotation(MethodNode node, Class<?> type) {
        return hasAnnotation(node, type.getName().replace('.', '/'));
    }

    public static void run() throws Throwable {
        System.out.println("Analysing Unsafe BinaryCompatibility");
        List<MethodNode> methods = new ArrayList<>();
        {
            Class<?> targetClass = Unsafe.getUnsafe().getClass();
            System.out.println("Unsafe implement: " + targetClass);

            while (targetClass != null) {
                ClassNode node = new ClassNode();
                new ClassReader(targetClass.getName()).accept(node, 0);
                if (node.methods != null) {
                    for (MethodNode m : node.methods) {
                        if ((m.access & Opcodes.ACC_STATIC) != 0) {
                            continue;
                        }
                        if (m.name.equals("<init>")) continue;
                        if (methods.stream().noneMatch(old -> old.name.equals(m.name) && old.desc.equals(m.desc))) {
                            methods.add(m);
                        }
                    }
                }

                targetClass = targetClass.getSuperclass();
                if (targetClass == Object.class) {
                    break;
                }
            }
        }
        MethodHandles.Lookup lookup = Root.getTrusted();

        for (MethodNode methodNode : methods) {
            System.out.println("Method: " + methodNode.name + methodNode.desc);
            InsnList instructions = methodNode.instructions;
            if (instructions == null) {
                System.out.println("  > No instructions");
                continue;
            }
            List<AnnotationNode> annotations = methodNode.invisibleAnnotations;
            if (annotations != null) {
                for (AnnotationNode a : annotations) {
                    System.out.println("  > Annotation: " + a.desc);
                }
            }
            for (AbstractInsnNode insnNode : instructions) {
                if (insnNode instanceof MethodInsnNode) {
                    MethodInsnNode methodInsnNode = (MethodInsnNode) insnNode;
                    System.out.println("  > Method invoke: " + methodInsnNode.owner + "." + methodInsnNode.name + methodInsnNode.desc);
                    Class<?> ownerClass = Class.forName(methodInsnNode.owner.replace('/', '.'));
                    MethodType methodType = MethodType.fromMethodDescriptorString(methodInsnNode.desc, ownerClass.getClassLoader());
                    String methodName = methodInsnNode.name;
                    try {
                        switch (methodInsnNode.getOpcode()) {
                            case Opcodes.INVOKEINTERFACE: {
                                MethodHandle handle = lookup.findVirtual(ownerClass, methodName, methodType);
                                Assertions.assertEquals(lookup.revealDirect(handle).getReferenceKind(), MethodHandleInfo.REF_invokeInterface);
                                break;
                            }
                            case Opcodes.INVOKESTATIC: {
                                MethodHandle handle = lookup.findStatic(ownerClass, methodName, methodType);
                                Assertions.assertEquals(lookup.revealDirect(handle).getReferenceKind(), MethodHandleInfo.REF_invokeStatic);
                                break;
                            }
                            case Opcodes.INVOKEVIRTUAL: {
                                MethodHandle handle = lookup.findVirtual(ownerClass, methodName, methodType);
                                Assertions.assertEquals(lookup.revealDirect(handle).getReferenceKind(), MethodHandleInfo.REF_invokeVirtual);
                                break;
                            }
                            case Opcodes.INVOKESPECIAL: {
                                if (methodName.equals("<init>")) {
                                    lookup.findConstructor(ownerClass, methodType);
                                    break;
                                }
                                throw new AssertionError("INVOKESPECIAL with-out <init>");
                            }
                            default:
                                throw new AssertionError("Unknown opcode: " + methodInsnNode.getOpcode() + "(" + Integer.toHexString(methodInsnNode.getOpcode()) + ")");
                        }
                    } catch (NoSuchMethodException nsme) {
                        if (hasAnnotation(methodNode, Analysis.SkipAnalysis.class)) {
                            System.out.println("  > " + nsme);
                        } else {
                            throw nsme;
                        }
                    }
                }
            }
        }

        for (Method method : Unsafe.class.getDeclaredMethods()) {
            if (Modifier.isAbstract(method.getModifiers())) {
                String desc = Type.getMethodDescriptor(method);
                if (methods.stream().noneMatch(it -> it.name.equals(method.getName()) && it.desc.equals(desc))) {
                    throw new AbstractMethodError(method + " not implement");
                }
            }
        }
    }
}
