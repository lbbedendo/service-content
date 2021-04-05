package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.SaveContentUsecase;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class SaveContentService implements SaveContentUsecase {
    private final ContentRepository contentRepository;

    public SaveContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content create(Content content) {
        return contentRepository.create(content);
    }

    @Override
    public Content inactivate(UUID id, String tenantId) {
        return contentRepository.inactivate(id, tenantId);
    }

    @Override
    public Content update(UUID id, Content content) {
        return contentRepository.update(id, content);
    }
}
