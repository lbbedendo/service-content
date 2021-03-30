package io.platosedu.usecase;

import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;

public interface UpdateContentUsecase {
    Content update(Content.ContentId id, Content content);
}
