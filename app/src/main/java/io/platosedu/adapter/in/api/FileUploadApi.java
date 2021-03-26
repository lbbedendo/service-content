package io.platosedu.adapter.in.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.platosedu.usecase.dto.FileUploadResponse;

import java.io.IOException;

public interface FileUploadApi {

    @Post(value = "/upload/{location}",
            consumes = MediaType.MULTIPART_FORM_DATA,
            produces = MediaType.APPLICATION_JSON)
    HttpResponse<FileUploadResponse> uploadFile(CompletedFileUpload file,
                                                String location) throws IOException;
}
