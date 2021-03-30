package io.platosedu.adapter.in.dto.request;

import io.micronaut.core.annotation.Introspected;
import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import lombok.Data;
import org.bson.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Introspected
public class ContentRequest {
    @NotNull
    private Content.Type type;
    @NotBlank
    private String name;
    private String description;
    private Boolean root;
    private Document data;
    private List<Document> attributes;
    private String lang;
    private List<String> tags;
    private Integer questions;
    private String path;
    private Integer examAttempts;
    private Boolean visible;
    private String isbn;
    private Boolean preview;
    private List<Link.Modality.Type> modalities;
    private Content.ProductionStatus productionStatus;
    private Boolean libras;
    private Content.VideoType videoType;
    private String cedoc;
    private Integer examTimeoutInMinutes;
}
