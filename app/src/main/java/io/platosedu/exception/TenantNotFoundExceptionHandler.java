package io.platosedu.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.multitenancy.exceptions.TenantNotFoundException;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {TenantNotFoundException.class, ExceptionHandler.class})
public class TenantNotFoundExceptionHandler implements
        ExceptionHandler<TenantNotFoundException, HttpResponse<JsonError>> {

    @Override
    public HttpResponse<JsonError> handle(HttpRequest request, TenantNotFoundException exception) {
        return HttpResponse.badRequest(new JsonError(exception.getMessage()));
    }
}
