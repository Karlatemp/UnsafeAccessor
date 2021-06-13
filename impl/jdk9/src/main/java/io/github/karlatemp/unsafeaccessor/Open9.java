package io.github.karlatemp.unsafeaccessor;

import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.SharedSecrets;

import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.security.AllPermission;
import java.security.ProtectionDomain;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static io.github.karlatemp.unsafeaccessor.BytecodeUtil.replace;

class Open9 extends ClassLoader implements Supplier<Object>, Consumer<Object> {
    private static Object env = null;

    @Override
    public void accept(Object o) {
        env = o;
    }

    @Override
    public Object get() {
        return env;
    }

    private Open9() {
        super(Open9.class.getClassLoader());
    }

    static class Injector {
        static {
            Class<?> klass = Injector.class;
            Module module = klass.getModule();
            Module open = klass.getClassLoader().getClass().getModule();
            module.addExports(klass.getPackageName(), open);
            module.addOpens(klass.getPackageName(), open);
            JavaLangAccess javaLangAccess = SharedSecrets.getJavaLangAccess();
            javaLangAccess.addExports(Object.class.getModule(), "jdk.internal.misc", open);
            javaLangAccess.addExports(open, "io.github.karlatemp.unsafeaccessor", module);
            javaLangAccess.addReads(module, open);
        }
    }

    Class<?> define(byte[] code) {
        ProtectionDomain domain = new ProtectionDomain(null,
                new AllPermission().newPermissionCollection()
        );
        return defineClass(null, code, 0, code.length, domain);
    }

    static Class<?> findSS() throws ClassNotFoundException {
        String[] classpath = {
                "jdk.internal.access.SharedSecrets",
                "jdk.internal.misc.SharedSecrets",
        };
        return findClass(classpath);
    }

    static Class<?> findClass(String[] classpath) throws ClassNotFoundException {
        ClassNotFoundException cnfe = null;
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        for (String s : classpath) {
            try {
                return Class.forName(s, true, loader);
            } catch (ClassNotFoundException ce) {
                if (cnfe == null) cnfe = ce;
                else cnfe.addSuppressed(ce);
            }
        }
        assert cnfe != null;
        throw cnfe;
    }

    static Class<?> findJLA() throws ClassNotFoundException {
        String[] classpath = {
                "jdk.internal.access.JavaLangAccess",
                "jdk.internal.misc.JavaLangAccess",
        };
        return findClass(classpath);
    }

    static Class<?> findJLMA() throws ClassNotFoundException {
        String[] classpath = {
                "jdk.internal.access.JavaLangModuleAccess",
                "jdk.internal.misc.JavaLangModuleAccess",
        };
        return findClass(classpath);
    }

    static byte[] doRemap(
            byte[] data,
            Class<?>... accessClasses
    ) {

        for (Class<?> c : accessClasses) {
            data = replace(data, "jdk/internal/access/" + c.getSimpleName(), c.getName().replace('.', '/'));
            data = replace(data, "Ljdk/internal/access/" + c.getSimpleName() + ";", "L" + c.getName().replace('.', '/') + ";");
            data = replace(data, "()Ljdk/internal/access/" + c.getSimpleName() + ";", "()L" + c.getName().replace('.', '/') + ";");
        }
        return data;
    }


    @SuppressWarnings("UnnecessarySemicolon")
    static Unsafe open() throws NoSuchMethodException {
        Open9 loader = new Open9();
        try (InputStream source = Open9.class.getResourceAsStream("Open9$Injector.class");
             InputStream mai9 = Open9.class.getResourceAsStream("ModuleAccessImpl$JDK9.class");
        ) {
            byte[] data;
            Class<?> JLA = findJLA();
            Class<?>[] ACCESS_CLASSES = {
                    JLA, findSS(), findJLMA()
            };

            Object proxy = Proxy.newProxyInstance(loader, new Class[]{JLA}, (proxy0, method, args) -> null);
            String namespace = proxy.getClass().getPackageName();
            {
                data = source.readAllBytes();
                String targetName = namespace + ".Injector",
                        targetJvmName = targetName.replace('.', '/');
                data = replace(data, "io/github/karlatemp/unsafeaccessor/Open9$Injector", targetJvmName);
                data = replace(data, "Lio/github/karlatemp/unsafeaccessor/Open9$Injector;", "L" + targetJvmName + ";");

                data = doRemap(data, ACCESS_CLASSES);

                Class<?> injectorClass = loader.define(data);
                Class.forName(injectorClass.getName(), true, loader);
            }
            {
                String targetName = namespace + ".ModuleAccessImpl$JDK9",
                        targetJvmName = targetName.replace('.', '/');

                data = mai9.readAllBytes();
                data = replace(data, "io/github/karlatemp/unsafeaccessor/ModuleAccessImpl$JDK9", targetJvmName);
                data = replace(data, "Lio/github/karlatemp/unsafeaccessor/ModuleAccessImpl$JDK9;", "L" + targetJvmName + ";");

                data = doRemap(data, ACCESS_CLASSES);
                env = (Supplier) () -> UnsafeAccess.INSTANCE;

                Class.forName(loader.define(data).getName(), true, loader);
                Root.Secret.MACCESS = (ModuleAccess) env;
                env = null;
            }
        } catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
        //noinspection ResultOfMethodCallIgnored
        jdk.internal.misc.Unsafe.class.getDeclaredMethod("getReference", Object.class, long.class);
        return new Impl9();
    }
}
