package runtest.subp.jrefw;

import io.github.karlatemp.javarefwrap.ConstructorInvokeCallback;
import io.github.karlatemp.javarefwrap.JavaReflectionWrap;
import io.github.karlatemp.javarefwrap.MethodInvokeCallback;
import io.github.karlatemp.javarefwrap.fa.ByteFieldAccessCallback;
import io.github.karlatemp.unsafeaccessor.UnsafeAccess;
import runtest.TestTask;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.util.Arrays;

public class JRefWrapTest {

    @TestTask
    void test() throws Throwable {
        UnsafeAccess access = UnsafeAccess.getInstance();
        JavaReflectionWrap instance = JavaReflectionWrap.newInstance(access);
        Constructor<?> constructor = instance.newConstructor(Object.class, null, null, Modifier.PUBLIC, new ConstructorInvokeCallback() {
            @Override
            public Object newInstance(Object[] args) throws InstantiationException, IllegalArgumentException, InvocationTargetException {
                System.out.println(Arrays.toString(args));
                return new Throwable("Stack trace");
            }
        });

        System.out.println(constructor);
        Object o = constructor.newInstance();
        System.out.println(o);
        ((Throwable) o).printStackTrace(System.out);

        Method method = instance.newMethod(Object.class, "test", void.class, null, null, Modifier.PUBLIC | Modifier.STATIC, new MethodInvokeCallback() {
            @Override
            public Object invoke(Object obj, Object[] args) throws IllegalArgumentException, InvocationTargetException {
                System.out.println("this: " + obj + ", " + Arrays.toString(args));
                new Throwable("Stack trace").printStackTrace(System.out);
                return null;
            }
        });
        System.out.println(method);
        method.invoke(null, (Object[]) null);

        Field field = instance.newField(String.class, "test", int.class, Modifier.PUBLIC | Modifier.STATIC, new ByteFieldAccessCallback() {
            @Override
            protected void setByte0(Object obj, byte b) {
            }

            @Override
            public byte getByte(Object obj) throws IllegalArgumentException {
                return 0;
            }
        }, null);
        System.out.println(field);
        System.out.println(field.getByte(null));
        access.openAccess(field);
    }
}
