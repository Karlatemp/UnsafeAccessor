package io.github.karlatemp.javarefwrap.fa;

import io.github.karlatemp.javarefwrap.AbsFieldAccessCallback;

public abstract class DoubleFieldAccessCallback extends AbsFieldAccessCallback {

    public Object get(Object obj) throws IllegalArgumentException {
        return getDouble(obj);
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

    public float getFloat(Object obj) throws IllegalArgumentException {
        throw newGetFloatIllegalArgumentException();
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
            setDouble(obj, (Byte) value);
            return;
        }
        if (value instanceof Short) {
            setDouble0(obj, (Short) value);
            return;
        }
        if (value instanceof Character) {
            setDouble0(obj, (Character) value);
            return;
        }
        if (value instanceof Integer) {
            setDouble0(obj, (Integer) value);
            return;
        }
        if (value instanceof Long) {
            setDouble0(obj, (Long) value);
            return;
        }
        if (value instanceof Float) {
            setDouble0(obj, (Float) value);
            return;
        }
        if (value instanceof Double) {
            setDouble0(obj, (Double) value);
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
        setDouble(obj, b);
    }

    public void setChar(Object obj, char c)
            throws IllegalArgumentException, IllegalAccessException {
        setDouble(obj, c);
    }

    public void setShort(Object obj, short s)
            throws IllegalArgumentException, IllegalAccessException {
        setDouble(obj, s);
    }

    public void setInt(Object obj, int i)
            throws IllegalArgumentException, IllegalAccessException {
        setDouble(obj, i);
    }

    public void setLong(Object obj, long l)
            throws IllegalArgumentException, IllegalAccessException {
        setDouble(obj, l);
    }

    public void setFloat(Object obj, float f)
            throws IllegalArgumentException, IllegalAccessException {
        setDouble(obj, f);
    }

    public void setDouble(Object obj, double d)
            throws IllegalArgumentException, IllegalAccessException {
        ensureObj(obj);
        if (isFinal) {
            throwFinalFieldIllegalAccessException(d);
        }
        setDouble0(obj, d);
    }

    protected abstract void setDouble0(Object obj, double d);
}
