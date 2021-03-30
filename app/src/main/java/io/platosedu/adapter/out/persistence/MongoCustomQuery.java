package io.platosedu.adapter.out.persistence;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

public interface MongoCustomQuery {
    Bson toBson();

    List<Bson> getFilters();

    default boolean isEmpty() {
        return getFilters().isEmpty();
    }

    default Bson getRegexFilter(String key, String value) {
        return new Document()
                .append(key, new Document("$regex", value).append("$options", "i"));
    }

    default Bson getTextFilter(String text) {
        return Filters.text(text);
    }

    default Bson getTextFilter(String text, TextSearchOptions options) {
        return Filters.text(text, options);
    }
}
