package io.platosedu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Content {

    private ContentId id;
    private Type type;
    private String name;
    private String description;
    private Boolean root;
    private Boolean active;
    private Document data;
    private List<Document> attributes;
    private String lang;
    private List<Tag.TagId> tags;
    private Integer questions;
    private String path;
    private Integer examAttempts;
    private Boolean visible;
    private String isbn;
    private Boolean preview;
    private LocalDateTime deletedAt;
    private String createdByUserId;
    private List<Link.Modality.Type> modalities;
    private ProductionStatus productionStatus;
    private Boolean libras;
    private VideoType videoType;
    private String cedoc;
    private Integer examTimeoutInMinutes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TenantId tenantId;

    public enum Type {
        FOLDER,
        BOOK,
        DISCUSSION,
        FILE,
        MARKDOWN,
        VIDEO,
        EXAM_RECOVERY(true),
        EXAM(true),
        FORUM,
        GRADE,
        ATTENDANCE,
        GRADE_ATTENDANCE,
        GRADE_SUB,
        GRADE_PDR,
        ATTENDANCE_SUB,
        ATTENDANCE_PDR,
        GRADE_ATTENDANCE_SUB,
        GRADE_ATTENDANCE_PDR;

        private boolean isExam = false;

        Type() {
        }

        Type(boolean isExam) {
            this.isExam = isExam;
        }

        public boolean isExam() {
            return isExam;
        }

        public static Set<Type> getExamTypes() {
            return Stream.of(Type.values())
                    .filter(contentType -> contentType.isExam)
                    .collect(Collectors.toSet());
        }
    }

    public enum ProductionStatus {
        LAYOUT,
        REVIEW,
        LAYOUT_ADJUSTMENT,
        DONE;

    }

    public enum VideoType {
        CLASS,
        PRACTICAL_CLASS,
        PRESENTATION,
        THEORY_INTO_PRACTICE,
        TIP,
        INTERVIEW,
        PROBLEM_SITUATION
    }

    @Value
    public static class ContentId {
        ObjectId id;
    }
}

