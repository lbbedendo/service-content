package io.platosedu.usecase;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.usecase.dto.ContentFilters;

public interface FindAllContentUsecase {
    Page<Content> findAll(Pageable pageable, ContentFilters filters, TenantId tenantId);
    Page<Content> findAll(Pageable pageable, TenantId tenantId);
}
