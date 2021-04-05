package io.platosedu.adapter.in.controller;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.platosedu.adapter.in.api.ContentApi;
import io.platosedu.adapter.in.dto.params.ContentAllLevelChildrenQueryParams;
import io.platosedu.adapter.in.dto.params.ContentChildrenQueryParams;
import io.platosedu.adapter.in.dto.params.ContentQueryParams;
import io.platosedu.adapter.in.dto.request.ContentRequest;
import io.platosedu.adapter.in.dto.response.ContentResponse;
import io.platosedu.adapter.out.persistence.mapper.ContentMapper;
import io.platosedu.configuration.authentication.CustomUserDetails;
import io.platosedu.usecase.FindContentUsecase;
import io.platosedu.usecase.SaveContentUsecase;
import io.platosedu.usecase.dto.LinkedContentResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller("/content")
@Secured(SecurityRule.IS_AUTHENTICATED)
@ExecuteOn(TaskExecutors.IO)
public class ContentController implements ContentApi {
    private final SaveContentUsecase saveContentUsecase;
    private final FindContentUsecase findContentUsecase;
    private final ContentMapper contentMapper;


    public ContentController(SaveContentUsecase saveContentUsecase,
                             FindContentUsecase findContentUsecase,
                             ContentMapper contentMapper) {
        this.saveContentUsecase = saveContentUsecase;
        this.findContentUsecase = findContentUsecase;
        this.contentMapper = contentMapper;
    }

    @Override
    public HttpResponse<ContentResponse> save(CustomUserDetails authentication,
                                              @Valid @Body ContentRequest contentRequest,
                                              @Header("tenantId") String tenantId) {
        var content = contentMapper.fromContentRequest(contentRequest, true, tenantId, authentication.getId());
        return HttpResponse.created(contentMapper.toContentResponse(saveContentUsecase.create(content)));
    }

    @Override
    public HttpResponse<ContentResponse> update(@PathVariable UUID id,
                                                @Valid @Body ContentRequest contentRequest,
                                                @Header("tenantId") String tenantId) {
        var content = contentMapper.fromContentRequest(contentRequest, true, tenantId);
        return findContentUsecase.findOne(id, tenantId)
                .map(c -> HttpResponse.ok(contentMapper.toContentResponse(
                        saveContentUsecase.update(id, content))))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public HttpResponse<Page<ContentResponse>> findAll(Pageable pageable,
                                                       ContentQueryParams params,
                                                       @Header("tenantId") String tenantId) {
        params.validateDateRange();
        var filters = params.toContentFilters();
        return HttpResponse.ok(findContentUsecase.findAll(pageable, filters, tenantId)
                        .map(contentMapper::toContentResponse));
    }

    @Override
    public HttpResponse<ContentResponse> findOne(@PathVariable UUID id, @Header("tenantId") String tenantId) {
        return findContentUsecase.findOne(id, tenantId)
                .map(content -> HttpResponse.ok(contentMapper.toContentResponse(content)))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public HttpResponse<Page<ContentResponse>> findFirstLevelChildrenOfContent(Pageable pageable,
                                                                               @PathVariable UUID contentId,
                                                                               ContentChildrenQueryParams params,
                                                                               @Header("tenantId") String tenantId) {
        throw new UnsupportedOperationException("Not implemented!");
    }


    @Override
    public HttpResponse<List<LinkedContentResponse>> findAllLevelChildrenOfContent(@PathVariable UUID contentId,
                                                                                   ContentAllLevelChildrenQueryParams params,
                                                                                   @Header("tenantId") String tenantId) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public HttpResponse<ContentResponse> delete(@PathVariable UUID id, @Header("tenantId") String tenantId) {
        return findContentUsecase.findOne(id, tenantId)
                .map(content -> HttpResponse.ok(contentMapper.toContentResponse(
                        saveContentUsecase.inactivate(id, tenantId))))
                .orElseGet(HttpResponse::notFound);
    }
}
