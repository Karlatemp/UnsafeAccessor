package io.github.karlatemp.unsafeaccessor;

import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.SharedSecrets;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

import static io.github.karlatemp.unsafeaccessor.BytecodeUtil.replace;

class UsfAllocImpl9 extends UsfAllocCtx {
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

    static byte[] inNamespace(byte[] data, String name, String namespace) {
        String targetJvmName = namespace.replace('.', '/') + "/UsfI_" + name;
        data = BytecodeUtil.replace(data, "io/github/karlatemp/unsafeaccessor/" + name, targetJvmName);
        data = BytecodeUtil.replace(data, "Lio/github/karlatemp/unsafeaccessor/" + name + ";", "L" + targetJvmName + ";");
        return data;
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

    static byte[] readC(String name) throws IOException {
        try (InputStream is = UsfAllocImpl9.class.getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

    void load() throws Throwable {
        this.ACCESS_C = new Class<?>[]{
                findJLA(), findSS(), findJLMA()
        };

        try {
            destroyLimitByUnsafe(this);
        } catch (Throwable throwable) {
            try {
                destroyLimitByProxy(this);
            } catch (Throwable t2) {
                throwable.addSuppressed(t2);
                throw throwable;
            }
        }
        Root.Secret.MACCESS = newModuleAccess(this);

        boolean isGetObj;
        try {
            Class.forName("jdk.internal.misc.Unsafe")
                    .getMethod("getObject", Object.class, long.class);
            isGetObj = true;
        } catch (NoSuchMethodException ignored) {
            isGetObj = false;
        }

        Unsafe.theUnsafe = isGetObj ? new Impl9Obj() : new Impl9();
    }

    void destroyLimitByUnsafe(UsfAllocCtx ctx) throws Throwable {
        sun.misc.Unsafe legacyUnsafe;
        {
            Field legacyUsfField = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
            legacyUsfField.setAccessible(true);
            legacyUnsafe = (sun.misc.Unsafe) legacyUsfField.get(null);
        }
        Module target = ctx.loader().getUnnamedModule();
        Module target2 = UsfAllocImpl9.class.getModule();
        ModuleLayer.Controller controller = (ModuleLayer.Controller) legacyUnsafe.allocateInstance(ModuleLayer.Controller.class);
        legacyUnsafe.putObject(controller,
                legacyUnsafe.objectFieldOffset(ModuleLayer.Controller.class.getDeclaredField("layer")),
                Object.class.getModule().getLayer()
        );
        for (Class<?> targetClass : ctx.ACCESS_C) {
            controller.addExports(targetClass.getModule(), targetClass.getPackageName(), target);
            controller.addExports(targetClass.getModule(), targetClass.getPackageName(), target2);
        }
        for (String opkg : new String[]{
                "jdk.internal.misc",
        }) {
            controller.addExports(Object.class.getModule(), opkg, target);
            controller.addExports(Object.class.getModule(), opkg, target2);
        }
        ctx.namespace = "io.github.karlatemp.unsafeaccessor";
    }

    void destroyLimitByProxy(UsfAllocCtx ctx) throws Exception {
        Object proxy = Proxy.newProxyInstance(
                ctx.loader(),
                new Class[]{ctx.ACCESS_C[0]},
                (proxy1, method, args) -> null
        );
        String namespace = ctx.namespace = proxy.getClass().getPackageName();

        { // Injector
            byte[] data = readC("UsfAllocImpl9$Injector.class");
            data = inNamespace(data, "UsfAllocImpl9$Injector", namespace);
            data = doRemap(data, ctx.ACCESS_C);
            ctx.loader().defineAndLoad(data);
        }
    }


    ModuleAccess newModuleAccess(UsfAllocCtx ctx) throws Exception {
        byte[] data = readC("ModuleAccessImpl$JDK9.class");
        data = inNamespace(data, "ModuleAccessImpl$JDK9", ctx.namespace);
        data = doRemap(data, ctx.ACCESS_C);
        ctx.loader().env = ((Supplier<Object>) () -> UnsafeAccess.INSTANCE);
        ctx.loader().defineAndLoad(data);
        return (ModuleAccess) ctx.loader().env;
    }
}
