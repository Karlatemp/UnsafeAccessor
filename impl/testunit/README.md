# impl.testunit

`jdk.internal.misc.Unsafe` is an internal API.
The api is different on different JDKs.

This module is for verity UnsafeAccessor can run on different JDKs.

For the test. This unit will run with finally artifact `/impl/loader/libs/unsafe-accessor.jar`.

Start test unit with `gradle doBuild`

## That this module do

- Verity `Root.getTrusted()` is `/trusted`
- Verity `Root.openAccess` working
- Verity `Unsafe.defineClass`, `Unsafe.defineClass0` working
- Verity `Unsafe.get**`, `Unsafe.put**` working
- Verity `Unsafe.allocateInstance` working
- Verity `Unsafe Memory Access` working
    - `Unsafe.allocateMemory`
    - `Unsafe.setMemory`
    - `Unsafe.copyMemory`
    - `Unsafe.freeMemory`
- Verity `Unsafe.throwException` working
- Verity `Unsafe.ensureClassInitialized` working
- Verity [Binary Compatibility](src/main/java/io/github/karlatemp/unsafeaccessor/BinaryCompatibilityAnalysis.java)
    - Verity all abstract methods of `io.github.karlatemp.unsafeaccessor.Unsafe` implemented
    - Verity not throw `NoSuchMethodError` in accessing `jdk.internal.misc.Unsafe`
        - `invokeCleaner(ByteBuffer)` not included. They are not defined in Java8

## Adding JDK test

Create a new file. named `vms.txt` in `/impl/testunit`

Add your JDK's top-path into it.

Example:

```text
C:\Program Files\AdoptOpenJDK\jdk-12.0.2.10-hotspot
C:\Program Files\AdoptOpenJDK\jdk-11.0.9.11-openj9
C:\Program Files\AdoptOpenJDK\jdk-14.0.2.12-hotspot
C:\Program Files\Java\jdk1.8.0_181
C:\Program Files\Java\jdk-13.0.2
C:\Program Files\Java\jdk-15
C:\Program Files\Amazon Corretto\jdk1.8.0_252
C:\Program Files\Java\jdk-11.0.9
C:\Program Files\Java\jdk-16
```