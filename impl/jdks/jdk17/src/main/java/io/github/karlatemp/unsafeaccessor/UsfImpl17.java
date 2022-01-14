package io.github.karlatemp.unsafeaccessor;

import java.lang.invoke.MethodHandles;

class UsfImpl17 {
    static class DAC extends UsfSpecificMet.DAC {
        DAC() {
        }

        @Override
        public Class<?> defineAnonymousClass(Class<?> hostClass, byte[] data, Object[] cpPatches) {
            try {
                MethodHandles.Lookup lookup = Root.RootLookupHolder.trustedIn(hostClass).in(hostClass);
                if (cpPatches == null) {
                    return lookup.defineHiddenClass(data, false).lookupClass();
                }
                return lookup.defineHiddenClassWithClassData(data, cpPatches, false).lookupClass();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
