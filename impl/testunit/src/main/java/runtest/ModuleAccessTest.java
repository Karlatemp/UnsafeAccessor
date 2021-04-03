package runtest;

public class ModuleAccessTest {
    public static void run() throws Throwable {
        try {
            Class.forName("java.lang.Module");
        } catch (ClassNotFoundException ignore) {
            return;
        }

        Class.forName("runtest.MAT")
                .getMethod("run").invoke(null);
    }
}
