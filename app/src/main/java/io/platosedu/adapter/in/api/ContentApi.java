package io.platosedu.adapter.in.api;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.platosedu.adapter.in.dto.params.ContentAllLevelChildrenQueryParams;
import io.platosedu.adapter.in.dto.params.ContentChildrenQueryParams;
import io.platosedu.adapter.in.dto.params.ContentQueryParams;
import io.platosedu.adapter.in.dto.response.ContentResponse;
import io.platosedu.adapter.in.dto.request.ContentRequest;
import io.platosedu.configuration.authentication.CustomUserDetails;
import io.platosedu.usecase.dto.LinkedContentResponse;

import javax.validation.Valid;
import java.util.List;

public interface ContentApi {
    @Post
    HttpResponse<ContentResponse> save(CustomUserDetails authentication, @Valid @Body ContentRequest contentRequest);

    @Put("/{id}")
    HttpResponse<ContentResponse> update(String id, @Valid @Body ContentRequest contentRequest);

    @Get("/{?params*}")
    HttpResponse<Page<ContentResponse>> findAll(Pageable pageable, ContentQueryParams params);

    @Get("/{id}")
    HttpResponse<ContentResponse> findOne(String id);

    @Get("/{contentId}/children{?params*}")
    HttpResponse<Page<ContentResponse>> findFirstLevelChildrenOfContent(Pageable pageable,
                                                                        String contentId,
                                                                        ContentChildrenQueryParams params);

    @Get("/{contentId}/all-children{?params*}")
    HttpResponse<List<LinkedContentResponse>> findAllLevelChildrenOfContent(String contentId,
                                                                            ContentAllLevelChildrenQueryParams params);

    @Delete("/{id}")
    HttpResponse<ContentResponse> delete(String id);
}
