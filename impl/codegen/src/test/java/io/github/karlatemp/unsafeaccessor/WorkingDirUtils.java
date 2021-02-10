package io.github.karlatemp.unsafeaccessor;

import java.io.File;
import java.net.URL;

public class WorkingDirUtils {
    public static final File projectDir;

    static {
        URL url = WorkingDirUtils.class.getResource("WorkingDirUtils.class");
        String file = url.getFile();
        System.out.println(file);
        int a = file.indexOf("impl/codegen/build/classes/java/test/io/github/karlatemp/unsafeaccessor");
        String x = file.substring(0, a);
        System.out.println(x);
        File fs = new File(x);
        System.out.println(fs);
        projectDir = fs;
    }
}
