package codegen;

import io.github.karlatemp.unsafeaccessor.CodeGenUtils;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

import java.util.Base64;

public class GenObjectInitializer {
    @Test
    public void main() throws Throwable {
        ClassNode newClass = new ClassNode();
        newClass.visit(
                Opcodes.V1_8,
                0,
                "io/github/karlatemp/unsafeaccessor/ObjectInitializerHolder",
                null,
                "java/lang/Object",
                null
        );
        CodeGenUtils.genCons(newClass);
        MethodVisitor code = newClass.visitMethod(Opcodes.ACC_STATIC, "code", "()[B", null, null);
        code.visitMethodInsn(Opcodes.INVOKESTATIC, "java/util/Base64", "getDecoder", "()Ljava/util/Base64$Decoder;", false);
        {
            ClassNode initializer = new ClassNode();
            initializer.visit(
                    Opcodes.V1_8,
                    0,
                    "io/github/karlatemp/unsafeaccessor/ObjectInitializerImpl",
                    null,
                    "jdk/internal/reflect/MagicAccessorImpl",
                    new String[]{"java/util/function/Consumer"}
            );
            CodeGenUtils.genCons(initializer);
            MethodVisitor accept = initializer.visitMethod(Opcodes.ACC_PUBLIC, "accept", "(Ljava/lang/Object;)V", null, null);
            accept.visitVarInsn(Opcodes.ALOAD, 0);
            accept.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            accept.visitInsn(Opcodes.RETURN);
            accept.visitMaxs(2, 2);
            byte[] bytecode = CodeGenUtils.toBytecode(initializer);
            code.visitLdcInsn(Base64.getEncoder().encodeToString(bytecode));
        }
        code.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Base64$Decoder", "decode", "(Ljava/lang/String;)[B", false);
        code.visitInsn(Opcodes.ARETURN);
        code.visitMaxs(3, 3);

        CodeGenUtils.save(newClass);
    }
}
