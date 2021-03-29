package io.platosedu.usecase;

import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;

import java.util.Optional;

public interface FindOneContentUsecase {
    Optional<Content> findOne(Content.ContentId id, TenantId tenantId);
}
