package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.UpdateContentUsecase;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class UpdateContentService implements UpdateContentUsecase {
    private final ContentRepository contentRepository;

    public UpdateContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content update(UUID id, Content content) {
        return contentRepository.update(id, content);
    }
}
