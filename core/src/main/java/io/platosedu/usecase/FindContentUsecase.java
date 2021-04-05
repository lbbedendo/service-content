package io.platosedu.usecase;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Content;
import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindContentUsecase {
    Optional<Content> findOne(UUID id, String tenantId);
    Page<Content> findAll(Pageable pageable, ContentFilters filters, String tenantId);
    Page<Content> findAll(Pageable pageable, String tenantId);
    Page<Content> findFirstLevelChildrenOfContent(Pageable pageable, String tenantId, ContentFilters contentFilters);
    List<LinkedContentResponse> findAllLevelChildrenOfContent(UUID contentId,
                                                              ContentFilters contentFilters);
}
