package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.CreateContentUsecase;

import javax.inject.Singleton;

@Singleton
public class CreateContentService implements CreateContentUsecase {
    private final ContentRepository contentRepository;

    public CreateContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Content create(Content content, TenantId tenantId) {
        return contentRepository.create(content, tenantId);
    }
}
