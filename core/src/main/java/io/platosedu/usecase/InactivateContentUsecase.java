package io.platosedu.usecase;

import io.platosedu.domain.Content;
import org.bson.types.ObjectId;

public interface InactivateContentUsecase {
    Content inactivate(ObjectId id, String tenantId);
}
