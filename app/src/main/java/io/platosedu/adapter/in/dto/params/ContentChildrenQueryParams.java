package io.platosedu.adapter.in.dto.params;

import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentChildrenQueryParams {
    private String name;
    private String text;
    private Boolean visible;
    private Boolean active;
    private Content.Type type;
    private Set<Link.Modality.Type> linkModality;

    public static ContentChildrenQueryParams empty() {
        return new ContentChildrenQueryParams();
    }
}
