package runtest;

import io.github.karlatemp.unsafeaccessor.Root;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.net.URL;
import java.net.URLClassLoader;

public class RootMethodHandleLookupTest {
    private static void privateMet() {
    }

    private static final Object finalField = null;

    public static void run() throws Throwable {
        MethodHandles.Lookup trusted = Root.getTrusted(RootMethodHandleLookupTest.class);
        System.out.println("Access: " + trusted.lookupModes());
        System.out.println("Access: " + trusted);
        trusted.findStatic(RootMethodHandleLookupTest.class, "privateMet", MethodType.methodType(void.class));
        trusted.findStaticGetter(RootMethodHandleLookupTest.class, "finalField", Object.class);
        Root.getTrusted(URLClassLoader.class)
                .findVirtual(URLClassLoader.class, "addURL", MethodType.methodType(void.class, URL.class));
    }
}
