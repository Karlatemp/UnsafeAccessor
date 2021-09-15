package io.github.karlatemp.unsafeaccessor;

import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.JavaLangModuleAccess;
import jdk.internal.access.SharedSecrets;

import java.lang.module.ModuleDescriptor;
import java.net.URI;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings({"unchecked", "rawtypes"})
class ModuleAccessImpl$JDK9 implements ModuleAccess {
    private static Object EVERYONE_MODULE;
    private static Object ALL_UNNAMED_MODULE;
    private static final Supplier<UnsafeAccess> SUPPLIER;

    private static Object findModule(String name) {
        try {
            return (Module) SUPPLIER.get().getTrustedIn(Module.class)
                    .findStaticGetter(Module.class, name, Module.class)
                    .invoke();
        } catch (Throwable throwable) {
            return throwable;
        }
    }

    static {
        ClassLoader classLoader = ModuleAccessImpl$JDK9.class.getClassLoader();
        SUPPLIER = (Supplier<UnsafeAccess>) ((Supplier) classLoader).get();
        ((Consumer<Object>) classLoader).accept(new ModuleAccessImpl$JDK9());
    }

    @Override
    public boolean isSupport() {
        return true;
    }

    static JavaLangAccess JLA = SharedSecrets.getJavaLangAccess();
    static JavaLangModuleAccess JLMA = SharedSecrets.getJavaLangModuleAccess();

    @Override
    public Object getModule(Class<?> klass) {
        return klass.getModule();
    }

    @Override
    public Object getALL_UNNAMED_MODULE() {
        if (ALL_UNNAMED_MODULE == null) {
            ALL_UNNAMED_MODULE = findModule("ALL_UNNAMED_MODULE");
        }
        if (ALL_UNNAMED_MODULE instanceof Throwable)
            throw new UnsupportedOperationException((Throwable) ALL_UNNAMED_MODULE);
        return ALL_UNNAMED_MODULE;
    }

    @Override
    public Object getEVERYONE_MODULE() {
        if (EVERYONE_MODULE == null) {
            EVERYONE_MODULE = findModule("EVERYONE_MODULE");
        }
        if (EVERYONE_MODULE instanceof Throwable)
            throw new UnsupportedOperationException((Throwable) EVERYONE_MODULE);
        return EVERYONE_MODULE;
    }

    @Override
    public Object newModuleBuilder(
            String mn,
            boolean strict,
            Set<?> ms
    ) {
        return JLMA.newModuleBuilder(
                mn, strict, (Set<ModuleDescriptor.Modifier>) ms
        );
    }

    @Override
    public Package definePackage(ClassLoader cl, String name, Object module) {
        return JLA.definePackage(cl, name, (Module) module);
    }

    @Override
    public Object defineModule(ClassLoader loader, Object descriptor, URI uri) {
        return JLA.defineModule(loader, (ModuleDescriptor) descriptor, uri);
    }

    @Override
    public Object defineUnnamedModule(ClassLoader loader) {
        return JLA.defineUnnamedModule(loader);
    }

    @Override
    public void addReads(Object m1, Object m2) {
        JLA.addReads((Module) m1, (Module) m2);
    }

    @Override
    public void addReadsAllUnnamed(Object m) {
        JLA.addReadsAllUnnamed((Module) m);
    }

    @Override
    public void addExports(Object m1, String pkg, Object m2) {
        JLA.addExports((Module) m1, pkg, (Module) m2);
    }

    @Override
    public void addExportsToAllUnnamed(Object m, String pkg) {
        JLA.addExportsToAllUnnamed((Module) m, pkg);
    }

    @Override
    public void addOpens(Object m1, String pkg, Object m2) {
        JLA.addOpens((Module) m1, pkg, (Module) m2);
    }

    @Override
    public void addOpensToAllUnnamed(Object m, String pkg) {
        JLA.addOpensToAllUnnamed((Module) m, pkg);
    }

    @Override
    public void addOpensToAllUnnamed(Object m, Iterator<String> packages) {
        while (packages.hasNext()) {
            JLA.addOpensToAllUnnamed((Module) m, packages.next());
        }
    }

    @Override
    public void addUses(Object m, Class<?> service) {
        JLA.addUses((Module) m, service);
    }
}
