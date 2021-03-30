package io.platosedu.adapter.in.dto.params;

import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import io.platosedu.exception.InvalidDateRangeException;
import io.platosedu.usecase.dto.ContentFilters;
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
public class ContentQueryParams {
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
    private Link.Modality.Type linkModality;
    private Content.ProductionStatus productionStatus;

    public void validateDateRange() {
        if ((startDate == null && endDate != null) || (startDate != null && endDate == null)) {
            throw new InvalidDateRangeException("Preencha ambos os filtros \"startDate\" e \"endDate\"");
        }
        if (startDate != null && startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException("\"startDate\" não pode ser maior que \"endDate\"");
        }
    }

    public static ContentQueryParams empty() {
        return new ContentQueryParams();
    }

    public ContentFilters toContentFilters() {
        return ContentFilters.builder()
                .type(type)
                .tags(tags)
                .active(active)
                .root(root)
                .startDate(startDate)
                .endDate(endDate)
                .name(name)
                .instructorUserName(instructorUserName)
                .text(text)
                .visible(visible)
                .linkModality(linkModality != null ? Set.of(linkModality) : null)
                .productionStatus(productionStatus)
                .build();
    }
}
