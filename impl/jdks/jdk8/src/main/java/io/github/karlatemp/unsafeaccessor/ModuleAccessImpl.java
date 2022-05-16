package io.github.karlatemp.unsafeaccessor;

import java.net.URI;
import java.util.Iterator;
import java.util.Set;

class ModuleAccessImpl {
    static class PendingInit implements ModuleAccess {
        @Override
        public boolean isSupport() {
            return true;
        }

        @Override
        public Object getModule(Class<?> klass) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object getEVERYONE_MODULE() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object getALL_UNNAMED_MODULE() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Package definePackage(ClassLoader cl, String name, Object module) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object defineModule(ClassLoader loader, Object descriptor, URI uri) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object defineUnnamedModule(ClassLoader loader) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addReads(Object m1, Object m2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addReadsAllUnnamed(Object m) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addExports(Object m1, String pkg, Object m2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addExportsToAllUnnamed(Object m, String pkg) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addOpens(Object m1, String pkg, Object m2) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addOpensToAllUnnamed(Object m, String pkg) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addOpensToAllUnnamed(Object m, Iterator<String> packages) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object newModuleBuilder(String mn, boolean strict, Set<?> ms) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addUses(Object m, Class<?> service) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object getUnnamedModule(ClassLoader cl) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isEnableNativeAccess(Object m) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isEnableNativeAccess0(Object m) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addEnableNativeAccess(Object m) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addEnableNativeAccess0(Object m) {
            throw new UnsupportedOperationException();
        }
    }

    static class Noop implements ModuleAccess {
        @Override
        public boolean isSupport() {
            return false;
        }

        @Override
        public Object getEVERYONE_MODULE() {
            return null;
        }

        @Override
        public Object getALL_UNNAMED_MODULE() {
            return null;
        }

        @Override
        public Object getModule(Class<?> klass) {
            return null;
        }

        @Override
        public Object newModuleBuilder(String mn, boolean strict, Set<?> ms) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Package definePackage(ClassLoader cl, String name, Object module) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object defineModule(ClassLoader loader, Object descriptor, URI uri) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object defineUnnamedModule(ClassLoader loader) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void addReads(Object m1, Object m2) {
        }

        @Override
        public void addReadsAllUnnamed(Object m) {
        }

        @Override
        public void addExports(Object m1, String pkg, Object m2) {
        }

        @Override
        public void addExportsToAllUnnamed(Object m, String pkg) {
        }

        @Override
        public void addOpens(Object m1, String pkg, Object m2) {
        }

        @Override
        public void addOpensToAllUnnamed(Object m, String pkg) {
        }

        @Override
        public void addOpensToAllUnnamed(Object m, Iterator<String> packages) {
        }

        @Override
        public void addUses(Object m, Class<?> service) {
        }

        @Override
        public Object getUnnamedModule(ClassLoader cl) {
            return null;
        }

        @Override
        public boolean isEnableNativeAccess(Object m) {
            return true;
        }

        @Override
        public boolean isEnableNativeAccess0(Object m) {
            return true;
        }

        @Override
        public void addEnableNativeAccess(Object m) {
        }

        @Override
        public void addEnableNativeAccess0(Object m) {
        }
    }
}
