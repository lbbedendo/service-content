package io.platosedu.usecase;

import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;

import java.util.List;
import java.util.UUID;

public interface FindAllLevelChildrenOfContentUsecase {
    List<LinkedContentResponse> findAllLevelChildrenOfContent(UUID contentId,
                                                              ContentFilters contentFilters);
}
