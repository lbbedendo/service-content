package io.platosedu.adapter.out.persistence.mapper;

import io.platosedu.domain.TenantId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TenantIdMapper {
    default TenantId fromString(String tenantId) {
        return new TenantId(tenantId);
    }

    default String toString(TenantId tenantId) {
        return tenantId != null ? tenantId.getValue() : null;
    }
}
