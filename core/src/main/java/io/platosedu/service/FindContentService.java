package io.platosedu.service;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Content;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.FindContentUsecase;
import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class FindContentService implements FindContentUsecase {
    private final ContentRepository contentRepository;

    public FindContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Optional<Content> findOne(UUID id, String tenantId) {
        return contentRepository.findOne(id, tenantId);
    }

    @Override
    public Page<Content> findAll(Pageable pageable, ContentFilters filters, String tenantId) {
        return contentRepository.findAll(pageable, filters, tenantId);
    }

    @Override
    public Page<Content> findAll(Pageable pageable, String tenantId) {
        return contentRepository.findAll(pageable, tenantId);
    }

    @Override
    public Page<Content> findFirstLevelChildrenOfContent(Pageable pageable, String tenantId, ContentFilters contentFilters) {
        return contentRepository.findFirstLevelChildrenOfContent(pageable, tenantId, contentFilters);
    }

    @Override
    public List<LinkedContentResponse> findAllLevelChildrenOfContent(UUID contentId, ContentFilters contentFilters) {
        return contentRepository.findAllLevelChildrenOfContent(contentId, contentFilters);
    }
}
