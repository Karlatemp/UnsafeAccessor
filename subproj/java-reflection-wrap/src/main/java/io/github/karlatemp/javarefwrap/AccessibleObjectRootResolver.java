package io.github.karlatemp.javarefwrap;

import io.github.karlatemp.unsafeaccessor.UnsafeAccess;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class AccessibleObjectRootResolver {
    final MethodHandle M, F, C;

    AccessibleObjectRootResolver(MethodHandle m, MethodHandle f, MethodHandle c) {
        M = m;
        F = f;
        C = c;
    }

    Object resolve(AccessibleObject o) throws Throwable {
        if (o == null) throw new NullPointerException();
        if (o instanceof Method) {
            return M.invoke(o);
        }
        if (o instanceof Field) {
            return F.invoke(o);
        }
        if (o instanceof Constructor) {
            return C.invoke(o);
        }
        throw new UnsupportedOperationException(o.getClass().getName());
    }

    static MethodHandle resolve(UnsafeAccess access, MethodHandles.Lookup clLookup) throws Exception {
        try {
            return clLookup.findVirtual(AccessibleObject.class, "getRoot", MethodType.methodType(AccessibleObject.class));
        } catch (NoSuchMethodException noSuchMethodException) {
            try {
                AccessibleObjectRootResolver resolver = new AccessibleObjectRootResolver(
                        access.getTrustedIn(Method.class).findGetter(Method.class, "root", Method.class),
                        access.getTrustedIn(Field.class).findGetter(Field.class, "root", Field.class),
                        access.getTrustedIn(Constructor.class).findGetter(Constructor.class, "root", Constructor.class)
                );
                return MethodHandles.lookup().findVirtual(
                        AccessibleObjectRootResolver.class, "resolve", MethodType.methodType(Object.class, AccessibleObject.class)
                ).bindTo(resolver);
            } catch (Throwable throwable) {
                noSuchMethodException.addSuppressed(throwable);
                throw noSuchMethodException;
            }
        }
    }
}
