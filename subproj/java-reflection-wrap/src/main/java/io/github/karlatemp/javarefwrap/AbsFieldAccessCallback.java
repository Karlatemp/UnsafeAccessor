package io.github.karlatemp.javarefwrap;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@SuppressWarnings("unused")
public abstract class AbsFieldAccessCallback implements FieldAccessCallback {
    protected Field field;
    protected boolean isFinal;
    protected boolean isStatic;

    protected void attachField(Field field) {
        this.field = field;
        this.isFinal = Modifier.isFinal(field.getModifiers());
        this.isStatic = Modifier.isStatic(field.getModifiers());
    }

    protected void ensureObj(Object o) {
        if (isStatic) return;
        // NOTE: will throw NullPointerException, as specified, if o is null
        if (!field.getDeclaringClass().isAssignableFrom(o.getClass())) {
            throwSetIllegalArgumentException(o);
        }
    }

    private String getQualifiedFieldName() {
        return field.getDeclaringClass().getName() + "." + field.getName();
    }

    protected IllegalArgumentException newGetIllegalArgumentException(String type) {
        return new IllegalArgumentException(
                "Attempt to get " + field.getType().getName() + " field \"" +
                        getQualifiedFieldName() + "\" with illegal data type conversion to " + type
        );
    }

    protected void throwFinalFieldIllegalAccessException(String attemptedType,
                                                         String attemptedValue)
            throws IllegalAccessException {
        throw new IllegalAccessException(getSetMessage(attemptedType, attemptedValue));

    }

    protected void throwFinalFieldIllegalAccessException(Object o) throws IllegalAccessException {
        throwFinalFieldIllegalAccessException(o != null ? o.getClass().getName() : "", "");
    }

    protected void throwFinalFieldIllegalAccessException(boolean z) throws IllegalAccessException {
        throwFinalFieldIllegalAccessException("boolean", Boolean.toString(z));
    }

    protected void throwFinalFieldIllegalAccessException(char b) throws IllegalAccessException {
        throwFinalFieldIllegalAccessException("char", Character.toString(b));
    }

    protected void throwFinalFieldIllegalAccessException(byte b) throws IllegalAccessException {
        throwFinalFieldIllegalAccessException("byte", Byte.toString(b));
    }

    protected void throwFinalFieldIllegalAccessException(short b) throws IllegalAccessException {
        throwFinalFieldIllegalAccessException("short", Short.toString(b));
    }

    protected void throwFinalFieldIllegalAccessException(int i) throws IllegalAccessException {
        throwFinalFieldIllegalAccessException("int", Integer.toString(i));
    }

    protected void throwFinalFieldIllegalAccessException(long i) throws IllegalAccessException {
        throwFinalFieldIllegalAccessException("long", Long.toString(i));
    }

    protected void throwFinalFieldIllegalAccessException(float f) throws IllegalAccessException {
        throwFinalFieldIllegalAccessException("float", Float.toString(f));
    }

    protected void throwFinalFieldIllegalAccessException(double f) throws IllegalAccessException {
        throwFinalFieldIllegalAccessException("double", Double.toString(f));
    }

    protected IllegalArgumentException newGetBooleanIllegalArgumentException() {
        return newGetIllegalArgumentException("boolean");
    }

    protected IllegalArgumentException newGetByteIllegalArgumentException() {
        return newGetIllegalArgumentException("byte");
    }

    protected IllegalArgumentException newGetCharIllegalArgumentException() {
        return newGetIllegalArgumentException("char");
    }

    protected IllegalArgumentException newGetShortIllegalArgumentException() {
        return newGetIllegalArgumentException("short");
    }

    protected IllegalArgumentException newGetIntIllegalArgumentException() {
        return newGetIllegalArgumentException("int");
    }

    protected IllegalArgumentException newGetLongIllegalArgumentException() {
        return newGetIllegalArgumentException("long");
    }

    protected IllegalArgumentException newGetFloatIllegalArgumentException() {
        return newGetIllegalArgumentException("float");
    }

    protected IllegalArgumentException newGetDoubleIllegalArgumentException() {
        return newGetIllegalArgumentException("double");
    }

    protected String getSetMessage(String attemptedType, String attemptedValue) {
        String err = "Can not set";
        if (Modifier.isStatic(field.getModifiers()))
            err += " static";
        if (isFinal)
            err += " final";
        err += " " + field.getType().getName() + " field " + getQualifiedFieldName() + " to ";
        if (!attemptedValue.isEmpty()) {
            err += "(" + attemptedType + ")" + attemptedValue;
        } else {
            if (!attemptedType.isEmpty())
                err += attemptedType;
            else
                err += "null value";
        }
        return err;
    }

    protected void throwSetIllegalArgumentException(String attemptedType,
                                                    String attemptedValue) {
        throw new IllegalArgumentException(getSetMessage(attemptedType, attemptedValue));
    }

    protected void throwSetIllegalArgumentException(Object o) {
        throwSetIllegalArgumentException(o != null ? o.getClass().getName() : "", "");
    }

    protected void throwSetIllegalArgumentException(boolean b) {
        throwSetIllegalArgumentException("boolean", Boolean.toString(b));
    }

    protected void throwSetIllegalArgumentException(byte b) {
        throwSetIllegalArgumentException("byte", Byte.toString(b));
    }

    protected void throwSetIllegalArgumentException(char c) {
        throwSetIllegalArgumentException("char", Character.toString(c));
    }

    protected void throwSetIllegalArgumentException(short s) {
        throwSetIllegalArgumentException("short", Short.toString(s));
    }

    protected void throwSetIllegalArgumentException(int i) {
        throwSetIllegalArgumentException("int", Integer.toString(i));
    }

    protected void throwSetIllegalArgumentException(long l) {
        throwSetIllegalArgumentException("long", Long.toString(l));
    }

    protected void throwSetIllegalArgumentException(float f) {
        throwSetIllegalArgumentException("float", Float.toString(f));
    }

    protected void throwSetIllegalArgumentException(double d) {
        throwSetIllegalArgumentException("double", Double.toString(d));
    }

}
