package io.platosedu.adapter.in.controller;

import com.mongodb.client.MongoClient;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.multitenancy.tenantresolver.TenantResolver;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.platosedu.adapter.in.api.ContentApi;
import io.platosedu.adapter.in.controller.fixtures.ContentControllerTestFixtures;
import io.platosedu.adapter.in.dto.request.ContentRequest;
import io.platosedu.adapter.out.persistence.content.ContentRepositoryAdapter;
import io.platosedu.configuration.mongodb.MongoConfiguration;
import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import io.platosedu.helper.TestHelper;
import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
    @Inject
    TenantResolver tenantResolver;

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
        request.setTags(List.of("5e31f6e162ad720bb595253d"));
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
        var response = client.save(TestHelper.mockCustomUserDetails(), request);
        assertThat(response.code()).isEqualTo(HttpStatus.CREATED.getCode());
        var body = response.body();
        assertThat(body).isNotNull();
        assertThat(body.getType()).isEqualTo(request.getType());
        assertThat(body.getName()).isEqualTo(request.getName());
        assertThat(body.getDescription()).isEqualTo(request.getDescription());
        assertThat(body.getRoot()).isEqualTo(request.getRoot());
        assertThat(body.getActive()).isTrue();
        assertThat(body.getLang()).isEqualTo(request.getLang());
        assertThat(body.getTags()).containsExactly("5e31f6e162ad720bb595253d");
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
                .isThrownBy(() -> client.save(TestHelper.mockCustomUserDetails(), request))
                .withMessage("save.contentSaveCommand.name: must not be blank");
    }
}