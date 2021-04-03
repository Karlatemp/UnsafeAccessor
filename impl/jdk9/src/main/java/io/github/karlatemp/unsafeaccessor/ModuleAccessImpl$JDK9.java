package io.github.karlatemp.unsafeaccessor;

import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.JavaLangModuleAccess;
import jdk.internal.access.SharedSecrets;

import java.lang.module.ModuleDescriptor;
import java.net.URI;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
class ModuleAccessImpl$JDK9 implements ModuleAccess {
    static {
        ((Consumer<Object>) ModuleAccessImpl$JDK9.class.getClassLoader())
                .accept(new ModuleAccessImpl$JDK9());
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
        JLA.addOpensToAllUnnamed((Module) m, packages);
    }

    @Override
    public void addUses(Object m, Class<?> service) {
        JLA.addUses((Module) m, service);
    }
}
