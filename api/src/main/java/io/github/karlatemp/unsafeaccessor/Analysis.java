package io.github.karlatemp.unsafeaccessor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

interface Analysis {
    @Retention(RetentionPolicy.CLASS)
    @interface SkipAnalysis {
    }
}
