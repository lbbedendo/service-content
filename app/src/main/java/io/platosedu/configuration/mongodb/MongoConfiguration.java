package io.platosedu.configuration.mongodb;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties("mongodb")
public class MongoConfiguration {
    @NotEmpty
    private String database;
    @NotEmpty
    private String collection;
    @NotEmpty
    private String uri;

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
