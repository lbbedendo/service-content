package io.platosedu.adapter.in.controller;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.multitenancy.tenantresolver.TenantResolver;
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
import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.usecase.CreateContentUsecase;
import io.platosedu.usecase.FindAllContentUsecase;
import io.platosedu.usecase.FindAllLevelChildrenOfContentUsecase;
import io.platosedu.usecase.FindFirstLevelChildrenOfContentUsecase;
import io.platosedu.usecase.FindOneContentUsecase;
import io.platosedu.usecase.InactivateContentUsecase;
import io.platosedu.usecase.UpdateContentUsecase;
import io.platosedu.usecase.dto.LinkedContentResponse;

import javax.validation.Valid;
import java.util.List;

@Controller("/content")
@Secured(SecurityRule.IS_AUTHENTICATED)
@ExecuteOn(TaskExecutors.IO)
public class ContentController extends MultiTenantController implements ContentApi {
    private final CreateContentUsecase createContentUsecase;
    private final UpdateContentUsecase updateContentUsecase;
    private final FindOneContentUsecase findOneContentUsecase;
    private final FindAllContentUsecase findAllContentUsecase;
    private final FindFirstLevelChildrenOfContentUsecase findFirstLevelChildrenOfContentUsecase;
    private final FindAllLevelChildrenOfContentUsecase findAllLevelChildrenOfContentUsecase;
    private final InactivateContentUsecase inactivateContentUsecase;
    private final ContentMapper contentMapper;


    public ContentController(TenantResolver tenantResolver,
                             CreateContentUsecase createContentUsecase,
                             UpdateContentUsecase updateContentUsecase,
                             FindOneContentUsecase findOneContentUsecase,
                             FindAllContentUsecase findAllContentUsecase,
                             FindFirstLevelChildrenOfContentUsecase findFirstLevelChildrenOfContentUsecase,
                             FindAllLevelChildrenOfContentUsecase findAllLevelChildrenOfContentUsecase,
                             InactivateContentUsecase inactivateContentUsecase,
                             ContentMapper contentMapper) {
        super(tenantResolver);
        this.createContentUsecase = createContentUsecase;
        this.updateContentUsecase = updateContentUsecase;
        this.findOneContentUsecase = findOneContentUsecase;
        this.findAllContentUsecase = findAllContentUsecase;
        this.findFirstLevelChildrenOfContentUsecase = findFirstLevelChildrenOfContentUsecase;
        this.findAllLevelChildrenOfContentUsecase = findAllLevelChildrenOfContentUsecase;
        this.inactivateContentUsecase = inactivateContentUsecase;
        this.contentMapper = contentMapper;
    }

    @Override
    public HttpResponse<ContentResponse> save(CustomUserDetails authentication,
                                              @Valid @Body ContentRequest contentRequest) {
        var content = contentMapper.fromContentRequest(contentRequest, true, resolveTenantId(), authentication.getId());
        return HttpResponse.created(contentMapper.toContentResponse(createContentUsecase.create(content)));
    }

    @Override
    public HttpResponse<ContentResponse> update(String id, @Valid @Body ContentRequest contentRequest) {
        var contentId = new Content.ContentId(id);
        var tenantId = new TenantId(resolveTenantId());
        var content = contentMapper.fromContentRequest(contentRequest, true, tenantId.getValue());
        return findOneContentUsecase.findOne(contentId, tenantId)
                .map(c -> HttpResponse.ok(contentMapper.toContentResponse(
                        updateContentUsecase.update(contentId, content))))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public HttpResponse<Page<ContentResponse>> findAll(Pageable pageable, ContentQueryParams params) {
        params.validateDateRange();
        var tenantId = new TenantId(resolveTenantId());
        var filters = params.toContentFilters();
        return HttpResponse.ok(findAllContentUsecase.findAll(pageable, filters, tenantId)
                        .map(contentMapper::toContentResponse));
    }

    @Override
    public HttpResponse<ContentResponse> findOne(String id) {
        var contentId = new Content.ContentId(id);
        var tenantId = new TenantId(id);
        return findOneContentUsecase.findOne(contentId, tenantId)
                .map(content -> HttpResponse.ok(contentMapper.toContentResponse(content)))
                .orElseGet(HttpResponse::notFound);
    }

    @Override
    public HttpResponse<Page<ContentResponse>> findFirstLevelChildrenOfContent(Pageable pageable,
                                                                               String contentId,
                                                                               ContentChildrenQueryParams params) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public HttpResponse<List<LinkedContentResponse>> findAllLevelChildrenOfContent(String contentId,
                                                                                   ContentAllLevelChildrenQueryParams params) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public HttpResponse<ContentResponse> delete(String id) {
        var contentId = new Content.ContentId(id);
        var tenantId = new TenantId(resolveTenantId());
        return findOneContentUsecase.findOne(contentId, tenantId)
                .map(content -> HttpResponse.ok(contentMapper.toContentResponse(
                        inactivateContentUsecase.inactivate(contentId, tenantId))))
                .orElseGet(HttpResponse::notFound);
    }
}
