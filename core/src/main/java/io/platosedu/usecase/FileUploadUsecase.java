package io.platosedu.usecase;

import io.micronaut.http.multipart.CompletedFileUpload;

import java.io.IOException;
import io.platosedu.usecase.dto.FileUploadResponse;

public interface FileUploadUsecase {
    FileUploadResponse upload(String location, CompletedFileUpload fileUpload) throws IOException;
}
