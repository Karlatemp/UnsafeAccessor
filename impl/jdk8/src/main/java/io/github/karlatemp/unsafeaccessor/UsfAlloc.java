package io.github.karlatemp.unsafeaccessor;

abstract class UsfAlloc {

    Unsafe newUnsafe(UsfAllocCtx ctx) throws Exception {
        return ctx.newUsfImpl(this);
    }

    void prepare(UsfAllocCtx ctx) throws Exception {
    }

    void destroyLimit(UsfAllocCtx ctx) throws Exception {
    }

    void completed(UsfAllocCtx ctx) throws Exception {
    }

    ModuleAccess newModuleAccess(UsfAllocCtx ctx) throws Exception {
        return new ModuleAccessImpl.Noop();
    }

    UsfAlloc checkSelectedRequirement() throws Exception {
        return this;
    }
}
