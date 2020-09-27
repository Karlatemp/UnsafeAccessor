package io.github.karlatemp.unsafeaccessor;

import jdk.internal.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.security.ProtectionDomain;

@SuppressWarnings("removal")
class Impl9 extends io.github.karlatemp.unsafeaccessor.Unsafe {
    private static final Unsafe usf = Unsafe.getUnsafe();

    @Override
    public boolean isJava9() {
        return true;
    }

    public int getInt(Object o, long offset) {
        return usf.getInt(o, offset);
    }

    public void putInt(Object o, long offset, int x) {
        usf.putInt(o, offset, x);
    }

    public Object getReference(Object o, long offset) {
        return usf.getReference(o, offset);
    }

    public void putReference(Object o, long offset, Object x) {
        usf.putReference(o, offset, x);
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

    public long getAddress(Object o, long offset) {
        return usf.getAddress(o, offset);
    }

    public void putAddress(Object o, long offset, long x) {
        usf.putAddress(o, offset, x);
    }

    public Object getUncompressedObject(long address) {
        return usf.getUncompressedObject(address);
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
        usf.copySwapMemory(srcBase, srcOffset, destBase, destOffset, bytes, elemSize);
    }

    public void copySwapMemory(long srcAddress, long destAddress, long bytes, long elemSize) {
        usf.copySwapMemory(srcAddress, destAddress, bytes, elemSize);
    }

    public void freeMemory(long address) {
        usf.freeMemory(address);
    }


    public long objectFieldOffset(Field f) {
        return usf.objectFieldOffset(f);
    }

    public long objectFieldOffset(Class<?> c, String name) {
        return usf.objectFieldOffset(c, name);
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
        return usf.defineClass0(name, b, off, len, loader, protectionDomain);
    }

    public Class<?> defineAnonymousClass(Class<?> hostClass, byte[] data, Object[] cpPatches) {
        return usf.defineAnonymousClass(hostClass, data, cpPatches);
    }

    public Object allocateInstance(Class<?> cls) throws InstantiationException {
        return usf.allocateInstance(cls);
    }

    public Object allocateUninitializedArray(Class<?> componentType, int length) {
        return usf.allocateUninitializedArray(componentType, length);
    }

    public void throwException(Throwable ee) {
        usf.throwException(ee);
    }

    public boolean compareAndSetReference(Object o, long offset, Object expected, Object x) {
        return usf.compareAndSetReference(o, offset, expected, x);
    }

    public Object compareAndExchangeReference(Object o, long offset, Object expected, Object x) {
        return usf.compareAndExchangeReference(o, offset, expected, x);
    }

    public Object compareAndExchangeReferenceAcquire(Object o, long offset, Object expected, Object x) {
        return usf.compareAndExchangeReferenceAcquire(o, offset, expected, x);
    }

    public Object compareAndExchangeReferenceRelease(Object o, long offset, Object expected, Object x) {
        return usf.compareAndExchangeReferenceRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetReferencePlain(Object o, long offset, Object expected, Object x) {
        return usf.weakCompareAndSetReferencePlain(o, offset, expected, x);
    }

    public boolean weakCompareAndSetReferenceAcquire(Object o, long offset, Object expected, Object x) {
        return usf.weakCompareAndSetReferenceAcquire(o, offset, expected, x);
    }

    public boolean weakCompareAndSetReferenceRelease(Object o, long offset, Object expected, Object x) {
        return usf.weakCompareAndSetReferenceRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetReference(Object o, long offset, Object expected, Object x) {
        return usf.weakCompareAndSetReference(o, offset, expected, x);
    }

    public boolean compareAndSetInt(Object o, long offset, int expected, int x) {
        return usf.compareAndSetInt(o, offset, expected, x);
    }

    public int compareAndExchangeInt(Object o, long offset, int expected, int x) {
        return usf.compareAndExchangeInt(o, offset, expected, x);
    }

    public int compareAndExchangeIntAcquire(Object o, long offset, int expected, int x) {
        return usf.compareAndExchangeIntAcquire(o, offset, expected, x);
    }

    public int compareAndExchangeIntRelease(Object o, long offset, int expected, int x) {
        return usf.compareAndExchangeIntRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetIntPlain(Object o, long offset, int expected, int x) {
        return usf.weakCompareAndSetIntPlain(o, offset, expected, x);
    }

    public boolean weakCompareAndSetIntAcquire(Object o, long offset, int expected, int x) {
        return usf.weakCompareAndSetIntAcquire(o, offset, expected, x);
    }

    public boolean weakCompareAndSetIntRelease(Object o, long offset, int expected, int x) {
        return usf.weakCompareAndSetIntRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetInt(Object o, long offset, int expected, int x) {
        return usf.weakCompareAndSetInt(o, offset, expected, x);
    }

    public byte compareAndExchangeByte(Object o, long offset, byte expected, byte x) {
        return usf.compareAndExchangeByte(o, offset, expected, x);
    }

    public boolean compareAndSetByte(Object o, long offset, byte expected, byte x) {
        return usf.compareAndSetByte(o, offset, expected, x);
    }

    public boolean weakCompareAndSetByte(Object o, long offset, byte expected, byte x) {
        return usf.weakCompareAndSetByte(o, offset, expected, x);
    }

    public boolean weakCompareAndSetByteAcquire(Object o, long offset, byte expected, byte x) {
        return usf.weakCompareAndSetByteAcquire(o, offset, expected, x);
    }

    public boolean weakCompareAndSetByteRelease(Object o, long offset, byte expected, byte x) {
        return usf.weakCompareAndSetByteRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetBytePlain(Object o, long offset, byte expected, byte x) {
        return usf.weakCompareAndSetBytePlain(o, offset, expected, x);
    }

    public byte compareAndExchangeByteAcquire(Object o, long offset, byte expected, byte x) {
        return usf.compareAndExchangeByteAcquire(o, offset, expected, x);
    }

    public byte compareAndExchangeByteRelease(Object o, long offset, byte expected, byte x) {
        return usf.compareAndExchangeByteRelease(o, offset, expected, x);
    }

    public short compareAndExchangeShort(Object o, long offset, short expected, short x) {
        return usf.compareAndExchangeShort(o, offset, expected, x);
    }

    public boolean compareAndSetShort(Object o, long offset, short expected, short x) {
        return usf.compareAndSetShort(o, offset, expected, x);
    }

    public boolean weakCompareAndSetShort(Object o, long offset, short expected, short x) {
        return usf.weakCompareAndSetShort(o, offset, expected, x);
    }

    public boolean weakCompareAndSetShortAcquire(Object o, long offset, short expected, short x) {
        return usf.weakCompareAndSetShortAcquire(o, offset, expected, x);
    }

    public boolean weakCompareAndSetShortRelease(Object o, long offset, short expected, short x) {
        return usf.weakCompareAndSetShortRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetShortPlain(Object o, long offset, short expected, short x) {
        return usf.weakCompareAndSetShortPlain(o, offset, expected, x);
    }

    public short compareAndExchangeShortAcquire(Object o, long offset, short expected, short x) {
        return usf.compareAndExchangeShortAcquire(o, offset, expected, x);
    }

    public short compareAndExchangeShortRelease(Object o, long offset, short expected, short x) {
        return usf.compareAndExchangeShortRelease(o, offset, expected, x);
    }

    public boolean compareAndSetChar(Object o, long offset, char expected, char x) {
        return usf.compareAndSetChar(o, offset, expected, x);
    }

    public char compareAndExchangeChar(Object o, long offset, char expected, char x) {
        return usf.compareAndExchangeChar(o, offset, expected, x);
    }

    public char compareAndExchangeCharAcquire(Object o, long offset, char expected, char x) {
        return usf.compareAndExchangeCharAcquire(o, offset, expected, x);
    }

    public char compareAndExchangeCharRelease(Object o, long offset, char expected, char x) {
        return usf.compareAndExchangeCharRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetChar(Object o, long offset, char expected, char x) {
        return usf.weakCompareAndSetChar(o, offset, expected, x);
    }

    public boolean weakCompareAndSetCharAcquire(Object o, long offset, char expected, char x) {
        return usf.weakCompareAndSetCharAcquire(o, offset, expected, x);
    }

    public boolean weakCompareAndSetCharRelease(Object o, long offset, char expected, char x) {
        return usf.weakCompareAndSetCharRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetCharPlain(Object o, long offset, char expected, char x) {
        return usf.weakCompareAndSetCharPlain(o, offset, expected, x);
    }

    public boolean compareAndSetBoolean(Object o, long offset, boolean expected, boolean x) {
        return usf.compareAndSetBoolean(o, offset, expected, x);
    }

    public boolean compareAndExchangeBoolean(Object o, long offset, boolean expected, boolean x) {
        return usf.compareAndExchangeBoolean(o, offset, expected, x);
    }

    public boolean compareAndExchangeBooleanAcquire(Object o, long offset, boolean expected, boolean x) {
        return usf.compareAndExchangeBooleanAcquire(o, offset, expected, x);
    }

    public boolean compareAndExchangeBooleanRelease(Object o, long offset, boolean expected, boolean x) {
        return usf.compareAndExchangeBooleanRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetBoolean(Object o, long offset, boolean expected, boolean x) {
        return usf.weakCompareAndSetBoolean(o, offset, expected, x);
    }

    public boolean weakCompareAndSetBooleanAcquire(Object o, long offset, boolean expected, boolean x) {
        return usf.weakCompareAndSetBooleanAcquire(o, offset, expected, x);
    }

    public boolean weakCompareAndSetBooleanRelease(Object o, long offset, boolean expected, boolean x) {
        return usf.weakCompareAndSetBooleanRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetBooleanPlain(Object o, long offset, boolean expected, boolean x) {
        return usf.weakCompareAndSetBooleanPlain(o, offset, expected, x);
    }

    public boolean compareAndSetFloat(Object o, long offset, float expected, float x) {
        return usf.compareAndSetFloat(o, offset, expected, x);
    }

    public float compareAndExchangeFloat(Object o, long offset, float expected, float x) {
        return usf.compareAndExchangeFloat(o, offset, expected, x);
    }

    public float compareAndExchangeFloatAcquire(Object o, long offset, float expected, float x) {
        return usf.compareAndExchangeFloatAcquire(o, offset, expected, x);
    }

    public float compareAndExchangeFloatRelease(Object o, long offset, float expected, float x) {
        return usf.compareAndExchangeFloatRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetFloatPlain(Object o, long offset, float expected, float x) {
        return usf.weakCompareAndSetFloatPlain(o, offset, expected, x);
    }

    public boolean weakCompareAndSetFloatAcquire(Object o, long offset, float expected, float x) {
        return usf.weakCompareAndSetFloatAcquire(o, offset, expected, x);
    }

    public boolean weakCompareAndSetFloatRelease(Object o, long offset, float expected, float x) {
        return usf.weakCompareAndSetFloatRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetFloat(Object o, long offset, float expected, float x) {
        return usf.weakCompareAndSetFloat(o, offset, expected, x);
    }

    public boolean compareAndSetDouble(Object o, long offset, double expected, double x) {
        return usf.compareAndSetDouble(o, offset, expected, x);
    }

    public double compareAndExchangeDouble(Object o, long offset, double expected, double x) {
        return usf.compareAndExchangeDouble(o, offset, expected, x);
    }

    public double compareAndExchangeDoubleAcquire(Object o, long offset, double expected, double x) {
        return usf.compareAndExchangeDoubleAcquire(o, offset, expected, x);
    }

    public double compareAndExchangeDoubleRelease(Object o, long offset, double expected, double x) {
        return usf.compareAndExchangeDoubleRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetDoublePlain(Object o, long offset, double expected, double x) {
        return usf.weakCompareAndSetDoublePlain(o, offset, expected, x);
    }

    public boolean weakCompareAndSetDoubleAcquire(Object o, long offset, double expected, double x) {
        return usf.weakCompareAndSetDoubleAcquire(o, offset, expected, x);
    }

    public boolean weakCompareAndSetDoubleRelease(Object o, long offset, double expected, double x) {
        return usf.weakCompareAndSetDoubleRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetDouble(Object o, long offset, double expected, double x) {
        return usf.weakCompareAndSetDouble(o, offset, expected, x);
    }

    public boolean compareAndSetLong(Object o, long offset, long expected, long x) {
        return usf.compareAndSetLong(o, offset, expected, x);
    }

    public long compareAndExchangeLong(Object o, long offset, long expected, long x) {
        return usf.compareAndExchangeLong(o, offset, expected, x);
    }

    public long compareAndExchangeLongAcquire(Object o, long offset, long expected, long x) {
        return usf.compareAndExchangeLongAcquire(o, offset, expected, x);
    }

    public long compareAndExchangeLongRelease(Object o, long offset, long expected, long x) {
        return usf.compareAndExchangeLongRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetLongPlain(Object o, long offset, long expected, long x) {
        return usf.weakCompareAndSetLongPlain(o, offset, expected, x);
    }

    public boolean weakCompareAndSetLongAcquire(Object o, long offset, long expected, long x) {
        return usf.weakCompareAndSetLongAcquire(o, offset, expected, x);
    }

    public boolean weakCompareAndSetLongRelease(Object o, long offset, long expected, long x) {
        return usf.weakCompareAndSetLongRelease(o, offset, expected, x);
    }

    public boolean weakCompareAndSetLong(Object o, long offset, long expected, long x) {
        return usf.weakCompareAndSetLong(o, offset, expected, x);
    }

    public Object getReferenceVolatile(Object o, long offset) {
        return usf.getReferenceVolatile(o, offset);
    }

    public void putReferenceVolatile(Object o, long offset, Object x) {
        usf.putReferenceVolatile(o, offset, x);
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
        return usf.getReferenceAcquire(o, offset);
    }

    public boolean getBooleanAcquire(Object o, long offset) {
        return usf.getBooleanAcquire(o, offset);
    }

    public byte getByteAcquire(Object o, long offset) {
        return usf.getByteAcquire(o, offset);
    }

    public short getShortAcquire(Object o, long offset) {
        return usf.getShortAcquire(o, offset);
    }

    public char getCharAcquire(Object o, long offset) {
        return usf.getCharAcquire(o, offset);
    }

    public int getIntAcquire(Object o, long offset) {
        return usf.getIntAcquire(o, offset);
    }

    public float getFloatAcquire(Object o, long offset) {
        return usf.getFloatAcquire(o, offset);
    }

    public long getLongAcquire(Object o, long offset) {
        return usf.getLongAcquire(o, offset);
    }

    public double getDoubleAcquire(Object o, long offset) {
        return usf.getDoubleAcquire(o, offset);
    }

    public void putReferenceRelease(Object o, long offset, Object x) {
        usf.putReferenceRelease(o, offset, x);
    }

    public void putBooleanRelease(Object o, long offset, boolean x) {
        usf.putBooleanRelease(o, offset, x);
    }

    public void putByteRelease(Object o, long offset, byte x) {
        usf.putByteRelease(o, offset, x);
    }

    public void putShortRelease(Object o, long offset, short x) {
        usf.putShortRelease(o, offset, x);
    }

    public void putCharRelease(Object o, long offset, char x) {
        usf.putCharRelease(o, offset, x);
    }

    public void putIntRelease(Object o, long offset, int x) {
        usf.putIntRelease(o, offset, x);
    }

    public void putFloatRelease(Object o, long offset, float x) {
        usf.putFloatRelease(o, offset, x);
    }

    public void putLongRelease(Object o, long offset, long x) {
        usf.putLongRelease(o, offset, x);
    }

    public void putDoubleRelease(Object o, long offset, double x) {
        usf.putDoubleRelease(o, offset, x);
    }

    public Object getReferenceOpaque(Object o, long offset) {
        return usf.getReferenceOpaque(o, offset);
    }

    public boolean getBooleanOpaque(Object o, long offset) {
        return usf.getBooleanOpaque(o, offset);
    }

    public byte getByteOpaque(Object o, long offset) {
        return usf.getByteOpaque(o, offset);
    }

    public short getShortOpaque(Object o, long offset) {
        return usf.getShortOpaque(o, offset);
    }

    public char getCharOpaque(Object o, long offset) {
        return usf.getCharOpaque(o, offset);
    }

    public int getIntOpaque(Object o, long offset) {
        return usf.getIntOpaque(o, offset);
    }

    public float getFloatOpaque(Object o, long offset) {
        return usf.getFloatOpaque(o, offset);
    }

    public long getLongOpaque(Object o, long offset) {
        return usf.getLongOpaque(o, offset);
    }

    public double getDoubleOpaque(Object o, long offset) {
        return usf.getDoubleOpaque(o, offset);
    }

    public void putReferenceOpaque(Object o, long offset, Object x) {
        usf.putReferenceOpaque(o, offset, x);
    }

    public void putBooleanOpaque(Object o, long offset, boolean x) {
        usf.putBooleanOpaque(o, offset, x);
    }

    public void putByteOpaque(Object o, long offset, byte x) {
        usf.putByteOpaque(o, offset, x);
    }

    public void putShortOpaque(Object o, long offset, short x) {
        usf.putShortOpaque(o, offset, x);
    }

    public void putCharOpaque(Object o, long offset, char x) {
        usf.putCharOpaque(o, offset, x);
    }

    public void putIntOpaque(Object o, long offset, int x) {
        usf.putIntOpaque(o, offset, x);
    }

    public void putFloatOpaque(Object o, long offset, float x) {
        usf.putFloatOpaque(o, offset, x);
    }

    public void putLongOpaque(Object o, long offset, long x) {
        usf.putLongOpaque(o, offset, x);
    }

    public void putDoubleOpaque(Object o, long offset, double x) {
        usf.putDoubleOpaque(o, offset, x);
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
        return usf.getAndAddIntRelease(o, offset, delta);
    }

    public int getAndAddIntAcquire(Object o, long offset, int delta) {
        return usf.getAndAddIntAcquire(o, offset, delta);
    }

    public long getAndAddLong(Object o, long offset, long delta) {
        return usf.getAndAddLong(o, offset, delta);
    }

    public long getAndAddLongRelease(Object o, long offset, long delta) {
        return usf.getAndAddLongRelease(o, offset, delta);
    }

    public long getAndAddLongAcquire(Object o, long offset, long delta) {
        return usf.getAndAddLongAcquire(o, offset, delta);
    }

    public byte getAndAddByte(Object o, long offset, byte delta) {
        return usf.getAndAddByte(o, offset, delta);
    }

    public byte getAndAddByteRelease(Object o, long offset, byte delta) {
        return usf.getAndAddByteRelease(o, offset, delta);
    }

    public byte getAndAddByteAcquire(Object o, long offset, byte delta) {
        return usf.getAndAddByteAcquire(o, offset, delta);
    }

    public short getAndAddShort(Object o, long offset, short delta) {
        return usf.getAndAddShort(o, offset, delta);
    }

    public short getAndAddShortRelease(Object o, long offset, short delta) {
        return usf.getAndAddShortRelease(o, offset, delta);
    }

    public short getAndAddShortAcquire(Object o, long offset, short delta) {
        return usf.getAndAddShortAcquire(o, offset, delta);
    }

    public char getAndAddChar(Object o, long offset, char delta) {
        return usf.getAndAddChar(o, offset, delta);
    }

    public char getAndAddCharRelease(Object o, long offset, char delta) {
        return usf.getAndAddCharRelease(o, offset, delta);
    }

    public char getAndAddCharAcquire(Object o, long offset, char delta) {
        return usf.getAndAddCharAcquire(o, offset, delta);
    }

    public float getAndAddFloat(Object o, long offset, float delta) {
        return usf.getAndAddFloat(o, offset, delta);
    }

    public float getAndAddFloatRelease(Object o, long offset, float delta) {
        return usf.getAndAddFloatRelease(o, offset, delta);
    }

    public float getAndAddFloatAcquire(Object o, long offset, float delta) {
        return usf.getAndAddFloatAcquire(o, offset, delta);
    }

    public double getAndAddDouble(Object o, long offset, double delta) {
        return usf.getAndAddDouble(o, offset, delta);
    }

    public double getAndAddDoubleRelease(Object o, long offset, double delta) {
        return usf.getAndAddDoubleRelease(o, offset, delta);
    }

    public double getAndAddDoubleAcquire(Object o, long offset, double delta) {
        return usf.getAndAddDoubleAcquire(o, offset, delta);
    }

    public int getAndSetInt(Object o, long offset, int newValue) {
        return usf.getAndSetInt(o, offset, newValue);
    }

    public int getAndSetIntRelease(Object o, long offset, int newValue) {
        return usf.getAndSetIntRelease(o, offset, newValue);
    }

    public int getAndSetIntAcquire(Object o, long offset, int newValue) {
        return usf.getAndSetIntAcquire(o, offset, newValue);
    }

    public long getAndSetLong(Object o, long offset, long newValue) {
        return usf.getAndSetLong(o, offset, newValue);
    }

    public long getAndSetLongRelease(Object o, long offset, long newValue) {
        return usf.getAndSetLongRelease(o, offset, newValue);
    }

    public long getAndSetLongAcquire(Object o, long offset, long newValue) {
        return usf.getAndSetLongAcquire(o, offset, newValue);
    }

    public Object getAndSetReference(Object o, long offset, Object newValue) {
        return usf.getAndSetReference(o, offset, newValue);
    }

    public Object getAndSetReferenceRelease(Object o, long offset, Object newValue) {
        return usf.getAndSetReferenceRelease(o, offset, newValue);
    }

    public Object getAndSetReferenceAcquire(Object o, long offset, Object newValue) {
        return usf.getAndSetReferenceAcquire(o, offset, newValue);
    }

    public byte getAndSetByte(Object o, long offset, byte newValue) {
        return usf.getAndSetByte(o, offset, newValue);
    }

    public byte getAndSetByteRelease(Object o, long offset, byte newValue) {
        return usf.getAndSetByteRelease(o, offset, newValue);
    }

    public byte getAndSetByteAcquire(Object o, long offset, byte newValue) {
        return usf.getAndSetByteAcquire(o, offset, newValue);
    }

    public boolean getAndSetBoolean(Object o, long offset, boolean newValue) {
        return usf.getAndSetBoolean(o, offset, newValue);
    }

    public boolean getAndSetBooleanRelease(Object o, long offset, boolean newValue) {
        return usf.getAndSetBooleanRelease(o, offset, newValue);
    }

    public boolean getAndSetBooleanAcquire(Object o, long offset, boolean newValue) {
        return usf.getAndSetBooleanAcquire(o, offset, newValue);
    }

    public short getAndSetShort(Object o, long offset, short newValue) {
        return usf.getAndSetShort(o, offset, newValue);
    }

    public short getAndSetShortRelease(Object o, long offset, short newValue) {
        return usf.getAndSetShortRelease(o, offset, newValue);
    }

    public short getAndSetShortAcquire(Object o, long offset, short newValue) {
        return usf.getAndSetShortAcquire(o, offset, newValue);
    }

    public char getAndSetChar(Object o, long offset, char newValue) {
        return usf.getAndSetChar(o, offset, newValue);
    }

    public char getAndSetCharRelease(Object o, long offset, char newValue) {
        return usf.getAndSetCharRelease(o, offset, newValue);
    }

    public char getAndSetCharAcquire(Object o, long offset, char newValue) {
        return usf.getAndSetCharAcquire(o, offset, newValue);
    }

    public float getAndSetFloat(Object o, long offset, float newValue) {
        return usf.getAndSetFloat(o, offset, newValue);
    }

    public float getAndSetFloatRelease(Object o, long offset, float newValue) {
        return usf.getAndSetFloatRelease(o, offset, newValue);
    }

    public float getAndSetFloatAcquire(Object o, long offset, float newValue) {
        return usf.getAndSetFloatAcquire(o, offset, newValue);
    }

    public double getAndSetDouble(Object o, long offset, double newValue) {
        return usf.getAndSetDouble(o, offset, newValue);
    }

    public double getAndSetDoubleRelease(Object o, long offset, double newValue) {
        return usf.getAndSetDoubleRelease(o, offset, newValue);
    }

    public double getAndSetDoubleAcquire(Object o, long offset, double newValue) {
        return usf.getAndSetDoubleAcquire(o, offset, newValue);
    }

    public boolean getAndBitwiseOrBoolean(Object o, long offset, boolean mask) {
        return usf.getAndBitwiseOrBoolean(o, offset, mask);
    }

    public boolean getAndBitwiseOrBooleanRelease(Object o, long offset, boolean mask) {
        return usf.getAndBitwiseOrBooleanRelease(o, offset, mask);
    }

    public boolean getAndBitwiseOrBooleanAcquire(Object o, long offset, boolean mask) {
        return usf.getAndBitwiseOrBooleanAcquire(o, offset, mask);
    }

    public boolean getAndBitwiseAndBoolean(Object o, long offset, boolean mask) {
        return usf.getAndBitwiseAndBoolean(o, offset, mask);
    }

    public boolean getAndBitwiseAndBooleanRelease(Object o, long offset, boolean mask) {
        return usf.getAndBitwiseAndBooleanRelease(o, offset, mask);
    }

    public boolean getAndBitwiseAndBooleanAcquire(Object o, long offset, boolean mask) {
        return usf.getAndBitwiseAndBooleanAcquire(o, offset, mask);
    }

    public boolean getAndBitwiseXorBoolean(Object o, long offset, boolean mask) {
        return usf.getAndBitwiseXorBoolean(o, offset, mask);
    }

    public boolean getAndBitwiseXorBooleanRelease(Object o, long offset, boolean mask) {
        return usf.getAndBitwiseXorBooleanRelease(o, offset, mask);
    }

    public boolean getAndBitwiseXorBooleanAcquire(Object o, long offset, boolean mask) {
        return usf.getAndBitwiseXorBooleanAcquire(o, offset, mask);
    }

    public byte getAndBitwiseOrByte(Object o, long offset, byte mask) {
        return usf.getAndBitwiseOrByte(o, offset, mask);
    }

    public byte getAndBitwiseOrByteRelease(Object o, long offset, byte mask) {
        return usf.getAndBitwiseOrByteRelease(o, offset, mask);
    }

    public byte getAndBitwiseOrByteAcquire(Object o, long offset, byte mask) {
        return usf.getAndBitwiseOrByteAcquire(o, offset, mask);
    }

    public byte getAndBitwiseAndByte(Object o, long offset, byte mask) {
        return usf.getAndBitwiseAndByte(o, offset, mask);
    }

    public byte getAndBitwiseAndByteRelease(Object o, long offset, byte mask) {
        return usf.getAndBitwiseAndByteRelease(o, offset, mask);
    }

    public byte getAndBitwiseAndByteAcquire(Object o, long offset, byte mask) {
        return usf.getAndBitwiseAndByteAcquire(o, offset, mask);
    }

    public byte getAndBitwiseXorByte(Object o, long offset, byte mask) {
        return usf.getAndBitwiseXorByte(o, offset, mask);
    }

    public byte getAndBitwiseXorByteRelease(Object o, long offset, byte mask) {
        return usf.getAndBitwiseXorByteRelease(o, offset, mask);
    }

    public byte getAndBitwiseXorByteAcquire(Object o, long offset, byte mask) {
        return usf.getAndBitwiseXorByteAcquire(o, offset, mask);
    }

    public char getAndBitwiseOrChar(Object o, long offset, char mask) {
        return usf.getAndBitwiseOrChar(o, offset, mask);
    }

    public char getAndBitwiseOrCharRelease(Object o, long offset, char mask) {
        return usf.getAndBitwiseOrCharRelease(o, offset, mask);
    }

    public char getAndBitwiseOrCharAcquire(Object o, long offset, char mask) {
        return usf.getAndBitwiseOrCharAcquire(o, offset, mask);
    }

    public char getAndBitwiseAndChar(Object o, long offset, char mask) {
        return usf.getAndBitwiseAndChar(o, offset, mask);
    }

    public char getAndBitwiseAndCharRelease(Object o, long offset, char mask) {
        return usf.getAndBitwiseAndCharRelease(o, offset, mask);
    }

    public char getAndBitwiseAndCharAcquire(Object o, long offset, char mask) {
        return usf.getAndBitwiseAndCharAcquire(o, offset, mask);
    }

    public char getAndBitwiseXorChar(Object o, long offset, char mask) {
        return usf.getAndBitwiseXorChar(o, offset, mask);
    }

    public char getAndBitwiseXorCharRelease(Object o, long offset, char mask) {
        return usf.getAndBitwiseXorCharRelease(o, offset, mask);
    }

    public char getAndBitwiseXorCharAcquire(Object o, long offset, char mask) {
        return usf.getAndBitwiseXorCharAcquire(o, offset, mask);
    }

    public short getAndBitwiseOrShort(Object o, long offset, short mask) {
        return usf.getAndBitwiseOrShort(o, offset, mask);
    }

    public short getAndBitwiseOrShortRelease(Object o, long offset, short mask) {
        return usf.getAndBitwiseOrShortRelease(o, offset, mask);
    }

    public short getAndBitwiseOrShortAcquire(Object o, long offset, short mask) {
        return usf.getAndBitwiseOrShortAcquire(o, offset, mask);
    }

    public short getAndBitwiseAndShort(Object o, long offset, short mask) {
        return usf.getAndBitwiseAndShort(o, offset, mask);
    }

    public short getAndBitwiseAndShortRelease(Object o, long offset, short mask) {
        return usf.getAndBitwiseAndShortRelease(o, offset, mask);
    }

    public short getAndBitwiseAndShortAcquire(Object o, long offset, short mask) {
        return usf.getAndBitwiseAndShortAcquire(o, offset, mask);
    }

    public short getAndBitwiseXorShort(Object o, long offset, short mask) {
        return usf.getAndBitwiseXorShort(o, offset, mask);
    }

    public short getAndBitwiseXorShortRelease(Object o, long offset, short mask) {
        return usf.getAndBitwiseXorShortRelease(o, offset, mask);
    }

    public short getAndBitwiseXorShortAcquire(Object o, long offset, short mask) {
        return usf.getAndBitwiseXorShortAcquire(o, offset, mask);
    }

    public int getAndBitwiseOrInt(Object o, long offset, int mask) {
        return usf.getAndBitwiseOrInt(o, offset, mask);
    }

    public int getAndBitwiseOrIntRelease(Object o, long offset, int mask) {
        return usf.getAndBitwiseOrIntRelease(o, offset, mask);
    }

    public int getAndBitwiseOrIntAcquire(Object o, long offset, int mask) {
        return usf.getAndBitwiseOrIntAcquire(o, offset, mask);
    }

    public int getAndBitwiseAndInt(Object o, long offset, int mask) {
        return usf.getAndBitwiseAndInt(o, offset, mask);
    }

    public int getAndBitwiseAndIntRelease(Object o, long offset, int mask) {
        return usf.getAndBitwiseAndIntRelease(o, offset, mask);
    }

    public int getAndBitwiseAndIntAcquire(Object o, long offset, int mask) {
        return usf.getAndBitwiseAndIntAcquire(o, offset, mask);
    }

    public int getAndBitwiseXorInt(Object o, long offset, int mask) {
        return usf.getAndBitwiseXorInt(o, offset, mask);
    }

    public int getAndBitwiseXorIntRelease(Object o, long offset, int mask) {
        return usf.getAndBitwiseXorIntRelease(o, offset, mask);
    }

    public int getAndBitwiseXorIntAcquire(Object o, long offset, int mask) {
        return usf.getAndBitwiseXorIntAcquire(o, offset, mask);
    }

    public long getAndBitwiseOrLong(Object o, long offset, long mask) {
        return usf.getAndBitwiseOrLong(o, offset, mask);
    }

    public long getAndBitwiseOrLongRelease(Object o, long offset, long mask) {
        return usf.getAndBitwiseOrLongRelease(o, offset, mask);
    }

    public long getAndBitwiseOrLongAcquire(Object o, long offset, long mask) {
        return usf.getAndBitwiseOrLongAcquire(o, offset, mask);
    }

    public long getAndBitwiseAndLong(Object o, long offset, long mask) {
        return usf.getAndBitwiseAndLong(o, offset, mask);
    }

    public long getAndBitwiseAndLongRelease(Object o, long offset, long mask) {
        return usf.getAndBitwiseAndLongRelease(o, offset, mask);
    }

    public long getAndBitwiseAndLongAcquire(Object o, long offset, long mask) {
        return usf.getAndBitwiseAndLongAcquire(o, offset, mask);
    }

    public long getAndBitwiseXorLong(Object o, long offset, long mask) {
        return usf.getAndBitwiseXorLong(o, offset, mask);
    }

    public long getAndBitwiseXorLongRelease(Object o, long offset, long mask) {
        return usf.getAndBitwiseXorLongRelease(o, offset, mask);
    }

    public long getAndBitwiseXorLongAcquire(Object o, long offset, long mask) {
        return usf.getAndBitwiseXorLongAcquire(o, offset, mask);
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
        usf.loadLoadFence();
    }

    public void storeStoreFence() {
        usf.storeStoreFence();
    }

    public boolean isBigEndian() {
        return usf.isBigEndian();
    }

    public boolean unalignedAccess() {
        return usf.unalignedAccess();
    }

    public long getLongUnaligned(Object o, long offset) {
        return usf.getLongUnaligned(o, offset);
    }

    public long getLongUnaligned(Object o, long offset, boolean bigEndian) {
        return usf.getLongUnaligned(o, offset, bigEndian);
    }

    public int getIntUnaligned(Object o, long offset) {
        return usf.getIntUnaligned(o, offset);
    }

    public int getIntUnaligned(Object o, long offset, boolean bigEndian) {
        return usf.getIntUnaligned(o, offset, bigEndian);
    }

    public short getShortUnaligned(Object o, long offset) {
        return usf.getShortUnaligned(o, offset);
    }

    public short getShortUnaligned(Object o, long offset, boolean bigEndian) {
        return usf.getShortUnaligned(o, offset, bigEndian);
    }

    public char getCharUnaligned(Object o, long offset) {
        return usf.getCharUnaligned(o, offset);
    }

    public char getCharUnaligned(Object o, long offset, boolean bigEndian) {
        return usf.getCharUnaligned(o, offset, bigEndian);
    }

    public void putLongUnaligned(Object o, long offset, long x) {
        usf.putLongUnaligned(o, offset, x);
    }

    public void putLongUnaligned(Object o, long offset, long x, boolean bigEndian) {
        usf.putLongUnaligned(o, offset, x, bigEndian);
    }

    public void putIntUnaligned(Object o, long offset, int x) {
        usf.putIntUnaligned(o, offset, x);
    }

    public void putIntUnaligned(Object o, long offset, int x, boolean bigEndian) {
        usf.putIntUnaligned(o, offset, x, bigEndian);
    }

    public void putShortUnaligned(Object o, long offset, short x) {
        usf.putShortUnaligned(o, offset, x);
    }

    public void putShortUnaligned(Object o, long offset, short x, boolean bigEndian) {
        usf.putShortUnaligned(o, offset, x, bigEndian);
    }

    public void putCharUnaligned(Object o, long offset, char x) {
        usf.putCharUnaligned(o, offset, x);
    }

    public void putCharUnaligned(Object o, long offset, char x, boolean bigEndian) {
        usf.putCharUnaligned(o, offset, x, bigEndian);
    }

    public void invokeCleaner(ByteBuffer directBuffer) {
        usf.invokeCleaner(directBuffer);
    }

    @Deprecated(since = "12", forRemoval = true)
    public Object getObject(Object o, long offset) {
        return usf.getObject(o, offset);
    }

    @Deprecated(since = "12", forRemoval = true)
    public Object getObjectVolatile(Object o, long offset) {
        return usf.getObjectVolatile(o, offset);
    }

    @Deprecated(since = "12", forRemoval = true)
    public Object getObjectAcquire(Object o, long offset) {
        return usf.getObjectAcquire(o, offset);
    }

    @Deprecated(since = "12", forRemoval = true)
    public Object getObjectOpaque(Object o, long offset) {
        return usf.getObjectOpaque(o, offset);
    }

    @Deprecated(since = "12", forRemoval = true)
    public void putObject(Object o, long offset, Object x) {
        usf.putObject(o, offset, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public void putObjectVolatile(Object o, long offset, Object x) {
        usf.putObjectVolatile(o, offset, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public void putObjectOpaque(Object o, long offset, Object x) {
        usf.putObjectOpaque(o, offset, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public void putObjectRelease(Object o, long offset, Object x) {
        usf.putObjectRelease(o, offset, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public Object getAndSetObject(Object o, long offset, Object newValue) {
        return usf.getAndSetObject(o, offset, newValue);
    }

    @Deprecated(since = "12", forRemoval = true)
    public Object getAndSetObjectAcquire(Object o, long offset, Object newValue) {
        return usf.getAndSetObjectAcquire(o, offset, newValue);
    }

    @Deprecated(since = "12", forRemoval = true)
    public Object getAndSetObjectRelease(Object o, long offset, Object newValue) {
        return usf.getAndSetObjectRelease(o, offset, newValue);
    }

    @Deprecated(since = "12", forRemoval = true)
    public boolean compareAndSetObject(Object o, long offset, Object expected, Object x) {
        return usf.compareAndSetObject(o, offset, expected, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public Object compareAndExchangeObject(Object o, long offset, Object expected, Object x) {
        return usf.compareAndExchangeObject(o, offset, expected, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public Object compareAndExchangeObjectAcquire(Object o, long offset, Object expected, Object x) {
        return usf.compareAndExchangeObjectAcquire(o, offset, expected, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public Object compareAndExchangeObjectRelease(Object o, long offset, Object expected, Object x) {
        return usf.compareAndExchangeObjectRelease(o, offset, expected, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public boolean weakCompareAndSetObject(Object o, long offset, Object expected, Object x) {
        return usf.weakCompareAndSetObject(o, offset, expected, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public boolean weakCompareAndSetObjectAcquire(Object o, long offset, Object expected, Object x) {
        return usf.weakCompareAndSetObjectAcquire(o, offset, expected, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public boolean weakCompareAndSetObjectPlain(Object o, long offset, Object expected, Object x) {
        return usf.weakCompareAndSetObjectPlain(o, offset, expected, x);
    }

    @Deprecated(since = "12", forRemoval = true)
    public boolean weakCompareAndSetObjectRelease(Object o, long offset, Object expected, Object x) {
        return usf.weakCompareAndSetObjectRelease(o, offset, expected, x);
    }
}
