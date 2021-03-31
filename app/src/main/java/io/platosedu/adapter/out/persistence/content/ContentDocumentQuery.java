package io.platosedu.adapter.out.persistence.content;

import com.mongodb.client.model.Filters;
import io.platosedu.adapter.out.persistence.MongoCustomQuery;
import io.platosedu.usecase.dto.ContentFilters;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ContentDocumentQuery implements MongoCustomQuery {
    private final ContentFilters contentFilters;
    private final String tenantId;

    public static ContentDocumentQuery from(ContentFilters contentFilters) {
        return new ContentDocumentQuery(contentFilters, null);
    }

    public static ContentDocumentQuery from(ContentFilters contentFilters, String tenantId) {
        return new ContentDocumentQuery(contentFilters, tenantId);
    }

    private ContentDocumentQuery(ContentFilters contentFilters, String tenantId) {
        this.contentFilters = contentFilters;
        this.tenantId = tenantId;
    }

    @Override
    public Bson toBson() {
        return Filters.and(getFilters());
    }

    @Override
    public List<Bson> getFilters() {
        return Stream.of(
                getContentTypeFilter(),
                getTagsFilter(),
                getActiveFilter(),
                getRootFilter(),
                getCreatedAtRangeFilter(),
                getNameFilter(),
                getInstructorUserNameFilter(),
                getTextFilter(),
                getVisibleFilter(),
                getParentIdFilter(),
                getLinkModalityFilter(),
                getProductionStatusFilter(),
                getTenantIdFilter())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Bson getContentTypeFilter() {
        return Objects.nonNull(contentFilters.getType())
                ? Filters.eq("type", contentFilters.getType())
                : null;
    }

    public Bson getTagsFilter() {
        return Objects.nonNull(contentFilters.getTags()) && !contentFilters.getTags().isEmpty()
                ? Filters.in("tags", contentFilters.getTags()
                .stream().map(ObjectId::new)
                .collect(Collectors.toList()))
                : null;
    }

    public Bson getActiveFilter() {
        return Objects.nonNull(contentFilters.getActive())
                ? Filters.eq("active", contentFilters.getActive())
                : null;
    }

    public Bson getActiveFilter(String fieldName) {
        return Objects.nonNull(contentFilters.getActive())
                ? Filters.eq(fieldName, contentFilters.getActive())
                : null;
    }

    public Bson getRootFilter() {
        return Objects.nonNull(contentFilters.getRoot())
                ? Filters.eq("root", contentFilters.getRoot())
                : null;
    }

    public Bson getCreatedAtRangeFilter() {
        return Objects.nonNull(contentFilters.getStartDate()) && Objects.nonNull(contentFilters.getEndDate())
                ? Filters.and(
                Filters.gte("createdAt", contentFilters.getStartDate()),
                Filters.lt("createdAt", contentFilters.getEndDate()))
                : null;
    }

    public Bson getNameFilter() {
        return Objects.nonNull(contentFilters.getName())
                ? getRegexFilter("name", contentFilters.getName().trim())
                : null;
    }

    public Bson getInstructorUserNameFilter() {
        return Objects.nonNull(contentFilters.getInstructorUserName())
                ? getRegexFilter("instructor.user.name", contentFilters.getInstructorUserName().trim())
                : null;
    }

    public Bson getTextFilter() {
        return Objects.nonNull(contentFilters.getText())
                ? getTextFilter(contentFilters.getText())
                : null;
    }

    public Bson getVisibleFilter() {
        return Objects.nonNull(contentFilters.getVisible())
                ? Filters.eq("visible", contentFilters.getVisible())
                : null;
    }

    public Bson getVisibleFilter(String fieldName) {
        return Objects.nonNull(contentFilters.getVisible())
                ? Filters.eq(fieldName, contentFilters.getVisible())
                : null;
    }

    public Bson getParentIdFilter() {
        return Objects.nonNull(contentFilters.getParentId())
                ? Filters.eq("link.parentId", new ObjectId(contentFilters.getParentId()))
                : null;
    }

    public Bson getLinkModalityFilter() {
        return Objects.nonNull(contentFilters.getLinkModality())
                ? Filters.in("link.modalities.modality", contentFilters.getLinkModality())
                : null;
    }

    public Bson getProductionStatusFilter() {
        return Objects.nonNull(contentFilters.getProductionStatus())
                ? Filters.in("productionStatus", contentFilters.getProductionStatus())
                : null;
    }

    public Bson getTenantIdFilter() {
        return Objects.nonNull(tenantId)
                ? Filters.eq("tenantId", tenantId)
                : null;
    }
}
