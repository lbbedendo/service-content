package io.platosedu.adapter.out.persistence.mapper;

import io.platosedu.utilities.ObjectIdUtil;
import org.bson.types.ObjectId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "jsr330", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ObjectIdMapper {

    default ObjectId fromString(String id) {
        return ObjectIdUtil.fromStringOrNull(id);
    }

    default String toString(ObjectId id) {
        return id != null ? id.toHexString() : null;
    }

    default List<String> toString(List<ObjectId> ids) {
        return ids != null ? ids.stream().map(ObjectId::toHexString).collect(toList()) : List.of();
    }

    default List<ObjectId> fromString(List<String> ids) {
        return ids != null ? ids.stream().map(ObjectId::new).collect(toList()) : List.of();
    }
}
