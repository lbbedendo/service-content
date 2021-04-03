package io.platosedu.service;

import io.platosedu.domain.Content;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.FindOneContentUsecase;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class FindOneContentService implements FindOneContentUsecase {
    private final ContentRepository contentRepository;

    public FindOneContentService(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Optional<Content> findOne(UUID id, String tenantId) {
        return contentRepository.findOne(id, tenantId);
    }
}
