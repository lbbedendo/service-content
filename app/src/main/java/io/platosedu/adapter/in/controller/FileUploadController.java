package io.platosedu.adapter.in.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.platosedu.adapter.in.api.FileUploadApi;
import io.platosedu.application.port.in.FileUploadUsecase;
import io.platosedu.application.port.in.dto.FileUploadResponse;

import java.io.IOException;

@Controller("/file")
@Secured(SecurityRule.IS_AUTHENTICATED)
@ExecuteOn(TaskExecutors.IO)
public class FileUploadController implements FileUploadApi {

    private final FileUploadUsecase fileUploadUsecase;

    public FileUploadController(FileUploadUsecase fileUploadUsecase) {
        this.fileUploadUsecase = fileUploadUsecase;
    }

    @Override
    public HttpResponse<FileUploadResponse> uploadFile(CompletedFileUpload file, String location) throws IOException {
        return HttpResponse.ok(fileUploadUsecase.upload(location, file));
    }
}
