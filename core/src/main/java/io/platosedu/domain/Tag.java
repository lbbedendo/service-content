package io.platosedu.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Tag {

    private ObjectId id;
    private String name;
    private Boolean active;
    private String createdByUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String tenantId;

    public Tag(ObjectId id,
               String name,
               Boolean active,
               LocalDateTime updatedAt,
               LocalDateTime createdAt,
               String tenantId) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.tenantId = tenantId;
    }

}
