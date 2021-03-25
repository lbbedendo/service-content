package io.platosedu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Link {

    private ObjectId id;
    private ObjectId contentId;
    private ObjectId parentId;
    private List<Modality> modalities;
    private Integer contentPosition;
    private ObjectId collectionId;
    private String createdByUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String tenantId;

    @Data
    public static class Modality {
        private ObjectId id;
        private Type modality;
        private Period period;
        private Double weight;
        private Double avg;
        private Integer totalWorkload;
        private Integer theoreticalWorkload;
        private Integer practicalWorkload;
        private Period examRequestPeriod;
        private Integer periodExtension;

        public Modality() {
        }

        @Builder
        public Modality(ObjectId id,
                        Type modality,
                        Period period,
                        Double weight,
                        Double avg,
                        Integer totalWorkload,
                        Integer theoreticalWorkload,
                        Integer practicalWorkload,
                        Period examRequestPeriod,
                        Integer periodExtension) {
            this.id = id;
            this.modality = modality;
            this.period = period;
            this.weight = weight;
            this.avg = avg;
            this.totalWorkload = totalWorkload;
            this.theoreticalWorkload = theoreticalWorkload;
            this.practicalWorkload = practicalWorkload;
            this.examRequestPeriod = examRequestPeriod;
            this.periodExtension = periodExtension;
        }

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
