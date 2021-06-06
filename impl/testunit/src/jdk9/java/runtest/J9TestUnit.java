package runtest;

public class J9TestUnit {
    public static void invoke() throws Throwable {
        try {
            Class.forName("runtest.loct.Loct9")
                    .getMethod("invoke")
                    .invoke(null);
        } catch (ClassNotFoundException ignore) {
        }
    }
}
