package io.platosedu.adapter.out.persistence.mapper;

import io.platosedu.utilities.UUIDUtil;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Mapper(componentModel = "jsr330", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UUIDMapper {

    default UUID fromString(String id) {
        return UUIDUtil.fromStringOrNull(id);
    }

    default String toString(UUID id) {
        return id != null ? id.toString() : null;
    }

    default List<String> toString(List<UUID> ids) {
        return ids != null ? ids.stream().map(UUID::toString).collect(toList()) : List.of();
    }

    default List<UUID> fromString(List<String> ids) {
        return ids != null ? ids.stream().map(UUID::fromString).collect(toList()) : List.of();
    }
}
