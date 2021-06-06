package runtest;

import java.util.ArrayList;
import java.util.List;

public class RunTestUnit {
    public static String taskUnitName = System.getenv("taskUnitName");

    public static void main(String[] args) throws Throwable {
        List<String> classes = new ArrayList<>(TestTasks.findTestClasses());
        classes.sort(String::compareTo);
        TestTasks.runTests(classes);
    }
}
