package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.persistence.ContentRepository;
import io.platosedu.usecase.FindOneContentUsecase;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class FindOneContentService implements FindOneContentUsecase {
    private final ContentRepository contentRepository;

    public FindOneContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Optional<Content> findOne(Content.ContentId id, TenantId tenantId) {
        return contentRepository.findOne(id, tenantId);
    }
}
