package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.InactivateContentUsecase;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class InactivateContentService implements InactivateContentUsecase {
    private final ContentRepository contentRepository;

    public InactivateContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content inactivate(UUID id, String tenantId) {
        return contentRepository.inactivate(id, tenantId);
    }
}
