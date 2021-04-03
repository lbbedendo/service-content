package io.platosedu.adapter.out.persistence.content;

import com.mongodb.client.MongoClient;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.adapter.out.persistence.MongoCrudRepository;
import io.platosedu.configuration.mongodb.MongoConfiguration;
import io.platosedu.domain.Content;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

@Singleton
public class ContentRepositoryAdapter extends MongoCrudRepository<Content, UUID> implements ContentRepository {
    public static final String COLLECTION_NAME = "content";
    public static final Class<Content> TYPE = Content.class;

    public ContentRepositoryAdapter(MongoClient mongoClient,
                                    MongoConfiguration mongoConfiguration) {
        super(mongoClient, mongoConfiguration);
    }

    @Override
    public Content inactivate(UUID id, String tenantId) {
        return super.inactivate(id, tenantId);
    }

    @Override
    public Content create(Content content) {
        content.setId(UUID.randomUUID());
        content.setCreatedAt(LocalDateTime.now());
        content.setUpdatedAt(LocalDateTime.now());
        return super.save(content);
    }

    @Override
    public Content update(UUID id, Content content) {
        return findOneAndUpdate(
                and(eq(content.getId()), eq("tenantId", content.getTenantId())),
                combine(
                        set("type", content.getType()),
                        set("name", content.getName()),
                        set("description", content.getDescription()),
                        set("root", content.getRoot()),
                        set("data", content.getData()),
                        set("lang", content.getLang()),
                        set("tags", content.getTags()),
                        set("attributes", content.getAttributes()),
                        set("questions", content.getQuestions()),
                        set("path", content.getPath()),
                        set("updatedAt", LocalDateTime.now()),
                        set("examAttempts", content.getExamAttempts()),
                        set("visible", content.getVisible()),
                        set("isbn", content.getIsbn()),
                        set("preview", content.getPreview()),
                        set("modalities", content.getModalities()),
                        set("productionStatus", content.getProductionStatus()),
                        set("libras", content.getLibras()),
                        set("videoType", content.getVideoType()),
                        set("cedoc", content.getCedoc()),
                        set("examTimeoutInMinutes", content.getExamTimeoutInMinutes())));
    }

    @Override
    public Optional<Content> findOne(UUID id, String tenantId) {
        return super.findOne(id, tenantId);
    }

    @Override
    public Page<Content> findAll(Pageable pageable, ContentFilters filters, String tenantId) {
        var query = ContentDocumentQuery.from(filters, tenantId);
        return list(pageable, query.toBson());
    }

    @Override
    public Page<Content> findAll(Pageable pageable, String tenantId) {
        return listAll(pageable, tenantId);
    }

    @Override
    public Page<Content> findFirstLevelChildrenOfContent(Pageable pageable, String tenantId, ContentFilters filters) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public List<LinkedContentResponse> findAllLevelChildrenOfContent(UUID contentId,
                                                                     ContentFilters filters) {
        throw new UnsupportedOperationException("Not implemented!");
    }

    @Override
    public String getCollectionName() {
        return COLLECTION_NAME;
    }

    @Override
    public Class<Content> getType() {
        return TYPE;
    }
}
