package io.platosedu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AttributeType {

    private AttributeTypeId id;
    private String name;
    private String slug;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String tenantId;

    @Value
    public static class AttributeTypeId {
        private final ObjectId id;
    }
}
