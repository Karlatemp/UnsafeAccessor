package io.github.karlatemp.unsafeaccessor;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AllPermission;
import java.security.ProtectionDomain;
import java.util.Base64;

class BytecodeUtil {

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

    static class CLoader extends ClassLoader {
        ProtectionDomain domain = new ProtectionDomain(
                null, new AllPermission().newPermissionCollection()
        );

        Class<?> load(byte[] code) {
            return defineClass(null, code, 0, code.length, domain);
        }

        Class<?> load(String code) {
            return load(Base64.getDecoder().decode(code));
        }
    }
}
