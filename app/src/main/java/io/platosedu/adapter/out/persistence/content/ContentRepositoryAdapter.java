package io.platosedu.adapter.out.persistence.content;

import com.mongodb.client.MongoClient;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.adapter.out.persistence.MongoCrudRepository;
import io.platosedu.adapter.out.persistence.mapper.ContentMapper;
import io.platosedu.configuration.mongodb.MongoConfiguration;
import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

@Singleton
public class ContentRepositoryAdapter extends MongoCrudRepository<ContentDocument> implements ContentRepository {
    public static final String COLLECTION_NAME = "content";
    public static final Class<ContentDocument> TYPE = ContentDocument.class;
    private final ContentMapper contentMapper;

    public ContentRepositoryAdapter(MongoClient mongoClient,
                                    MongoConfiguration mongoConfiguration,
                                    ContentMapper contentMapper) {
        super(mongoClient, mongoConfiguration);
        this.contentMapper = contentMapper;
    }

    @Override
    public Content inactivate(Content.ContentId id, TenantId tenantId) {
        return contentMapper.toDomainEntity(super.inactivate(id.getValue(), tenantId.getValue()));
    }

    @Override
    public Content create(Content content) {
        var document = contentMapper.toDocument(content);
        return contentMapper.toDomainEntity(super.save(document));
    }

    @Override
    public Content update(Content.ContentId id, Content content) {
        var document = contentMapper.toDocument(content);
        var contentUpdated = findOneAndUpdate(
                and(eq(document.getId()), eq("tenantId", document.getTenantId())),
                combine(
                        set("type", document.getType()),
                        set("name", document.getName()),
                        set("description", document.getDescription()),
                        set("root", document.getRoot()),
                        set("data", document.getData()),
                        set("lang", document.getLang()),
                        set("tags", document.getTags()),
                        set("attributes", document.getAttributes()),
                        set("questions", document.getQuestions()),
                        set("path", document.getPath()),
                        set("updatedAt", LocalDateTime.now()),
                        set("examAttempts", document.getExamAttempts()),
                        set("visible", document.getVisible()),
                        set("isbn", document.getIsbn()),
                        set("preview", document.getPreview()),
                        set("modalities", document.getModalities()),
                        set("productionStatus", document.getProductionStatus()),
                        set("libras", document.getLibras()),
                        set("videoType", document.getVideoType()),
                        set("cedoc", document.getCedoc()),
                        set("examTimeoutInMinutes", document.getExamTimeoutInMinutes())));
        return contentMapper.toDomainEntity(contentUpdated);
    }

    @Override
    public Optional<Content> findOne(Content.ContentId id, TenantId tenantId) {
        return findOne(id.getValue(), tenantId.getValue()).map(contentMapper::toDomainEntity);
    }

    @Override
    public Page<Content> findAll(Pageable pageable, ContentFilters filters, TenantId tenantId) {
        var query = ContentDocumentQuery.from(filters, tenantId);
        return list(pageable, query.toBson()).map(contentMapper::toDomainEntity);
    }

    @Override
    public Page<Content> findAll(Pageable pageable, TenantId tenantId) {
        return listAll(pageable, tenantId.getValue()).map(contentMapper::toDomainEntity);
    }

    @Override
    public Page<Content> findFirstLevelChildrenOfContent(Pageable pageable, TenantId tenantId, ContentFilters filters) {
       throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public List<LinkedContentResponse> findAllLevelChildrenOfContent(Content.ContentId contentId,
                                                                     ContentFilters filters) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override
    public Class<ContentDocument> getType() {
        return TYPE;
    }
}
