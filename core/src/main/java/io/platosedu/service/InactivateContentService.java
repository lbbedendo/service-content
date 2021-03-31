package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.InactivateContentUsecase;
import org.bson.types.ObjectId;

import javax.inject.Singleton;

@Singleton
public class InactivateContentService implements InactivateContentUsecase {
    private final ContentRepository contentRepository;

    public InactivateContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content inactivate(ObjectId id, String tenantId) {
        return contentRepository.inactivate(id, tenantId);
    }
}
