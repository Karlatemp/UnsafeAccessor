# impl.codegen

This module for generate jvm bytecode without sources.

Generated classes will save to `/impl/loader`

# Run

`./gradlew :impl.codegen:runCodeGen`

Or execute [RunCodeGen.java](src/test/java/io/github/karlatemp/unsafeaccessor/RunCodeGen.java)

# Register new generator

Add a new java class in
[codegen](src/test/java/io/github/karlatemp/unsafeaccessor/codegen)
direction with `public static void main(String[] args)`


