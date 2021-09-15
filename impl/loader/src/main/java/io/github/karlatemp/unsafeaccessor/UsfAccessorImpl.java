package io.github.karlatemp.unsafeaccessor;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
class UsfAccessorImpl extends UsfAccessor.UsfAccessorSpi {

    static class UsfACtxImpl extends UsfAllocCtx {
        @Override
        Unsafe newUsfImpl(UsfAlloc alloc) throws Exception {
            return (Unsafe) newUsf(alloc);
        }

        Object newUsf(UsfAlloc alloc) throws Exception {
            if (alloc instanceof UsfAllocImpl8) {
                return new SunMiscUnsafeImpl();
            }
            return newUsf9(alloc);
        }

        @SuppressWarnings("SwitchStatementWithTooFewBranches")
        Object newUsf9(UsfAlloc $$$$$$$$$$$$$$$$) throws Exception {
            UsfAllocImpl9 alloc = (UsfAllocImpl9) $$$$$$$$$$$$$$$$;
            boolean isGetObj;
            try {
                alloc.usfClass().getMethod("getObject", Object.class, long.class);
                isGetObj = true;
            } catch (NoSuchMethodException ignored) {
                isGetObj = false;
            }

            String cname = alloc.getClass().getSimpleName();
            String sname = null;
            switch (cname) {
                case "UsfAllocImpl17":
                    return new UsfImpl17();
                default:
                    return isGetObj ? new Impl9Obj() : new Impl9();
            }
        }

    }

    static UsfAlloc findImpl9() {
        ExceptionInInitializerError e = new ExceptionInInitializerError("no any provider found");
        for (String k : new String[]{
                "17",
                "9",
                "8",
        }) {
            try {
                UsfAlloc alloc = Class.forName("io.github.karlatemp.unsafeaccessor.UsfAllocImpl" + k)
                        .asSubclass(UsfAlloc.class)
                        .getDeclaredConstructor()
                        .newInstance()
                        .checkSelectedRequirement();
                if (alloc != null) return alloc;
            } catch (Throwable ex) {
                e.addSuppressed(ex);
            }
        }
        throw e;
    }

    void initialize() {
        Root.Secret.MACCESS = new ModuleAccessImpl.PendingInit();
        UsfAlloc alloc;
        try {
            Class.forName("java.lang.Module");
            alloc = findImpl9();
        } catch (ClassNotFoundException ignored) {
            alloc = new UsfAllocImpl8();
        }
        try {
            UsfAllocCtx ctx = new UsfACtxImpl();
            alloc.prepare(ctx);
            alloc.destroyLimit(ctx);

            ctx.putUnsafeInstance(alloc.newUnsafe(ctx));

            ctx.putModuleAccess(alloc.newModuleAccess(ctx));
        } catch (Throwable throwable) {
            throw new ExceptionInInitializerError(throwable);
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
