package io.github.karlatemp.unsafeaccessor;

import java.util.ArrayList;
import java.util.List;

class UsfAllocCtx {
    interface C<T> {
        T call() throws Throwable;
    }

    static <T> T rc(C<T> c) {
        try {
            return c.call();
        } catch (Throwable throwable) {
            throw new ExceptionInInitializerError(throwable);
        }
    }

    static <T> T rc(C<T> c1, C<T>... c) {
        if (c == null) return rc(c1);
        Throwable mt;
        List<Throwable> errs = new ArrayList<>(c.length);
        try {
            return c1.call();
        } catch (Throwable throwable) {
            mt = throwable;
        }
        for (C<T> c0 : c) {
            try {
                return c0.call();
            } catch (Throwable throwable) {
                errs.add(throwable);
            }
        }
        ExceptionInInitializerError error = new ExceptionInInitializerError(mt);
        for (Throwable t : errs) {
            error.addSuppressed(t);
        }
        throw error;
    }

    private DynClassLoader loader;

    void putUnsafeInstance(Unsafe usf) {
        Unsafe.theUnsafe = usf;
    }

    void putModuleAccess(ModuleAccess ma) {
        Root.Secret.MACCESS = ma;
    }

    DynClassLoader loader() {
        if (loader == null)
            return loader = new DynClassLoader();
        return loader;
    }

    String namespace;
    Class<?>[] ACCESS_C;

    void load() throws Throwable {
    }
}
