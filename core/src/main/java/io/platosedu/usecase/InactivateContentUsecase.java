package io.platosedu.usecase;

import io.platosedu.domain.Content;

import java.util.UUID;

public interface InactivateContentUsecase {
    Content inactivate(UUID id, String tenantId);
}
