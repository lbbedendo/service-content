package io.platosedu.usecase;

import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;

public interface InactivateContentUsecase {
    Content inactivate(Content.ContentId id, TenantId tenantId);
}
