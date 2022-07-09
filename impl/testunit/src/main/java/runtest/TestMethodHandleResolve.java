package runtest;

import io.github.karlatemp.unsafeaccessor.Root;
import io.github.karlatemp.unsafeaccessor.Unsafe;
import io.github.karlatemp.unsafeaccessor.UnsafeAccess;
import org.junit.jupiter.api.Assertions;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TestMethodHandleResolve {
    MethodType mhType(boolean oarg, String actType, Class<?> type) {
        if (actType.equals("get")) {
            if (oarg) {
                return MethodType.methodType(type, Object.class, long.class);
            } else {
                return MethodType.methodType(type, long.class);
            }
        } else {
            if (oarg) {
                return MethodType.methodType(void.class, Object.class, long.class, type);
            } else {
                return MethodType.methodType(void.class, long.class, type);
            }
        }
    }

    @TestTask
    void run() throws Throwable {
        String[] actTypes = {"get", "put"};
        Map<String, Class<?>> typeMap = new HashMap<>();
        {
            Object[] pvMap = {
                    "Byte",         /**/byte.class,
                    "Char",         /**/char.class,
                    "Short",        /**/short.class,
                    "Int",          /**/int.class,
                    "Long",         /**/long.class,
                    "Float",        /**/float.class,
                    "Double",       /**/double.class,
                    "Boolean",      /**/boolean.class,
                    "Reference",    /**/Object.class,
            };
            for (int i = 0; i < pvMap.length; i += 2) {
                typeMap.put(pvMap[i].toString(), (Class<?>) pvMap[i + 1]);
            }
        }
        Collection<Class<?>> noDirectAddrSet = Arrays.asList(boolean.class, Object.class);
        String[] privTypes = typeMap.keySet().toArray(new String[0]);
        String[] primTypes = {
                "Byte", "Char", "Short", "Int", "Long",
                "Float", "Double",
                "Boolean",
        };
        String[] unalignedTypes = {
                "Char", "Short", "Int", "Long",
        };
        UnsafeAccess ua = UnsafeAccess.getInstance();
        for (String actType : actTypes) {
            for (String privType : privTypes) {
                String methodName = actType + privType;
                if (!noDirectAddrSet.contains(typeMap.get(privType))) {
                    Root.MethodHandleLookup.lookup(ua, methodName, mhType(
                            false, actType, typeMap.get(privType)
                    ));
                }
                Root.MethodHandleLookup.lookup(ua, methodName, mhType(
                        true, actType, typeMap.get(privType)
                ));
            }
            for (String primType : primTypes) {
                String methodName = actType + primType;
                if (!noDirectAddrSet.contains(typeMap.get(primType))) {
                    Root.MethodHandleLookup.lookup(ua, methodName, mhType(
                            false, actType, typeMap.get(primType)
                    ), true, false);
                }
                Root.MethodHandleLookup.lookup(ua, methodName, mhType(
                        true, actType, typeMap.get(primType)
                ), true, false);
            }
        }

        // all unsafe methods should be able to be resolve
        for (Method m : Unsafe.class.getDeclaredMethods()) {
            if (Modifier.isStatic(m.getModifiers())) continue;
            if (!Modifier.isPublic(m.getModifiers())) continue;
            Root.MethodHandleLookup.lookup(ua,
                    m.getName(), MethodType.methodType(m.getReturnType(), m.getParameterTypes()),
                    false, false
            );
        }
    }

    @TestTask
    void testInvokeDynamic() throws Throwable {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        writer.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "sawet/sda22te2/A23fx", null, "java/lang/Object", null);

        writer.visitField(Opcodes.ACC_STATIC, "UNSAFE_ACCESS", "Lio/github/karlatemp/unsafeaccessor/UnsafeAccess;", null, null);
        MethodVisitor mymethod;

        mymethod = writer.visitMethod(Opcodes.ACC_STATIC, "test1", "(J)I", null, null);
        mymethod.visitVarInsn(Opcodes.LLOAD, 0);
        mymethod.visitInvokeDynamicInsn("getInt", "(J)I",
                new Handle(Opcodes.H_INVOKESTATIC,
                        "io/github/karlatemp/unsafeaccessor/Root$MethodHandleLookup",
                        "resolve", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;IILjava/lang/invoke/MethodHandle;)Ljava/lang/invoke/CallSite;",
                        false
                ),
                false, false,
                new Handle(Opcodes.H_GETSTATIC,
                        "sawet/sda22te2/A23fx",
                        "UNSAFE_ACCESS", "Lio/github/karlatemp/unsafeaccessor/UnsafeAccess;",
                        false
                )
        );
        mymethod.visitInsn(Opcodes.IRETURN);
        mymethod.visitMaxs(0, 0);


        mymethod = writer.visitMethod(Opcodes.ACC_STATIC, "test2", "(J)I", null, null);
        mymethod.visitVarInsn(Opcodes.LLOAD, 0);
        mymethod.visitInvokeDynamicInsn("getInt", "(J)I",
                new Handle(Opcodes.H_INVOKESTATIC,
                        "io/github/karlatemp/unsafeaccessor/Root$MethodHandleLookup",
                        "resolveHandle", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
                        false
                )
        );
        mymethod.visitInsn(Opcodes.IRETURN);
        mymethod.visitMaxs(0, 0);


        mymethod = writer.visitMethod(Opcodes.ACC_STATIC, "test3", "(J)I", null, null);
        mymethod.visitVarInsn(Opcodes.LLOAD, 0);
        mymethod.visitInvokeDynamicInsn("getInt", "(J)I",
                new Handle(Opcodes.H_INVOKESTATIC,
                        "io/github/karlatemp/unsafeaccessor/Root$MethodHandleLookup",
                        "resolve", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;ILjava/lang/invoke/MethodHandle;)Ljava/lang/invoke/CallSite;",
                        false
                ),
                false,
                new Handle(Opcodes.H_GETSTATIC,
                        "sawet/sda22te2/A23fx",
                        "UNSAFE_ACCESS", "Lio/github/karlatemp/unsafeaccessor/UnsafeAccess;",
                        false
                )
        );
        mymethod.visitInsn(Opcodes.IRETURN);
        mymethod.visitMaxs(0, 0);



        Class<?> klass = RunTestUnit.define(writer);
        Unsafe usf = Unsafe.getUnsafe();
        MethodHandles.Lookup lk = Root.getTrusted(klass);
        long sizex = usf.allocateMemory(Integer.SIZE);
        int rsp;

        usf.putInt(sizex, 114);
        rsp = (int) lk.findStatic(klass, "test1", MethodType.methodType(int.class, long.class)).invoke(sizex);
        Assertions.assertEquals(114, rsp);


        usf.putInt(sizex, 514);
        rsp = (int) lk.findStatic(klass, "test2", MethodType.methodType(int.class, long.class)).invoke(sizex);
        Assertions.assertEquals(514, rsp);

        usf.putInt(sizex, 1919);
        rsp = (int) lk.findStatic(klass, "test3", MethodType.methodType(int.class, long.class)).invoke(sizex);
        Assertions.assertEquals(1919, rsp);


    }
}
