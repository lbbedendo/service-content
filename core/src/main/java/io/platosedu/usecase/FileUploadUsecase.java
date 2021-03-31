package io.platosedu.usecase;

import io.micronaut.http.multipart.CompletedFileUpload;
import io.platosedu.usecase.dto.FileUploadResponse;

import java.io.IOException;

public interface FileUploadUsecase {
    FileUploadResponse upload(String location, CompletedFileUpload fileUpload) throws IOException;
}
