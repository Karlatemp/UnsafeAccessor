package codegen.jdk9;

import io.github.karlatemp.unsafeaccessor.CodeGenUtils;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class Impl9Obj {
    @Test
    void gen() throws Throwable {
        ClassNode node = new ClassNode();
        node.visit(Opcodes.V1_8, 0, "io/github/karlatemp/unsafeaccessor/Impl9Obj", null, "io/github/karlatemp/unsafeaccessor/Unsafe", null);

        CodeGenUtils.genCons(node);
        CodeGenUtils.save(node);
    }
}
