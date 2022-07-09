package io.github.karlatemp.unsafeaccessor;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.ProtectionDomain;

@SuppressWarnings({"all"})
class SunMiscUnsafe extends io.github.karlatemp.unsafeaccessor.Unsafe {
    protected final Unsafe theUnsafe = initUnsafe();

    private static Unsafe initUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
    }

    @Override
    public Object getOriginalUnsafe() {
        return theUnsafe;
    }

    @Override
    public long objectFieldOffset(Class<?> c, String name) {
        try {
            return theUnsafe.objectFieldOffset(c.getDeclaredField(name));
        } catch (NoSuchFieldException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public Object getReference(Object o, long offset) {
        return theUnsafe.getObject(o, offset);
    }

    @Override
    public Object getReferenceAcquire(Object o, long offset) {
        return theUnsafe.getObject(o, offset);
    }

    @Override
    public boolean getBooleanAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getByteAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getShortAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getCharAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getIntAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getFloatAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLongAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getDoubleAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getReferenceOpaque(Object o, long offset) {
        return theUnsafe.getObject(o, offset);
    }

    @Override
    public boolean getBooleanOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getByteOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getShortOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getCharOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getIntOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getFloatOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLongOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getDoubleOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putReference(Object o, long offset, Object x) {
        theUnsafe.putObject(o, offset, x);
    }

    @Override
    public void putReferenceOpaque(Object o, long offset, Object x) {
        theUnsafe.putObject(o, offset, x);
    }

    @Override
    public void putBooleanOpaque(Object o, long offset, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putByteOpaque(Object o, long offset, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putShortOpaque(Object o, long offset, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putCharOpaque(Object o, long offset, char x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putIntOpaque(Object o, long offset, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putFloatOpaque(Object o, long offset, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putLongOpaque(Object o, long offset, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putDoubleOpaque(Object o, long offset, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putReferenceRelease(Object o, long offset, Object x) {
        theUnsafe.putObject(o, offset, x);
    }

    @Override
    public void putBooleanRelease(Object o, long offset, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putByteRelease(Object o, long offset, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putShortRelease(Object o, long offset, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putCharRelease(Object o, long offset, char x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putIntRelease(Object o, long offset, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putFloatRelease(Object o, long offset, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putLongRelease(Object o, long offset, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putDoubleRelease(Object o, long offset, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putReferenceVolatile(Object o, long offset, Object x) {
        theUnsafe.putObjectVolatile(o, offset, x);
    }

    @Override
    public int getInt(Object o, long offset) {
        return theUnsafe.getInt(o, offset);
    }

    @Override
    public void putInt(Object o, long offset, int x) {
        theUnsafe.putInt(o, offset, x);
    }

    @Override
    public Object getObject(Object o, long offset) {
        return theUnsafe.getObject(o, offset);
    }

    @Override
    public void putObject(Object o, long offset, Object x) {
        theUnsafe.putObject(o, offset, x);
    }

    @Override
    public boolean getBoolean(Object o, long offset) {
        return theUnsafe.getBoolean(o, offset);
    }

    @Override
    public void putBoolean(Object o, long offset, boolean x) {
        theUnsafe.putBoolean(o, offset, x);
    }

    @Override
    public byte getByte(Object o, long offset) {
        return theUnsafe.getByte(o, offset);
    }

    @Override
    public void putByte(Object o, long offset, byte x) {
        theUnsafe.putByte(o, offset, x);
    }

    @Override
    public short getShort(Object o, long offset) {
        return theUnsafe.getShort(o, offset);
    }

    @Override
    public void putShort(Object o, long offset, short x) {
        theUnsafe.putShort(o, offset, x);
    }

    @Override
    public char getChar(Object o, long offset) {
        return theUnsafe.getChar(o, offset);
    }

    @Override
    public void putChar(Object o, long offset, char x) {
        theUnsafe.putChar(o, offset, x);
    }

    @Override
    public long getLong(Object o, long offset) {
        return theUnsafe.getLong(o, offset);
    }

    @Override
    public void putLong(Object o, long offset, long x) {
        theUnsafe.putLong(o, offset, x);
    }

    @Override
    public float getFloat(Object o, long offset) {
        return theUnsafe.getFloat(o, offset);
    }

    @Override
    public void putFloat(Object o, long offset, float x) {
        theUnsafe.putFloat(o, offset, x);
    }

    @Override
    public double getDouble(Object o, long offset) {
        return theUnsafe.getDouble(o, offset);
    }

    @Override
    public void putDouble(Object o, long offset, double x) {
        theUnsafe.putDouble(o, offset, x);
    }

    @Override
    public Object getUncompressedObject(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getByte(long address) {
        return theUnsafe.getByte(address);
    }

    @Override
    public void putByte(long address, byte x) {
        theUnsafe.putByte(address, x);
    }

    @Override
    public short getShort(long address) {
        return theUnsafe.getShort(address);
    }

    @Override
    public void putShort(long address, short x) {
        theUnsafe.putShort(address, x);
    }

    @Override
    public char getChar(long address) {
        return theUnsafe.getChar(address);
    }

    @Override
    public void putChar(long address, char x) {
        theUnsafe.putChar(address, x);
    }

    @Override
    public int getInt(long address) {
        return theUnsafe.getInt(address);
    }

    @Override
    public void putInt(long address, int x) {
        theUnsafe.putInt(address, x);
    }

    @Override
    public long getLong(long address) {
        return theUnsafe.getLong(address);
    }

    @Override
    public void putLong(long address, long x) {
        theUnsafe.putLong(address, x);
    }

    @Override
    public float getFloat(long address) {
        return theUnsafe.getFloat(address);
    }

    @Override
    public void putFloat(long address, float x) {
        theUnsafe.putFloat(address, x);
    }

    @Override
    public double getDouble(long address) {
        return theUnsafe.getDouble(address);
    }

    @Override
    public void putDouble(long address, double x) {
        theUnsafe.putDouble(address, x);
    }

    @Override
    public long getAddress(long address) {
        return theUnsafe.getAddress(address);
    }

    @Override
    public void putAddress(long address, long x) {
        theUnsafe.putAddress(address, x);
    }

    @Override
    public long allocateMemory(long bytes) {
        return theUnsafe.allocateMemory(bytes);
    }

    @Override
    public long reallocateMemory(long address, long bytes) {
        return theUnsafe.reallocateMemory(address, bytes);
    }

    @Override
    public void setMemory(Object o, long offset, long bytes, byte value) {
        theUnsafe.setMemory(o, offset, bytes, value);
    }

    @Override
    public void setMemory(long address, long bytes, byte value) {
        theUnsafe.setMemory(address, bytes, value);
    }

    @Override
    public void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes) {
        theUnsafe.copyMemory(srcBase, srcOffset, destBase, destOffset, bytes);
    }

    @Override
    public void copyMemory(long srcAddress, long destAddress, long bytes) {
        theUnsafe.copyMemory(srcAddress, destAddress, bytes);
    }

    @Override
    public void copySwapMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes, long elemSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void copySwapMemory(long srcAddress, long destAddress, long bytes, long elemSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void freeMemory(long address) {
        theUnsafe.freeMemory(address);
    }

    @Override
    public long objectFieldOffset(Field f) {
        return theUnsafe.objectFieldOffset(f);
    }

    @Override
    public long staticFieldOffset(Field f) {
        return theUnsafe.staticFieldOffset(f);
    }

    @Override
    public Object staticFieldBase(Field f) {
        return theUnsafe.staticFieldBase(f);
    }

    @Override
    public boolean shouldBeInitialized(Class<?> c) {
        return theUnsafe.shouldBeInitialized(c);
    }

    @Override
    public void ensureClassInitialized(Class<?> c) {
        theUnsafe.ensureClassInitialized(c);
    }

    @Override
    public int arrayBaseOffset(Class<?> arrayClass) {
        return theUnsafe.arrayBaseOffset(arrayClass);
    }

    @Override
    public int arrayIndexScale(Class<?> arrayClass) {
        return theUnsafe.arrayIndexScale(arrayClass);
    }

    @Override
    public int addressSize() {
        return theUnsafe.addressSize();
    }

    @Override
    public int pageSize() {
        return theUnsafe.pageSize();
    }

    @Override
    public Class<?> defineClass(String name, byte[] b, int off, int len, ClassLoader loader, ProtectionDomain protectionDomain) {
        throw new AbstractMethodError();
    }

    @Override
    public Class<?> defineClass0(String name, byte[] b, int off, int len, ClassLoader loader, ProtectionDomain protectionDomain) {
        throw new AbstractMethodError();
    }

    @Override
    public Class<?> defineAnonymousClass(Class<?> hostClass, byte[] data, Object[] cpPatches) {
        return theUnsafe.defineAnonymousClass(hostClass, data, cpPatches);
    }

    @Override
    public Object allocateInstance(Class<?> cls) throws InstantiationException {
        return theUnsafe.allocateInstance(cls);
    }

    @Override
    public Object allocateUninitializedArray(Class<?> componentType, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void throwException(Throwable ee) {
        theUnsafe.throwException(ee);
    }

    @Override
    public boolean compareAndSetReference(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object compareAndExchangeReference(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object compareAndExchangeReferenceAcquire(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object compareAndExchangeReferenceRelease(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetReferencePlain(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetReferenceAcquire(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetReferenceRelease(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetReference(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndSetInt(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareAndExchangeInt(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareAndExchangeIntAcquire(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareAndExchangeIntRelease(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetIntPlain(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetIntAcquire(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetIntRelease(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetInt(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte compareAndExchangeByte(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndSetByte(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetByte(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetByteAcquire(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetByteRelease(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetBytePlain(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte compareAndExchangeByteAcquire(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte compareAndExchangeByteRelease(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short compareAndExchangeShort(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndSetShort(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetShort(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetShortAcquire(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetShortRelease(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetShortPlain(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short compareAndExchangeShortAcquire(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short compareAndExchangeShortRelease(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndSetChar(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char compareAndExchangeChar(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char compareAndExchangeCharAcquire(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char compareAndExchangeCharRelease(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetChar(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetCharAcquire(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetCharRelease(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetCharPlain(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndSetBoolean(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndExchangeBoolean(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndExchangeBooleanAcquire(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndExchangeBooleanRelease(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetBoolean(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetBooleanAcquire(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetBooleanRelease(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetBooleanPlain(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndSetFloat(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float compareAndExchangeFloat(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float compareAndExchangeFloatAcquire(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float compareAndExchangeFloatRelease(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetFloatPlain(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetFloatAcquire(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetFloatRelease(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetFloat(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndSetDouble(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double compareAndExchangeDouble(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double compareAndExchangeDoubleAcquire(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double compareAndExchangeDoubleRelease(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetDoublePlain(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetDoubleAcquire(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetDoubleRelease(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetDouble(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndSetLong(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long compareAndExchangeLong(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long compareAndExchangeLongAcquire(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long compareAndExchangeLongRelease(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetLongPlain(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetLongAcquire(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetLongRelease(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetLong(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getReferenceVolatile(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    public boolean compareAndSwapObject(Object o, long offset, Object expected, Object x) {
        return theUnsafe.compareAndSwapObject(o, offset, expected, x);
    }

    public boolean compareAndSwapInt(Object o, long offset, int expected, int x) {
        return theUnsafe.compareAndSwapInt(o, offset, expected, x);
    }

    public boolean compareAndSwapLong(Object o, long offset, long expected, long x) {
        return theUnsafe.compareAndSwapLong(o, offset, expected, x);
    }

    @Override
    public Object getObjectVolatile(Object o, long offset) {
        return theUnsafe.getObjectVolatile(o, offset);
    }

    @Override
    public Object getObjectAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getObjectOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putObjectVolatile(Object o, long offset, Object x) {
        theUnsafe.putObjectVolatile(o, offset, x);
    }

    @Override
    public void putObjectOpaque(Object o, long offset, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putObjectRelease(Object o, long offset, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAndSetObject(Object o, long offset, Object newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAndSetObjectAcquire(Object o, long offset, Object newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAndSetObjectRelease(Object o, long offset, Object newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean compareAndSetObject(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object compareAndExchangeObject(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object compareAndExchangeObjectAcquire(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object compareAndExchangeObjectRelease(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetObject(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetObjectAcquire(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetObjectPlain(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean weakCompareAndSetObjectRelease(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getIntVolatile(Object o, long offset) {
        return theUnsafe.getIntVolatile(o, offset);
    }

    @Override
    public void putIntVolatile(Object o, long offset, int x) {
        theUnsafe.putIntVolatile(o, offset, x);
    }

    @Override
    public boolean getBooleanVolatile(Object o, long offset) {
        return theUnsafe.getBooleanVolatile(o, offset);
    }

    @Override
    public void putBooleanVolatile(Object o, long offset, boolean x) {
        theUnsafe.putBooleanVolatile(o, offset, x);
    }

    @Override
    public byte getByteVolatile(Object o, long offset) {
        return theUnsafe.getByteVolatile(o, offset);
    }

    @Override
    public void putByteVolatile(Object o, long offset, byte x) {
        theUnsafe.putByteVolatile(o, offset, x);
    }

    @Override
    public short getShortVolatile(Object o, long offset) {
        return theUnsafe.getShortVolatile(o, offset);
    }

    @Override
    public void putShortVolatile(Object o, long offset, short x) {
        theUnsafe.putShortVolatile(o, offset, x);
    }

    @Override
    public char getCharVolatile(Object o, long offset) {
        return theUnsafe.getCharVolatile(o, offset);
    }

    @Override
    public void putCharVolatile(Object o, long offset, char x) {
        theUnsafe.putCharVolatile(o, offset, x);
    }

    @Override
    public long getLongVolatile(Object o, long offset) {
        return theUnsafe.getLongVolatile(o, offset);
    }

    @Override
    public void putLongVolatile(Object o, long offset, long x) {
        theUnsafe.putLongVolatile(o, offset, x);
    }

    @Override
    public float getFloatVolatile(Object o, long offset) {
        return theUnsafe.getFloatVolatile(o, offset);
    }

    @Override
    public void putFloatVolatile(Object o, long offset, float x) {
        theUnsafe.putFloatVolatile(o, offset, x);
    }

    @Override
    public double getDoubleVolatile(Object o, long offset) {
        return theUnsafe.getDoubleVolatile(o, offset);
    }

    @Override
    public void putDoubleVolatile(Object o, long offset, double x) {
        theUnsafe.putDoubleVolatile(o, offset, x);
    }

    public void putOrderedObject(Object o, long offset, Object x) {
        theUnsafe.putOrderedObject(o, offset, x);
    }

    public void putOrderedInt(Object o, long offset, int x) {
        theUnsafe.putOrderedInt(o, offset, x);
    }

    public void putOrderedLong(Object o, long offset, long x) {
        theUnsafe.putOrderedLong(o, offset, x);
    }

    @Override
    public void unpark(Object thread) {
        theUnsafe.unpark(thread);
    }

    @Override
    public void park(boolean isAbsolute, long time) {
        theUnsafe.park(isAbsolute, time);
    }

    @Override
    public int getLoadAverage(double[] loadavg, int nelems) {
        return theUnsafe.getLoadAverage(loadavg, nelems);
    }

    @Override
    public int getAndAddInt(Object o, long offset, int delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndAddIntRelease(Object o, long offset, int delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndAddIntAcquire(Object o, long offset, int delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndAddLong(Object o, long offset, long delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndAddLongRelease(Object o, long offset, long delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndAddLongAcquire(Object o, long offset, long delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndAddByte(Object o, long offset, byte delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndAddByteRelease(Object o, long offset, byte delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndAddByteAcquire(Object o, long offset, byte delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndAddShort(Object o, long offset, short delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndAddShortRelease(Object o, long offset, short delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndAddShortAcquire(Object o, long offset, short delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndAddChar(Object o, long offset, char delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndAddCharRelease(Object o, long offset, char delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndAddCharAcquire(Object o, long offset, char delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getAndAddFloat(Object o, long offset, float delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getAndAddFloatRelease(Object o, long offset, float delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getAndAddFloatAcquire(Object o, long offset, float delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getAndAddDouble(Object o, long offset, double delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getAndAddDoubleRelease(Object o, long offset, double delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getAndAddDoubleAcquire(Object o, long offset, double delta) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndSetInt(Object o, long offset, int newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndSetIntRelease(Object o, long offset, int newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndSetIntAcquire(Object o, long offset, int newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndSetLong(Object o, long offset, long newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndSetLongRelease(Object o, long offset, long newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndSetLongAcquire(Object o, long offset, long newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAndSetReference(Object o, long offset, Object newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAndSetReferenceRelease(Object o, long offset, Object newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getAndSetReferenceAcquire(Object o, long offset, Object newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndSetByte(Object o, long offset, byte newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndSetByteRelease(Object o, long offset, byte newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndSetByteAcquire(Object o, long offset, byte newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndSetBoolean(Object o, long offset, boolean newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndSetBooleanRelease(Object o, long offset, boolean newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndSetBooleanAcquire(Object o, long offset, boolean newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndSetShort(Object o, long offset, short newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndSetShortRelease(Object o, long offset, short newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndSetShortAcquire(Object o, long offset, short newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndSetChar(Object o, long offset, char newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndSetCharRelease(Object o, long offset, char newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndSetCharAcquire(Object o, long offset, char newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getAndSetFloat(Object o, long offset, float newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getAndSetFloatRelease(Object o, long offset, float newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getAndSetFloatAcquire(Object o, long offset, float newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getAndSetDouble(Object o, long offset, double newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getAndSetDoubleRelease(Object o, long offset, double newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getAndSetDoubleAcquire(Object o, long offset, double newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndBitwiseOrBoolean(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndBitwiseOrBooleanRelease(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndBitwiseOrBooleanAcquire(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndBitwiseAndBoolean(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndBitwiseAndBooleanRelease(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndBitwiseAndBooleanAcquire(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndBitwiseXorBoolean(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndBitwiseXorBooleanRelease(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getAndBitwiseXorBooleanAcquire(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndBitwiseOrByte(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndBitwiseOrByteRelease(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndBitwiseOrByteAcquire(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndBitwiseAndByte(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndBitwiseAndByteRelease(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndBitwiseAndByteAcquire(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndBitwiseXorByte(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndBitwiseXorByteRelease(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte getAndBitwiseXorByteAcquire(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndBitwiseOrChar(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndBitwiseOrCharRelease(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndBitwiseOrCharAcquire(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndBitwiseAndChar(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndBitwiseAndCharRelease(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndBitwiseAndCharAcquire(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndBitwiseXorChar(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndBitwiseXorCharRelease(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public char getAndBitwiseXorCharAcquire(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndBitwiseOrShort(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndBitwiseOrShortRelease(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndBitwiseOrShortAcquire(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndBitwiseAndShort(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndBitwiseAndShortRelease(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndBitwiseAndShortAcquire(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndBitwiseXorShort(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndBitwiseXorShortRelease(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAndBitwiseXorShortAcquire(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndBitwiseOrInt(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndBitwiseOrIntRelease(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndBitwiseOrIntAcquire(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndBitwiseAndInt(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndBitwiseAndIntRelease(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndBitwiseAndIntAcquire(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndBitwiseXorInt(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndBitwiseXorIntRelease(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAndBitwiseXorIntAcquire(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndBitwiseOrLong(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndBitwiseOrLongRelease(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndBitwiseOrLongAcquire(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndBitwiseAndLong(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndBitwiseAndLongRelease(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndBitwiseAndLongAcquire(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndBitwiseXorLong(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndBitwiseXorLongRelease(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAndBitwiseXorLongAcquire(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadFence() {
        theUnsafe.loadFence();
    }

    @Override
    public void storeFence() {
        theUnsafe.storeFence();
    }

    @Override
    public void fullFence() {
        theUnsafe.fullFence();
    }

    @Override
    public void loadLoadFence() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void storeStoreFence() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBigEndian() {
        return BIG_ENDIAN;
    }

    private static final boolean BIG_ENDIAN = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;

    @Override
    public boolean unalignedAccess() {
        return false;
    }

    // region unaligned

    private static int pickPos(int top, int pos) {
        return BIG_ENDIAN ? top - pos : pos;
    }

    // These methods construct integers from bytes.  The byte ordering
    // is the native endianness of this platform.
    private static long makeLong(byte i0, byte i1, byte i2, byte i3, byte i4, byte i5, byte i6, byte i7) {
        return ((toUnsignedLong(i0) << pickPos(56, 0))
                | (toUnsignedLong(i1) << pickPos(56, 8))
                | (toUnsignedLong(i2) << pickPos(56, 16))
                | (toUnsignedLong(i3) << pickPos(56, 24))
                | (toUnsignedLong(i4) << pickPos(56, 32))
                | (toUnsignedLong(i5) << pickPos(56, 40))
                | (toUnsignedLong(i6) << pickPos(56, 48))
                | (toUnsignedLong(i7) << pickPos(56, 56)));
    }

    private static long makeLong(short i0, short i1, short i2, short i3) {
        return ((toUnsignedLong(i0) << pickPos(48, 0))
                | (toUnsignedLong(i1) << pickPos(48, 16))
                | (toUnsignedLong(i2) << pickPos(48, 32))
                | (toUnsignedLong(i3) << pickPos(48, 48)));
    }

    private static long makeLong(int i0, int i1) {
        return (toUnsignedLong(i0) << pickPos(32, 0))
                | (toUnsignedLong(i1) << pickPos(32, 32));
    }

    private static int makeInt(short i0, short i1) {
        return (toUnsignedInt(i0) << pickPos(16, 0))
                | (toUnsignedInt(i1) << pickPos(16, 16));
    }

    private static int makeInt(byte i0, byte i1, byte i2, byte i3) {
        return ((toUnsignedInt(i0) << pickPos(24, 0))
                | (toUnsignedInt(i1) << pickPos(24, 8))
                | (toUnsignedInt(i2) << pickPos(24, 16))
                | (toUnsignedInt(i3) << pickPos(24, 24)));
    }

    private static short makeShort(byte i0, byte i1) {
        return (short) ((toUnsignedInt(i0) << pickPos(8, 0))
                | (toUnsignedInt(i1) << pickPos(8, 8)));
    }

    private static byte pick(byte le, byte be) {
        return BIG_ENDIAN ? be : le;
    }

    private static short pick(short le, short be) {
        return BIG_ENDIAN ? be : le;
    }

    private static int pick(int le, int be) {
        return BIG_ENDIAN ? be : le;
    }

    // These methods write integers to memory from smaller parts
    // provided by their caller.  The ordering in which these parts
    // are written is the native endianness of this platform.
    private void putLongParts(Object o, long offset, byte i0, byte i1, byte i2, byte i3, byte i4, byte i5, byte i6, byte i7) {
        putByte(o, offset + 0, pick(i0, i7));
        putByte(o, offset + 1, pick(i1, i6));
        putByte(o, offset + 2, pick(i2, i5));
        putByte(o, offset + 3, pick(i3, i4));
        putByte(o, offset + 4, pick(i4, i3));
        putByte(o, offset + 5, pick(i5, i2));
        putByte(o, offset + 6, pick(i6, i1));
        putByte(o, offset + 7, pick(i7, i0));
    }

    private void putLongParts(Object o, long offset, short i0, short i1, short i2, short i3) {
        putShort(o, offset + 0, pick(i0, i3));
        putShort(o, offset + 2, pick(i1, i2));
        putShort(o, offset + 4, pick(i2, i1));
        putShort(o, offset + 6, pick(i3, i0));
    }

    private void putLongParts(Object o, long offset, int i0, int i1) {
        putInt(o, offset + 0, pick(i0, i1));
        putInt(o, offset + 4, pick(i1, i0));
    }

    private void putIntParts(Object o, long offset, short i0, short i1) {
        putShort(o, offset + 0, pick(i0, i1));
        putShort(o, offset + 2, pick(i1, i0));
    }

    private void putIntParts(Object o, long offset, byte i0, byte i1, byte i2, byte i3) {
        putByte(o, offset + 0, pick(i0, i3));
        putByte(o, offset + 1, pick(i1, i2));
        putByte(o, offset + 2, pick(i2, i1));
        putByte(o, offset + 3, pick(i3, i0));
    }

    private void putShortParts(Object o, long offset, byte i0, byte i1) {
        putByte(o, offset + 0, pick(i0, i1));
        putByte(o, offset + 1, pick(i1, i0));
    }

    // Zero-extend an integer
    private static int toUnsignedInt(byte n) {
        return n & 0xff;
    }

    private static int toUnsignedInt(short n) {
        return n & 0xffff;
    }

    private static long toUnsignedLong(byte n) {
        return n & 0xffl;
    }

    private static long toUnsignedLong(short n) {
        return n & 0xffffl;
    }

    private static long toUnsignedLong(int n) {
        return n & 0xffffffffl;
    }

    // Maybe byte-reverse an integer
    private static char convEndian(boolean big, char n) {
        return big == BIG_ENDIAN ? n : Character.reverseBytes(n);
    }

    private static short convEndian(boolean big, short n) {
        return big == BIG_ENDIAN ? n : Short.reverseBytes(n);
    }

    private static int convEndian(boolean big, int n) {
        return big == BIG_ENDIAN ? n : Integer.reverseBytes(n);
    }

    private static long convEndian(boolean big, long n) {
        return big == BIG_ENDIAN ? n : Long.reverseBytes(n);
    }

    public final long getLongUnaligned(Object o, long offset) {
        if ((offset & 7) == 0) {
            return getLong(o, offset);
        } else if ((offset & 3) == 0) {
            return makeLong(getInt(o, offset),
                    getInt(o, offset + 4));
        } else if ((offset & 1) == 0) {
            return makeLong(getShort(o, offset),
                    getShort(o, offset + 2),
                    getShort(o, offset + 4),
                    getShort(o, offset + 6));
        } else {
            return makeLong(getByte(o, offset),
                    getByte(o, offset + 1),
                    getByte(o, offset + 2),
                    getByte(o, offset + 3),
                    getByte(o, offset + 4),
                    getByte(o, offset + 5),
                    getByte(o, offset + 6),
                    getByte(o, offset + 7));
        }
    }

    public final long getLongUnaligned(Object o, long offset, boolean bigEndian) {
        return convEndian(bigEndian, getLongUnaligned(o, offset));
    }

    public final int getIntUnaligned(Object o, long offset) {
        if ((offset & 3) == 0) {
            return getInt(o, offset);
        } else if ((offset & 1) == 0) {
            return makeInt(getShort(o, offset),
                    getShort(o, offset + 2));
        } else {
            return makeInt(getByte(o, offset),
                    getByte(o, offset + 1),
                    getByte(o, offset + 2),
                    getByte(o, offset + 3));
        }
    }

    public final int getIntUnaligned(Object o, long offset, boolean bigEndian) {
        return convEndian(bigEndian, getIntUnaligned(o, offset));
    }

    public final short getShortUnaligned(Object o, long offset) {
        if ((offset & 1) == 0) {
            return getShort(o, offset);
        } else {
            return makeShort(getByte(o, offset),
                    getByte(o, offset + 1));
        }
    }

    public final short getShortUnaligned(Object o, long offset, boolean bigEndian) {
        return convEndian(bigEndian, getShortUnaligned(o, offset));
    }

    public final char getCharUnaligned(Object o, long offset) {
        if ((offset & 1) == 0) {
            return getChar(o, offset);
        } else {
            return (char) makeShort(getByte(o, offset),
                    getByte(o, offset + 1));
        }
    }

    public final char getCharUnaligned(Object o, long offset, boolean bigEndian) {
        return convEndian(bigEndian, getCharUnaligned(o, offset));
    }

    public final void putLongUnaligned(Object o, long offset, long x) {
        if ((offset & 7) == 0) {
            putLong(o, offset, x);
        } else if ((offset & 3) == 0) {
            putLongParts(o, offset,
                    (int) (x >> 0),
                    (int) (x >>> 32));
        } else if ((offset & 1) == 0) {
            putLongParts(o, offset,
                    (short) (x >>> 0),
                    (short) (x >>> 16),
                    (short) (x >>> 32),
                    (short) (x >>> 48));
        } else {
            putLongParts(o, offset,
                    (byte) (x >>> 0),
                    (byte) (x >>> 8),
                    (byte) (x >>> 16),
                    (byte) (x >>> 24),
                    (byte) (x >>> 32),
                    (byte) (x >>> 40),
                    (byte) (x >>> 48),
                    (byte) (x >>> 56));
        }
    }

    public final void putLongUnaligned(Object o, long offset, long x, boolean bigEndian) {
        putLongUnaligned(o, offset, convEndian(bigEndian, x));
    }

    public final void putIntUnaligned(Object o, long offset, int x) {
        if ((offset & 3) == 0) {
            putInt(o, offset, x);
        } else if ((offset & 1) == 0) {
            putIntParts(o, offset,
                    (short) (x >> 0),
                    (short) (x >>> 16));
        } else {
            putIntParts(o, offset,
                    (byte) (x >>> 0),
                    (byte) (x >>> 8),
                    (byte) (x >>> 16),
                    (byte) (x >>> 24));
        }
    }

    public final void putIntUnaligned(Object o, long offset, int x, boolean bigEndian) {
        putIntUnaligned(o, offset, convEndian(bigEndian, x));
    }

    public final void putShortUnaligned(Object o, long offset, short x) {
        if ((offset & 1) == 0) {
            putShort(o, offset, x);
        } else {
            putShortParts(o, offset,
                    (byte) (x >>> 0),
                    (byte) (x >>> 8));
        }
    }

    public final void putShortUnaligned(Object o, long offset, short x, boolean bigEndian) {
        putShortUnaligned(o, offset, convEndian(bigEndian, x));
    }

    public final void putCharUnaligned(Object o, long offset, char x) {
        putShortUnaligned(o, offset, (short) x);
    }

    public final void putCharUnaligned(Object o, long offset, char x, boolean bigEndian) {
        putCharUnaligned(o, offset, convEndian(bigEndian, x));
    }
    // endregion

    @Override
    @Analysis.SkipAnalysis
    @SuppressWarnings("all")
    public void invokeCleaner(ByteBuffer directBuffer) {
        theUnsafe.invokeCleaner(directBuffer);
    }

}
