package io.platosedu.service;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Content;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.FindFirstLevelChildrenOfContentUsecase;
import io.platosedu.usecase.dto.ContentFilters;

import javax.inject.Singleton;

@Singleton
public class FindFirstLevelChildrenOfContentService implements FindFirstLevelChildrenOfContentUsecase {
    private final ContentRepository contentRepository;

    public FindFirstLevelChildrenOfContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Page<Content> findFirstLevelChildrenOfContent(Pageable pageable,
                                                         String tenantId,
                                                         ContentFilters contentFilters) {
        return contentRepository.findFirstLevelChildrenOfContent(pageable, tenantId, contentFilters);
    }
}
