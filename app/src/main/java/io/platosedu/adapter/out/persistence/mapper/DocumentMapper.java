package io.platosedu.adapter.out.persistence.mapper;

import org.bson.Document;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "jsr330", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DocumentMapper {
    default Document fromMap(Map<String, Object> map) {
        return new Document(map);
    }

    default Map<String, Object> toMap(Document document) {
        var result = new LinkedHashMap<String, Object>();
        if (document != null) {
            for (Map.Entry<String, Object> entry : document.entrySet()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    default List<Map<String, Object>> toMapList(List<Document> documents) {
        return documents != null
                ? documents.stream()
                .map(this::toMap)
                .collect(Collectors.toList())
                : null;
    }
}
