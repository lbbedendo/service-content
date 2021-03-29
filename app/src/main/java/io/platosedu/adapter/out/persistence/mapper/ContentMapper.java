package io.platosedu.adapter.out.persistence.mapper;

import io.platosedu.adapter.out.persistence.ContentDocument;
import io.platosedu.domain.Content;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "jsr330",
        uses = {ObjectIdMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContentMapper {
    Content toDomainEntity(ContentDocument contentDocument);

    ContentDocument toDocument(Content content, String tenantId);
}
