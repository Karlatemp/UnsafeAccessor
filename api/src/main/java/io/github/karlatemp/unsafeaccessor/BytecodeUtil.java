package io.github.karlatemp.unsafeaccessor;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AllPermission;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;
import java.util.Base64;

public class BytecodeUtil {

    public static byte[] replace(byte[] source, byte[] replace, byte[] target) {
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

    public static byte[] replace(byte[] classfile, String const1, String const2) {
        return replace(classfile, toJvm(const1), toJvm(const2));
    }

    public static byte[] replaceC(byte[] c, String f, String t) {
        c = replace(c, f, t);
        c = replace(c, "L" + f + ";", "L" + t + ";");
        return c;
    }

    public static byte[] toJvm(String const0) {
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

    public static class CLoader extends ClassLoader {
        private final ProtectionDomain domain;

        public CLoader() {
            this(ClassLoader.getSystemClassLoader());
        }

        public CLoader(ClassLoader parent) {
            super(parent);
            AllPermission ap = new AllPermission();
            PermissionCollection pc = ap.newPermissionCollection();
            pc.add(ap);
            pc.setReadOnly();
            domain = new ProtectionDomain(null, pc);
        }

        public Class<?> load(byte[] code) {
            return defineClass(null, code, 0, code.length, domain);
        }

        public Class<?> load(String code) {
            return load(Base64.getDecoder().decode(code));
        }
    }
}
