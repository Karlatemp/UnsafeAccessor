package io.github.karlatemp.javarefwrap.fa;

import io.github.karlatemp.javarefwrap.AbsFieldAccessCallback;

public abstract class FloatFieldAccessCallback extends AbsFieldAccessCallback {

    public Object get(Object obj) throws IllegalArgumentException {
        return getFloat(obj);
    }

    public boolean getBoolean(Object obj) throws IllegalArgumentException {
        throw newGetBooleanIllegalArgumentException();
    }

    public byte getByte(Object obj) throws IllegalArgumentException {
        throw newGetByteIllegalArgumentException();
    }

    public char getChar(Object obj) throws IllegalArgumentException {
        throw newGetCharIllegalArgumentException();
    }

    public short getShort(Object obj) throws IllegalArgumentException {
        throw newGetShortIllegalArgumentException();
    }

    public int getInt(Object obj) throws IllegalArgumentException {
        throw newGetIntIllegalArgumentException();
    }

    public long getLong(Object obj) throws IllegalArgumentException {
        throw newGetLongIllegalArgumentException();
    }


    public double getDouble(Object obj) throws IllegalArgumentException {
        return getFloat(obj);
    }

    public void set(Object obj, Object value)
            throws IllegalArgumentException, IllegalAccessException {
        ensureObj(obj);
        if (isFinal) {
            throwFinalFieldIllegalAccessException(value);
        }
        if (value == null) {
            throwSetIllegalArgumentException(value);
        }
        if (value instanceof Byte) {
            setFloat0(obj, (Byte) value);
            return;
        }
        if (value instanceof Short) {
            setFloat0(obj, (Short) value);
            return;
        }
        if (value instanceof Character) {
            setFloat0(obj, (Character) value);
            return;
        }
        if (value instanceof Integer) {
            setFloat0(obj, (Integer) value);
            return;
        }
        if (value instanceof Long) {
            setFloat0(obj, (Long) value);
            return;
        }
        if (value instanceof Float) {
            setFloat0(obj, (Float) value);
            return;
        }
        throwSetIllegalArgumentException(value);
    }

    public void setBoolean(Object obj, boolean z)
            throws IllegalArgumentException, IllegalAccessException {
        throwSetIllegalArgumentException(z);
    }

    public void setByte(Object obj, byte b)
            throws IllegalArgumentException, IllegalAccessException {
        setFloat(obj, b);
    }

    public void setChar(Object obj, char c)
            throws IllegalArgumentException, IllegalAccessException {
        setFloat(obj, c);
    }

    public void setShort(Object obj, short s)
            throws IllegalArgumentException, IllegalAccessException {
        setFloat(obj, s);
    }

    public void setInt(Object obj, int i)
            throws IllegalArgumentException, IllegalAccessException {
        setFloat(obj, i);
    }

    public void setLong(Object obj, long l)
            throws IllegalArgumentException, IllegalAccessException {
        setFloat(obj, l);
    }

    public void setFloat(Object obj, float f)
            throws IllegalArgumentException, IllegalAccessException {
        ensureObj(obj);
        if (isFinal) {
            throwFinalFieldIllegalAccessException(f);
        }
        setFloat0(obj, f);
    }

    protected abstract void setFloat0(Object obj, float f);

    public void setDouble(Object obj, double d) throws IllegalArgumentException, IllegalAccessException {
        throwSetIllegalArgumentException(d);
    }
}
