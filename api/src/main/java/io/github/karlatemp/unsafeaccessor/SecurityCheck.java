package io.github.karlatemp.unsafeaccessor;

import java.lang.reflect.AccessibleObject;
import java.security.Permission;

/**
 * Security Manager
 * <p>
 * Use this class to limit classes to access Unsafe
 *
 * @since 1.3.0
 */
public final class SecurityCheck extends ProtectedObject {
    /**
     * Limit applications to access UnsafeAccessor
     *
     * @since 1.6.0
     */
    public static abstract class AccessLimiter {
        static final AccessLimiter NOOP = new AccessLimiter() {
            @Override
            public void preGetUnsafe() {
            }

            @Override
            public void preGetSecurityCheck() {
            }

            @Override
            public void preGetTrustedLookup(Class<?> target) {
            }

            @Override
            public void preGetUnsafeAccess() {
            }

            @Override
            public void preOpenAccessible(AccessibleObject object) {
            }
        };

        public abstract void preGetUnsafe();

        public void preGetSecurityCheck() {
            preGetUnsafe();
        }

        public void preGetUnsafeAccess() {
            preGetUnsafe();
        }

        public void preGetTrustedLookup(Class<?> target) {
            preGetUnsafe();
        }

        public void preOpenAccessible(AccessibleObject object) {
            preGetUnsafe();
        }
    }

    private static class AccessLimiterSM extends AccessLimiter {
        Permission getUsf, open;

        @Override
        public void preGetUnsafe() {
            Permission getUsf = this.getUsf;
            SecurityManager sm = System.getSecurityManager();
            if (getUsf != null && sm != null) sm.checkPermission(getUsf);
        }

        @Override
        public void preOpenAccessible(AccessibleObject object) {
            Permission open = this.open;
            SecurityManager sm = System.getSecurityManager();
            if (open != null && sm != null) sm.checkPermission(open);
        }
    }

    SecurityCheck() {
        trusted = true;
    }

    static final SecurityCheck INSTANCE = new SecurityCheck();
    static AccessLimiter LIMITER = AccessLimiter.NOOP;

    public static SecurityCheck getInstance() {
        return INSTANCE;
    }

    public AccessLimiter getLimiter() {
        checkTrusted();
        AccessLimiter LM = LIMITER;
        if (LM == AccessLimiter.NOOP) return null;
        return LM;
    }

    public void setLimiter(AccessLimiter value) {
        if (value == null) value = AccessLimiter.NOOP;
        LIMITER = value;
    }

    @Deprecated
    public Permission getPermission() {
        checkTrusted();
        if (LIMITER instanceof AccessLimiterSM) return ((AccessLimiterSM) LIMITER).getUsf;
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public Permission getPermissionOpenAccess() {
        checkTrusted();
        if (LIMITER instanceof AccessLimiterSM) return ((AccessLimiterSM) LIMITER).open;
        throw new UnsupportedOperationException();
    }

    private static AccessLimiterSM sm() {
        if (LIMITER == null) {
            return (AccessLimiterSM) (LIMITER = new AccessLimiterSM());
        }
        AccessLimiter AL = LIMITER;
        if (AL instanceof AccessLimiterSM) return (AccessLimiterSM) AL;
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void setPermissionOpenAccess(Permission permissionOpenAccess) {
        checkTrusted();
        sm().open = permissionOpenAccess;
    }

    @Deprecated
    public void setPermission(Permission permission) {
        checkTrusted();
        sm().getUsf = permission;
    }

    @Deprecated
    public void enableSecurityCheck() {
        checkTrusted();
        if (LIMITER == null) {
            AccessLimiterSM sm = new SecurityCheck.AccessLimiterSM();
            sm.getUsf = sm.open = new RuntimePermission("unsafe.getInstance");
            LIMITER = sm;
        }
    }
}
