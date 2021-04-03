package io.platosedu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Link {

    private UUID id;
    private UUID contentId;
    private UUID parentId;
    private List<Modality> modalities;
    private Integer contentPosition;
    private UUID collectionId;
    private String createdByUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String tenantId;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Modality {
        private UUID id;
        private Type type;
        private Period period;
        private Double weight;
        private Double avg;
        private Integer totalWorkload;
        private Integer theoreticalWorkload;
        private Integer practicalWorkload;
        private Period examRequestPeriod;
        private Integer periodExtension;

        public enum Type {
            ALL,
            PRESENCIAL,
            EAD,
            SEMI_PRESENCIAL,
            INTENSIVO,
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Period {
        private Integer start;
        private Integer end;
    }
}
