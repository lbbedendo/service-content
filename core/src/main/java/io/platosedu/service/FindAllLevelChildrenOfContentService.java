package io.platosedu.service;

import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.FindAllLevelChildrenOfContentUsecase;
import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;
import org.bson.types.ObjectId;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class FindAllLevelChildrenOfContentService implements FindAllLevelChildrenOfContentUsecase {
    private final ContentRepository contentRepository;

    public FindAllLevelChildrenOfContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public List<LinkedContentResponse> findAllLevelChildrenOfContent(ObjectId contentId,
                                                                     ContentFilters contentFilters) {
        return contentRepository.findAllLevelChildrenOfContent(contentId, contentFilters);
    }
}
