package io.platosedu.adapter.out.persistence;

import com.mongodb.client.MongoClient;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.adapter.out.persistence.mapper.ContentMapper;
import io.platosedu.configuration.mongodb.MongoConfiguration;
import io.platosedu.domain.Content;
import io.platosedu.domain.TenantId;
import io.platosedu.port.out.ContentRepository;
import io.platosedu.usecase.dto.ContentFilters;
import io.platosedu.usecase.dto.LinkedContentResponse;

import java.util.List;
import java.util.Optional;

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
        return contentMapper.toDomainEntity(super.inactivate(id.toString(), tenantId.getId()));
    }

    @Override
    public Content create(Content content, TenantId tenantId) {
        var document = contentMapper.toDocument(content, tenantId.getId());
        return contentMapper.toDomainEntity(super.save(document));
    }

    @Override
    public Content update(Content.ContentId id, TenantId tenantId, Content content) {
        return null;
    }

    @Override
    public Optional<Content> findOne(Content.ContentId id, TenantId tenantId) {
        return Optional.empty();
    }

    @Override
    public Page<Content> findAll(Pageable pageable, ContentFilters filters, TenantId tenantId) {
        return null;
    }

    @Override
    public Page<Content> findAll(Pageable pageable, TenantId tenantId) {
        return null;
    }

    @Override
    public Page<Content> findFirstLevelChildrenOfContent(Pageable pageable, TenantId tenantId, ContentFilters filters) {
        return null;
    }

    @Override
    public List<LinkedContentResponse> findAllLevelChildrenOfContent(Content.ContentId contentId,
                                                                     ContentFilters filters) {
        return null;
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
