package io.platosedu.adapter.out.persistence.mapper;

import io.platosedu.adapter.in.dto.request.ContentRequest;
import io.platosedu.adapter.in.dto.response.ContentResponse;
import io.platosedu.domain.Content;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "jsr330",
        uses = {UUIDMapper.class,
                DocumentMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Content fromContentRequest(ContentRequest contentRequest, Boolean active, String tenantId, String createdByUserId);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdByUserId", ignore = true)
    Content fromContentRequest(ContentRequest contentRequest, Boolean active, String tenantId);

    ContentResponse toContentResponse(Content content);
}
