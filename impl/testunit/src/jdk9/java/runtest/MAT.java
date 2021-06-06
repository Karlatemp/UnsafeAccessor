package runtest;

import io.github.karlatemp.unsafeaccessor.ModuleAccess;
import io.github.karlatemp.unsafeaccessor.Root;
import io.github.karlatemp.unsafeaccessor.Unsafe;
import org.junit.jupiter.api.Assertions;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.module.ModuleDescriptor;
import java.util.Set;
import java.util.UUID;

public class MAT {
    @TestTask
    public static void run() {
        ModuleAccess access = Root.getModuleAccess();
        Unsafe usf = Unsafe.getUnsafe();


        Assertions.assertTrue(access.isSupport());
        Assertions.assertNotNull(access.getModule(ModuleAccess.class));
        Assertions.assertNotNull(access.getModule(MAT.class));
        ClassLoader cl = MAT.class.getClassLoader();
        {

            String ppk = "io.kjg.ppkv." + UUID.randomUUID();

            ModuleDescriptor.Builder moduleBuilder = (ModuleDescriptor.Builder) access.newModuleBuilder("testMod", false, Set.of());
            moduleBuilder.packages(
                    Set.of(ppk)
            );

            Object unnamedModule = access.defineModule(cl, moduleBuilder.build(), null);
            System.out.println("UN: " + unnamedModule);
            Assertions.assertNotSame(unnamedModule, access.getModule(MAT.class));
            ClassWriter cw = new ClassWriter(0);
            cw.visit(Opcodes.V1_8, 0, (ppk + ".RX").replace('.', '/'), null, "java/lang/Object", null);
            byte[] b = cw.toByteArray();
            Class<?> c = usf.defineClass(null, b, 0, b.length, cl, null);
            System.out.println("ZPKG " + c.getPackage());
            Assertions.assertSame(access.getModule(c), unnamedModule);
        }

    }
}
