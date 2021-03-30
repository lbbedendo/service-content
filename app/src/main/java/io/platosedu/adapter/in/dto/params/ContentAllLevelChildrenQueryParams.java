package io.platosedu.adapter.in.dto.params;

import io.platosedu.domain.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentAllLevelChildrenQueryParams {
    private Integer maxDepth;
    private Boolean active;
    private Boolean visible;
    private Set<Link.Modality.Type> linkModality;
}
