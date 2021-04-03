package io.platosedu.configuration.converters;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class UUIDTypeConverter implements TypeConverter<String, UUID> {
    @Override
    public Optional<UUID> convert(String object, Class<UUID> targetType, ConversionContext context) {
        return Optional.of(UUID.fromString(object));
    }
}
