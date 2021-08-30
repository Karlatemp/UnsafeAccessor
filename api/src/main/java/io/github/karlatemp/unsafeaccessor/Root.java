package io.github.karlatemp.unsafeaccessor;

import org.jetbrains.annotations.Contract;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.function.Consumer;

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

    /**
     * Return the trusted lookup.
     * <p>
     * Use {@link #getTrusted(Class)}
     *
     * @return MethodHandles.Lookup.IMPL_LOOKUP
     */
    @Deprecated
    @Contract(pure = true)
    public static MethodHandles.Lookup getTrusted() {
        SecurityCheck.LIMITER.preGetTrustedLookup(null);
        return RootLookupHolder.ROOT;
    }

    @Contract(pure = true)
    public static MethodHandles.Lookup getTrusted(Class<?> k) {
        SecurityCheck.LIMITER.preGetTrustedLookup(k);
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
                int modes = ROOT.lookupModes();
                return modes == 0x40 // AdoptOpenJDK - jdk 11.0.9.11 openj9
                        || modes == 0x80 // Eclipse OpenJ9 VM - 11.0.12.7-openj9
                        ;
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
            if (target == null) return ROOT;
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
            if (isAccessible) {
                SecurityCheck.LIMITER.preOpenAccessible(object);
            }
            openAccess0(object, isAccessible);
        }
    }

    static class Secret {
        static ModuleAccess MACCESS;
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

    /**
     * @since 1.5.0
     */
    @Contract(pure = true)
    public static ModuleAccess getModuleAccess() {
        getUnsafe();
        return Secret.MACCESS;
    }

    static class ObjectInitializer {
        static Consumer<Object> initializer;

        static Consumer<Object> initializer() {
            if (initializer != null) return initializer;
            synchronized (ObjectInitializer.class) {
                if (initializer != null) return initializer;
                initializer = UsfAccessor.allocateObjectInitializer();
            }
            return initializer;
        }
    }

    /**
     * Do nothing
     *
     * @since 1.6.0
     */
    public static void initializeObject(Object instance) {
        if (instance == null) return;
        Unsafe.getUnsafe0().ensureClassInitialized(instance.getClass());
        ObjectInitializer.initializer().accept(instance);
    }
}
