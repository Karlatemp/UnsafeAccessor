package io.github.karlatemp.javarefwrap;

import io.github.karlatemp.unsafeaccessor.UnsafeAccess;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <h1>Java Reflection Wrap</h1>
 * <p>
 * A system for changing implementation of {@link java.lang.reflect}.
 */
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class JavaReflectionWrap {
    private static final Class[] C_ARRAY_ZERO = new Class[0];

    private RuntimeException throw0(Throwable throwable) {
        if (throwable == null) throw new NullPointerException();
        access.getUnsafe().throwException(throwable);
        return new RuntimeException(throwable);
    }

    private static Class<?>[] notNull(Class<?>[] a) {
        return notNullCArray((Class[]) a);
    }

    private static <T> Class<T>[] notNullCArray(Class<T>[] array) {
        if (array == null) return C_ARRAY_ZERO;
        return array;
    }

    private final UnsafeAccess access;
    private static boolean trustedFinal = true;

    private final MethodHandle
            MH_C_constructor,
            MH_C_set,
            MH_M_constructor,
            MH_H_set,
            MH_F_constructor,
            MH_F_setOverrideFieldAccessor,
            MH_F_setFieldAccessor,
            MH_A_getRoot,
            MH_C_copy,
            MH_M_copy,
            MH_F_copy;

    @SuppressWarnings("JavaLangInvokeHandleSignature")
    private JavaReflectionWrap(UnsafeAccess access) throws Exception {
        this.access = access;
        MethodHandles.Lookup lookup = access.getTrustedIn(Constructor.class);
        MH_C_constructor = lookup.findConstructor(Constructor.class, MethodType.methodType(void.class,
                Class.class, Class[].class, Class[].class,
                int.class, int.class, String.class, byte[].class, byte[].class
        ));
        MH_C_set = lookup.findSetter(Constructor.class, "constructorAccessor",
                Class.forName(AccessWrapperHolder.jRefPkg + "ConstructorAccessor")
        );
        MH_M_constructor = lookup.findConstructor(Method.class, MethodType.methodType(void.class,
                Class.class, String.class, Class[].class, Class.class, Class[].class, int.class, int.class, String.class, byte[].class, byte[].class, byte[].class
        ));
        MH_H_set = lookup.findSetter(Method.class, "methodAccessor",
                Class.forName(AccessWrapperHolder.jRefPkg + "MethodAccessor")
        );
        MethodHandle F_constructor = null;
        if (trustedFinal) {
            try {
                F_constructor = lookup.findConstructor(Field.class, MethodType.methodType(void.class,
                        Class.class, String.class, Class.class, int.class, boolean.class, int.class, String.class, byte[].class
                ));
            } catch (NoSuchMethodException ignored) {
                trustedFinal = false;
            }
        }
        if (F_constructor == null) {
            F_constructor = lookup.findConstructor(Field.class, MethodType.methodType(void.class,
                    Class.class, String.class, Class.class, int.class, int.class, String.class, byte[].class
            ));
        }
        MH_F_constructor = F_constructor;
        MH_F_setFieldAccessor = lookup.findSetter(Field.class, "fieldAccessor",
                Class.forName(AccessWrapperHolder.jRefPkg + "FieldAccessor")
        );
        MH_F_setOverrideFieldAccessor = lookup.findSetter(Field.class, "overrideFieldAccessor",
                Class.forName(AccessWrapperHolder.jRefPkg + "FieldAccessor")
        );

        MH_A_getRoot = AccessibleObjectRootResolver.resolve(access, lookup);

        MH_C_copy = lookup.findVirtual(Constructor.class, "copy", MethodType.methodType(Constructor.class));
        MH_M_copy = lookup.findVirtual(Method.class, "copy", MethodType.methodType(Method.class));
        MH_F_copy = lookup.findVirtual(Field.class, "copy", MethodType.methodType(Field.class));
    }

    /**
     * Get a new {@link JavaReflectionWrap} instance.
     */
    public static JavaReflectionWrap newInstance(UnsafeAccess access) {
        Objects.requireNonNull(access, "Require unsafe access interface.");
        AccessWrapperHolder.initialize(access);
        try {
            return new JavaReflectionWrap(access);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Attach a custom FieldAccessor to field.
     *
     * @apiNote It will only change operations of {@link Field}.*. Actions
     * of {@link MethodHandles.Lookup#unreflectGetter(Field)}/unreflectSetter
     * will not change
     */
    public Field attach(
            Field field,
            FieldAccessCallback overrideCallback,
            FieldAccessCallback nonOverrideCallback
    ) {
        Objects.requireNonNull(field, "field");
        Objects.requireNonNull(overrideCallback, "overrideCallback");
        try {
            if (overrideCallback instanceof AbsFieldAccessCallback) {
                ((AbsFieldAccessCallback) overrideCallback).attachField(field);
            }
            if (nonOverrideCallback == null) {
                nonOverrideCallback = overrideCallback = (FieldAccessCallback) AccessWrapperHolder.HF.invoke(overrideCallback);
            } else {
                if (nonOverrideCallback instanceof AbsFieldAccessCallback) {
                    ((AbsFieldAccessCallback) nonOverrideCallback).attachField(field);
                }
                overrideCallback = (FieldAccessCallback) AccessWrapperHolder.HF.invoke(overrideCallback);
                nonOverrideCallback = (FieldAccessCallback) AccessWrapperHolder.HF.invoke(nonOverrideCallback);
            }
            MH_F_setOverrideFieldAccessor.invoke(field, overrideCallback);
            MH_F_setFieldAccessor.invoke(field, nonOverrideCallback);
            return field;
        } catch (Throwable throwable) {
            throw throw0(throwable);
        }
    }

    /**
     * Attach a custom ConstructorAccessor to a constructor.
     *
     * @apiNote It will only change operations of {@link Constructor#newInstance(Object...)}. Actions of
     * {@link MethodHandles.Lookup#unreflectConstructor(Constructor)} will not change
     */
    public <T> Constructor<T> attach(Constructor<T> constructor, ConstructorInvokeCallback callback) {
        Objects.requireNonNull(constructor, "constructor");
        Objects.requireNonNull(callback, "callback");
        try {
            MH_C_set.invoke(constructor, AccessWrapperHolder.HC.invoke(callback));
            return constructor;
        } catch (Throwable throwable) {
            throw throw0(throwable);
        }
    }

    /**
     * Attach a custom MethodAccessor to a method
     *
     * @apiNote It will only change operations of {@link Method#invoke(Object, Object...)}. Actions of
     * {@link MethodHandles.Lookup#unreflect(Method)} will not change
     */
    public Method attach(Method method, MethodInvokeCallback callback) {
        Objects.requireNonNull(method, "method");
        Objects.requireNonNull(callback, "callback");
        try {
            MH_H_set.invoke(method, AccessWrapperHolder.HM.invoke(callback));
            return method;
        } catch (Throwable throwable) {
            throw throw0(throwable);
        }
    }

    /**
     * Allocate a new <b>FAKE</b> {@link Field}.
     *
     * @apiNote Use this api with caution. The fake field may crash jvm by
     * {@link MethodHandles.Lookup#unreflectGetter(Field)}/unreflectSetter
     */
    public Field newField(
            Class<?> declaringClass,
            String name, Class<?> type,
            int modifiers,
            FieldAccessCallback overrideCallback,
            FieldAccessCallback nonOverrideCallback
    ) {
        Objects.requireNonNull(declaringClass, "declaringClass");
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(overrideCallback, "overrideCallback");

        try {
            Field field;
            if (trustedFinal) {
                field = (Field) MH_F_constructor.invoke(
                        declaringClass, name, type, modifiers, true, 1, null, null
                );
            } else {
                field = (Field) MH_F_constructor.invoke(
                        declaringClass, name, type, modifiers, 1, null, null
                );
            }
            return attach(field, overrideCallback, nonOverrideCallback);
        } catch (Throwable throwable) {
            throw throw0(throwable);
        }
    }

    /**
     * Allocate a new <b>FAKE</b> {@link Constructor}.
     *
     * @apiNote Use this api with caution. The fake constructor may crash jvm by
     * {@link MethodHandles.Lookup#unreflectConstructor(Constructor)}
     */
    public <T> Constructor<T> newConstructor(
            Class<T> declaringClass, Class<?>[] parameters,
            Class<?>[] checkedExceptions, int modifiers,
            ConstructorInvokeCallback callback
    ) {
        Objects.requireNonNull(declaringClass, "declaringClass");
        Objects.requireNonNull(callback, "callback");
        try {
            Constructor<T> c = (Constructor<T>) MH_C_constructor.invoke(
                    declaringClass, notNull(parameters), notNull(checkedExceptions), modifiers, 1, null, null, null
            );
            return attach(c, callback);
        } catch (Throwable throwable) {
            throw throw0(throwable);
        }
    }

    /**
     * Allocate a new <b>FAKE</b> {@link Method}
     *
     * @apiNote Use this api with caution. The fake method may crash jvm by
     * {@link MethodHandles.Lookup#unreflect(Method)}
     */
    public Method newMethod(
            Class<?> declaringClass, String name,
            Class<?> returnType, Class<?>[] parameters,
            Class<?>[] checkedExceptions,
            int modifiers, MethodInvokeCallback callback
    ) {
        Objects.requireNonNull(declaringClass, "declaringClass");
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(returnType, "returnType");
        Objects.requireNonNull(callback, "callback");
        try {
            Method m = (Method) MH_M_constructor.invoke(
                    declaringClass, name, notNull(parameters),
                    returnType, notNull(checkedExceptions),
                    modifiers, 1, null, null, null, null
            );
            return attach(m, callback);
        } catch (Throwable throwable) {
            throw throw0(throwable);
        }
    }

    /**
     * Get the root of a {@link AccessibleObject}. Please ensure it is one of
     * {@link Method}, {@link Constructor} or {@link Field}, before calling this method.
     *
     * @apiNote SubMembers(Field, Method, Constructor) that not initialized accessor, never called getXXX/setXXX/invoke/newInstance,
     * will use the accessor of their root.
     */
    public <T extends AccessibleObject> T getRoot(T accessibleObject) {
        if (accessibleObject == null) return null;
        try {
            AccessibleObject root = (AccessibleObject) MH_A_getRoot.invoke(accessibleObject);
            if (root == null) return accessibleObject;
            return (T) root;
        } catch (Throwable throwable) {
            throw throw0(throwable);
        }
    }

    /**
     * Copy an existing field
     */
    public Field copy(Field field) {
        Objects.requireNonNull(field, "field");
        try {
            return (Field) MH_F_copy.invoke(getRoot(field));
        } catch (Throwable throwable) {
            throw throw0(throwable);
        }
    }

    /**
     * Copy an existing method
     */
    public Method copy(Method method) {
        Objects.requireNonNull(method, "method");
        try {
            return (Method) MH_M_copy.invoke(getRoot(method));
        } catch (Throwable throwable) {
            throw throw0(throwable);
        }
    }

    /**
     * Copy an existing constructor
     */
    public <T> Constructor<T> copy(Constructor<T> constructor) {
        Objects.requireNonNull(constructor, "constructor");
        try {
            return (Constructor<T>) MH_C_copy.invoke(getRoot(constructor));
        } catch (Throwable throwable) {
            throw throw0(throwable);
        }
    }

}
