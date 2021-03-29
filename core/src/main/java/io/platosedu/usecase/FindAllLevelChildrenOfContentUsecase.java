package io.platosedu.usecase;

import io.platosedu.domain.Content;
import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;

import java.util.List;

public interface FindAllLevelChildrenOfContentUsecase {
    List<LinkedContentResponse> findAllLevelChildrenOfContent(Content.ContentId contentId,
                                                              ContentFilters contentFilters);
}
