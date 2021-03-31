package io.platosedu.port.out;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.domain.Content;
import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ContentRepository {
    Content inactivate(ObjectId id, String tenantId);
    Content create(Content content);
    Content update(ObjectId id, Content content);
    Optional<Content> findOne(ObjectId id, String tenantId);
    Page<Content> findAll(Pageable pageable, ContentFilters filters, String tenantId);
    Page<Content> findAll(Pageable pageable, String tenantId);
    Page<Content> findFirstLevelChildrenOfContent(Pageable pageable, String tenantId, ContentFilters filters);
    List<LinkedContentResponse> findAllLevelChildrenOfContent(ObjectId contentId, ContentFilters filters);
}
