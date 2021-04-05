package io.platosedu.usecase;

import io.platosedu.domain.Content;

import java.util.UUID;

public interface SaveContentUsecase {
    Content create(Content content);
    Content inactivate(UUID id, String tenantId);
    Content update(UUID id, Content content);
}
