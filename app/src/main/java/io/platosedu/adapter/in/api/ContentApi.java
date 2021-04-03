package io.platosedu.adapter.in.api;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.platosedu.adapter.in.dto.params.ContentAllLevelChildrenQueryParams;
import io.platosedu.adapter.in.dto.params.ContentChildrenQueryParams;
import io.platosedu.adapter.in.dto.params.ContentQueryParams;
import io.platosedu.adapter.in.dto.request.ContentRequest;
import io.platosedu.adapter.in.dto.response.ContentResponse;
import io.platosedu.configuration.authentication.CustomUserDetails;
import io.platosedu.usecase.dto.LinkedContentResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface ContentApi {
    @Post
    HttpResponse<ContentResponse> save(CustomUserDetails authentication,
                                       @Valid @Body ContentRequest contentRequest,
                                       @Header("tenantId") String tenantId);

    @Put("/{id}")
    HttpResponse<ContentResponse> update(@PathVariable UUID id,
                                         @Valid @Body ContentRequest contentRequest,
                                         @Header("tenantId") String tenantId);

    @Get("/{?params*}")
    HttpResponse<Page<ContentResponse>> findAll(Pageable pageable,
                                                ContentQueryParams params,
                                                @Header("tenantId") String tenantId);

    @Get("/{id}")
    HttpResponse<ContentResponse> findOne(@PathVariable UUID id, @Header("tenantId") String tenantId);

    @Get("/{contentId}/children{?params*}")
    HttpResponse<Page<ContentResponse>> findFirstLevelChildrenOfContent(Pageable pageable,
                                                                        @PathVariable UUID contentId,
                                                                        ContentChildrenQueryParams params,
                                                                        @Header("tenantId") String tenantId);

    @Get("/{contentId}/all-children{?params*}")
    HttpResponse<List<LinkedContentResponse>> findAllLevelChildrenOfContent(@PathVariable UUID contentId,
                                                                            ContentAllLevelChildrenQueryParams params,
                                                                            @Header("tenantId") String tenantId);

    @Delete("/{id}")
    HttpResponse<ContentResponse> delete(@PathVariable UUID id, @Header("tenantId") String tenantId);
}
