package io.platosedu.usecase;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Content;
import io.platosedu.usecase.dto.ContentFilters;

public interface FindFirstLevelChildrenOfContentUsecase {
    Page<Content> findFirstLevelChildrenOfContent(Pageable pageable, String tenantId, ContentFilters contentFilters);
}
