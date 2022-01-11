package codegen;

import io.github.karlatemp.unsafeaccessor.CodeGenUtils;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

import java.lang.reflect.Method;
import java.security.ProtectionDomain;

public class SunMiscUnsafeCodeGen {

    public Class<?> defineClass(String name, byte[] b, int off, int len, ClassLoader loader, ProtectionDomain protectionDomain) {
        throw new AbstractMethodError();
    }

    private static void doGen(ClassNode node, String name, String desc) {
        MethodVisitor method = node.visitMethod(Opcodes.ACC_PUBLIC, name, desc, null, null);
        method.visitVarInsn(Opcodes.ALOAD, 0);
        method.visitFieldInsn(Opcodes.GETFIELD, "io/github/karlatemp/unsafeaccessor/SunMiscUnsafe", "theUnsafe", "Lsun/misc/Unsafe;");
        Type[] args = Type.getArgumentTypes(desc);
        int counter = 1;
        for (Type t : args) {
            method.visitVarInsn(t.getOpcode(Opcodes.ILOAD), counter);
            counter += t.getSize();
        }
        method.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "sun/misc/Unsafe", "defineClass", desc, false);
        method.visitInsn(Opcodes.ARETURN);
        counter++;
        method.visitMaxs(counter, counter);
    }

    @Test
    public void main() throws Throwable {
        Method method = SunMiscUnsafeCodeGen.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class, ClassLoader.class, ProtectionDomain.class);
        String desc = Type.getMethodDescriptor(method);

        ClassNode newClass = new ClassNode();
        newClass.visit(
                Opcodes.V1_8,
                0,
                "io/github/karlatemp/unsafeaccessor/SunMiscUnsafeImpl",
                null,
                "io/github/karlatemp/unsafeaccessor/SunMiscUnsafe",
                null
        );
        CodeGenUtils.genCons(newClass);
        doGen(newClass, "defineClass", desc);
        doGen(newClass, "defineClass0", desc);
        CodeGenUtils.save(newClass);
    }
}
