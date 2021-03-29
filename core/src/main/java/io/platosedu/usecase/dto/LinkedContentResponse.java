package io.platosedu.usecase.dto;

import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkedContentResponse {
    private Link link;
    private Content childContent;
    private Integer level;
}
