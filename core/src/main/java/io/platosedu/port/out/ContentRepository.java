package io.platosedu.port.out;

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
    Content create(Content content);
    Content update(Content.ContentId id, Content content);
    Optional<Content> findOne(Content.ContentId id, TenantId tenantId);
    Page<Content> findAll(Pageable pageable, ContentFilters filters, TenantId tenantId);
    Page<Content> findAll(Pageable pageable, TenantId tenantId);
    Page<Content> findFirstLevelChildrenOfContent(Pageable pageable, TenantId tenantId, ContentFilters filters);
    List<LinkedContentResponse> findAllLevelChildrenOfContent(Content.ContentId contentId, ContentFilters filters);
}
