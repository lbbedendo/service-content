package io.platosedu.adapter.out.persistence.content;

import io.platosedu.adapter.out.persistence.BaseMongoDocument;
import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContentDocument extends BaseMongoDocument {
    private Content.Type type;
    private String name;
    private String description;
    private Boolean root;
    private Boolean active;
    private Document data;
    private List<Document> attributes;
    private String lang;
    private List<ObjectId> tags;
    private Integer questions;
    private String path;
    private Integer examAttempts;
    private Boolean visible;
    private String isbn;
    private Boolean preview;
    private LocalDateTime deletedAt;
    private String createdByUserId;
    private List<Link.Modality.Type> modalities;
    private Content.ProductionStatus productionStatus;
    private Boolean libras;
    private Content.VideoType videoType;
    private String cedoc;
    private Integer examTimeoutInMinutes;
}
