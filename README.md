# Unsafe Accessor

Here is a bridge to access Unsafe.

UnsafeAccessor is available on MavenCentral

## Use

```groovy
dependencies {
    compile("io.github.karlatemp:unsafe-accessor:1.6.0")
}
```

```xml
<dependency>
  <groupId>io.github.karlatemp</groupId>
  <artifactId>unsafe-accessor</artifactId>
  <version>1.6.0</version>
</dependency>
```

## Build

1. Clone this project
2. Open with IDEA
3. Select `Import Gradle Project`. Then wait...
4. Open `Project Structure... > Project Settings > Project`.
   Select Project SDK `JDK 9+`
5. Open `Settings > Build, Execution, Deployment > Build Tools > Gradle`.
   Change `Gradle JVM` to `Project JDK`
6. Run Gradle task `:impl.testunit:doBuild`
7. Get output artifact in `impl/loader/build/libs`

# Use example
[TestUnsafe.java](./impl/testunit/src/main/java/runtest/TestUnsafe.java)











