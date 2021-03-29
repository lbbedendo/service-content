package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.persistence.ContentRepository;
import io.platosedu.usecase.UpdateContentUsecase;

import javax.inject.Singleton;

@Singleton
public class UpdateContentService implements UpdateContentUsecase {
    private final ContentRepository contentRepository;

    public UpdateContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content update(Content.ContentId id, TenantId tenantId, Content content) {
        return contentRepository.update(id, tenantId, content);
    }
}
