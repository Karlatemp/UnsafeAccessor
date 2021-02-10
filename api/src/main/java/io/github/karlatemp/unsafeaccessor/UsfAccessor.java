package io.github.karlatemp.unsafeaccessor;

// internal
public class UsfAccessor {

    // Internal service for load Unsafe.
    protected static Object allocateUnsafe() {
        try {
            // in module :impl.loader
            Class<?> impl = Class.forName("io.github.karlatemp.unsafeaccessor.UsfAccessorImpl");
            return impl.getDeclaredMethod("allocateUnsafe").invoke(null);
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
