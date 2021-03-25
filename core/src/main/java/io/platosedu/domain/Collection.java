package io.platosedu.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Collection {

    private ObjectId id;
    private String name;
    private String description;
    private Boolean active;
    private String createdByUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String tenantId;

    public Collection(ObjectId id,
                      String name,
                      String description,
                      Boolean active,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt,
                      String tenantId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tenantId = tenantId;
    }

}
