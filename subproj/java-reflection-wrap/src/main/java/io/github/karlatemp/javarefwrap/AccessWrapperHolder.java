package io.github.karlatemp.javarefwrap;

import io.github.karlatemp.unsafeaccessor.BytecodeUtil;
import io.github.karlatemp.unsafeaccessor.ModuleAccess;
import io.github.karlatemp.unsafeaccessor.UnsafeAccess;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;

class AccessWrapperHolder {
    static Class<?> C, M, F;
    static String jRefPkg;
    static MethodHandle HC, HM, HF;

    static byte[] readAll(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        byte[] buffer = new byte[2048];
        while (true) {
            int a = is.read(buffer);
            if (a == -1) break;
            baos.write(buffer, 0, a);
        }
        return baos.toByteArray();
    }

    synchronized static void initialize(UnsafeAccess access) {
        if (C != null) return;
        String placeI = PlaceI.class.getName().replace('.', '/');
        try (
                InputStream constructor = AccessWrapperHolder.class.getResourceAsStream("AccessWrapperHolder$ConstructorI.class");
                InputStream method = AccessWrapperHolder.class.getResourceAsStream("AccessWrapperHolder$MethodI.class");
                InputStream field = AccessWrapperHolder.class.getResourceAsStream("AccessWrapperHolder$FieldI.class");
        ) {
            assert constructor != null;
            assert method != null;
            assert field != null;

            String jrefp;
            try {
                Class.forName("jdk.internal.reflect.MethodAccessor");
                jrefp = "jdk/internal/reflect/";
                jRefPkg = "jdk.internal.reflect.";
            } catch (ClassNotFoundException ignore) {
                jrefp = "sun/reflect/";
                jRefPkg = "sun.reflect.";
            }
            BytecodeUtil.CLoader cl = new BytecodeUtil.CLoader(AccessWrapperHolder.class.getClassLoader());

            ModuleAccess moduleAccess = access.getModuleAccess();
            Object clM = moduleAccess.getUnnamedModule(cl);
            Object jM = moduleAccess.getModule(Object.class);
            moduleAccess.addExports(jM, jRefPkg.substring(0, jRefPkg.length() - 1), clM);

            byte[] code;

            {
                code = readAll(constructor);
                code = BytecodeUtil.replaceC(code, placeI, jrefp + "ConstructorAccessor");
                C = cl.load(code);
            }
            {
                code = readAll(method);
                code = BytecodeUtil.replaceC(code, placeI, jrefp + "MethodAccessor");
                M = cl.load(code);
            }
            {
                code = readAll(field);
                code = BytecodeUtil.replaceC(code, placeI, jrefp + "FieldAccessor");
                F = cl.load(code);
            }

            HC = access.getTrustedIn(C).findConstructor(C, MethodType.methodType(void.class, ConstructorInvokeCallback.class));
            HM = access.getTrustedIn(M).findConstructor(M, MethodType.methodType(void.class, MethodInvokeCallback.class));
            HF = access.getTrustedIn(F).findConstructor(F, MethodType.methodType(void.class, FieldAccessCallback.class));

        } catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }

    private interface PlaceI {
    }

    private static class ConstructorI implements ConstructorInvokeCallback, PlaceI {
        private final ConstructorInvokeCallback delegate;

        ConstructorI(ConstructorInvokeCallback delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object newInstance(Object[] args) throws InstantiationException, IllegalArgumentException, InvocationTargetException {
            return delegate.newInstance(args);
        }
    }

    private static class MethodI implements MethodInvokeCallback, PlaceI {
        private final MethodInvokeCallback delegate;

        MethodI(MethodInvokeCallback delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object obj, Object[] args) throws IllegalArgumentException, InvocationTargetException {
            return delegate.invoke(obj, args);
        }
    }

    private static class FieldI implements FieldAccessCallback, PlaceI {
        private final FieldAccessCallback delegate;

        FieldI(FieldAccessCallback delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object get(Object obj) throws IllegalArgumentException {
            return delegate.get(obj);
        }

        @Override
        public boolean getBoolean(Object obj) throws IllegalArgumentException {
            return delegate.getBoolean(obj);
        }

        @Override
        public byte getByte(Object obj) throws IllegalArgumentException {
            return delegate.getByte(obj);
        }

        @Override
        public char getChar(Object obj) throws IllegalArgumentException {
            return delegate.getChar(obj);
        }

        @Override
        public short getShort(Object obj) throws IllegalArgumentException {
            return delegate.getShort(obj);
        }

        @Override
        public int getInt(Object obj) throws IllegalArgumentException {
            return delegate.getInt(obj);
        }

        @Override
        public long getLong(Object obj) throws IllegalArgumentException {
            return delegate.getLong(obj);
        }

        @Override
        public float getFloat(Object obj) throws IllegalArgumentException {
            return delegate.getFloat(obj);
        }

        @Override
        public double getDouble(Object obj) throws IllegalArgumentException {
            return delegate.getDouble(obj);
        }

        @Override
        public void set(Object obj, Object value) throws IllegalArgumentException, IllegalAccessException {
            delegate.set(obj, value);
        }

        @Override
        public void setBoolean(Object obj, boolean z) throws IllegalArgumentException, IllegalAccessException {
            delegate.setBoolean(obj, z);
        }

        @Override
        public void setByte(Object obj, byte b) throws IllegalArgumentException, IllegalAccessException {
            delegate.setByte(obj, b);
        }

        @Override
        public void setChar(Object obj, char c) throws IllegalArgumentException, IllegalAccessException {
            delegate.setChar(obj, c);
        }

        @Override
        public void setShort(Object obj, short s) throws IllegalArgumentException, IllegalAccessException {
            delegate.setShort(obj, s);
        }

        @Override
        public void setInt(Object obj, int i) throws IllegalArgumentException, IllegalAccessException {
            delegate.setInt(obj, i);
        }

        @Override
        public void setLong(Object obj, long l) throws IllegalArgumentException, IllegalAccessException {
            delegate.setLong(obj, l);
        }

        @Override
        public void setFloat(Object obj, float f) throws IllegalArgumentException, IllegalAccessException {
            delegate.setFloat(obj, f);
        }

        @Override
        public void setDouble(Object obj, double d) throws IllegalArgumentException, IllegalAccessException {
            delegate.setDouble(obj, d);
        }
    }
}
