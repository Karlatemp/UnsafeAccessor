package io.github.karlatemp.unsafeaccessor;

import jdk.internal.access.JavaLangAccess;
import jdk.internal.access.SharedSecrets;

import java.io.*;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.security.AllPermission;
import java.security.ProtectionDomain;

class Open9 extends ClassLoader {
    private Open9() {
        super(Open9.class.getClassLoader());
    }

    static class Injector {
        static {
            Class<?> klass = Injector.class;
            Module module = klass.getModule();
            Module open = klass.getClassLoader().getClass().getModule();
            module.addExports(klass.getPackageName(), open);
            JavaLangAccess javaLangAccess = SharedSecrets.getJavaLangAccess();
            javaLangAccess.addExports(Object.class.getModule(), "jdk.internal.misc", open);
        }
    }

    static byte[] replace(byte[] source, byte[] replace, byte[] target) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(source.length);
        int sourceLength = source.length,
                replaceLength = replace.length,
                targetLength = target.length,
                replaceLengthR1 = replaceLength - 1;
        root:
        for (int i = 0; i < sourceLength; i++) {
            if (i + replaceLength <= sourceLength) {
                for (int z = 0; z < replaceLength; z++) {
                    if (replace[z] != source[i + z]) {
                        outputStream.write(source[i]);
                        continue root;
                    }
                }
                outputStream.write(target, 0, targetLength);
                i += replaceLengthR1;
            } else {
                outputStream.write(source[i]);
            }
        }
        return outputStream.toByteArray();
    }

    static byte[] replace(byte[] classfile, String const1, String const2) {
        return replace(classfile, toJvm(const1), toJvm(const2));
    }

    static byte[] toJvm(String const0) {
        byte[] bytes = const0.getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length + 2);
        try {
            new DataOutputStream(bos).writeShort(bytes.length);
        } catch (IOException ioException) {
            throw new AssertionError(ioException);
        }
        bos.write(bytes, 0, bytes.length);
        return bos.toByteArray();
    }

    Class<?> define(byte[] code) {
        ProtectionDomain domain = new ProtectionDomain(null,
                new AllPermission().newPermissionCollection()
        );
        return defineClass(null, code, 0, code.length, domain);
    }

    static Unsafe open() {
        Open9 loader = new Open9();
        try (InputStream source = Open9.class.getResourceAsStream("Open9$Injector.class")) {
            byte[] data = source.readAllBytes();
            Class<?> JLA = Class.forName("jdk.internal.access.JavaLangAccess");
            Object proxy = Proxy.newProxyInstance(loader, new Class[]{JLA}, (proxy0, method, args) -> null);
            String namespace = proxy.getClass().getPackageName();
            String targetName = namespace + ".Injector",
                    targetJvmName = targetName.replace('.', '/');
            data = replace(data, "io/github/karlatemp/unsafeaccessor/Open9$Injector", targetJvmName);
            data = replace(data, "Lio/github/karlatemp/unsafeaccessor/Open9$Injector;", "L" + targetJvmName + ";");
            Class<?> injectorClass = loader.define(data);
            Class.forName(injectorClass.getName(), true, loader);
        } catch (Exception exception) {
            throw new ExceptionInInitializerError(exception);
        }
        return new Impl9();
    }
}
