package io.platosedu.adapter.out.persistence.mapper;

import io.platosedu.domain.Tag;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TagIdMapper {
    default Tag.TagId fromString(String id) {
        return new Tag.TagId(id);
    }

    default String toString(Tag.TagId id) {
        return id != null ? id.getValue() : null;
    }
}
