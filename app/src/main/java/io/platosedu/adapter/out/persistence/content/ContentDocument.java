package io.platosedu.adapter.out.persistence.content;

import io.platosedu.adapter.out.persistence.BaseMongoDocument;
import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
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

    @Builder
    public ContentDocument(ObjectId id,
                           Content.Type type,
                           String name,
                           String description,
                           Boolean root,
                           Boolean active,
                           Document data,
                           List<Document> attributes,
                           String lang,
                           List<ObjectId> tags,
                           Integer questions,
                           String path,
                           Integer examAttempts,
                           Boolean visible,
                           String isbn,
                           Boolean preview,
                           LocalDateTime deletedAt,
                           String createdByUserId,
                           List<Link.Modality.Type> modalities,
                           Content.ProductionStatus productionStatus,
                           Boolean libras,
                           Content.VideoType videoType,
                           String cedoc,
                           Integer examTimeoutInMinutes,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt,
                           String tenantId) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.root = root;
        this.active = active;
        this.data = data;
        this.attributes = attributes;
        this.lang = lang;
        this.tags = tags;
        this.questions = questions;
        this.path = path;
        this.examAttempts = examAttempts;
        this.visible = visible;
        this.isbn = isbn;
        this.preview = preview;
        this.deletedAt = deletedAt;
        this.createdByUserId = createdByUserId;
        this.modalities = modalities;
        this.productionStatus = productionStatus;
        this.libras = libras;
        this.videoType = videoType;
        this.cedoc = cedoc;
        this.examTimeoutInMinutes = examTimeoutInMinutes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tenantId = tenantId;
    }
}
