package io.platosedu.adapter.in.controller;

import io.micronaut.multitenancy.tenantresolver.TenantResolver;

public class MultiTenantController {
    protected final TenantResolver tenantResolver;

    public MultiTenantController(TenantResolver tenantResolver) {
        this.tenantResolver = tenantResolver;
    }

    public String resolveTenantId() {
        return tenantResolver.resolveTenantIdentifier().toString();
    }
}
