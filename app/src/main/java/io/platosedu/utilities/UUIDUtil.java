package io.platosedu.utilities;

import io.micronaut.core.util.StringUtils;

import javax.annotation.Nullable;
import java.util.UUID;

public class UUIDUtil {
    public static UUID fromStringOrNull(@Nullable String value) {
        return StringUtils.isNotEmpty(value)
                ? UUID.fromString(value)
                : null;
    }
}
