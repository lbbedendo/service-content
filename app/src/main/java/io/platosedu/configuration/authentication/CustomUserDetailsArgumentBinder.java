package io.platosedu.configuration.authentication;

import io.micronaut.core.convert.ArgumentConversionContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.bind.binders.TypedRequestArgumentBinder;
import io.micronaut.http.filter.OncePerRequestHttpServerFilter;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.filters.SecurityFilter;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.Optional;

@Singleton
public class CustomUserDetailsArgumentBinder implements TypedRequestArgumentBinder<CustomUserDetails> {
    @Override
    public Argument<CustomUserDetails> argumentType() {
        return Argument.of(CustomUserDetails.class);
    }

    @Override
    public BindingResult<CustomUserDetails> bind(ArgumentConversionContext<CustomUserDetails> context,
                                                 HttpRequest<?> source) {
        if (source.getAttributes().contains(OncePerRequestHttpServerFilter.getKey(SecurityFilter.class))) {
            final Optional<Authentication> authentication = source.getUserPrincipal(Authentication.class);
            if (authentication.isPresent()) {
                var attributes = authentication.get().getAttributes();
                return () -> Optional.of(
                        new CustomUserDetails(
                                (String) attributes.get("sub"),
                                (Collection<String>) attributes.get("roles"),
                                (String) attributes.get("id"),
                                (String) attributes.get("email"),
                                (String) attributes.get("cpf"),
                                attributes));
            }
        }
        return Optional::empty;
    }
}
