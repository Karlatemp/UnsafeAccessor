package io.github.karlatemp.unsafeaccessor;

class UsfAccessorImpl {
    static Object allocateUnsafe() throws Exception {
        try {
            Class.forName("java.lang.Module");
            Root.Secret.MACCESS = new ModuleAccessImpl.PendingInit();

            try {
                return Open9.open();
            } catch (NoSuchMethodException ignored) {
                return new Impl9Obj();
            }
        } catch (ClassNotFoundException ignored) {
            Root.Secret.MACCESS = new ModuleAccessImpl.Noop();
            return new SunMiscUnsafeImpl();
        }
    }
}
