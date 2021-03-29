package io.platosedu.usecase.dto;

import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentFilters {
    private Content.Type type;
    private List<String> tags;
    private Boolean active;
    private Boolean root;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String name;
    private String instructorUserName;
    private String text;
    private Boolean visible;
    private String parentId;
    private Set<Link.Modality.Type> linkModality;
    private Content.ProductionStatus productionStatus;
    private GraphSearchSettings graphSearchSettings;

    @Data
    @Builder
    public static class GraphSearchSettings {
        private static final int DEFAULT_GRAPH_SEARCH_DEPTH = 50;
        @Builder.Default
        private Integer maxDepth = DEFAULT_GRAPH_SEARCH_DEPTH;
    }
}
