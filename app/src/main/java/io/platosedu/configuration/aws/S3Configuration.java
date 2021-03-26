package io.platosedu.configuration.aws;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ConfigurationProperties("aws.s3")
public class S3Configuration {

    @NotBlank
    private String region;

    @NotNull
    private Long multipartUploadThreshold;

    @NotNull
    private Integer maxUploadThreads;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getMultipartUploadThreshold() {
        return multipartUploadThreshold;
    }

    public void setMultipartUploadThreshold(Long multipartUploadThreshold) {
        this.multipartUploadThreshold = multipartUploadThreshold;
    }

    public Integer getMaxUploadThreads() {
        return maxUploadThreads;
    }

    public void setMaxUploadThreads(Integer maxUploadThreads) {
        this.maxUploadThreads = maxUploadThreads;
    }
}
