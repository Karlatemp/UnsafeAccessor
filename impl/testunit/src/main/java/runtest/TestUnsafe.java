package runtest;

import io.github.karlatemp.unsafeaccessor.Root;
import io.github.karlatemp.unsafeaccessor.Unsafe;
import org.junit.jupiter.api.Assertions;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

@SuppressWarnings("DuplicatedCode")
public class TestUnsafe {
    public static int staticInt;
    public static double staticDouble;
    public static float staticFloat;
    public static short staticShort;
    public static byte staticByte;
    public static boolean staticBoolean;
    public static long staticLong;
    public static char staticChar;
    public static final Object staticObject = new Object();

    public int declaredInt;
    public double declaredDouble;
    public float declaredFloat;
    public short declaredShort;
    public byte declaredByte;
    public boolean declaredBoolean;
    public long declaredLong;
    public char declaredChar;
    public final Object declaredObject = new Object();

    static class TestClassInitialize {
        static boolean initialized = false;

        static class Initializer {
            static {
                initialized = true;
            }
        }
    }

    @TestTask
    public static void runTest() throws Throwable {
        System.out.println("Invoking Unsafe Test Unit....");

        Unsafe usf = Unsafe.getUnsafe();
        //noinspection ResultOfMethodCallIgnored
        usf.getOriginalUnsafe();
        // region Unsafe.put**, Unsafe.get**
        System.out.println("Testing Unsafe.get**, Unsafe.put**");
        {
            {
                Field field = TestUnsafe.class.getDeclaredField("staticInt");
                Object base = usf.staticFieldBase(field);
                long offset = usf.staticFieldOffset(field);
                usf.putInt(base, offset, 123);
                Assertions.assertEquals(123, staticInt);
                Assertions.assertEquals(staticInt, usf.getInt(base, offset));
                usf.putInt(base, offset, 456);
                Assertions.assertEquals(456, staticInt);
                Assertions.assertEquals(staticInt, usf.getInt(base, offset));
            }
            {
                Field field = TestUnsafe.class.getDeclaredField("staticDouble");
                Object base = usf.staticFieldBase(field);
                long offset = usf.staticFieldOffset(field);
                usf.putDouble(base, offset, 123);
                Assertions.assertEquals(123, staticDouble);
                Assertions.assertEquals(staticDouble, usf.getDouble(base, offset));
                usf.putDouble(base, offset, 456);
                Assertions.assertEquals(456, staticDouble);
                Assertions.assertEquals(staticDouble, usf.getDouble(base, offset));
            }
            {
                Field field = TestUnsafe.class.getDeclaredField("staticFloat");
                Object base = usf.staticFieldBase(field);
                long offset = usf.staticFieldOffset(field);
                usf.putFloat(base, offset, 123);
                Assertions.assertEquals(123, staticFloat);
                Assertions.assertEquals(staticFloat, usf.getFloat(base, offset));
                usf.putFloat(base, offset, 456);
                Assertions.assertEquals(456, staticFloat);
                Assertions.assertEquals(staticFloat, usf.getFloat(base, offset));
            }
            {
                Field field = TestUnsafe.class.getDeclaredField("staticShort");
                Object base = usf.staticFieldBase(field);
                long offset = usf.staticFieldOffset(field);
                usf.putShort(base, offset, (short) 123);
                Assertions.assertEquals((short) 123, staticShort);
                Assertions.assertEquals(staticShort, usf.getShort(base, offset));
                usf.putShort(base, offset, (short) 456);
                Assertions.assertEquals((short) 456, staticShort);
                Assertions.assertEquals(staticShort, usf.getShort(base, offset));
            }
            {
                Field field = TestUnsafe.class.getDeclaredField("staticByte");
                Object base = usf.staticFieldBase(field);
                long offset = usf.staticFieldOffset(field);
                usf.putByte(base, offset, (byte) 123);
                Assertions.assertEquals((byte) 123, staticByte);
                Assertions.assertEquals(staticByte, usf.getByte(base, offset));
                usf.putByte(base, offset, (byte) 456);
                Assertions.assertEquals((byte) 456, staticByte);
                Assertions.assertEquals(staticByte, usf.getByte(base, offset));
            }
            {
                Field field = TestUnsafe.class.getDeclaredField("staticBoolean");
                Object base = usf.staticFieldBase(field);
                long offset = usf.staticFieldOffset(field);
                usf.putBoolean(base, offset, true);
                Assertions.assertTrue(staticBoolean);
                Assertions.assertTrue(usf.getBoolean(base, offset));
                usf.putBoolean(base, offset, false);
                Assertions.assertFalse(staticBoolean);
                Assertions.assertFalse(usf.getBoolean(base, offset));
            }
            {
                Field field = TestUnsafe.class.getDeclaredField("staticLong");
                Object base = usf.staticFieldBase(field);
                long offset = usf.staticFieldOffset(field);
                usf.putLong(base, offset, 123456789L);
                Assertions.assertEquals(123456789L, staticLong);
                Assertions.assertEquals(staticLong, usf.getLong(base, offset));
                usf.putLong(base, offset, 987654321L);
                Assertions.assertEquals(987654321L, staticLong);
                Assertions.assertEquals(staticLong, usf.getLong(base, offset));
            }
            {
                Field field = TestUnsafe.class.getDeclaredField("staticChar");
                Object base = usf.staticFieldBase(field);
                long offset = usf.staticFieldOffset(field);
                usf.putChar(base, offset, '\u1234');
                Assertions.assertEquals('\u1234', staticChar);
                Assertions.assertEquals(staticChar, usf.getChar(base, offset));
                usf.putChar(base, offset, '\u4321');
                Assertions.assertEquals('\u4321', staticChar);
                Assertions.assertEquals(staticChar, usf.getChar(base, offset));
            }
            {
                Field field = TestUnsafe.class.getDeclaredField("staticObject");
                Object base = usf.staticFieldBase(field);
                long offset = usf.staticFieldOffset(field);
                Object obj = new Object();
                usf.putReference(base, offset, obj);
                Assertions.assertSame(obj, staticObject);
                Assertions.assertSame(obj, usf.getReference(base, offset));
                usf.putReference(base, offset, null);
                Assertions.assertSame(null, usf.getReference(base, offset));
            }
        }
        {
            TestUnsafe base = new TestUnsafe();
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class, "declaredInt");
                usf.putInt(base, offset, 123);
                Assertions.assertEquals(123, base.declaredInt);
                Assertions.assertEquals(base.declaredInt, usf.getInt(base, offset));
                usf.putInt(base, offset, 456);
                Assertions.assertEquals(456, base.declaredInt);
                Assertions.assertEquals(base.declaredInt, usf.getInt(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class, "declaredDouble");
                usf.putDouble(base, offset, 123);
                Assertions.assertEquals(123, base.declaredDouble);
                Assertions.assertEquals(base.declaredDouble, usf.getDouble(base, offset));
                usf.putDouble(base, offset, 456);
                Assertions.assertEquals(456, base.declaredDouble);
                Assertions.assertEquals(base.declaredDouble, usf.getDouble(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class, "declaredFloat");
                usf.putFloat(base, offset, 123);
                Assertions.assertEquals(123, base.declaredFloat);
                Assertions.assertEquals(base.declaredFloat, usf.getFloat(base, offset));
                usf.putFloat(base, offset, 456);
                Assertions.assertEquals(456, base.declaredFloat);
                Assertions.assertEquals(base.declaredFloat, usf.getFloat(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class, "declaredShort");
                usf.putShort(base, offset, (short) 123);
                Assertions.assertEquals((short) 123, base.declaredShort);
                Assertions.assertEquals(base.declaredShort, usf.getShort(base, offset));
                usf.putShort(base, offset, (short) 456);
                Assertions.assertEquals((short) 456, base.declaredShort);
                Assertions.assertEquals(base.declaredShort, usf.getShort(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class, "declaredByte");
                usf.putByte(base, offset, (byte) 123);
                Assertions.assertEquals((byte) 123, base.declaredByte);
                Assertions.assertEquals(base.declaredByte, usf.getByte(base, offset));
                usf.putByte(base, offset, (byte) 456);
                Assertions.assertEquals((byte) 456, base.declaredByte);
                Assertions.assertEquals(base.declaredByte, usf.getByte(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class, "declaredBoolean");
                usf.putBoolean(base, offset, true);
                Assertions.assertTrue(base.declaredBoolean);
                Assertions.assertTrue(usf.getBoolean(base, offset));
                usf.putBoolean(base, offset, false);
                Assertions.assertFalse(base.declaredBoolean);
                Assertions.assertFalse(usf.getBoolean(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class, "declaredLong");
                usf.putLong(base, offset, 123456789L);
                Assertions.assertEquals(123456789L, base.declaredLong);
                Assertions.assertEquals(base.declaredLong, usf.getLong(base, offset));
                usf.putLong(base, offset, 987654321L);
                Assertions.assertEquals(987654321L, base.declaredLong);
                Assertions.assertEquals(base.declaredLong, usf.getLong(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class, "declaredChar");
                usf.putChar(base, offset, '\u1234');
                Assertions.assertEquals('\u1234', base.declaredChar);
                Assertions.assertEquals(base.declaredChar, usf.getChar(base, offset));
                usf.putChar(base, offset, '\u4321');
                Assertions.assertEquals('\u4321', base.declaredChar);
                Assertions.assertEquals(base.declaredChar, usf.getChar(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class, "declaredObject");
                Object obj = new Object();
                usf.putReference(base, offset, obj);
                Assertions.assertSame(obj, base.declaredObject);
                Assertions.assertSame(obj, usf.getReference(base, offset));
                usf.putReference(base, offset, null);
                Assertions.assertSame(null, usf.getReference(base, offset));
            }
        }
        {
            TestUnsafe base = new TestUnsafe();
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class.getDeclaredField("declaredInt"));
                usf.putInt(base, offset, 123);
                Assertions.assertEquals(123, base.declaredInt);
                Assertions.assertEquals(base.declaredInt, usf.getInt(base, offset));
                usf.putInt(base, offset, 456);
                Assertions.assertEquals(456, base.declaredInt);
                Assertions.assertEquals(base.declaredInt, usf.getInt(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class.getDeclaredField("declaredDouble"));
                usf.putDouble(base, offset, 123);
                Assertions.assertEquals(123, base.declaredDouble);
                Assertions.assertEquals(base.declaredDouble, usf.getDouble(base, offset));
                usf.putDouble(base, offset, 456);
                Assertions.assertEquals(456, base.declaredDouble);
                Assertions.assertEquals(base.declaredDouble, usf.getDouble(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class.getDeclaredField("declaredFloat"));
                usf.putFloat(base, offset, 123);
                Assertions.assertEquals(123, base.declaredFloat);
                Assertions.assertEquals(base.declaredFloat, usf.getFloat(base, offset));
                usf.putFloat(base, offset, 456);
                Assertions.assertEquals(456, base.declaredFloat);
                Assertions.assertEquals(base.declaredFloat, usf.getFloat(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class.getDeclaredField("declaredShort"));
                usf.putShort(base, offset, (short) 123);
                Assertions.assertEquals((short) 123, base.declaredShort);
                Assertions.assertEquals(base.declaredShort, usf.getShort(base, offset));
                usf.putShort(base, offset, (short) 456);
                Assertions.assertEquals((short) 456, base.declaredShort);
                Assertions.assertEquals(base.declaredShort, usf.getShort(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class.getDeclaredField("declaredByte"));
                usf.putByte(base, offset, (byte) 123);
                Assertions.assertEquals((byte) 123, base.declaredByte);
                Assertions.assertEquals(base.declaredByte, usf.getByte(base, offset));
                usf.putByte(base, offset, (byte) 456);
                Assertions.assertEquals((byte) 456, base.declaredByte);
                Assertions.assertEquals(base.declaredByte, usf.getByte(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class.getDeclaredField("declaredBoolean"));
                usf.putBoolean(base, offset, true);
                Assertions.assertTrue(base.declaredBoolean);
                Assertions.assertTrue(usf.getBoolean(base, offset));
                usf.putBoolean(base, offset, false);
                Assertions.assertFalse(base.declaredBoolean);
                Assertions.assertFalse(usf.getBoolean(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class.getDeclaredField("declaredLong"));
                usf.putLong(base, offset, 123456789L);
                Assertions.assertEquals(123456789L, base.declaredLong);
                Assertions.assertEquals(base.declaredLong, usf.getLong(base, offset));
                usf.putLong(base, offset, 987654321L);
                Assertions.assertEquals(987654321L, base.declaredLong);
                Assertions.assertEquals(base.declaredLong, usf.getLong(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class.getDeclaredField("declaredChar"));
                usf.putChar(base, offset, '\u1234');
                Assertions.assertEquals('\u1234', base.declaredChar);
                Assertions.assertEquals(base.declaredChar, usf.getChar(base, offset));
                usf.putChar(base, offset, '\u4321');
                Assertions.assertEquals('\u4321', base.declaredChar);
                Assertions.assertEquals(base.declaredChar, usf.getChar(base, offset));
            }
            {
                long offset = usf.objectFieldOffset(TestUnsafe.class.getDeclaredField("declaredObject"));
                Object obj = new Object();
                usf.putReference(base, offset, obj);
                Assertions.assertSame(obj, base.declaredObject);
                Assertions.assertSame(obj, usf.getReference(base, offset));
                usf.putReference(base, offset, null);
                Assertions.assertSame(null, usf.getReference(base, offset));
            }
        }
        // endregion
        // region Unsafe.allocateInstance
        {
            System.out.println("Testing Unsafe.allocateInstance");
            class Nope {
                Nope() {
                    throw new AssertionError();
                }
            }
            Assertions.assertSame(Nope.class, usf.allocateInstance(Nope.class).getClass());
        }
        // endregion
        // region Unsafe memory
        {
            System.out.println("Testing Unsafe Memory Access");
            long memory = usf.allocateMemory(1024);
            usf.setMemory(memory, 1024, (byte) 0);

            Assertions.assertEquals(0, usf.getInt(memory + 90));
            usf.putInt(memory + 90, 123456);
            Assertions.assertEquals(123456, usf.getInt(memory + 90));

            Assertions.assertEquals(0, usf.getLong(memory + 12));
            usf.putLong(memory + 12, 123456);
            Assertions.assertEquals(123456, usf.getLong(memory + 12));

            {
                int[] tmp = new int[]{5978, 487, 897, 791, 46786, 7876, 7978945, 467, 676, 76876, 4, 87, 45, 1245};
                long offset = 30L;
                usf.copyMemory(tmp, Unsafe.ARRAY_INT_BASE_OFFSET, null, memory + offset, tmp.length * Integer.BYTES);
                Assertions.assertEquals(Unsafe.ARRAY_INT_INDEX_SCALE, Integer.BYTES);
                for (int i = 0; i < tmp.length; i++) {
                    Assertions.assertEquals(tmp[i], usf.getInt(memory + offset + (i * Integer.BYTES)));
                }
                usf.setMemory(tmp, Unsafe.ARRAY_INT_BASE_OFFSET, tmp.length * Integer.BYTES, (byte) 0xAF);
                for (int j : tmp) {
                    Assertions.assertEquals((int) 0xAFAF_AFAF_AFAF_AFAFL, j);
                }
            }

            usf.freeMemory(memory);
        }
        // endregion
        // region Unsafe.throwException
        {
            System.out.println("Checking Unsafe.throwException");
            Throwable a = new Throwable();
            try {
                usf.throwException(a);
                throw new AssertionError();
            } catch (Throwable throwable) {
                //noinspection ConstantConditions
                if (throwable != a) {
                    throw new AssertionError(a);
                }
            }
        }
        // endregion
        // region Unsafe.ensureClassInitialized
        {
            System.out.println("Testing Unsafe.ensureClassInitialized");

            Assertions.assertFalse(TestClassInitialize.initialized);
            //noinspection ResultOfMethodCallIgnored
            TestClassInitialize.Initializer.class.toString();
            Assertions.assertFalse(TestClassInitialize.initialized);

            Assertions.assertTrue(usf.shouldBeInitialized(TestClassInitialize.Initializer.class));
            usf.ensureClassInitialized(TestClassInitialize.Initializer.class);
            Assertions.assertTrue(TestClassInitialize.initialized);
        }
        // endregion
    }

    @SuppressWarnings("deprecation")
    @TestTask(name = "check Root.getTrusted()")
    public static void checkTrusted() {
        // region check Root.getTrusted()
        String toString = Root.getTrusted().toString();
        if (toString.equals("/trusted")) {
            return;
        }

        // AdoptOpenJDK - jdk 11.0.9.11 openj9
        if (Root.getTrusted().lookupClass() == MethodHandle.class) {
            int modes = Root.getTrusted().lookupModes();
            if (modes == 0x40 || modes == 0x80) {
                if (toString.equals("java.lang.invoke.MethodHandle")) {
                    return;
                }
            }
        }

        Assertions.assertEquals("/trusted", Root.getTrusted().toString());
        // endregion
    }

    @SuppressWarnings("deprecation")
    @TestTask(name = "check set accessible")
    public static void checkSetAccessible() throws Exception {
        System.out.println("Checking AccessibleObject.setAccessible");
        Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        System.out.println(addURL.isAccessible());
        Assertions.assertFalse(addURL.isAccessible());
        System.out.println(Root.openAccess(addURL));
        System.out.println(addURL.isAccessible());
        Assertions.assertTrue(addURL.isAccessible());
    }

    @TestTask(name = "check Unsafe.defineClass")
    public static void testDefineClass() throws Exception {
        System.out.println("Checking class defining");
        ClassWriter writer1 = new ClassWriter(0),
                writer2 = new ClassWriter(0),
                writer3 = new ClassWriter(0),
                writer4 = new ClassWriter(0);
        writer1.visit(Opcodes.V1_8, 0, "testSwe/WEASawx", null, "java/lang/Object", null);
        writer2.visit(Opcodes.V1_8, 0, "testSwe/WEAS687", null, "java/lang/Object", null);
        writer3.visit(Opcodes.V1_8, 0, "testSwe/AAXWXu", null, "java/lang/Object", null);
        writer4.visit(Opcodes.V1_8, 0, "testSwe/AWZXaex", null, "java/lang/Object", null);

        byte[] code1 = writer1.toByteArray();
        byte[] code2 = writer2.toByteArray();
        byte[] code3 = writer3.toByteArray();
        byte[] code4 = writer4.toByteArray();

        ClassLoader loader = RunTestUnit.class.getClassLoader();

        Class<?> class1 = Unsafe.getUnsafe().defineClass(null, code1, 0, code1.length, loader, null);
        Class<?> class2 = Unsafe.getUnsafe().defineClass0(null, code2, 0, code2.length, loader, null);
        Class<?> class3 = Unsafe.getUnsafe().defineClass0(null, code3, 0, code3.length, null, null);
        Class<?> class4 = Unsafe.getUnsafe().defineAnonymousClass(class1, code4, null);

        Assertions.assertEquals("testSwe.WEASawx", class1.getName());
        Assertions.assertEquals("testSwe.WEAS687", class2.getName());
        Assertions.assertEquals("testSwe.AAXWXu", class3.getName());
        System.out.println("AnonymousClass: " + class4);
        Assertions.assertTrue(class4.getName().contains("/"), class4.getName());

        Assertions.assertSame(loader, class1.getClassLoader());
        Assertions.assertSame(loader, class2.getClassLoader());
        Assertions.assertSame(null, class3.getClassLoader());
        Assertions.assertSame(loader, class4.getClassLoader());
    }
}
