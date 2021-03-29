package io.platosedu.persistence;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;

import java.util.List;
import java.util.Optional;

public interface ContentRepository {
    Content inactivate(Content.ContentId id, TenantId tenantId);
    Content create(Content content, TenantId tenantId);
    Content update(Content.ContentId id, TenantId tenantId, Content content);
    Optional<Content> findOne(Content.ContentId id, TenantId tenantId);
    Page<Content> findAll(Pageable pageable, ContentFilters filters, TenantId tenantId);
    Page<Content> findAllChildrenOfContent(Pageable pageable, TenantId tenantId, ContentFilters filters);
    List<LinkedContentResponse> findAllLevelChildrenOfContent(String contentId, ContentFilters filters);
}
