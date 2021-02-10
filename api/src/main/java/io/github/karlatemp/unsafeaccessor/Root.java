package io.github.karlatemp.unsafeaccessor;

import org.jetbrains.annotations.Contract;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * JVM Root Access.
 *
 * @since 1.1.0
 */
@SuppressWarnings("DefaultAnnotationParam")
public class Root {
    @Contract(pure = false)
    public static Unsafe getUnsafe() {
        return Unsafe.getUnsafe();
    }

    @Contract(pure = true)
    public static MethodHandles.Lookup getTrusted() {
        return RootLookupHolder.ROOT;
    }

    @Contract(pure = false, value = "null, _ -> fail")
    public static void setAccessible(AccessibleObject object, boolean isAccessible) {
        OpenAccess.openAccess(object, isAccessible);
    }

    @Contract(pure = false, value = "null -> fail")
    public static <T extends AccessibleObject> T openAccess(T object) {
        setAccessible(object, true);
        return object;
    }

    private static class RootLookupHolder {
        private static final MethodHandles.Lookup ROOT;

        static {
            try {
                Unsafe unsafe = getUnsafe();
                MethodHandles.Lookup lookup;
                try {
                    Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                    openAccess(field);
                    lookup = (MethodHandles.Lookup) field.get(null);
                } catch (Throwable any) {
                    if (unsafe.isJava9()) {
                        lookup = MethodHandles.lookup();
                        unsafe.putReference(
                                lookup,
                                unsafe.objectFieldOffset(MethodHandles.Lookup.class, "lookupClass"),
                                Object.class
                        );
                        unsafe.putInt(
                                lookup,
                                unsafe.objectFieldOffset(MethodHandles.Lookup.class, "allowedModes"),
                                -1
                        );
                    } else {
                        throw any;
                    }
                }
                ROOT = lookup;
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    private static class OpenAccess {
        private static final Unsafe usf = getUnsafe();
        private static final long overrideOffset;

        static {
            if (usf.isJava9()) {
                overrideOffset = usf.objectFieldOffset(AccessibleObject.class, "override");
            } else {
                try {
                    Field field = AccessibleObject.class.getDeclaredField("override");
                    overrideOffset = usf.objectFieldOffset(field);
                } catch (Throwable throwable) {
                    throw new ExceptionInInitializerError(throwable);
                }
            }
        }

        static void openAccess(AccessibleObject object, boolean isAccessible) {
            if (object == null) throw new NullPointerException("object");
            usf.putBoolean(object, overrideOffset, isAccessible);
        }
    }

    /**
     * Throw a new exception
     *
     * @since 1.2.0
     */
    @Contract(pure = false)
    public static <T> T throw0(Throwable throwable) {
        if (throwable == null) throw new NullPointerException();
        getUnsafe().throwException(throwable);
        throw new RuntimeException();
    }

    /**
     * Allocate a new object, but not initialized.
     *
     * @see Unsafe#allocateInstance(Class)
     * @since 1.2.0
     */
    @SuppressWarnings("unchecked")
    @Contract(pure = false)
    public static <T> T allocate(Class<T> klass) throws InstantiationException {
        return (T) getUnsafe().allocateInstance(klass);
    }
}
