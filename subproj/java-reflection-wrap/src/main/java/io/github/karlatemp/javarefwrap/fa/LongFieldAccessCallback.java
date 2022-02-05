package io.github.karlatemp.javarefwrap.fa;

import io.github.karlatemp.javarefwrap.AbsFieldAccessCallback;

public abstract class LongFieldAccessCallback extends AbsFieldAccessCallback {

    public Object get(Object obj) throws IllegalArgumentException {
        return getLong(obj);
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

    public float getFloat(Object obj) throws IllegalArgumentException {
        return getLong(obj);
    }

    public double getDouble(Object obj) throws IllegalArgumentException {
        return getLong(obj);
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
            setLong0(obj, (Byte) value);
            return;
        }
        if (value instanceof Short) {
            setLong0(obj, (Short) value);
            return;
        }
        if (value instanceof Character) {
            setLong0(obj, (Character) value);
            return;
        }
        if (value instanceof Integer) {
            setLong0(obj, (Integer) value);
            return;
        }
        if (value instanceof Long) {
            setLong0(obj, (Long) value);
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
        setLong(obj, b);
    }

    public void setChar(Object obj, char c)
            throws IllegalArgumentException, IllegalAccessException {
        setLong(obj, c);
    }

    public void setShort(Object obj, short s)
            throws IllegalArgumentException, IllegalAccessException {
        setLong(obj, s);
    }

    public void setInt(Object obj, int i)
            throws IllegalArgumentException, IllegalAccessException {
        setLong(obj, i);
    }

    public void setLong(Object obj, long l)
            throws IllegalArgumentException, IllegalAccessException {
        ensureObj(obj);
        if (isFinal) {
            throwFinalFieldIllegalAccessException(l);
        }
        setLong0(obj, l);
    }

    protected abstract void setLong0(Object obj, long l);

    public void setFloat(Object obj, float f)
            throws IllegalArgumentException, IllegalAccessException {
        throwSetIllegalArgumentException(f);
    }

    public void setDouble(Object obj, double d)
            throws IllegalArgumentException, IllegalAccessException {
        throwSetIllegalArgumentException(d);
    }
}
