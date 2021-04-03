package io.platosedu.usecase;

import io.platosedu.domain.Content;

import java.util.Optional;
import java.util.UUID;

public interface FindOneContentUsecase {
    Optional<Content> findOne(UUID id, String tenantId);
}
