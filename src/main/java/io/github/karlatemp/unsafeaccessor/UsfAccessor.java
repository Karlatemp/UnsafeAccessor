package io.github.karlatemp.unsafeaccessor;

public class UsfAccessor {
    protected static Object allocateUnsafe() {
        try {
            Class.forName("java.lang.Module");
            return Open9.open();
        } catch (ClassNotFoundException ignored) {
        }
        return new Impl8();
    }

    public static void main(String[] args) {
        System.out.println(Unsafe.getUnsafe());
    }
}
