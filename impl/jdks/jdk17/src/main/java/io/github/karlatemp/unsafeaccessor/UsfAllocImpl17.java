package io.github.karlatemp.unsafeaccessor;

class UsfAllocImpl17 extends UsfAllocImpl9 {
    @SuppressWarnings("JavaReflectionMemberAccess")
    @Override
    UsfAlloc checkSelectedRequirement() throws Exception {
        try {
            Class.forName("jdk.internal.misc.Unsafe").getMethod(
                    "defineAnonymousClass",
                    Class.class,
                    byte[].class,
                    Object[].class
            );
        } catch (NoSuchMethodException ignore) {
            return this;
        }
        return null;
    }
}
