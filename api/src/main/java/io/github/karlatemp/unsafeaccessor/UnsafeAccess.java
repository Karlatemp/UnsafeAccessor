package io.github.karlatemp.unsafeaccessor;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.AccessibleObject;

/**
 * Direct unsafe access
 * <p>
 * A bridge object to access unsafe.
 * <p>
 * Unlike other methods, using this bridge will not trigger any permission checks.
 * The permission check will only happen in {@link #getInstance()}
 *
 * @since 1.4.0
 */
public final class UnsafeAccess extends ProtectedObject {
    static final UnsafeAccess INSTANCE = new UnsafeAccess();

    public static UnsafeAccess getInstance() {
        SecurityCheck.getInstance();
        return INSTANCE;
    }

    public SecurityCheck getSecuritySettings() {
        checkTrusted();
        return SecurityCheck.INSTANCE;
    }

    public Unsafe getUnsafe() {
        checkTrusted();
        return Unsafe.getUnsafe0();
    }

    /**
     * Use {@link #getTrustedIn(Class)}
     *
     * @return MethodHandles.Lookup.IMPL_LOOKUP
     */
    @Deprecated
    public MethodHandles.Lookup getTrusted() {
        checkTrusted();
        return Root.RootLookupHolder.ROOT;
    }

    public MethodHandles.Lookup getTrustedIn(Class<?> target) {
        checkTrusted();
        return Root.RootLookupHolder.trustedIn(target);
    }

    public <T extends AccessibleObject> T openAccess(T object) {
        checkTrusted();
        Root.OpenAccess.openAccess0(object, true);
        return object;
    }

    public <T extends AccessibleObject> T setAccessible(T object, boolean accessible) {
        checkTrusted();
        Root.OpenAccess.openAccess0(object, accessible);
        return object;
    }

    /**
     * @since 1.5.0
     */
    public ModuleAccess getModuleAccess() {
        checkTrusted();
        Unsafe.getUnsafe0();
        return Root.Secret.MACCESS;
    }
}
