package io.platosedu.adapter.in.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import javax.inject.Inject;

public class TestControllerBase {
    @Inject
    @Client("/")
    HttpClient httpClient;

    protected BearerAccessRefreshToken performLoginAndRetrieveAccessToken() {
        var credentials = new UsernamePasswordCredentials("sherlock", "password");
        var request = HttpRequest.POST("/login", credentials);
        return httpClient.toBlocking().retrieve(request, BearerAccessRefreshToken.class);
    }
}
