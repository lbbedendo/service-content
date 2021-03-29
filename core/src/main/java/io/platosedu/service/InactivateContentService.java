package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.persistence.ContentRepository;
import io.platosedu.usecase.InactivateContentUsecase;

import javax.inject.Singleton;

@Singleton
public class InactivateContentService implements InactivateContentUsecase {
    private final ContentRepository contentRepository;

    public InactivateContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content inactivate(Content.ContentId id, TenantId tenantId) {
        return contentRepository.inactivate(id, tenantId);
    }
}
