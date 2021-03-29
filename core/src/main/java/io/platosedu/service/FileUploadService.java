package io.platosedu.service;

import io.micronaut.http.multipart.CompletedFileUpload;
import io.platosedu.domain.ContentFile;
import io.platosedu.port.out.FileRepository;
import io.platosedu.usecase.FileUploadUsecase;
import io.platosedu.usecase.dto.FileUploadResponse;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class FileUploadService implements FileUploadUsecase {
    private final FileRepository fileRepository;

    public FileUploadService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileUploadResponse upload(String location, CompletedFileUpload fileUpload) throws IOException {
        var contentFile = ContentFile.from(location, fileUpload.getFilename());
        var url = fileRepository.save(contentFile, fileUpload);
        return FileUploadResponse.of(true, url);
    }
}
