package io.platosedu.adapter.in.controller.fixtures;

import io.platosedu.adapter.in.dto.request.ContentRequest;
import io.platosedu.domain.Content;
import io.platosedu.domain.Link;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ContentControllerTestFixtures {
    public static List<Content> multipleContents() {
        return List.of(
                Content.builder()
                        .id(UUID.fromString("c8f5959e-21ec-4f07-ae29-e4dba6819cb5"))
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
                        .id(UUID.fromString("742b1b14-9139-4954-a986-8be333df9ba7"))
                        .type(Content.Type.FILE)
                        .name("file.txt")
                        .root(false)
                        .active(true)
                        .lang("PT-BR")
                        .tags(List.of(UUID.fromString("ee2027fd-5303-4df9-8e26-c4f6bd2ca079")))
                        .questions(15)
                        .path("/path/to/file.txt")
                        .visible(false)
                        .updatedAt(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .preview(false)
                        .tenantId("platos")
                        .build(),
                Content.builder()
                        .id(UUID.fromString("a9368a5b-8b1c-4f41-9521-6af3d7c95eac"))
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
                        .id(UUID.fromString("433097e5-70d0-4198-9384-5e14c4392b51"))
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
                        .id(UUID.fromString("a34af580-d18c-4533-aeaa-ae1e1ab1ebfb"))
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
        contentSaveCommand.setTags(List.of("f0213462-58fd-42db-9bff-12e5879e61a9"));
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
