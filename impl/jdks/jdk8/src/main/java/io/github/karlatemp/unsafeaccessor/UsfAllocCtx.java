package io.github.karlatemp.unsafeaccessor;

import java.util.function.Consumer;
import java.util.function.Supplier;

class UsfAllocCtx {
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

    @SuppressWarnings("rawtypes")
    static class DynClassLoader extends ClassLoader implements Supplier, Consumer {
        Object env;

        DynClassLoader() {
            super(DynClassLoader.class.getClassLoader());
        }

        @Override
        public Object get() {
            return env;
        }

        @Override
        public void accept(Object o) {
            env = o;
        }

        Class<?> define(byte[] code) {
            return defineClass(null, code, 0, code.length, null);
        }

        Class<?> defineAndLoad(byte[] data) throws ClassNotFoundException {
            return Class.forName(define(data).getName(), true, this);
        }
    }

    String namespace;
    Class<?>[] ACCESS_C;

    Unsafe newUsfImpl(UsfAlloc thiz) throws Exception {
        throw new UnsupportedOperationException();
    }
}
