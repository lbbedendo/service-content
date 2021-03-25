package io.platosedu.application.service;

import io.micronaut.http.multipart.CompletedFileUpload;
import io.platosedu.application.port.in.FileUploadUsecase;
import io.platosedu.application.port.in.dto.FileUploadResponse;
import io.platosedu.application.port.out.FileRepository;
import io.platosedu.domain.ContentFile;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.UUID;

@Singleton
public class FileUploadService implements FileUploadUsecase {
    private final FileRepository fileRepository;

    public FileUploadService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileUploadResponse upload(String location, CompletedFileUpload fileUpload) throws IOException {
        var contentFile = ContentFile.builder()
                .name(UUID.randomUUID().toString().concat("_").concat(fileUpload.getFilename()))
                .location(location)
                .build();
        var url = fileRepository.save(contentFile, fileUpload);
        return FileUploadResponse.of(true, url);
    }
}
