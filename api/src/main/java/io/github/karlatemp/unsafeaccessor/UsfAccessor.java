package io.github.karlatemp.unsafeaccessor;

import java.util.function.Consumer;

// internal
public class UsfAccessor {
    static abstract class UsfAccessorSpi {
        abstract Object allocateUnsafe();

        abstract Consumer<Object> allocateObjectInitializer();
    }

    static UsfAccessorSpi spi;

    static UsfAccessorSpi spi() {
        if (spi != null) return spi;
        synchronized (UsfAccessorSpi.class) {
            if (spi != null) return spi;
            try {
                // in module :impl.loader
                Class<?> impl = Class.forName("io.github.karlatemp.unsafeaccessor.UsfAccessorImpl");
                return spi = impl.asSubclass(UsfAccessorSpi.class).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    // Internal service for load Unsafe.
    protected static Object allocateUnsafe() {
        return spi().allocateUnsafe();
    }

    protected static Consumer<Object> allocateObjectInitializer() {
        return spi().allocateObjectInitializer();
    }
}
