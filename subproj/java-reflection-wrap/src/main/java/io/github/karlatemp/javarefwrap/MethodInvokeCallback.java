package io.github.karlatemp.javarefwrap;

import java.lang.reflect.InvocationTargetException;

public interface MethodInvokeCallback {
    /**
     * Matches specification in {@link java.lang.reflect.Method}
     */
    public Object invoke(Object obj, Object[] args)
            throws IllegalArgumentException, InvocationTargetException;
}
