package runtest;

import io.github.karlatemp.unsafeaccessor.Root;
import io.github.karlatemp.unsafeaccessor.Unsafe;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

class TestTasks {
    static byte[] EMPL = new byte[1024];

    static {
        Arrays.fill(EMPL, (byte) ' ');
    }


    static Collection<String> findTestClasses() throws Exception {
        File src = new File("src");
        Path bfs = src.toPath();
        Collection<String> result = new ArrayList<>(60);
        try (Stream<File> fs = Files
                .walk(bfs)
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(it -> it.getName().endsWith(".java"))
        ) {
            Iterator<File> iterator = fs.iterator();
            while (iterator.hasNext()) {
                File file = iterator.next();
                Path ps = bfs.relativize(file.toPath());
                Path pwx = ps.subpath(2, ps.getNameCount());
                String stringw = pwx.toString()
                        .replace(File.separatorChar, '/');
                stringw = stringw.substring(0, stringw.length() - 5);
                result.add(stringw);
            }
        }
        Collection<String> filteredResult = new ArrayList<>(result.size());
        ClassLoader CL = TestTasks.class.getClassLoader();
        String testTaskName = TestTask.class.getName().replace('.', '/');
        for (String klass : result) {
            try (InputStream rs = CL.getResourceAsStream(klass + ".class")) {
                if (rs == null) continue;
                try (BufferedInputStream bis = new BufferedInputStream(rs)) {
                    ClassNode cn = new ClassNode();
                    new ClassReader(bis)
                            .accept(cn,
                                    ClassReader.SKIP_CODE
                            );
                    List<MethodNode> methods = cn.methods;
                    if (methods == null) continue;
                    mrl:
                    for (MethodNode method : methods) {
                        if (method.visibleAnnotations == null) continue;
                        for (AnnotationNode an : method.visibleAnnotations) {
                            if (an.desc.contains(testTaskName)) {
                                filteredResult.add(klass.replace('/', '.'));
                                break mrl;
                            }
                        }
                    }
                }
            }
        }
        return filteredResult;
    }

    static class AccessibleBAOS extends ByteArrayOutputStream {
        AccessibleBAOS() {
        }

        AccessibleBAOS(int c) {
            super(c);
        }

        byte[] bytes() {
            return this.buf;
        }

        int pointer() {
            return this.count;
        }
    }

    static class DroppedBAOS extends AccessibleBAOS {
        static final DroppedBAOS INSTANCE = new DroppedBAOS();

        @Override
        public void write(int b) {
        }

        @SuppressWarnings("NullableProblems")
        @Override
        public void write(byte[] b) {
        }

        @Override
        public void write(byte[] b, int off, int len) {
        }
    }

    static class RunResponse {
        String name;
        AccessibleBAOS rawOutput;
        Throwable exception;
        Collection<RunResponse> subtasks;
        Status status = Status.NOT_EXECUTED;
        long time;
        String timeStringed;

        boolean echo_forceEchoRawOutputs;
        boolean echo_noRawBar;

        enum Status {
            FAILED, SUCCESSFUL, NOT_EXECUTED;

            @SuppressWarnings("OptionalGetWithoutIsPresent")
            static int MAX_WIDTH = Stream.of(values()).max(
                    Comparator.comparingInt(it -> it.name().length())
            ).get().name().length();
        }

        void begin() {
            time = System.currentTimeMillis();
        }

        void complete() {
            time = System.currentTimeMillis() - time;
        }
    }

    static void echo(int size, PrintStream ps) {
        ps.flush();
        ps.write(EMPL, 0, size);
        ps.flush();
    }

    static void prefix(int depth, PrintStream out) {
        echo(depth * 4, out);
    }

    static int lineSize(RunResponse response, int depth) {
        int current = response.name.length() + (depth * 4);

        if (response.subtasks == null) return current;
        depth++;
        for (RunResponse subtask : response.subtasks) {
            current = Math.max(current, lineSize(subtask, depth));
        }
        return current;
    }

    static int timedSize(RunResponse response) {
        response.timeStringed = Duration.ofMillis(response.time).toString();

        if (response.subtasks == null) return response.timeStringed.length();
        int t = response.timeStringed.length();
        for (RunResponse task : response.subtasks) {
            t = Math.max(t, timedSize(task));
        }
        return t;
    }

    static class DumpOptions {
        boolean noRawOutput;
        boolean noException;
        boolean throwExceptionOnFailure;
        boolean echoExceptionAlways;
        boolean echoOutputAlways;
    }

    static void dump(RunResponse response, PrintStream out, DumpOptions options) {
        int t_width = timedSize(response);
        int size = lineSize(response, 0) + 5 + RunResponse.Status.MAX_WIDTH + t_width;
        dump(response, out, size, t_width, 0, options);

        if (options.throwExceptionOnFailure && response.status == RunResponse.Status.FAILED) {
            Throwable error = response.exception;
            if (error == null) error = new RuntimeException();
            if (response.subtasks != null) {
                for (RunResponse subtask : response.subtasks) omitExceptions(subtask, error);
            }
            Unsafe.getUnsafe().throwException(error);
        }
    }

    static void omitExceptions(RunResponse response, Throwable target) {
        if (response.exception != null)
            target.addSuppressed(response.exception);
        if (response.subtasks == null) return;
        for (RunResponse subtask : response.subtasks)
            omitExceptions(subtask, target);
    }

    static void dump(RunResponse response, PrintStream out, int width, int t_with, int depth, DumpOptions options) {
        prefix(depth, out);
        out.append(response.name);
        String ed = response.timeStringed;
        // System.out.println(ed);
        echo(width - (depth * 4) - response.name.length() - RunResponse.Status.MAX_WIDTH - t_with - 2, out);
        out.append(ed);
        echo(t_with + 2 - ed.length(), out);
        out.println(response.status);

        rawOutput:
        if (response.echo_forceEchoRawOutputs || response.exception != null || (out == System.out || options.echoExceptionAlways)) {
            if (options.noRawOutput || response.rawOutput == null || response.rawOutput.pointer() < 1) {
                if (response.rawOutput == null) break rawOutput;
                if (!response.echo_forceEchoRawOutputs) {
                    break rawOutput;
                }
            }

            int px = depth * 4 + 2;

            if (!response.echo_noRawBar) {
                echo(px, out);
                out.println("==========[ Raw Output ]==========");
            }

            ByteArrayInputStream bis = new ByteArrayInputStream(
                    response.rawOutput.bytes(),
                    0,
                    response.rawOutput.pointer()
            );
            try (Scanner scanner = new Scanner(new InputStreamReader(bis))) {
                while (scanner.hasNextLine()) {
                    echo(px, out);
                    out.println(scanner.nextLine());
                }
            }
            if (!response.echo_noRawBar) {
                echo(px, out);
                out.println("==================================");
            }
        }

        exception:
        if (response.exception != null && !options.noException) {

            checkingRules:
            {
                if (options.echoExceptionAlways) break checkingRules;
                if (out == System.out) break checkingRules;
                if (response.status == RunResponse.Status.NOT_EXECUTED) break exception;
            }

            StringWriter sw = new StringWriter();
            try (PrintWriter pw = new PrintWriter(sw)) {
                response.exception.printStackTrace(pw);
            }
            try (Scanner scanner = new Scanner(sw.toString())) {
                while (scanner.hasNextLine()) {
                    echo(depth * 4 + 2, out);
                    out.println(scanner.nextLine());
                }
            }
        }

        if (response.subtasks != null) {
            int d1 = depth + 1;
            for (RunResponse subtask : response.subtasks) {
                dump(subtask, out, width, t_with, d1, options);
            }
        }

    }

    static void runTests(Collection<String> tasks) throws Throwable {
        PrintStream
                standardOutput = System.out,
                standardError = System.err;

        RunResponse root = new RunResponse();
        boolean failed = false;
        root.name = "<Unsafe Accessor Testing Unit>";
        root.subtasks = new ArrayList<>(tasks.size() + 1);
        root.begin();

        { // ECHO Unsafe options
            RunResponse echo = new RunResponse();
            echo.name = "<System Environment>";
            echo.echo_forceEchoRawOutputs = true;
            echo.echo_noRawBar = true;
            echo.status = RunResponse.Status.SUCCESSFUL;
            root.subtasks.add(echo);

            try (PrintStream ps = new PrintStream(echo.rawOutput = new AccessibleBAOS())) {
                Unsafe usf = Unsafe.getUnsafe();
                ps.println("Gradle Task Unit        : " + RunTestUnit.taskUnitName);
                ps.println("Unsafe instance         : " + usf);
                ps.println("Unsafe instance class   : " + usf.getClass());
                RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
                ps.println("Runtime Spec Name       : " + rt.getSpecName());
                ps.println("Runtime Spec Version    : " + rt.getSpecVersion());
                ps.println("Runtime Spec Vendor     : " + rt.getSpecVendor());

                ps.println("Runtime Vm   Name       : " + rt.getVmName());
                ps.println("Runtime Vm   Version    : " + rt.getVmVersion());
                ps.println("Runtime Vm   Vendor     : " + rt.getVmVendor());

                ps.println("Is Java 9               : " + usf.isJava9());
            }
        }

        for (String task : tasks) {
            RunResponse unitResponse = new RunResponse();
            unitResponse.name = task;
            unitResponse.begin();
            root.subtasks.add(unitResponse);
            Class<?> klass;
            try {
                klass = Class.forName(task);
            } catch (ClassFormatError e) {
                unitResponse.exception = e;
                unitResponse.status = RunResponse.Status.NOT_EXECUTED;
                continue;
            }

            long unitStartTime = System.currentTimeMillis();

            Method[] declaredMethods = klass.getDeclaredMethods();
            List<Method> testUnitTasks = new ArrayList<>(declaredMethods.length);
            for (Method method : declaredMethods) {
                if (method.isAnnotationPresent(TestTask.class)) {
                    testUnitTasks.add(method);
                    Root.openAccess(method);
                }
            }
            unitResponse.subtasks = new ArrayList<>(testUnitTasks.size());
            Object testUnitInstance = null;
            for (Method method : testUnitTasks) {
                if (!Modifier.isStatic(method.getModifiers())) {
                    Constructor<?> mainConstructor = klass.getDeclaredConstructor();
                    Root.openAccess(mainConstructor);
                    testUnitInstance = mainConstructor.newInstance();
                    break;
                }
            }
            boolean unitFailed = false;

            for (Method testUnitMethod : testUnitTasks) {
                RunResponse testUnit = new RunResponse();
                testUnit.name = testUnitMethod.getName();
                unitResponse.subtasks.add(testUnit);
                TestTask annotation = testUnitMethod.getAnnotation(TestTask.class);
                assert annotation != null;
                {
                    String an = annotation.name();
                    if (!an.isEmpty()) {
                        testUnit.name = an;
                    }
                }


                AccessibleBAOS rawOutput = annotation.hiddenOutput() ? DroppedBAOS.INSTANCE : new AccessibleBAOS();
                testUnit.rawOutput = rawOutput;
                testUnit.begin();
                try (PrintStream ps = new PrintStream(rawOutput)) {
                    System.setOut(ps);
                    System.setErr(ps);
                    testUnit.status = RunResponse.Status.FAILED;
                    try {
                        testUnitMethod.invoke(testUnitInstance);
                        testUnit.status = RunResponse.Status.SUCCESSFUL;
                    } catch (InvocationTargetException exception) {
                        testUnit.exception = exception.getTargetException();
                    } catch (Throwable e) {
                        testUnit.exception = e;
                    }
                    unitFailed |= testUnit.status == RunResponse.Status.FAILED;
                }
                testUnit.complete();
                System.setOut(standardOutput);
                System.setOut(standardError);
            }

            unitResponse.complete();
            failed |= unitFailed;
            unitResponse.status = unitFailed ? RunResponse.Status.FAILED : RunResponse.Status.SUCCESSFUL;
        }

        root.complete();
        root.status = failed ? RunResponse.Status.FAILED : RunResponse.Status.SUCCESSFUL;

        System.setOut(standardOutput);
        System.setErr(standardError);

        DumpOptions options = new DumpOptions();
        boolean dump = Boolean.parseBoolean(System.getenv("writeToFile"));
        options.throwExceptionOnFailure = !dump;
        options.noException = false;
        options.noRawOutput = false;
        dump(root, standardOutput, options);

        if (dump) {
            options.throwExceptionOnFailure = true;
            File runInfo = new File("build/run-unit-response.txt");
            try (PrintStream ps = new PrintStream(new BufferedOutputStream(
                    new FileOutputStream(runInfo, true)
            ), false, "UTF8")) {
                ps.println("Gradle task: " + RunTestUnit.taskUnitName);
                ps.println();
                dump(root, ps, options);
                ps.println();
                ps.println("===============================================");
                ps.println();
            }
        }
    }

}
