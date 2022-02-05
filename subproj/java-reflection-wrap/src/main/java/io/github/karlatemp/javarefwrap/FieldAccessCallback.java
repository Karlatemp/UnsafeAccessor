package io.github.karlatemp.javarefwrap;

public interface FieldAccessCallback {
    /** Matches specification in {@link java.lang.reflect.Field} */
    public Object get(Object obj) throws IllegalArgumentException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public boolean getBoolean(Object obj) throws IllegalArgumentException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public byte getByte(Object obj) throws IllegalArgumentException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public char getChar(Object obj) throws IllegalArgumentException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public short getShort(Object obj) throws IllegalArgumentException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public int getInt(Object obj) throws IllegalArgumentException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public long getLong(Object obj) throws IllegalArgumentException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public float getFloat(Object obj) throws IllegalArgumentException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public double getDouble(Object obj) throws IllegalArgumentException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public void set(Object obj, Object value)
            throws IllegalArgumentException, IllegalAccessException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public void setBoolean(Object obj, boolean z)
            throws IllegalArgumentException, IllegalAccessException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public void setByte(Object obj, byte b)
            throws IllegalArgumentException, IllegalAccessException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public void setChar(Object obj, char c)
            throws IllegalArgumentException, IllegalAccessException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public void setShort(Object obj, short s)
            throws IllegalArgumentException, IllegalAccessException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public void setInt(Object obj, int i)
            throws IllegalArgumentException, IllegalAccessException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public void setLong(Object obj, long l)
            throws IllegalArgumentException, IllegalAccessException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public void setFloat(Object obj, float f)
            throws IllegalArgumentException, IllegalAccessException;

    /** Matches specification in {@link java.lang.reflect.Field} */
    public void setDouble(Object obj, double d)
            throws IllegalArgumentException, IllegalAccessException;
}
