package io.platosedu.adapter.in.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import lombok.Data;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ContentResponse {
    @JsonSerialize(using = ToStringSerializer.class)
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Content.Type type;
    private String name;
    private String description;
    private Boolean root;
    private Boolean active;
    private Document data;
    private List<Document> attributes;
    private String lang;
    private List<String> tags;
    private Integer questions;
    private String path;
    private Integer examAttempts;
    private Boolean visible;
    private String isbn;
    private String tenantId;
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
