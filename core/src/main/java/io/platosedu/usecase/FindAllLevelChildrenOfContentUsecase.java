package io.platosedu.usecase;

import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;
import org.bson.types.ObjectId;

import java.util.List;

public interface FindAllLevelChildrenOfContentUsecase {
    List<LinkedContentResponse> findAllLevelChildrenOfContent(ObjectId contentId,
                                                              ContentFilters contentFilters);
}
