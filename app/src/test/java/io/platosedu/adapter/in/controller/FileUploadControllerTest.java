package io.platosedu.adapter.in.controller;

import com.amazonaws.services.s3.AmazonS3;
import io.findify.s3mock.S3Mock;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.client.multipart.MultipartBody;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.platosedu.usecase.dto.FileUploadResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;
import java.io.File;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@MicronautTest
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class FileUploadControllerTest extends TestControllerBase {

    @Inject
    AmazonS3 s3Client;
    S3Mock s3Mock;

    @BeforeAll
    public void setUp() {
        s3Mock = new S3Mock.Builder()
                .withPort(8001)
                .withInMemoryBackend()
                .build();
        s3Mock.start();
        s3Client.createBucket("kosmos-content-test");
    }

    @AfterAll
    public void cleanUp() {
        s3Mock.shutdown();
    }

    @Test
    public void uploadFile_ok_whenSuccesfullyUploadingAFileToAmazonS3() {
        var requestBody = MultipartBody.builder()
                .addPart("file", new File(Objects.requireNonNull(
                        getClass().getClassLoader().getResource("platos_logo.png")).getFile()))
                .build();
        var bearerToken = performLoginAndRetrieveAccessToken();
        var response = httpClient.toBlocking().exchange(
                HttpRequest.POST("/file/upload/kosmos-content-test", requestBody)
                        .header("Authorization", "Bearer " + bearerToken.getAccessToken())
                        .contentType(MediaType.MULTIPART_FORM_DATA_TYPE),
                FileUploadResponse.class);
        assertThat(response.code()).isEqualTo(HttpStatus.OK.getCode());
        var responseBody = response.body();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getSuccess()).isTrue();
        assertThat(responseBody.getUrl()).isNotNull();
    }

    @Test
    public void uploadFile_forbidden_whenBucketNameIsNotPresentInTheUrl() {
        var requestBody = MultipartBody.builder()
                .addPart("file", new File(Objects.requireNonNull(
                        getClass().getClassLoader().getResource("platos_logo.png")).getFile()))
                .build();
        var bearerToken = performLoginAndRetrieveAccessToken();
        assertThatExceptionOfType(HttpClientResponseException.class)
                .isThrownBy(() -> httpClient.toBlocking().exchange(
                        HttpRequest.POST("/file/upload", requestBody)
                                .header("Authorization", "Bearer " + bearerToken.getAccessToken())
                                .contentType(MediaType.MULTIPART_FORM_DATA_TYPE)))
                .withMessage("Forbidden");
    }
}