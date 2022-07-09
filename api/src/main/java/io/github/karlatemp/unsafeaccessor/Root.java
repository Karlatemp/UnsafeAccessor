package io.github.karlatemp.unsafeaccessor;

import org.jetbrains.annotations.Contract;

import java.lang.invoke.*;
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
        Unsafe.getUnsafe0().throwException(throwable);
        throw new RuntimeException(throwable);
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

    /**
     * Lookup for method handles
     *
     * @since 1.7.0
     */
    @SuppressWarnings("UnusedReturnValue")
    public static class MethodHandleLookup {
        private static void checkAccess(UnsafeAccess access) {
            if (access == null) {
                getUnsafe();
            } else {
                access.checkTrusted();
            }
        }

        public static MethodHandle lookup(
                UnsafeAccess access,
                String methodName,
                MethodType methodType
        ) throws NoSuchMethodException {
            return lookup(access, methodName, methodType, false, true);
        }

        /**
         * Search a method handle from unsafe instance
         *
         * @param access       The unsafe access object. No perm checking when access provided
         * @param methodName   The name of target method. Eg: {@code "getInt"}
         * @param methodType   The method type of target method.<br/> Eg: {@code MethodType.methodType(int.class, long.class)}
         * @param lookupDirect Do the direct search.
         * @param doBind       Bind the unsafe object to method handle. Provide `false` to run {@link MethodHandles.Lookup#revealDirect(MethodHandle)}
         */
        public static MethodHandle lookup(
                UnsafeAccess access,
                String methodName,
                MethodType methodType,
                boolean lookupDirect,
                boolean doBind
        ) throws NoSuchMethodException {
            checkAccess(access);
            if (lookupDirect) {
                return MHLookup.lookupDirect(methodName, methodType, doBind);
            } else {
                return MHLookup.lookup(methodName, methodType, doBind ? null : new Object[1]);
            }
        }

        private static UnsafeAccess detectUnsafeAccessHold(MethodHandles.Lookup lookup, MethodHandle mh) {
            if (mh != null) {
                if (mh.type().parameterCount() != 0) {
                    throw new IllegalArgumentException("parameters not empty: " + mh);
                }
                if (mh.type().returnType() != UnsafeAccess.class) {
                    throw new IllegalArgumentException("Provided method handle is not a access check handle.");
                }
                try {
                    return (UnsafeAccess) mh.invokeExact();
                } catch (Error | RuntimeException e) {
                    throw e;
                } catch (Throwable throwable) {
                    throw new InternalError(throwable);
                }
            }
            if (lookup == null) return null;
            if (lookup == MethodHandles.publicLookup()) return null;
            try {
                try {
                    return detectUnsafeAccessHold(null, lookup.findStaticGetter(
                            lookup.lookupClass(), "UA", UnsafeAccess.class
                    ));
                } catch (NoSuchFieldException ignored) {
                }
                try {
                    return detectUnsafeAccessHold(null, lookup.findStaticGetter(
                            lookup.lookupClass(), "UNSAFE_ACCESS", UnsafeAccess.class
                    ));
                } catch (NoSuchFieldException ignored) {
                }
            } catch (IllegalAccessException ignored) {
                throw new IllegalArgumentException("Provided caller <" + lookup + "> have no full access for itself");
            }
            return null;
        }

        public static CallSite resolveHandle(
                MethodHandles.Lookup caller,
                String methodName,
                MethodType methodType
        ) throws NoSuchMethodException {
            return resolve(caller, methodName, methodType, 0, null);
        }

        public static CallSite resolveHandleDirect(
                MethodHandles.Lookup caller,
                String methodName,
                MethodType methodType
        ) throws NoSuchMethodException {
            return resolve(caller, methodName, methodType, 1, null);
        }

        public static CallSite resolve(
                MethodHandles.Lookup caller,
                String methodName,
                MethodType methodType,
                int direct,
                MethodHandle unsafeAccess_static_getter
        ) throws NoSuchMethodException {
            return resolve(caller, methodName, methodType, direct, 0, unsafeAccess_static_getter);
        }

        /**
         * Resolve a method handle for `invokeDynamic`
         * <p>
         * Example: <pre>{@code
         * mymethod.visitInvokeDynamicInsn("getInt", "(J)I", new Handle(
         *         Opcodes.H_INVOKESTATIC,
         *         "io/github/karlatemp/unsafeaccessor/Root$MethodHandleLookup",
         *         "resolve", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;IILjava/lang/invoke/MethodHandle;)Ljava/lang/invoke/CallSite;", false
         * ), 0, 0, new Handle(
         *         Opcodes.H_GETSTATIC,
         *         "org/example/generated/MyClass", "UNSAFE_ACCESS", "Lio/github/karlatemp/unsafeaccessor/UnsafeAccess;",
         *         false
         * ));
         * }</pre>
         *
         * @param noCallerCheck              if {@code true}, will skip caller permission detect
         * @param unsafeAccess_static_getter The handle to get an unsafe-access instance. <br/>
         */
        public static CallSite resolve(
                MethodHandles.Lookup caller,
                String methodName,
                MethodType methodType,
                int direct,
                int noCallerCheck,
                MethodHandle unsafeAccess_static_getter
        ) throws NoSuchMethodException {
            return new ConstantCallSite(
                    lookup(detectUnsafeAccessHold(icb(noCallerCheck) ? null : caller, unsafeAccess_static_getter),
                            methodName, methodType, icb(direct), true
                    )
            );
        }

        private static boolean icb(int v) {
            return v != 0;
        }
    }
}
