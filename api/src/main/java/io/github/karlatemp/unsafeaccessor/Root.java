package io.github.karlatemp.unsafeaccessor;

import org.jetbrains.annotations.Contract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.security.Permission;

/**
 * JVM Root Access.
 *
 * @since 1.1.0
 */
@SuppressWarnings("DefaultAnnotationParam")
public class Root {
    @Contract(pure = false)
    public static Unsafe getUnsafe() {
        Permission p = SecurityCheck.PERMISSION_GET_UNSAFE;
        if (p != null) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) sm.checkPermission(p);
        }

        return Unsafe.getUnsafe0();
    }

    /**
     * Return the trusted lookup.
     *
     * Use {@link #getTrusted(Class)}
     * @return MethodHandles.Lookup.IMPL_LOOKUP
     */
    @Deprecated
    @Contract(pure = true)
    public static MethodHandles.Lookup getTrusted() {
        Permission p = SecurityCheck.PERMISSION_GET_UNSAFE;
        if (p != null) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) sm.checkPermission(p);
        }

        return RootLookupHolder.ROOT;
    }

    @Contract(pure = true)
    public static MethodHandles.Lookup getTrusted(Class<?> k) {
        //noinspection ResultOfMethodCallIgnored
        getTrusted();
        return RootLookupHolder.trustedIn(k);
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

    static class RootLookupHolder {
        static final MethodHandles.Lookup ROOT;

        static boolean isOpenJ9vm() {
            if (ROOT.lookupClass() == MethodHandle.class) {
                return ROOT.lookupModes() == 0x40;
            }
            return false;
        }

        static final boolean isOpenj9;
        static final long accessMode;

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
            isOpenj9 = isOpenJ9vm();
            if (isOpenj9) {
                accessMode = Unsafe.getUnsafe0().objectFieldOffset(MethodHandles.Lookup.class, "accessMode");
            } else {
                accessMode = -1;
            }
        }

        static MethodHandles.Lookup trustedIn(Class<?> target) {
            if (isOpenj9) {
                MethodHandles.Lookup lookup = ROOT.in(target);
                Unsafe.getUnsafe0().putLong(lookup, accessMode, ROOT.lookupModes());
                return lookup;
            }
            return ROOT;
        }
    }

    static class OpenAccess {
        private static final Unsafe usf = Unsafe.getUnsafe0();
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

        static void openAccess0(AccessibleObject object, boolean isAccessible) {
            if (object == null) throw new NullPointerException("object");
            usf.putBoolean(object, overrideOffset, isAccessible);
        }

        static void openAccess(AccessibleObject object, boolean isAccessible) {
            {
                Permission p = SecurityCheck.PERMISSION_OPEN_ACCESS;
                if (p == null) p = SecurityCheck.PERMISSION_GET_UNSAFE;
                if (p != null) {
                    SecurityManager sm = System.getSecurityManager();
                    if (sm != null) sm.checkPermission(p);
                }
            }
            openAccess0(object, isAccessible);
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
