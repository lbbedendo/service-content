package io.platosedu.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class AttributeType {

    private ObjectId id;
    private String name;
    private String slug;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String tenantId;

}
