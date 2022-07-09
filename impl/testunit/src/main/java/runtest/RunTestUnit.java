package runtest;

import io.github.karlatemp.unsafeaccessor.Unsafe;
import org.objectweb.asm.ClassWriter;

import java.util.ArrayList;
import java.util.List;

public class RunTestUnit {
    public static String taskUnitName = System.getenv("taskUnitName");

    public static void main(String[] args) throws Throwable {
        List<String> classes = new ArrayList<>(TestTasks.findTestClasses());
        classes.sort(String::compareTo);
        TestTasks.runTests(classes);
    }

    public static Class<?> define(ClassWriter cw) {
        return define(cw.toByteArray());
    }

    public static Class<?> define(byte[] code) {
        return Unsafe.getUnsafe().defineClass(null, code, 0, code.length, ClassLoader.getSystemClassLoader(), null);
    }
}
