package io.github.karlatemp.javarefwrap.fa;

import io.github.karlatemp.javarefwrap.AbsFieldAccessCallback;

public abstract class ByteFieldAccessCallback extends AbsFieldAccessCallback {

    public Object get(Object obj) throws IllegalArgumentException {
        return getByte(obj);
    }

    public boolean getBoolean(Object obj) throws IllegalArgumentException {
        throw newGetBooleanIllegalArgumentException();
    }

    public char getChar(Object obj) throws IllegalArgumentException {
        throw newGetCharIllegalArgumentException();
    }

    public short getShort(Object obj) throws IllegalArgumentException {
        return getByte(obj);
    }

    public int getInt(Object obj) throws IllegalArgumentException {
        return getByte(obj);
    }

    public long getLong(Object obj) throws IllegalArgumentException {
        return getByte(obj);
    }

    public float getFloat(Object obj) throws IllegalArgumentException {
        return getByte(obj);
    }

    public double getDouble(Object obj) throws IllegalArgumentException {
        return getByte(obj);
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
            setByte0(obj, (Byte) value);
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
        ensureObj(obj);
        if (isFinal) {
            throwFinalFieldIllegalAccessException(b);
        }
        setByte0(obj, b);
    }

    protected abstract void setByte0(Object obj, byte b);

    public void setChar(Object obj, char c)
            throws IllegalArgumentException, IllegalAccessException {
        throwSetIllegalArgumentException(c);
    }

    public void setShort(Object obj, short s)
            throws IllegalArgumentException, IllegalAccessException {
        throwSetIllegalArgumentException(s);
    }

    public void setInt(Object obj, int i)
            throws IllegalArgumentException, IllegalAccessException {
        throwSetIllegalArgumentException(i);
    }

    public void setLong(Object obj, long l)
            throws IllegalArgumentException, IllegalAccessException {
        throwSetIllegalArgumentException(l);
    }

    public void setFloat(Object obj, float f)
            throws IllegalArgumentException, IllegalAccessException {
        throwSetIllegalArgumentException(f);
    }

    public void setDouble(Object obj, double d)
            throws IllegalArgumentException, IllegalAccessException {
        throwSetIllegalArgumentException(d);
    }
}
