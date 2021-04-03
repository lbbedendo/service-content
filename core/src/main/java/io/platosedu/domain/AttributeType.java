package io.platosedu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AttributeType {

    private UUID id;
    private String name;
    private String slug;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String tenantId;
}
