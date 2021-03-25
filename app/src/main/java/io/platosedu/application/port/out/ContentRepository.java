package io.platosedu.application.port.out;

import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;

public interface ContentRepository {
    Content inactivate(Content.ContentId id, TenantId tenantId);
    Content update(Content.ContentId id, TenantId tenantId, Content content);
}
