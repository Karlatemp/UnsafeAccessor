package io.github.karlatemp.unsafeaccessor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

class MHLookup {
    static MethodHandle lookupDirect(String name, MethodType type, boolean bind) throws NoSuchMethodException {
        Object anyx = Unsafe.getUnsafe0().getOriginalUnsafe();
        MethodHandles.Lookup lookup = Root.RootLookupHolder.trustedIn(anyx.getClass());
        MethodHandle rsp;
        try {
            rsp = lookup.findVirtual(anyx.getClass(), name, type);
        } catch (IllegalAccessException e) {
            throw new InternalError(e);
        }
        return bind ? rsp.bindTo(anyx) : rsp;
    }

    private static boolean checkUsingObj() {
        Unsafe usf = Unsafe.getUnsafe0();
        if (!usf.isJava9()) return true;
        return usf.getClass().getName().endsWith("Obj");
    }

    private static final boolean isUsingObj = checkUsingObj();

    static MethodHandle lookup(String name, MethodType type, Object[] bind) throws NoSuchMethodException {
        Unsafe usf = Unsafe.getUnsafe0();
        Object anyx = usf.getOriginalUnsafe();
        MethodHandles.Lookup lookup = Root.RootLookupHolder.trustedIn(anyx.getClass());
        String directName;
        if (isUsingObj) {
            directName = name.replace("Reference", "Object");
        } else directName = name;

        Object bindx = null;
        MethodHandle rsp = null;
        try {
            try {
                rsp = lookup.findVirtual(anyx.getClass(), directName, type);
                bindx = anyx;
            } catch (NoSuchMethodException ignored) {
                if (!usf.isJava9()) {
                    // Opaque acquire Release
                    try {
                        String nwm = directName
                                .replace("Opaque", "Volatile")
                                .replace("Release", "Volatile")
                                .replace("Acquire", "Volatile");
                        rsp = lookup.findVirtual(anyx.getClass(), directName, type);
                        bindx = anyx;
                    } catch (NoSuchMethodException ignored2) {
                    }
                }
                if (rsp == null) {
                    rsp = lookup.findVirtual(Unsafe.class, name, type);
                    bindx = Unsafe.getUnsafe0();
                }
            }
        } catch (IllegalAccessException e) {
            throw new InternalError(e);
        }

        if (bind == null) return rsp.bindTo(bindx);
        bind[0] = bindx;
        return rsp;
    }
}
