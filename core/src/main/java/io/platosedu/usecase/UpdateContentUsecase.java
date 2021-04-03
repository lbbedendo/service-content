package io.platosedu.usecase;

import io.platosedu.domain.Content;

import java.util.UUID;

public interface UpdateContentUsecase {
    Content update(UUID id, Content content);
}
