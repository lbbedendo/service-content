package io.platosedu.usecase.dto;

import lombok.Data;

import java.net.URL;

@Data
public class FileUploadResponse {
    private Boolean success;
    private String url;

    public static FileUploadResponse of(Boolean success, URL url) {
        var response = new FileUploadResponse();
        response.setSuccess(success);
        response.setUrl(url.toExternalForm());
        return response;
    }
}
