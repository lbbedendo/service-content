package io.platosedu.usecase;

import io.platosedu.domain.Content;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface FindOneContentUsecase {
    Optional<Content> findOne(ObjectId id, String tenantId);
}
