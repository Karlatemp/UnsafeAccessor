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

public class ModuleAccessTest {
    @TestTask
    public static void run() {
        ModuleAccess access = Root.getModuleAccess();
        Unsafe usf = Unsafe.getUnsafe();


        Assertions.assertTrue(access.isSupport());
        Assertions.assertNotNull(access.getModule(ModuleAccess.class));
        Assertions.assertNotNull(access.getModule(ModuleAccessTest.class));
        ClassLoader cl = ModuleAccessTest.class.getClassLoader();
        {

            String ppk = "io.kjg.ppkv." + UUID.randomUUID();

            ModuleDescriptor.Builder moduleBuilder = (ModuleDescriptor.Builder) access.newModuleBuilder("testMod", false, Set.of());
            moduleBuilder.packages(
                    Set.of(ppk)
            );

            Object unnamedModule = access.defineModule(cl, moduleBuilder.build(), null);
            System.out.println("UN: " + unnamedModule);
            Assertions.assertNotSame(unnamedModule, access.getModule(ModuleAccessTest.class));
            ClassWriter cw = new ClassWriter(0);
            cw.visit(Opcodes.V1_8, 0, (ppk + ".RX").replace('.', '/'), null, "java/lang/Object", null);
            byte[] b = cw.toByteArray();
            Class<?> c = usf.defineClass(null, b, 0, b.length, cl, null);
            System.out.println("ZPKG " + c.getPackage());
            Assertions.assertSame(access.getModule(c), unnamedModule);
        }

    }

    @TestTask(name = "test methods")
    public static void testMethods() {
        ModuleAccess access = Root.getModuleAccess();
        Unsafe usf = Unsafe.getUnsafe();

        ClassLoader cl = ModuleAccessTest.class.getClassLoader();
        ModuleDescriptor.Builder moduleBuilder = (ModuleDescriptor.Builder) access.newModuleBuilder(
                "asra.xatafaser.azxarmoivdsoijzoijfoijdsjoi",
                false,
                Set.of()
        );
        String pkg = "aoiroiasjria.sdaiorjaoica.ar3094ksdaxapkfrafdasd.asejmaodh";
        moduleBuilder.packages(Set.of(pkg));
        Module module = (Module) access.defineModule(cl, moduleBuilder.build(), null);

        Module m1 = ModuleAccessTest.class.getModule();

        Assertions.assertFalse(module.isExported(pkg));
        Assertions.assertFalse(module.isOpen(pkg));

        Assertions.assertFalse(module.isExported(pkg, m1));
        Assertions.assertFalse(module.isOpen(pkg, m1));

        access.addExports(module, pkg, m1);

        Assertions.assertTrue(module.isExported(pkg, m1));
        Assertions.assertFalse(module.isOpen(pkg, m1));

        access.addOpens(module, pkg, m1);

        Assertions.assertTrue(module.isExported(pkg, m1));
        Assertions.assertTrue(module.isOpen(pkg, m1));

        Assertions.assertFalse(module.isExported(pkg));
        Assertions.assertFalse(module.isOpen(pkg));

        Module newUm = (Module) access.defineUnnamedModule(cl);


        Assertions.assertFalse(module.isExported(pkg, newUm));
        Assertions.assertFalse(module.isOpen(pkg, newUm));

        access.addExportsToAllUnnamed(module, pkg);

        Assertions.assertTrue(module.isExported(pkg, newUm));
        Assertions.assertFalse(module.isOpen(pkg, newUm));

        access.addOpensToAllUnnamed(module, pkg);

        Assertions.assertTrue(module.isExported(pkg, newUm));
        Assertions.assertTrue(module.isOpen(pkg, newUm));

        Module jl = java.util.logging.Logger.class.getModule();


        Assertions.assertFalse(module.isExported(pkg, jl));
        Assertions.assertFalse(module.isOpen(pkg, jl));

        access.addExports(module, pkg, access.getEVERYONE_MODULE());
        Assertions.assertTrue(module.isExported(pkg, jl));
        Assertions.assertFalse(module.isOpen(pkg, jl));

        access.addOpens(module, pkg, access.getEVERYONE_MODULE());
        Assertions.assertTrue(module.isExported(pkg, jl));
        Assertions.assertTrue(module.isOpen(pkg, jl));

    }
}
