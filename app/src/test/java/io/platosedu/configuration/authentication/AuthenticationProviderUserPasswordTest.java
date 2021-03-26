package io.platosedu.configuration.authentication;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.annotation.Nullable;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;

@Singleton
@Requires(env = "test")
public class AuthenticationProviderUserPasswordTest implements AuthenticationProvider {

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {
        if (authenticationRequest.getIdentity().equals("sherlock") &&
                authenticationRequest.getSecret().equals("password")) {
            return Flowable.just(
                    new CustomUserDetails(
                            "sherlock.holmes",
                            List.of("ADMIN", "CONTENT_ADMIN"),
                            "5ecfc5628b346a4f2cfacfa4",
                            "sherlock.holmes@kroton.com.br",
                            "77995126018",
                            Map.of(
                                    "id", "5ecfc5628b346a4f2cfacfa4",
                                    "email", "sherlock.holmes@kroton.com.br",
                                    "cpf", "77995126018"
                            )
                    )
            );
        }
        return Flowable.just(new AuthenticationFailed());
    }
}
