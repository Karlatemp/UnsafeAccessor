package io.github.karlatemp.unsafeaccessor;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class CodeGenUtils {
    public static void genCons(ClassNode node) {
        MethodVisitor init = node.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        init.visitMaxs(1, 1);
        init.visitVarInsn(Opcodes.ALOAD, 0);
        init.visitMethodInsn(Opcodes.INVOKESPECIAL, node.superName, "<init>", "()V", false);
        init.visitInsn(Opcodes.RETURN);
    }

    public static byte[] toBytecode(ClassNode node) {
        ClassWriter writer = new ClassWriter(0);
        node.accept(writer);
        return writer.toByteArray();
    }

    public static void save(ClassNode node) {
        File output = new File(WorkingDirUtils.projectDir, "impl/loader/generated");
        File file = new File(output, node.name + ".class");
        save(file, toBytecode(node));
    }

    public static void save(File file, byte[] content) {
        file.getParentFile().mkdirs();
        try (FileOutputStream fo = new FileOutputStream(file)) {
            fo.write(content);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void save(byte[] code, String name) {
        File dir = new File(
                WorkingDirUtils.projectDir, "impl/codegen/build/x"
        );
        save(new File(dir, name + ".class"), code);
        save(new File(dir, name + ".b64.txt"), Base64.getEncoder().encode(code));
    }

    public static void main(String[] args) throws Throwable {
        ClassNode node = new ClassNode();
        node.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "test/Test", null, "java/lang/Object", null);
        genCons(node);
        BytecodeUtil.CLoader loader = new BytecodeUtil.CLoader();
        Class<?> aClass = loader.load(toBytecode(node));
        System.out.println(aClass);
        System.out.println(aClass.newInstance());
    }
}
