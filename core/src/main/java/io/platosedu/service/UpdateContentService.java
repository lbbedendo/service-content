package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.UpdateContentUsecase;

import javax.inject.Singleton;

@Singleton
public class UpdateContentService implements UpdateContentUsecase {
    private final ContentRepository contentRepository;

    public UpdateContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content update(Content.ContentId id, Content content) {
        return contentRepository.update(id, content);
    }
}
