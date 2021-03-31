package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.FindOneContentUsecase;
import org.bson.types.ObjectId;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class FindOneContentService implements FindOneContentUsecase {
    private final ContentRepository contentRepository;

    public FindOneContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Optional<Content> findOne(ObjectId id, String tenantId) {
        return contentRepository.findOne(id, tenantId);
    }
}
