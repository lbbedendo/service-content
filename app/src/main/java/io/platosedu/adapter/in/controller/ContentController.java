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
import io.platosedu.domain.TenantId;
import io.platosedu.usecase.CreateContentUsecase;
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
    private final FindFirstLevelChildrenOfContentUsecase findFirstLevelChildrenOfContentUsecase;
    private final FindAllLevelChildrenOfContentUsecase findAllLevelChildrenOfContentUsecase;
    private final InactivateContentUsecase inactivateContentUsecase;
    private final ContentMapper contentMapper;


    public ContentController(TenantResolver tenantResolver,
                             CreateContentUsecase createContentUsecase,
                             UpdateContentUsecase updateContentUsecase,
                             FindOneContentUsecase findOneContentUsecase,
                             FindFirstLevelChildrenOfContentUsecase findFirstLevelChildrenOfContentUsecase,
                             FindAllLevelChildrenOfContentUsecase findAllLevelChildrenOfContentUsecase,
                             InactivateContentUsecase inactivateContentUsecase,
                             ContentMapper contentMapper) {
        super(tenantResolver);
        this.createContentUsecase = createContentUsecase;
        this.updateContentUsecase = updateContentUsecase;
        this.findOneContentUsecase = findOneContentUsecase;
        this.findFirstLevelChildrenOfContentUsecase = findFirstLevelChildrenOfContentUsecase;
        this.findAllLevelChildrenOfContentUsecase = findAllLevelChildrenOfContentUsecase;
        this.inactivateContentUsecase = inactivateContentUsecase;
        this.contentMapper = contentMapper;
    }

    @Override
    public HttpResponse<ContentResponse> save(CustomUserDetails authentication,
                                              @Valid @Body ContentRequest contentRequest) {
        var content = contentMapper.fromContentRequest(contentRequest, authentication.getId());
        var tenantId = new TenantId(resolveTenantId());
        return HttpResponse.created(contentMapper.toContentResponse(createContentUsecase.create(content, tenantId)));
    }

    @Override
    public HttpResponse<ContentResponse> update(String id, @Valid @Body ContentRequest contentRequest) {
        return null;
    }

    @Override
    public HttpResponse<Page<ContentResponse>> findAll(Pageable pageable, ContentQueryParams params) {
        return null;
    }

    @Override
    public HttpResponse<ContentResponse> findOne(String id) {
        return null;
    }

    @Override
    public HttpResponse<Page<ContentResponse>> findFirstLevelChildrenOfContent(Pageable pageable,
                                                                               String contentId,
                                                                               ContentChildrenQueryParams params) {
        return null;
    }

    @Override
    public HttpResponse<List<LinkedContentResponse>> findAllLevelChildrenOfContent(String contentId,
                                                                                   ContentAllLevelChildrenQueryParams params) {
        return null;
    }

    @Override
    public HttpResponse<ContentResponse> delete(String id) {
        return null;
    }
}
