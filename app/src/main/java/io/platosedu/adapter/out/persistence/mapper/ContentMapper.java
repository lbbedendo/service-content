package io.platosedu.adapter.out.persistence.mapper;

import io.platosedu.adapter.in.dto.request.ContentRequest;
import io.platosedu.adapter.in.dto.response.ContentResponse;
import io.platosedu.adapter.out.persistence.content.ContentDocument;
import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "jsr330",
        uses = {ObjectIdMapper.class,
                ContentIdMapper.class,
                TagIdMapper.class,
                TenantIdMapper.class,
                DocumentMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContentMapper {
    Content toDomainEntity(ContentDocument contentDocument);

    ContentDocument toDocument(Content content, TenantId tenantId);

    Content fromContentRequest(ContentRequest contentRequest, String createdByUserId);

    ContentResponse toContentResponse(Content content);
}
