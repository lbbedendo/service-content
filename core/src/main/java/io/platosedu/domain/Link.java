package io.platosedu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Link {

    private LinkId id;
    private Content.ContentId contentId;
    private Content.ContentId parentId;
    private List<Modality> modalities;
    private Integer contentPosition;
    private Collection.CollectionId collectionId;
    private String createdByUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TenantId tenantId;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Modality {
        private ModalityId id;
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

        @Value
        public static class ModalityId {
            ObjectId id;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Period {
        private Integer start;
        private Integer end;
    }

    @Value
    public static class LinkId {
        ObjectId id;
    }
}
