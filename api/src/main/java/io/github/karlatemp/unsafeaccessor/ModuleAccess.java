package io.github.karlatemp.unsafeaccessor;

import java.net.URI;
import java.util.Iterator;
import java.util.Set;

/**
 * Access for {@link java.lang.Module}
 *
 * @since 1.5.0
 */
@SuppressWarnings("Since15")
public interface ModuleAccess {
    boolean isSupport();

    /**
     * special Module to mean "everyone"
     * <p>
     * Only used for {@link #addExports(Object, String, Object)}, {@link #addOpens(Object, String, Object)}
     *
     * @since 1.6.0
     */
    Object getEVERYONE_MODULE();

    /**
     * special Module to mean "all unnamed modules"
     * <p>
     * Only used for {@link #addExports(Object, String, Object)}, {@link #addOpens(Object, String, Object)}
     *
     * @since 1.6.0
     */
    Object getALL_UNNAMED_MODULE();

    /**
     * klass.getModule()
     */
    Object getModule(Class<?> klass);

    /*ModuleDescriptor.Builder*/Object newModuleBuilder(
            String mn,
            boolean strict,
            /*Set<ModuleDescriptor.Modifier>*/
            Set<?> ms
    );

    /**
     * Define a Package of the given name and module by the given class loader.
     */
    Package definePackage(ClassLoader cl, String name, Object module);

    Object defineModule(ClassLoader loader, Object/*ModuleDescriptor*/ descriptor, URI uri);

    /**
     * Defines the unnamed module for the given class loader.
     */
    Object defineUnnamedModule(ClassLoader loader);

    /**
     * Updates the readability so that module m1 reads m2. The new read edge
     * does not result in a strong reference to m2 (m2 can be GC'ed).
     * <p>
     * This method is the same as m1.addReads(m2) but without a permission check.
     */
    void addReads(Object m1, Object m2);

    /**
     * Updates module m to read all unnamed modules.
     */
    void addReadsAllUnnamed(Object m);


    /**
     * Updates module m1 to export a package to module m2. The export does
     * not result in a strong reference to m2 (m2 can be GC'ed).
     */
    void addExports(Object m1, String pkg, Object m2);

    /**
     * Updates a module m to export a package to all unnamed modules.
     */
    void addExportsToAllUnnamed(Object m, String pkg);

    /**
     * Updates module m1 to open a package to module m2. Opening the
     * package does not result in a strong reference to m2 (m2 can be GC'ed).
     */
    void addOpens(Object m1, String pkg, Object m2);

    /**
     * Updates module m to open a package to all unnamed modules.
     */
    void addOpensToAllUnnamed(Object m, String pkg);

    /**
     * Updates module m to open all packages returned by the given iterator.
     */
    void addOpensToAllUnnamed(Object m, Iterator<String> packages);

    /**
     * Updates module m to use a service.
     */
    void addUses(Object m, Class<?> service);

    Object getUnnamedModule(ClassLoader cl);

    /**
     * Returns true if module m can access restricted methods.
     *
     * @implNote Always true if current jrt doesn't contain EnableNativeAccess field
     * @since 1.7.0, JDK 17
     */
    boolean isEnableNativeAccess(Object m);

    /**
     * Return direct field value of module
     *
     * @implNote Always true if current jrt doesn't contain EnableNativeAccess field
     * @since 1.7.0, JDK 17
     */
    boolean isEnableNativeAccess0(Object m);

    /**
     * Updates module m to allow access to restricted methods.
     *
     * @apiNote Error when module is an unnamed module.
     * @since 1.7.0, JDK 17
     */
    void addEnableNativeAccess(Object m);

    /**
     * Updates module m to allow access to restricted methods.
     *
     * @apiNote May have no effect if module is an unnamed module.
     * @since 1.7.0, JDK 17
     */
    void addEnableNativeAccess0(Object m);
}
