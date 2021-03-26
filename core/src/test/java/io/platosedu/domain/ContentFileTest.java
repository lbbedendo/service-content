package io.platosedu.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ContentFileTest {
    @Test
    public void from_contentFileWithUUIDInName_whenCreatingANewContentFileWithLocationAndFileName() {
        var contentFile = ContentFile.from("cosmos-bucket-s3", "file.txt");
        assertThat(contentFile.getName().split("_"))
                .hasSize(2)
                .satisfies(array -> {
                    assertThat(UUID.fromString(array[0])).isNotNull();
                    assertThat(array[1]).isEqualTo("file.txt");
                });
    }
}