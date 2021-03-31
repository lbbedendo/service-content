package io.platosedu.adapter.in.controller.fixtures;

import io.platosedu.adapter.in.dto.request.ContentRequest;
import io.platosedu.domain.Content;
import io.platosedu.domain.Link;
import io.platosedu.domain.Tag;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public class ContentControllerTestFixtures {
    public static List<Content> multipleContents() {
        return List.of(
                Content.builder()
                        .id(new ObjectId("5de029c9919cf85887af3a9b"))
                        .type(Content.Type.FOLDER)
                        .name("Gestão de Pessoas")
                        .description("Descrição do curso Gestão de Pessoas")
                        .root(true)
                        .active(true)
                        .lang("PT-BR")
                        .questions(5)
                        .visible(true)
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .preview(true)
                        .tenantId("platos")
                        .build(),
                Content.builder()
                        .id(new ObjectId("5de02a3705aeb57efc34be22"))
                        .type(Content.Type.FILE)
                        .name("file.txt")
                        .root(false)
                        .active(true)
                        .lang("PT-BR")
                        .tags(List.of(new ObjectId("5e31f47be0635bec87eb0a21")))
                        .questions(15)
                        .path("/path/to/file.txt")
                        .visible(false)
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .preview(false)
                        .tenantId("platos")
                        .build(),
                Content.builder()
                        .id(new ObjectId("5de66841766ca21238e612d5"))
                        .type(Content.Type.MARKDOWN)
                        .name("test.md")
                        .root(false)
                        .active(true)
                        .lang("PT-BR")
                        .questions(5)
                        .examAttempts(10)
                        .visible(true)
                        .updatedAt(LocalDateTime.of(2019, 12, 3, 10, 0, 0, 0))
                        .createdAt(LocalDateTime.of(2019, 12, 3, 10, 0, 0, 0))
                        .preview(false)
                        .tenantId("platos")
                        .build(),
                Content.builder()
                        .id(new ObjectId("5e5d65cf06701019d7b169cb"))
                        .type(Content.Type.BOOK)
                        .name("Gestão de Obras Públicas")
                        .root(false)
                        .active(true)
                        .lang("PT-BR")
                        .questions(5)
                        .examAttempts(10)
                        .visible(true)
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .preview(false)
                        .tenantId("kroton")
                        .build(),
                Content.builder()
                        .id(new ObjectId("5e6a8e8035e27bca2ca1b694"))
                        .type(Content.Type.BOOK)
                        .name("MBA em Finanças")
                        .root(false)
                        .active(true)
                        .lang("PT-BR")
                        .visible(true)
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .preview(false)
                        .tenantId("platos")
                        .build()
        );
    }

    public static ContentRequest umContentRequest() {
        var contentSaveCommand = new ContentRequest();
        contentSaveCommand.setType(Content.Type.FILE);
        contentSaveCommand.setName("test_file.txt");
        contentSaveCommand.setDescription("description updated");
        contentSaveCommand.setRoot(true);
        contentSaveCommand.setLang("EN-US");
        contentSaveCommand.setQuestions(0);
        contentSaveCommand.setTags(List.of("5e31f6e162ad720bb595253d"));
        contentSaveCommand.setExamAttempts(2);
        contentSaveCommand.setVisible(false);
        contentSaveCommand.setIsbn("9782123456803");
        contentSaveCommand.setPreview(false);
        contentSaveCommand.setModalities(List.of(Link.Modality.Type.ALL));
        contentSaveCommand.setProductionStatus(Content.ProductionStatus.DONE);
        contentSaveCommand.setLibras(true);
        contentSaveCommand.setVideoType(Content.VideoType.PRESENTATION);
        contentSaveCommand.setCedoc("codigo2");
        contentSaveCommand.setExamTimeoutInMinutes(60);
        return contentSaveCommand;
    }
}
