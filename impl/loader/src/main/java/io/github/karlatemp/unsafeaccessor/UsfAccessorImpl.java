package io.github.karlatemp.unsafeaccessor;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
class UsfAccessorImpl extends UsfAccessor.UsfAccessorSpi {
    Object allocateUnsafe() {
        try {
            Class.forName("java.lang.Module");
            Root.Secret.MACCESS = new ModuleAccessImpl.PendingInit();

            try {
                return Open9.open();
            } catch (NoSuchMethodException ignored) {
                return new Impl9Obj();
            }
        } catch (ClassNotFoundException ignored) {
            Root.Secret.MACCESS = new ModuleAccessImpl.Noop();
            return new SunMiscUnsafeImpl();
        }
    }

    static Class<?> findC(ClassLoader cl, String... names) {
        List<Throwable> throwables = new ArrayList<>();
        for (String n : names) {
            try {
                return Class.forName(n, false, cl);
            } catch (ClassNotFoundException e) {
                throwables.add(e);
            }
        }
        NoClassDefFoundError error = new NoClassDefFoundError();
        for (Throwable t : throwables) error.addSuppressed(t);
        throw error;
    }

    @Override
    Consumer<Object> allocateObjectInitializer() {
        byte[] code = ObjectInitializerHolder.code();
        Class<?> MagicAccessorImpl = findC(
                null,
                "jdk.internal.reflect.MagicAccessorImpl",
                "sun.reflect.MagicAccessorImpl"
        );
        Class<? extends ClassLoader> DelegatingClassLoader = findC(
                null,
                "jdk.internal.reflect.DelegatingClassLoader",
                "sun.reflect.DelegatingClassLoader"
        ).asSubclass(ClassLoader.class);
        if (!MagicAccessorImpl.getName().equals("jdk.internal.reflect.MagicAccessorImpl")) {
            code = BytecodeUtil.replace(code,
                    "jdk/internal/reflect/MagicAccessorImpl",
                    MagicAccessorImpl.getName().replace('.', '/')
            );
        }
        try {
            Constructor<? extends ClassLoader> constructor = DelegatingClassLoader.getDeclaredConstructor(ClassLoader.class);
            Root.OpenAccess.openAccess0(constructor, true);
            ClassLoader delegate = constructor.newInstance(ClassLoader.getSystemClassLoader());
            Class<?> klass = Unsafe.getUnsafe0().defineClass(null, code, 0, code.length, delegate, null);
            Constructor<?> constructor1 = klass.getDeclaredConstructor();
            Root.OpenAccess.openAccess0(constructor1, true);
            return (Consumer<Object>) constructor1.newInstance();
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
