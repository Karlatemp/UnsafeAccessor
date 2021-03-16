package io.github.karlatemp.unsafeaccessor;

import java.security.Permission;

/**
 * Security Manager
 * <p>
 * Use this class to limit classes to access Unsafe
 *
 * @since 1.3.0
 */
public final class SecurityCheck extends ProtectedObject {

    SecurityCheck() {
        trusted = true;
    }


    static final SecurityCheck INSTANCE = new SecurityCheck();
    static Permission PERMISSION_GET_UNSAFE;
    static Permission PERMISSION_OPEN_ACCESS;

    public static SecurityCheck getInstance() {
        Permission p = PERMISSION_GET_UNSAFE;
        if (p != null) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) sm.checkPermission(p);
        }
        return INSTANCE;
    }

    public Permission getPermission() {
        checkTrusted();
        return PERMISSION_GET_UNSAFE;
    }

    public Permission getPermissionOpenAccess() {
        checkTrusted();
        return PERMISSION_OPEN_ACCESS;
    }

    public void setPermissionOpenAccess(Permission permissionOpenAccess) {
        checkTrusted();
        PERMISSION_OPEN_ACCESS = permissionOpenAccess;
    }

    public void setPermission(Permission permission) {
        checkTrusted();
        PERMISSION_GET_UNSAFE = permission;
    }

    public void enableSecurityCheck() {
        checkTrusted();
        setPermission(new RuntimePermission("unsafe.getInstance"));
    }
}
