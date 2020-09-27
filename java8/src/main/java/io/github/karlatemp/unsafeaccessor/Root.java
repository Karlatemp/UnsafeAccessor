package io.github.karlatemp.unsafeaccessor;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * JVM Root Access.
 *
 * @since 1.1.0
 */
public class Root {
    public static Unsafe getUnsafe() {
        return Unsafe.getUnsafe();
    }

    public static MethodHandles.Lookup getTrusted() {
        return RootLookupHolder.ROOT;
    }

    public static void setAccessible(AccessibleObject object, boolean isAccessible) {
        OpenAccess.openAccess(object, isAccessible);
    }

    public static <T extends AccessibleObject> T openAccess(T object) {
        setAccessible(object, true);
        return object;
    }

    private static class RootLookupHolder {
        private static final MethodHandles.Lookup ROOT;

        static {
            try {
                Unsafe unsafe = getUnsafe();
                if (unsafe.isJava9()) {
                    MethodHandles.Lookup lookup = MethodHandles.lookup();
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
                    ROOT = lookup;
                } else {
                    Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                    field.setAccessible(true);
                    ROOT = (MethodHandles.Lookup) field.get(null);
                }
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
            usf.putBoolean(object, overrideOffset, isAccessible);
        }
    }
}
