package io.github.karlatemp.unsafeaccessor.codegen;

import io.github.karlatemp.unsafeaccessor.CodeGenUtils;
import io.github.karlatemp.unsafeaccessor.WorkingDirUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.Remapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.File;
import java.io.FileInputStream;

public class GenLiberica11 {
    public static void main(String[] args) throws Exception {
        File model = new File(
                WorkingDirUtils.projectDir,
                "impl/jdk9/build/classes/java/main/io/github/karlatemp/unsafeaccessor/Impl9.class"
        );
        ClassNode node = new ClassNode();
        new ClassReader(new FileInputStream(model))
                .accept(node, 0);
        ClassNode newClass = new ClassNode();
        node.accept(new ClassRemapper(newClass, new Remapper() {
            @Override
            public String mapType(String internalName) {
                if (internalName.equals("io/github/karlatemp/unsafeaccessor/Impl9")) {
                    return "io/github/karlatemp/unsafeaccessor/Impl9Obj";
                }
                return super.mapType(internalName);
            }

            @Override
            public String mapMethodName(String owner, String name, String descriptor) {
                if (owner.equals("jdk/internal/misc/Unsafe")) {
                    return name.replace("Reference", "Object");
                }
                return super.mapMethodName(owner, name, descriptor);
            }
        }));
        MethodNode invokeCleaner = newClass.methods.stream().filter(it -> it.name.equals("invokeCleaner")).findFirst().get();
        invokeCleaner.visitAnnotation("Lio/github/karlatemp/unsafeaccessor/Analysis$SkipAnalysis;", false);

        CodeGenUtils.save(newClass);
    }
}
