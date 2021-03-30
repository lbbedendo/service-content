package io.platosedu.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {InvalidDateRangeException.class, ExceptionHandler.class})
public class InvalidDateRangeExceptionHandler implements
        ExceptionHandler<InvalidDateRangeException, HttpResponse<JsonError>> {

    @Override
    public HttpResponse<JsonError> handle(HttpRequest request, InvalidDateRangeException exception) {
        return HttpResponse.badRequest(new JsonError(exception.getMessage()));
    }
}
