package io.platosedu.adapter.in.controller;

import com.mongodb.client.MongoClient;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.platosedu.adapter.in.api.ContentApi;
import io.platosedu.adapter.in.controller.fixtures.ContentControllerTestFixtures;
import io.platosedu.adapter.in.dto.params.ContentQueryParams;
import io.platosedu.adapter.in.dto.request.ContentRequest;
import io.platosedu.adapter.in.dto.response.ContentResponse;
import io.platosedu.adapter.out.persistence.content.ContentRepositoryAdapter;
import io.platosedu.configuration.mongodb.MongoConfiguration;
import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import io.platosedu.exception.InvalidDateRangeException;
import io.platosedu.helper.TestHelper;
import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static io.platosedu.adapter.in.controller.fixtures.ContentControllerTestFixtures.umContentRequest;
import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.groups.Tuple.tuple;

@MicronautTest
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class ContentControllerTest {
    @Inject
    @Client("/content")
    ContentApi client;
    @Inject
    MongoClient mongoClient;
    @Inject
    MongoConfiguration mongoConfig;

    @BeforeAll
    public void setUp() {
        var mongoCollection = mongoClient
                .getDatabase(mongoConfig.getDatabase())
                .getCollection(ContentRepositoryAdapter.COLLECTION_NAME, ContentRepositoryAdapter.TYPE);
        mongoCollection.insertMany(ContentControllerTestFixtures.multipleContents());
    }

    @Test
    public void save_contentCreated_whenRequestBodyIsValid() {
        var request = new ContentRequest();
        request.setType(Content.Type.FOLDER);
        request.setName("MBA em Gestão de Negócios");
        request.setDescription("Essa é uma descrição do curso MBA em Gestão de Negócios");
        request.setRoot(true);
        request.setLang("PT-BR");
        request.setQuestions(10);
        request.setTags(List.of("90ead1f6-ff8c-43f1-99f6-4215969dde2a"));
        request.setPath("/path/to/file");
        request.setExamAttempts(2);
        request.setVisible(true);
        request.setIsbn("9781234567897");
        request.setPreview(true);
        request.setModalities(List.of(Link.Modality.Type.EAD, Link.Modality.Type.INTENSIVO));
        request.setProductionStatus(Content.ProductionStatus.LAYOUT);
        request.setLibras(true);
        request.setVideoType(Content.VideoType.INTERVIEW);
        request.setCedoc("codigo1");
        request.setExamTimeoutInMinutes(0);
        request.setData(new Document(Map.ofEntries(entry("time", 1550476186479L), entry("version", "2.8.1"))));
        request.setAttributes(
                List.of(
                        new Document(
                                Map.ofEntries(
                                        entry("type", "paragraph"),
                                        entry("text", "The example of text that was written in <b>one of popular</b> text editors."))),
                        new Document(
                                Map.ofEntries(
                                        entry("type", "header"),
                                        entry("text", "With the header of course")))
                )
        );
        var response = client.save(TestHelper.mockCustomUserDetails(), request, "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getType()).isEqualTo(request.getType());
        assertThat(body.getName()).isEqualTo(request.getName());
        assertThat(body.getDescription()).isEqualTo(request.getDescription());
        assertThat(body.getRoot()).isEqualTo(request.getRoot());
        assertThat(body.getActive()).isTrue();
        assertThat(body.getLang()).isEqualTo(request.getLang());
        assertThat(body.getTags()).containsExactly("90ead1f6-ff8c-43f1-99f6-4215969dde2a");
        assertThat(body.getQuestions()).isEqualTo(request.getQuestions());
        assertThat(body.getPath()).isEqualTo(request.getPath());
        assertThat(body.getCreatedAt()).isNotNull();
        assertThat(body.getUpdatedAt()).isNotNull();
        assertThat(body.getExamAttempts()).isEqualTo(2);
        assertThat(body.getVisible()).isEqualTo(request.getVisible());
        assertThat(body.getIsbn()).isEqualTo(request.getIsbn());
        assertThat(body.getPreview()).isTrue();
        assertThat(body.getTenantId()).isEqualTo("platos");
        assertThat(body.getCreatedByUserId()).isEqualTo("5ecfb01fe805bc9f584a0c6d");
        assertThat(body.getModalities()).containsExactlyInAnyOrder(Link.Modality.Type.EAD, Link.Modality.Type.INTENSIVO);
        assertThat(body.getProductionStatus()).isEqualTo(request.getProductionStatus());
        assertThat(body.getLibras()).isEqualTo(request.getLibras());
        assertThat(body.getVideoType()).isEqualTo(request.getVideoType());
        assertThat(body.getCedoc()).isEqualTo(request.getCedoc());
        assertThat(body.getExamTimeoutInMinutes()).isEqualTo(request.getExamTimeoutInMinutes());
        assertThat(body.getData()).isEqualTo(new Document(Map.of("time", 1550476186479L, "version", "2.8.1")));
        assertThat(body.getAttributes())
                .hasSize(2)
                .containsExactly(
                        new Document(
                                Map.ofEntries(
                                        entry("type", "paragraph"),
                                        entry("text", "The example of text that was written in <b>one of popular</b> text editors."))),
                        new Document(
                                Map.ofEntries(
                                        entry("type", "header"),
                                        entry("text", "With the header of course"))));
    }

    @Test
    public void save_exception_whenNameIsEmpty() {
        var request = new ContentRequest();
        request.setType(Content.Type.FOLDER);
        request.setRoot(true);
        request.setLang("PT-BR");
        request.setQuestions(10);
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> client.save(TestHelper.mockCustomUserDetails(), request, "platos"))
                .withMessage("save.contentRequest.name: must not be blank");
    }

    @Test
    public void save_exception_whenTypeIsEmpty() {
        var request = new ContentRequest();
        request.setName("Gestão de Pessoas");
        request.setRoot(true);
        request.setLang("PT-BR");
        request.setQuestions(10);
        assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> client.save(TestHelper.mockCustomUserDetails(), request, "platos"))
                .withMessage("save.contentRequest.type: must not be null");
    }

    @Test
    public void findOne_content_whenSearchingForOneSpecificContent() {
        var response = client.findOne(UUID.fromString("c8f5959e-21ec-4f07-ae29-e4dba6819cb5"), "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getId().toString()).isEqualTo("c8f5959e-21ec-4f07-ae29-e4dba6819cb5");
    }

    @Test
    public void findOne_notFound_whenTenantIdDoesNotMatchTenantIdOfDocument() {
        var response = client.findOne(UUID.fromString("ee57f7c9-26a0-4b50-8558-ed5a47da3213"), "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void findAll_notEmpty_whenSearchingAllContentsWithNoFilter() {
        var response = client.findAll(defaultPage(), ContentQueryParams.empty(), "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        assertThat(response.body()).isNotEmpty();
    }

    @Test
    public void findAll_notEmpty_whenFilteringContentsByType() {
        var params = new ContentQueryParams();
        params.setType(Content.Type.FILE);
        var response = client.findAll(defaultPage(), params, "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body)
                .isNotEmpty()
                .extracting(
                        ContentResponse::getId,
                        ContentResponse::getType)
                .containsExactly(
                        tuple(UUID.fromString("742b1b14-9139-4954-a986-8be333df9ba7"), Content.Type.FILE));
    }

    @Test
    public void findAll_notEmpty_whenFilteringContentsByRoot() {
        var params = new ContentQueryParams();
        params.setRoot(true);
        var response = client.findAll(defaultPage(), params, "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body)
                .isNotEmpty()
                .extracting(
                        ContentResponse::getId,
                        ContentResponse::getRoot)
                .containsExactly(
                        tuple(UUID.fromString("c8f5959e-21ec-4f07-ae29-e4dba6819cb5"), true));
    }

    @Test
    public void findAll_resultPaginated_whenRequestWithPagination() {
        var pageRequest = Pageable.from(0, 1);
        var response = client.findAll(pageRequest, ContentQueryParams.empty(), "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body).hasSize(1);
        assertThat(body.getTotalPages()).isGreaterThan(1);
    }

    @Test
    public void findAll_oneContent_whenFilteringContentsByTags() {
        var pageRequest = Pageable.from(0, 10);
        var params = new ContentQueryParams();
        params.setTags(List.of("ee2027fd-5303-4df9-8e26-c4f6bd2ca079"));
        var response = client.findAll(pageRequest, params, "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body)
                .hasSize(1)
                .extracting(
                        ContentResponse::getId,
                        ContentResponse::getTags)
                .containsExactly(
                        tuple(
                                UUID.fromString("742b1b14-9139-4954-a986-8be333df9ba7"),
                                List.of("ee2027fd-5303-4df9-8e26-c4f6bd2ca079")));
    }

    @Test
    public void findAll_InvalidDateRangeException_whenEndDateIsNotPresentInRange() {
        var params = new ContentQueryParams();
        params.setStartDate(LocalDateTime.now());
        assertThatExceptionOfType(InvalidDateRangeException.class)
                .isThrownBy(() -> client.findAll(defaultPage(), params, "platos"))
                .withMessage("Preencha ambos os filtros \"startDate\" e \"endDate\"");
    }

    @Test
    public void findAll_InvalidDateRangeException_whenStartDateIsNotPresentInRange() {
        var params = new ContentQueryParams();
        params.setEndDate(LocalDateTime.now());
        assertThatExceptionOfType(InvalidDateRangeException.class)
                .isThrownBy(() -> client.findAll(defaultPage(), params, "platos"))
                .withMessage("Preencha ambos os filtros \"startDate\" e \"endDate\"");
    }

    @Test
    public void findAll_InvalidDateRangeException_whenStartDateIsGreaterThanEndDate() {
        var params = new ContentQueryParams();
        params.setStartDate(LocalDateTime.of(2019, 12, 5, 13, 0, 0, 0));
        params.setEndDate(LocalDateTime.of(2019, 12, 4, 13, 0, 0, 0));
        assertThatExceptionOfType(InvalidDateRangeException.class)
                .isThrownBy(() -> client.findAll(defaultPage(), params, "platos"))
                .withMessage("\"startDate\" não pode ser maior que \"endDate\"");
    }

    @Test
    public void findAll_onlyVisibleContents_whenFilteringVisibleContents() {
        var params = new ContentQueryParams();
        params.setVisible(true);
        var response = client.findAll(Pageable.from(0), params, "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body)
                .hasSize(3)
                .extracting(ContentResponse::getActive)
                .containsOnly(true);
    }

    @Test
    public void findAll_contentsMatchingTenantId_whenNoFilterIsPresent() {
        var response = client.findAll(Pageable.from(0), ContentQueryParams.empty(), "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        assertThat(response.body())
                .isNotEmpty()
                .extracting(ContentResponse::getTenantId)
                .containsOnly("platos");
    }

    @Test
    public void delete_contentInactivated_whenRemovingAContent() {
        var response = client.delete(UUID.fromString("742b1b14-9139-4954-a986-8be333df9ba7"), "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getActive()).isFalse();
        assertThat(body.getDeletedAt()).isAfter(body.getCreatedAt());
    }

    @Test
    public void update_contentUpdated_whenUpdatingAnExistingContent() {
        var request = umContentRequest();
        var response = client.update(UUID.fromString("c8f5959e-21ec-4f07-ae29-e4dba6819cb5"), request, "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getId().toString()).isEqualTo("c8f5959e-21ec-4f07-ae29-e4dba6819cb5");
        assertThat(body.getType()).isEqualTo(request.getType());
        assertThat(body.getName()).isEqualTo(request.getName());
        assertThat(body.getDescription()).isEqualTo(request.getDescription());
        assertThat(body.getRoot()).isEqualTo(request.getRoot());
        assertThat(body.getLang()).isEqualTo(request.getLang());
        assertThat(body.getQuestions()).isEqualTo(request.getQuestions());
        assertThat(body.getTags()).isNotEmpty();
        assertThat(body.getExamAttempts()).isEqualTo(2);
        assertThat(body.getVisible()).isFalse();
        assertThat(body.getIsbn()).isEqualTo(request.getIsbn());
        assertThat(body.getPreview()).isFalse();
        assertThat(body.getModalities()).containsExactlyInAnyOrder(Link.Modality.Type.ALL);
        assertThat(body.getProductionStatus()).isEqualTo(request.getProductionStatus());
        assertThat(body.getLibras()).isEqualTo(request.getLibras());
        assertThat(body.getVideoType()).isEqualTo(request.getVideoType());
        assertThat(body.getCedoc()).isEqualTo(request.getCedoc());
        assertThat(body.getExamTimeoutInMinutes()).isEqualTo(request.getExamTimeoutInMinutes());
    }

    @Test
    public void update_notFound_whenContentDoesNotExists() {
        var request = umContentRequest();
        var response = client.update(UUID.fromString("cd12bd46-10ca-4690-9656-26c8ec4db695"), request, "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.NOT_FOUND.getCode());
    }

    @Test
    public void update_updatedAtGreaterThanCreatedAt_whenUpdatingAnExistingContent() {
        var request = umContentRequest();
        var response = client.update(UUID.fromString("a9368a5b-8b1c-4f41-9521-6af3d7c95eac"), request, "platos");
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getUpdatedAt()).isAfter(body.getCreatedAt());
    }

    private Pageable defaultPage() {
        return Pageable.from(0, 10);
    }
}