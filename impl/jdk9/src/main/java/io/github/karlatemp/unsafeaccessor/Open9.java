package io.github.karlatemp.unsafeaccessor;

import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.SharedSecrets;

import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.security.AllPermission;
import java.security.ProtectionDomain;

import static io.github.karlatemp.unsafeaccessor.BytecodeUtil.replace;

class Open9 extends ClassLoader {
    private Open9() {
        super(Open9.class.getClassLoader());
    }

    static class Injector {
        static {
            Class<?> klass = Injector.class;
            Module module = klass.getModule();
            Module open = klass.getClassLoader().getClass().getModule();
            module.addExports(klass.getPackageName(), open);
            JavaLangAccess javaLangAccess = SharedSecrets.getJavaLangAccess();
            javaLangAccess.addExports(Object.class.getModule(), "jdk.internal.misc", open);
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

    static Unsafe open() throws NoSuchMethodException {
        Open9 loader = new Open9();
        try (InputStream source = Open9.class.getResourceAsStream("Open9$Injector.class")) {
            byte[] data = source.readAllBytes();
            Class<?> JLA = findJLA(), SS = findSS();
            Object proxy = Proxy.newProxyInstance(loader, new Class[]{JLA}, (proxy0, method, args) -> null);
            String namespace = proxy.getClass().getPackageName();
            String targetName = namespace + ".Injector",
                    targetJvmName = targetName.replace('.', '/');
            data = replace(data, "io/github/karlatemp/unsafeaccessor/Open9$Injector", targetJvmName);
            data = replace(data, "Lio/github/karlatemp/unsafeaccessor/Open9$Injector;", "L" + targetJvmName + ";");

            data = replace(data, "Ljdk/internal/access/JavaLangAccess;", "L" + JLA.getName().replace('.', '/') + ";");
            data = replace(data, "jdk/internal/access/JavaLangAccess", JLA.getName().replace('.', '/'));
            data = replace(data, "()Ljdk/internal/access/JavaLangAccess;", "()L" + JLA.getName().replace('.', '/') + ";");

            data = replace(data, "Ljdk/internal/access/SharedSecrets;", "L" + SS.getName().replace('.', '/') + ";");
            data = replace(data, "jdk/internal/access/SharedSecrets", SS.getName().replace('.', '/'));

            Class<?> injectorClass = loader.define(data);
            Class.forName(injectorClass.getName(), true, loader);
        } catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
        //noinspection ResultOfMethodCallIgnored
        jdk.internal.misc.Unsafe.class.getDeclaredMethod("getReference", Object.class, long.class);
        return new Impl9();
    }
}
