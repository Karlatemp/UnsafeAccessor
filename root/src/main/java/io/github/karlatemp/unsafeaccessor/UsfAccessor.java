package io.github.karlatemp.unsafeaccessor;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class UsfAccessor {
    private static final Object loadingLock = new Object();
    private static Object impl;

    protected static Object allocateUnsafe() {
        synchronized (loadingLock) {
            if (impl != null) return impl;
            try {
                Class.forName("java.lang.Module");
                return impl = Open9.open();
            } catch (ClassNotFoundException ignored) {
            }
            return impl = new Impl8();
        }
    }

    public static void main(String[] args) throws Throwable {
        System.out.println(Unsafe.getUnsafe());
        System.out.println(Unsafe.getUnsafe().isJava9());
        System.out.println(Root.getTrusted());
        Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        System.out.println(Root.openAccess(addURL));
    }
}
