package io.github.karlatemp.unsafeaccessor;

import sun.misc.Cleaner;
import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.security.ProtectionDomain;

@SuppressWarnings({"all"})
class Impl8 extends io.github.karlatemp.unsafeaccessor.Unsafe {
    private static final Unsafe usf = openUnsafe();

    private static Unsafe openUnsafe() {
        Class<Unsafe> usf = Unsafe.class;
        try {
            Field theUnsafe = usf.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public int getInt(Object o, long offset) {
        return usf.getInt(o, offset);
    }

    public void putInt(Object o, long offset, int x) {
        usf.putInt(o, offset, x);
    }


    public Object getReference(Object o, long offset) {
        return usf.getObject(o, offset);
    }


    public void putReference(Object o, long offset, Object x) {
        usf.putObject(o, offset, x);
    }

    public Object getObject(Object o, long offset) {
        return usf.getObject(o, offset);
    }

    public void putObject(Object o, long offset, Object x) {
        usf.putObject(o, offset, x);
    }

    public boolean getBoolean(Object o, long offset) {
        return usf.getBoolean(o, offset);
    }

    public void putBoolean(Object o, long offset, boolean x) {
        usf.putBoolean(o, offset, x);
    }

    public byte getByte(Object o, long offset) {
        return usf.getByte(o, offset);
    }

    public void putByte(Object o, long offset, byte x) {
        usf.putByte(o, offset, x);
    }

    public short getShort(Object o, long offset) {
        return usf.getShort(o, offset);
    }

    public void putShort(Object o, long offset, short x) {
        usf.putShort(o, offset, x);
    }

    public char getChar(Object o, long offset) {
        return usf.getChar(o, offset);
    }

    public void putChar(Object o, long offset, char x) {
        usf.putChar(o, offset, x);
    }

    public long getLong(Object o, long offset) {
        return usf.getLong(o, offset);
    }

    public void putLong(Object o, long offset, long x) {
        usf.putLong(o, offset, x);
    }

    public float getFloat(Object o, long offset) {
        return usf.getFloat(o, offset);
    }

    public void putFloat(Object o, long offset, float x) {
        usf.putFloat(o, offset, x);
    }

    public double getDouble(Object o, long offset) {
        return usf.getDouble(o, offset);
    }

    public void putDouble(Object o, long offset, double x) {
        usf.putDouble(o, offset, x);
    }


    public Object getUncompressedObject(long address) {
        throw new UnsupportedOperationException();
    }

    public byte getByte(long address) {
        return usf.getByte(address);
    }

    public void putByte(long address, byte x) {
        usf.putByte(address, x);
    }

    public short getShort(long address) {
        return usf.getShort(address);
    }

    public void putShort(long address, short x) {
        usf.putShort(address, x);
    }

    public char getChar(long address) {
        return usf.getChar(address);
    }

    public void putChar(long address, char x) {
        usf.putChar(address, x);
    }

    public int getInt(long address) {
        return usf.getInt(address);
    }

    public void putInt(long address, int x) {
        usf.putInt(address, x);
    }

    public long getLong(long address) {
        return usf.getLong(address);
    }

    public void putLong(long address, long x) {
        usf.putLong(address, x);
    }

    public float getFloat(long address) {
        return usf.getFloat(address);
    }

    public void putFloat(long address, float x) {
        usf.putFloat(address, x);
    }

    public double getDouble(long address) {
        return usf.getDouble(address);
    }

    public void putDouble(long address, double x) {
        usf.putDouble(address, x);
    }

    public long getAddress(long address) {
        return usf.getAddress(address);
    }

    public void putAddress(long address, long x) {
        usf.putAddress(address, x);
    }

    public long allocateMemory(long bytes) {
        return usf.allocateMemory(bytes);
    }

    public long reallocateMemory(long address, long bytes) {
        return usf.reallocateMemory(address, bytes);
    }

    public void setMemory(Object o, long offset, long bytes, byte value) {
        usf.setMemory(o, offset, bytes, value);
    }

    public void setMemory(long address, long bytes, byte value) {
        usf.setMemory(address, bytes, value);
    }

    public void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes) {
        usf.copyMemory(srcBase, srcOffset, destBase, destOffset, bytes);
    }

    public void copyMemory(long srcAddress, long destAddress, long bytes) {
        usf.copyMemory(srcAddress, destAddress, bytes);
    }


    public void copySwapMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes, long elemSize) {
        throw new UnsupportedOperationException();
    }


    public void copySwapMemory(long srcAddress, long destAddress, long bytes, long elemSize) {
        throw new UnsupportedOperationException();
    }

    public void freeMemory(long address) {
        usf.freeMemory(address);
    }

    public long objectFieldOffset(Field f) {
        return usf.objectFieldOffset(f);
    }


    public long objectFieldOffset(Class<?> c, String name) {
        Field field = null;
        try {
            field = c.getDeclaredField(name);
            return objectFieldOffset(field);
        } catch (NoSuchFieldException e) {
            throw new NoSuchFieldError(name);
        }
    }

    public long staticFieldOffset(Field f) {
        return usf.staticFieldOffset(f);
    }

    public Object staticFieldBase(Field f) {
        return usf.staticFieldBase(f);
    }

    public boolean shouldBeInitialized(Class<?> c) {
        return usf.shouldBeInitialized(c);
    }

    public void ensureClassInitialized(Class<?> c) {
        usf.ensureClassInitialized(c);
    }

    public int arrayBaseOffset(Class<?> arrayClass) {
        return usf.arrayBaseOffset(arrayClass);
    }

    public int arrayIndexScale(Class<?> arrayClass) {
        return usf.arrayIndexScale(arrayClass);
    }

    public int addressSize() {
        return usf.addressSize();
    }

    public int pageSize() {
        return usf.pageSize();
    }


    public Class<?> defineClass(String name, byte[] b, int off, int len, ClassLoader loader, ProtectionDomain protectionDomain) {
        return usf.defineClass(name, b, off, len, loader, protectionDomain);
    }


    public Class<?> defineClass0(String name, byte[] b, int off, int len, ClassLoader loader, ProtectionDomain protectionDomain) {
        return usf.defineClass(name, b, off, len, loader, protectionDomain);
    }

    public Class<?> defineAnonymousClass(Class<?> hostClass, byte[] data, Object[] cpPatches) {
        return usf.defineAnonymousClass(hostClass, data, cpPatches);
    }

    public Object allocateInstance(Class<?> cls) throws InstantiationException {
        return usf.allocateInstance(cls);
    }


    public Object allocateUninitializedArray(Class<?> componentType, int length) {
        return null;
    }

    public void throwException(Throwable ee) {
        usf.throwException(ee);
    }


    public boolean compareAndSetReference(Object o, long offset, Object expected, Object x) {
        return usf.compareAndSwapObject(o, offset, expected, x);
    }


    public Object compareAndExchangeReference(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }


    public Object compareAndExchangeReferenceAcquire(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();

    }


    public Object compareAndExchangeReferenceRelease(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetReferencePlain(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetReferenceAcquire(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetReferenceRelease(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetReference(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();

    }


    public boolean compareAndSetInt(Object o, long offset, int expected, int x) {
        return usf.compareAndSwapInt(o, offset, expected, x);
    }


    public int compareAndExchangeInt(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }


    public int compareAndExchangeIntAcquire(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }


    public int compareAndExchangeIntRelease(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetIntPlain(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetIntAcquire(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetIntRelease(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetInt(Object o, long offset, int expected, int x) {
        throw new UnsupportedOperationException();

    }


    public byte compareAndExchangeByte(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();

    }


    public boolean compareAndSetByte(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetByte(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetByteAcquire(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetByteRelease(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetBytePlain(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();

    }


    public byte compareAndExchangeByteAcquire(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();

    }


    public byte compareAndExchangeByteRelease(Object o, long offset, byte expected, byte x) {
        throw new UnsupportedOperationException();

    }


    public short compareAndExchangeShort(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();

    }


    public boolean compareAndSetShort(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetShort(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetShortAcquire(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetShortRelease(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetShortPlain(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();

    }


    public short compareAndExchangeShortAcquire(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();

    }


    public short compareAndExchangeShortRelease(Object o, long offset, short expected, short x) {
        throw new UnsupportedOperationException();

    }


    public boolean compareAndSetChar(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();

    }


    public char compareAndExchangeChar(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();

    }


    public char compareAndExchangeCharAcquire(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();

    }


    public char compareAndExchangeCharRelease(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetChar(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetCharAcquire(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetCharRelease(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetCharPlain(Object o, long offset, char expected, char x) {
        throw new UnsupportedOperationException();

    }


    public boolean compareAndSetBoolean(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();

    }


    public boolean compareAndExchangeBoolean(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();

    }


    public boolean compareAndExchangeBooleanAcquire(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();

    }


    public boolean compareAndExchangeBooleanRelease(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetBoolean(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetBooleanAcquire(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetBooleanRelease(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();

    }


    public boolean weakCompareAndSetBooleanPlain(Object o, long offset, boolean expected, boolean x) {
        throw new UnsupportedOperationException();

    }


    public boolean compareAndSetFloat(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();

    }


    public float compareAndExchangeFloat(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }


    public float compareAndExchangeFloatAcquire(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }


    public float compareAndExchangeFloatRelease(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetFloatPlain(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetFloatAcquire(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetFloatRelease(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetFloat(Object o, long offset, float expected, float x) {
        throw new UnsupportedOperationException();
    }


    public boolean compareAndSetDouble(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }


    public double compareAndExchangeDouble(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }


    public double compareAndExchangeDoubleAcquire(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }


    public double compareAndExchangeDoubleRelease(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetDoublePlain(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetDoubleAcquire(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetDoubleRelease(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetDouble(Object o, long offset, double expected, double x) {
        throw new UnsupportedOperationException();
    }


    public boolean compareAndSetLong(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }


    public long compareAndExchangeLong(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }


    public long compareAndExchangeLongAcquire(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }


    public long compareAndExchangeLongRelease(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetLongPlain(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetLongAcquire(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetLongRelease(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetLong(Object o, long offset, long expected, long x) {
        throw new UnsupportedOperationException();
    }


    public Object getReferenceVolatile(Object o, long offset) {
        return null;
    }


    public void putReferenceVolatile(Object o, long offset, Object x) {
        throw new UnsupportedOperationException();
    }

    public boolean compareAndSwapObject(Object o, long offset, Object expected, Object x) {
        return usf.compareAndSwapObject(o, offset, expected, x);
    }

    public boolean compareAndSwapInt(Object o, long offset, int expected, int x) {
        return usf.compareAndSwapInt(o, offset, expected, x);
    }

    public boolean compareAndSwapLong(Object o, long offset, long expected, long x) {
        return usf.compareAndSwapLong(o, offset, expected, x);
    }

    public Object getObjectVolatile(Object o, long offset) {
        return usf.getObjectVolatile(o, offset);
    }


    public Object getObjectAcquire(Object o, long offset) {
        return null;
    }


    public Object getObjectOpaque(Object o, long offset) {
        return null;
    }

    public void putObjectVolatile(Object o, long offset, Object x) {
        usf.putObjectVolatile(o, offset, x);
    }


    public void putObjectOpaque(Object o, long offset, Object x) {
        throw new UnsupportedOperationException();
    }


    public void putObjectRelease(Object o, long offset, Object x) {
        throw new UnsupportedOperationException();
    }

    public int getIntVolatile(Object o, long offset) {
        return usf.getIntVolatile(o, offset);
    }

    public void putIntVolatile(Object o, long offset, int x) {
        usf.putIntVolatile(o, offset, x);
    }

    public boolean getBooleanVolatile(Object o, long offset) {
        return usf.getBooleanVolatile(o, offset);
    }

    public void putBooleanVolatile(Object o, long offset, boolean x) {
        usf.putBooleanVolatile(o, offset, x);
    }

    public byte getByteVolatile(Object o, long offset) {
        return usf.getByteVolatile(o, offset);
    }

    public void putByteVolatile(Object o, long offset, byte x) {
        usf.putByteVolatile(o, offset, x);
    }

    public short getShortVolatile(Object o, long offset) {
        return usf.getShortVolatile(o, offset);
    }

    public void putShortVolatile(Object o, long offset, short x) {
        usf.putShortVolatile(o, offset, x);
    }

    public char getCharVolatile(Object o, long offset) {
        return usf.getCharVolatile(o, offset);
    }

    public void putCharVolatile(Object o, long offset, char x) {
        usf.putCharVolatile(o, offset, x);
    }

    public long getLongVolatile(Object o, long offset) {
        return usf.getLongVolatile(o, offset);
    }

    public void putLongVolatile(Object o, long offset, long x) {
        usf.putLongVolatile(o, offset, x);
    }

    public float getFloatVolatile(Object o, long offset) {
        return usf.getFloatVolatile(o, offset);
    }

    public void putFloatVolatile(Object o, long offset, float x) {
        usf.putFloatVolatile(o, offset, x);
    }

    public double getDoubleVolatile(Object o, long offset) {
        return usf.getDoubleVolatile(o, offset);
    }

    public void putDoubleVolatile(Object o, long offset, double x) {
        usf.putDoubleVolatile(o, offset, x);
    }


    public Object getReferenceAcquire(Object o, long offset) {
        return null;
    }


    public boolean getBooleanAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public byte getByteAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public short getShortAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public char getCharAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public int getIntAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public float getFloatAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public long getLongAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public double getDoubleAcquire(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public void putReferenceRelease(Object o, long offset, Object x) {
        throw new UnsupportedOperationException();
    }


    public void putBooleanRelease(Object o, long offset, boolean x) {
        throw new UnsupportedOperationException();
    }


    public void putByteRelease(Object o, long offset, byte x) {
        throw new UnsupportedOperationException();
    }


    public void putShortRelease(Object o, long offset, short x) {
        throw new UnsupportedOperationException();
    }


    public void putCharRelease(Object o, long offset, char x) {
        throw new UnsupportedOperationException();
    }


    public void putIntRelease(Object o, long offset, int x) {
        throw new UnsupportedOperationException();
    }


    public void putFloatRelease(Object o, long offset, float x) {
        throw new UnsupportedOperationException();
    }


    public void putLongRelease(Object o, long offset, long x) {
        throw new UnsupportedOperationException();
    }


    public void putDoubleRelease(Object o, long offset, double x) {
        throw new UnsupportedOperationException();
    }


    public Object getReferenceOpaque(Object o, long offset) {
        return null;
    }


    public boolean getBooleanOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public byte getByteOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public short getShortOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public char getCharOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public int getIntOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public float getFloatOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public long getLongOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public double getDoubleOpaque(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public void putReferenceOpaque(Object o, long offset, Object x) {
        throw new UnsupportedOperationException();
    }


    public void putBooleanOpaque(Object o, long offset, boolean x) {
        throw new UnsupportedOperationException();
    }


    public void putByteOpaque(Object o, long offset, byte x) {
        throw new UnsupportedOperationException();
    }


    public void putShortOpaque(Object o, long offset, short x) {
        throw new UnsupportedOperationException();
    }


    public void putCharOpaque(Object o, long offset, char x) {
        throw new UnsupportedOperationException();
    }


    public void putIntOpaque(Object o, long offset, int x) {
        throw new UnsupportedOperationException();
    }


    public void putFloatOpaque(Object o, long offset, float x) {
        throw new UnsupportedOperationException();
    }


    public void putLongOpaque(Object o, long offset, long x) {
        throw new UnsupportedOperationException();
    }


    public void putDoubleOpaque(Object o, long offset, double x) {
        throw new UnsupportedOperationException();
    }

    public void putOrderedObject(Object o, long offset, Object x) {
        usf.putOrderedObject(o, offset, x);
    }

    public void putOrderedInt(Object o, long offset, int x) {
        usf.putOrderedInt(o, offset, x);
    }

    public void putOrderedLong(Object o, long offset, long x) {
        usf.putOrderedLong(o, offset, x);
    }

    public void unpark(Object thread) {
        usf.unpark(thread);
    }

    public void park(boolean isAbsolute, long time) {
        usf.park(isAbsolute, time);
    }

    public int getLoadAverage(double[] loadavg, int nelems) {
        return usf.getLoadAverage(loadavg, nelems);
    }

    public int getAndAddInt(Object o, long offset, int delta) {
        return usf.getAndAddInt(o, offset, delta);
    }


    public int getAndAddIntRelease(Object o, long offset, int delta) {
        throw new UnsupportedOperationException();
    }


    public int getAndAddIntAcquire(Object o, long offset, int delta) {
        throw new UnsupportedOperationException();
    }

    public long getAndAddLong(Object o, long offset, long delta) {
        return usf.getAndAddLong(o, offset, delta);
    }


    public long getAndAddLongRelease(Object o, long offset, long delta) {
        throw new UnsupportedOperationException();
    }


    public long getAndAddLongAcquire(Object o, long offset, long delta) {
        throw new UnsupportedOperationException();
    }


    public byte getAndAddByte(Object o, long offset, byte delta) {
        throw new UnsupportedOperationException();
    }


    public byte getAndAddByteRelease(Object o, long offset, byte delta) {
        throw new UnsupportedOperationException();
    }


    public byte getAndAddByteAcquire(Object o, long offset, byte delta) {
        throw new UnsupportedOperationException();
    }


    public short getAndAddShort(Object o, long offset, short delta) {
        throw new UnsupportedOperationException();
    }


    public short getAndAddShortRelease(Object o, long offset, short delta) {
        throw new UnsupportedOperationException();
    }


    public short getAndAddShortAcquire(Object o, long offset, short delta) {
        throw new UnsupportedOperationException();
    }


    public char getAndAddChar(Object o, long offset, char delta) {
        throw new UnsupportedOperationException();
    }


    public char getAndAddCharRelease(Object o, long offset, char delta) {
        throw new UnsupportedOperationException();
    }


    public char getAndAddCharAcquire(Object o, long offset, char delta) {
        throw new UnsupportedOperationException();
    }


    public float getAndAddFloat(Object o, long offset, float delta) {
        throw new UnsupportedOperationException();
    }


    public float getAndAddFloatRelease(Object o, long offset, float delta) {
        throw new UnsupportedOperationException();
    }


    public float getAndAddFloatAcquire(Object o, long offset, float delta) {
        throw new UnsupportedOperationException();
    }


    public double getAndAddDouble(Object o, long offset, double delta) {
        throw new UnsupportedOperationException();
    }


    public double getAndAddDoubleRelease(Object o, long offset, double delta) {
        throw new UnsupportedOperationException();
    }


    public double getAndAddDoubleAcquire(Object o, long offset, double delta) {
        throw new UnsupportedOperationException();
    }

    public int getAndSetInt(Object o, long offset, int newValue) {
        return usf.getAndSetInt(o, offset, newValue);
    }


    public int getAndSetIntRelease(Object o, long offset, int newValue) {
        throw new UnsupportedOperationException();
    }


    public int getAndSetIntAcquire(Object o, long offset, int newValue) {
        throw new UnsupportedOperationException();
    }

    public long getAndSetLong(Object o, long offset, long newValue) {
        return usf.getAndSetLong(o, offset, newValue);
    }


    public long getAndSetLongRelease(Object o, long offset, long newValue) {
        throw new UnsupportedOperationException();
    }


    public long getAndSetLongAcquire(Object o, long offset, long newValue) {
        throw new UnsupportedOperationException();
    }


    public Object getAndSetReference(Object o, long offset, Object newValue) {
        return null;
    }


    public Object getAndSetReferenceRelease(Object o, long offset, Object newValue) {
        return null;
    }


    public Object getAndSetReferenceAcquire(Object o, long offset, Object newValue) {
        return null;
    }


    public byte getAndSetByte(Object o, long offset, byte newValue) {
        throw new UnsupportedOperationException();
    }


    public byte getAndSetByteRelease(Object o, long offset, byte newValue) {
        throw new UnsupportedOperationException();
    }


    public byte getAndSetByteAcquire(Object o, long offset, byte newValue) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndSetBoolean(Object o, long offset, boolean newValue) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndSetBooleanRelease(Object o, long offset, boolean newValue) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndSetBooleanAcquire(Object o, long offset, boolean newValue) {
        throw new UnsupportedOperationException();
    }


    public short getAndSetShort(Object o, long offset, short newValue) {
        throw new UnsupportedOperationException();
    }


    public short getAndSetShortRelease(Object o, long offset, short newValue) {
        throw new UnsupportedOperationException();
    }


    public short getAndSetShortAcquire(Object o, long offset, short newValue) {
        throw new UnsupportedOperationException();
    }


    public char getAndSetChar(Object o, long offset, char newValue) {
        throw new UnsupportedOperationException();
    }


    public char getAndSetCharRelease(Object o, long offset, char newValue) {
        throw new UnsupportedOperationException();
    }


    public char getAndSetCharAcquire(Object o, long offset, char newValue) {
        throw new UnsupportedOperationException();
    }


    public float getAndSetFloat(Object o, long offset, float newValue) {
        throw new UnsupportedOperationException();
    }


    public float getAndSetFloatRelease(Object o, long offset, float newValue) {
        throw new UnsupportedOperationException();
    }


    public float getAndSetFloatAcquire(Object o, long offset, float newValue) {
        throw new UnsupportedOperationException();
    }


    public double getAndSetDouble(Object o, long offset, double newValue) {
        throw new UnsupportedOperationException();
    }


    public double getAndSetDoubleRelease(Object o, long offset, double newValue) {
        throw new UnsupportedOperationException();
    }


    public double getAndSetDoubleAcquire(Object o, long offset, double newValue) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndBitwiseOrBoolean(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndBitwiseOrBooleanRelease(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndBitwiseOrBooleanAcquire(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndBitwiseAndBoolean(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndBitwiseAndBooleanRelease(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndBitwiseAndBooleanAcquire(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndBitwiseXorBoolean(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndBitwiseXorBooleanRelease(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }


    public boolean getAndBitwiseXorBooleanAcquire(Object o, long offset, boolean mask) {
        throw new UnsupportedOperationException();
    }


    public byte getAndBitwiseOrByte(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }


    public byte getAndBitwiseOrByteRelease(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }


    public byte getAndBitwiseOrByteAcquire(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }


    public byte getAndBitwiseAndByte(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }


    public byte getAndBitwiseAndByteRelease(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }


    public byte getAndBitwiseAndByteAcquire(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }


    public byte getAndBitwiseXorByte(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }


    public byte getAndBitwiseXorByteRelease(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }


    public byte getAndBitwiseXorByteAcquire(Object o, long offset, byte mask) {
        throw new UnsupportedOperationException();
    }


    public char getAndBitwiseOrChar(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }


    public char getAndBitwiseOrCharRelease(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }


    public char getAndBitwiseOrCharAcquire(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }


    public char getAndBitwiseAndChar(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }


    public char getAndBitwiseAndCharRelease(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }


    public char getAndBitwiseAndCharAcquire(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }


    public char getAndBitwiseXorChar(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }


    public char getAndBitwiseXorCharRelease(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }


    public char getAndBitwiseXorCharAcquire(Object o, long offset, char mask) {
        throw new UnsupportedOperationException();
    }


    public short getAndBitwiseOrShort(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }


    public short getAndBitwiseOrShortRelease(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }


    public short getAndBitwiseOrShortAcquire(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }


    public short getAndBitwiseAndShort(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }


    public short getAndBitwiseAndShortRelease(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }


    public short getAndBitwiseAndShortAcquire(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }


    public short getAndBitwiseXorShort(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }


    public short getAndBitwiseXorShortRelease(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }


    public short getAndBitwiseXorShortAcquire(Object o, long offset, short mask) {
        throw new UnsupportedOperationException();
    }


    public int getAndBitwiseOrInt(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }


    public int getAndBitwiseOrIntRelease(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }


    public int getAndBitwiseOrIntAcquire(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }


    public int getAndBitwiseAndInt(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }


    public int getAndBitwiseAndIntRelease(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }


    public int getAndBitwiseAndIntAcquire(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }


    public int getAndBitwiseXorInt(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }


    public int getAndBitwiseXorIntRelease(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }


    public int getAndBitwiseXorIntAcquire(Object o, long offset, int mask) {
        throw new UnsupportedOperationException();
    }


    public long getAndBitwiseOrLong(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }


    public long getAndBitwiseOrLongRelease(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }


    public long getAndBitwiseOrLongAcquire(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }


    public long getAndBitwiseAndLong(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }


    public long getAndBitwiseAndLongRelease(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }


    public long getAndBitwiseAndLongAcquire(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }


    public long getAndBitwiseXorLong(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }


    public long getAndBitwiseXorLongRelease(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }


    public long getAndBitwiseXorLongAcquire(Object o, long offset, long mask) {
        throw new UnsupportedOperationException();
    }

    public Object getAndSetObject(Object o, long offset, Object newValue) {
        return usf.getAndSetObject(o, offset, newValue);
    }


    public Object getAndSetObjectAcquire(Object o, long offset, Object newValue) {
        return null;
    }


    public Object getAndSetObjectRelease(Object o, long offset, Object newValue) {
        return null;
    }


    public boolean compareAndSetObject(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }


    public Object compareAndExchangeObject(Object o, long offset, Object expected, Object x) {
        return null;
    }


    public Object compareAndExchangeObjectAcquire(Object o, long offset, Object expected, Object x) {
        return null;
    }


    public Object compareAndExchangeObjectRelease(Object o, long offset, Object expected, Object x) {
        return null;
    }


    public boolean weakCompareAndSetObject(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetObjectAcquire(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetObjectPlain(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }


    public boolean weakCompareAndSetObjectRelease(Object o, long offset, Object expected, Object x) {
        throw new UnsupportedOperationException();
    }

    public void loadFence() {
        usf.loadFence();
    }

    public void storeFence() {
        usf.storeFence();
    }

    public void fullFence() {
        usf.fullFence();
    }


    public void loadLoadFence() {
        throw new UnsupportedOperationException();
    }


    public void storeStoreFence() {
        throw new UnsupportedOperationException();
    }


    public boolean isBigEndian() {
        throw new UnsupportedOperationException();
    }


    public boolean unalignedAccess() {
        throw new UnsupportedOperationException();
    }


    public long getLongUnaligned(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public long getLongUnaligned(Object o, long offset, boolean bigEndian) {
        throw new UnsupportedOperationException();
    }


    public int getIntUnaligned(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public int getIntUnaligned(Object o, long offset, boolean bigEndian) {
        throw new UnsupportedOperationException();
    }


    public short getShortUnaligned(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public short getShortUnaligned(Object o, long offset, boolean bigEndian) {
        throw new UnsupportedOperationException();
    }


    public char getCharUnaligned(Object o, long offset) {
        throw new UnsupportedOperationException();
    }


    public char getCharUnaligned(Object o, long offset, boolean bigEndian) {
        throw new UnsupportedOperationException();
    }


    public void putLongUnaligned(Object o, long offset, long x) {
        throw new UnsupportedOperationException();
    }


    public void putLongUnaligned(Object o, long offset, long x, boolean bigEndian) {
        throw new UnsupportedOperationException();
    }


    public void putIntUnaligned(Object o, long offset, int x) {
        throw new UnsupportedOperationException();
    }


    public void putIntUnaligned(Object o, long offset, int x, boolean bigEndian) {
        throw new UnsupportedOperationException();
    }


    public void putShortUnaligned(Object o, long offset, short x) {
        throw new UnsupportedOperationException();
    }


    public void putShortUnaligned(Object o, long offset, short x, boolean bigEndian) {
        throw new UnsupportedOperationException();
    }


    public void putCharUnaligned(Object o, long offset, char x) {
        throw new UnsupportedOperationException();
    }


    public void putCharUnaligned(Object o, long offset, char x, boolean bigEndian) {
        throw new UnsupportedOperationException();
    }

    public void invokeCleaner(ByteBuffer directBuffer) {
        if (!directBuffer.isDirect())
            throw new IllegalArgumentException("buffer is non-direct");

        DirectBuffer db = (DirectBuffer) directBuffer;
        if (db.attachment() != null)
            throw new IllegalArgumentException("duplicate or slice");

        Cleaner cleaner = db.cleaner();
        if (cleaner != null) {
            cleaner.clean();
        }
    }
}
