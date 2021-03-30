package io.platosedu.adapter.out.persistence.mapper;

import io.platosedu.domain.Content;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ContentIdMapper {
    default Content.ContentId fromString(String id) {
        return new Content.ContentId(id);
    }

    default String toString(Content.ContentId id) {
        return id != null ? id.getValue() : null;
    }
}
