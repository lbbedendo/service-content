package io.platosedu.persistence;

import io.micronaut.http.multipart.CompletedFileUpload;
import io.platosedu.domain.ContentFile;

import java.io.IOException;
import java.net.URL;

public interface FileRepository {
    URL save(ContentFile contentFile, CompletedFileUpload fileUpload) throws IOException;
}
