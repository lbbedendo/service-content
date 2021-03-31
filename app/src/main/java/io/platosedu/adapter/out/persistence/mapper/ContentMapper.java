package io.platosedu.adapter.out.persistence.mapper;

import io.platosedu.adapter.in.dto.request.ContentRequest;
import io.platosedu.adapter.in.dto.response.ContentResponse;
import io.platosedu.domain.Content;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "jsr330",
        uses = {ObjectIdMapper.class,
                DocumentMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContentMapper {
    Content fromContentRequest(ContentRequest contentRequest, Boolean active, String tenantId, String createdByUserId);

    Content fromContentRequest(ContentRequest contentRequest, Boolean active, String tenantId);

    ContentResponse toContentResponse(Content content);
}
