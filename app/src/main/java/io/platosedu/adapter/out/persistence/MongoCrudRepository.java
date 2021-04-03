package io.platosedu.adapter.out.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Sorts;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.platosedu.configuration.mongodb.MongoConfiguration;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public abstract class MongoCrudRepository<T, ID> {
    public static final FindOneAndUpdateOptions defaultFindOneAndUpdateOptions =
            new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
    protected MongoClient mongoClient;
    protected MongoConfiguration mongoConfiguration;

    public MongoCrudRepository(MongoClient mongoClient, MongoConfiguration mongoConfiguration) {
        this.mongoClient = mongoClient;
        this.mongoConfiguration = mongoConfiguration;
    }

    public T save(T document) {
        getCollection().insertOne(document);
        return document;
    }

    public void saveAll(List<T> documents) {
        if (!documents.isEmpty())
            getCollection().insertMany(documents);
    }

    public T findOneAndUpdate(Bson filter, Bson update) {
        return findOneAndUpdate(filter, update, defaultFindOneAndUpdateOptions);
    }

    public T findOneAndUpdate(Bson filter, Bson update, FindOneAndUpdateOptions options) {
        return getCollection().findOneAndUpdate(filter, update, options);
    }

    public T findOneAndDelete(Bson filter) {
        return getCollection().findOneAndDelete(filter);
    }

    public long delete(Bson filter) {
        return getCollection().deleteOne(filter).getDeletedCount();
    }

    public T inactivate(ID id, String tenantId) {
        return findOneAndUpdate(
                and(eq(id), eq("tenantId", tenantId)),
                combine(
                        set("active", false),
                        set("updatedAt", LocalDateTime.now())));
    }

    public Stream<T> list(Bson filter) {
        return StreamSupport
                .stream(getCollection().find(filter, getType()).spliterator(), false);
    }

    public Stream<T> list(Bson filter, Bson sort) {
        return StreamSupport
                .stream(getCollection().find(filter, getType())
                        .sort(sort).spliterator(), false);
    }

    public Page<T> list(Pageable pageable, Bson filter, String tenantId) {
        var filterWithTenantId = and(eq("tenantId", tenantId), filter);
        return list(pageable, filterWithTenantId);
    }

    public Page<T> list(Pageable pageable, Bson filter) {
        var totalSize = countDocuments(filter);
        var content = StreamSupport
                .stream(getCollection()
                        .find(filter, getType())
                        .skip((int) pageable.getOffset())
                        .limit(pageable.getSize())
                        .sort(getSort(pageable))
                        .spliterator(), false)
                .collect(Collectors.toList());
        return Page.of(content, pageable, totalSize);
    }

    public Stream<T> listAll() {
        return StreamSupport
                .stream(getCollection().find().spliterator(), false);
    }

    public Page<T> listAll(Pageable pageable, String tenantId) {
        return list(pageable, eq("tenantId", tenantId));
    }

    public Page<T> listAll(Pageable pageable) {
        return list(pageable, new Document());
    }

    public Optional<T> findOne(Bson filter) {
        return Optional.ofNullable(getCollection().find(filter).first());
    }

    public Optional<T> findOne(ID id) {
        return findOne(eq(id));
    }

    public Optional<T> findOne(ID id, String tenantId) {
        return findOne(
                and(
                        eq(id),
                        eq("tenantId", tenantId)));
    }

    public List<T> findAll(List<ID> ids) {
        return StreamSupport
                .stream(getCollection().find(in("_id", ids)).spliterator(), false)
                .collect(Collectors.toList());
    }

    public abstract String getCollectionName();

    private Bson getSort(Pageable pageable) {
        return Sorts.orderBy(pageable
                .getSort()
                .getOrderBy()
                .stream()
                .map(order -> order.isAscending()
                        ? Sorts.ascending(order.getProperty())
                        : Sorts.descending(order.getProperty()))
                .collect(Collectors.toList()));
    }

    public long countDocuments(Bson filter) {
        return getCollection().countDocuments(filter);
    }

    public MongoCollection<T> getCollection() {
        return mongoClient
                .getDatabase(this.mongoConfiguration.getDatabase())
                .getCollection(getCollectionName(), getType());
    }

    public abstract Class<T> getType();
}
