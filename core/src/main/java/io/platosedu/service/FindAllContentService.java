package io.platosedu.service;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.FindAllContentUsecase;
import io.platosedu.usecase.dto.ContentFilters;

import javax.inject.Singleton;

@Singleton
public class FindAllContentService implements FindAllContentUsecase {
    private final ContentRepository contentRepository;

    public FindAllContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Page<Content> findAll(Pageable pageable, ContentFilters filters, TenantId tenantId) {
        return contentRepository.findAll(pageable, filters, tenantId);
    }

    @Override
    public Page<Content> findAll(Pageable pageable, TenantId tenantId) {
        return contentRepository.findAll(pageable, tenantId);
    }
}
