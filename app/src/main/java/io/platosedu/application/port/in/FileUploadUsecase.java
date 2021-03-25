package io.platosedu.application.port.in;

import io.micronaut.http.multipart.CompletedFileUpload;
import io.platosedu.application.port.in.dto.FileUploadResponse;

import java.io.IOException;

public interface FileUploadUsecase {
    FileUploadResponse upload(String location, CompletedFileUpload fileUpload) throws IOException;
}
