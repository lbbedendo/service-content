package io.platosedu.usecase;

import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;

public interface CreateContentUsecase {
    Content create(Content content);
}
