package io.platosedu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ContentFile {
    private String name;
    private String location;

    public static ContentFile from(String location, String fileName) {
        return ContentFile.builder()
                .name(UUID.randomUUID().toString().concat("_").concat(fileName))
                .location(location)
                .build();
    }
}
