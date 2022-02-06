package runtest;

import io.github.karlatemp.unsafeaccessor.Root;
import io.github.karlatemp.unsafeaccessor.Unsafe;

public class TestObjectInitializer {
    @SuppressWarnings("InnerClassMayBeStatic")
    class Kcl {
        Kcl() {
            throw new RuntimeException("SB");
        }

        Kcl(Void s) {

        }
    }

    // @TestTask
    void run() throws Exception {
        Unsafe usf = Unsafe.getUnsafe();
        Root.initializeObject(usf.allocateInstance(Object.class));
        Root.initializeObject(usf.allocateInstance(System.class));
        usf.allocateInstance(Kcl.class);
        System.gc();
        Root.initializeObject(usf.allocateInstance(Kcl.class));
        System.gc();
    }
}
