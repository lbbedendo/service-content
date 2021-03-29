package io.platosedu.utilities;

import io.micronaut.core.util.StringUtils;
import org.bson.types.ObjectId;

import javax.annotation.Nullable;

public class ObjectIdUtil {
    public static ObjectId fromStringOrNull(@Nullable String value) {
        return StringUtils.isNotEmpty(value)
                ? new ObjectId(value)
                : null;
    }
}
