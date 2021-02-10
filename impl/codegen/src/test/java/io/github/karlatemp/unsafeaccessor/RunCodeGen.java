package io.github.karlatemp.unsafeaccessor;

import java.io.File;
import java.lang.reflect.Method;

public class RunCodeGen {
    public static void main(String[] args) throws Throwable {
        File classes = new File(WorkingDirUtils.projectDir, "impl/codegen/build/classes/java/test/io/github/karlatemp/unsafeaccessor/codegen");
        for (File f : classes.listFiles()) {
            Class<?> aClass = Class.forName("io.github.karlatemp.unsafeaccessor.codegen." + f.getName().replace(".class", ""));
            try {
                Method main = aClass.getDeclaredMethod("main", String[].class);
                System.out.println("Running " + aClass.getName());
                main.invoke(null, (Object) args);
            } catch (NoSuchMethodException ignored) {
            }
        }
    }
}
