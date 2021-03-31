package io.platosedu.usecase;

import io.platosedu.domain.Content;
import org.bson.types.ObjectId;

public interface UpdateContentUsecase {
    Content update(ObjectId id, Content content);
}
