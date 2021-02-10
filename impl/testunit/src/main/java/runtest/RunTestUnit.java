package runtest;

import io.github.karlatemp.unsafeaccessor.BinaryCompatibilityAnalysis;
import io.github.karlatemp.unsafeaccessor.Root;
import io.github.karlatemp.unsafeaccessor.Unsafe;
import org.junit.jupiter.api.Assertions;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.invoke.MethodHandle;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class RunTestUnit {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Throwable {
        Unsafe usf = Unsafe.getUnsafe();
        System.out.println("Usf = " + usf);
        System.out.println(Unsafe.class.getResource("Unsafe.class"));
        System.out.println(Unsafe.getUnsafe());

        // region ECHO
        RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
        System.out.println("== SPEC ==");
        System.out.println(rt.getSpecName());
        System.out.println(rt.getSpecVendor());
        System.out.println(rt.getSpecVersion());
        System.out.println("==  VM  ==");
        System.out.println(rt.getVmName());
        System.out.println(rt.getVmVendor());
        System.out.println(rt.getVmVersion());
        System.out.println("=========");
        // endregion

        // region check Root.getTrusted()
        trustCheck:
        {
            String toString = Root.getTrusted().toString();
            if (toString.equals("/trusted")) {
                break trustCheck;
            }

            // AdoptOpenJDK - jdk 11.0.9.11 openj9
            if (Root.getTrusted().lookupClass() == MethodHandle.class) {
                if (Root.getTrusted().lookupModes() == 0x40) {
                    if (toString.equals("java.lang.invoke.MethodHandle")) {
                        break trustCheck;
                    }
                }
            }

            Assertions.assertEquals("/trusted", Root.getTrusted().toString());
        }
        // endregion


        System.out.println("IsJava9 = " + Unsafe.getUnsafe().isJava9());

        // region check set accessible
        {
            System.out.println("Checking AccessibleObject.setAccessible");
            Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            System.out.println(addURL.isAccessible());
            Assertions.assertFalse(addURL.isAccessible());
            System.out.println(Root.openAccess(addURL));
            System.out.println(addURL.isAccessible());
            Assertions.assertTrue(addURL.isAccessible());
        }
        // endregion

        // region check Unsafe.defineClass
        {
            System.out.println("Checking class defining");
            ClassWriter writer1 = new ClassWriter(0),
                    writer2 = new ClassWriter(0),
                    writer3 = new ClassWriter(0);
            writer1.visit(Opcodes.V1_8, 0, "testSwe/WEASawx", null, "java/lang/Object", null);
            writer2.visit(Opcodes.V1_8, 0, "testSwe/WEAS687", null, "java/lang/Object", null);
            writer3.visit(Opcodes.V1_8, 0, "testSwe/AAXWXu", null, "java/lang/Object", null);

            byte[] code1 = writer1.toByteArray(), code2 = writer2.toByteArray(), code3 = writer3.toByteArray();
            ClassLoader loader = RunTestUnit.class.getClassLoader();

            Class<?> class1 = Unsafe.getUnsafe().defineClass(null, code1, 0, code1.length, loader, null);
            Class<?> class2 = Unsafe.getUnsafe().defineClass0(null, code2, 0, code2.length, loader, null);
            Class<?> class3 = Unsafe.getUnsafe().defineClass0(null, code3, 0, code3.length, null, null);

            Assertions.assertEquals("testSwe.WEASawx", class1.getName());
            Assertions.assertEquals("testSwe.WEAS687", class2.getName());
            Assertions.assertEquals("testSwe.AAXWXu", class3.getName());

            Assertions.assertSame(loader, class1.getClassLoader());
            Assertions.assertSame(loader, class2.getClassLoader());
            Assertions.assertSame(null, class3.getClassLoader());
        }
        // endregion

        // region Check Unsafe
        {
            TestUnsafe.runTest();
        }
        // endregion

        BinaryCompatibilityAnalysis.run();
    }
}
