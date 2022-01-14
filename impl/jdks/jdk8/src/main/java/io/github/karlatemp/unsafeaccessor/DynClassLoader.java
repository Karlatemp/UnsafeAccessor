package io.github.karlatemp.unsafeaccessor;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("rawtypes")
class DynClassLoader extends ClassLoader implements Supplier, Consumer {
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
