package runtest;

import com.google.gson.Gson;
import io.github.karlatemp.unsafeaccessor.Root;
import org.apache.commons.text.StringEscapeUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GenTestReport {
    static class TaskModule {
        String name, status, timeStringed, exception;
        long time;
        List<TaskModule> subtasks;
        String rawOutput;
    }

    private static String xml(String raw) {
        return StringEscapeUtils.escapeHtml4(raw);
    }

    public static void main(String[] args) throws Throwable {
        File reports = new File("build/run-unit-response");
        File[] files = reports.listFiles();
        if (files == null || files.length == 0) throw new IllegalArgumentException("No report");
        System.setProperty("line.separator", "\n");
        Root.openAccess(System.class.getDeclaredField("lineSeparator"))
                .set(null, "\n");
        Gson gson = new Gson();

        List<TaskModule> reports0 = new ArrayList<>(files.length);

        try (PrintStream pw = new PrintStream("build/test-response.md")) {
            for (File file : files) {
                reports0.add(gson.fromJson(new BufferedReader(
                        new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)
                ), TaskModule.class));
            }

            reports0.sort(Comparator.comparing(it -> it.name));

            pw.println("<h1> Test Report </h1>\n");
            pw.print("<table><tr>");
            pw.print("<th>Gradle Task</th>");
            pw.print("<th>Time</th>");
            pw.print("<th>Status</th>");
            pw.println("</tr>");

            for (TaskModule module : reports0) {
                pw.print("<tr>");
                pw.append("<td>").append(xml(module.name)).print("</td>");
                pw.append("<td>").append(module.timeStringed).print("</td>");
                pw.append("<td>").append(module.status).print("</td>");
                pw.println("</tr>");
            }

            pw.println("</table>");


            for (TaskModule report1 : reports0) {
                genReport(pw, report1);
            }
        }
    }

    private static void printOutput(PrintStream pw, TaskModule module) {
        if (module.exception != null && !module.exception.isEmpty()) {
            if (module.rawOutput == null) {
                module.rawOutput = module.exception;
            } else {
                module.rawOutput = module.exception + "\n" + module.rawOutput;
            }
        }
        if (module.rawOutput == null || module.rawOutput.isEmpty()) return;
        pw.print("<tr><td colspan=\"4\">");
        pw.append("<details>");
        pw.append("<summary> Output of ").append(xml(module.name)).append("</summary>\n\n");
        pw.append("<pre>").append(xml(module.rawOutput)).append("</pre>");
        pw.println("</details></td></tr>");
    }

    private static void genReport(PrintStream pw, TaskModule module) {
        pw.println("<details>");

        pw.append("<summary> Task unit ").append(xml(module.name)).println("</summary>\n");

        pw.append("status: `").append(module.status).println("`\n");
        pw.append("time: ").append(module.timeStringed).println("\n");

        pw.print("\n<table>");
        pw.print("<tr>");
        pw.print("<th>Unit</th>");
        pw.print("<th>Task</th>");
        pw.print("<th>Time</th>");
        pw.print("<th>Status</th>");
        pw.println("</tr>");

        for (TaskModule sub1 : module.subtasks) {
            pw.print("<tr>");
            pw.append("<td>").append(xml(sub1.name)).print("</td>");
            pw.print("<td></td>");
            pw.append("<td>").append(sub1.timeStringed).print("</td>");
            pw.append("<td>").append(sub1.status).print("</td>");
            pw.println("</tr>");

            printOutput(pw, sub1);

            if (sub1.subtasks == null) continue;
            for (TaskModule task : sub1.subtasks) {
                pw.print("<tr>");
                pw.append("<td>").append(xml(sub1.name)).print("</td>");
                pw.append("<td>").append(xml(task.name)).append("</td>");
                pw.append("<td>").append(task.timeStringed).print("</td>");
                pw.append("<td>").append(task.status).print("</td>");
                pw.println("</tr>");

                printOutput(pw, task);
            }
        }

        pw.println("</table></details>");
    }

}
