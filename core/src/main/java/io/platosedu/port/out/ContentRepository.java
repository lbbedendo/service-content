package io.platosedu.port.out;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Content;
import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentRepository {
    Content inactivate(UUID id, String tenantId);
    Content create(Content content);
    Content update(UUID id, Content content);
    Optional<Content> findOne(UUID id, String tenantId);
    Page<Content> findAll(Pageable pageable, ContentFilters filters, String tenantId);
    Page<Content> findAll(Pageable pageable, String tenantId);
    Page<Content> findFirstLevelChildrenOfContent(Pageable pageable, String tenantId, ContentFilters filters);
    List<LinkedContentResponse> findAllLevelChildrenOfContent(UUID contentId, ContentFilters filters);
}
