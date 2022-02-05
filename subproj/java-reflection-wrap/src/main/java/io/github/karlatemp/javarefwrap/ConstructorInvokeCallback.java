package io.github.karlatemp.javarefwrap;

import java.lang.reflect.InvocationTargetException;

public interface ConstructorInvokeCallback {
    /**
     * Matches specification in {@link java.lang.reflect.Constructor}
     */
    public Object newInstance(Object[] args)
            throws InstantiationException,
            IllegalArgumentException,
            InvocationTargetException;
}
