package io.platosedu.configuration.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;

import javax.inject.Singleton;

@Factory
public class S3ClientFactory {

    @Singleton
    @Requires(beans = S3Configuration.class)
    @Requires(beans = AwsCredentialsConfiguration.class)
    public AmazonS3 amazonS3(S3Configuration s3Configuration,
                             AwsCredentialsConfiguration awsCredentialsConfiguration) {
        return AmazonS3Client.builder()
                .withRegion(s3Configuration.getRegion())
                .withCredentials(awsCredentialsConfiguration)
                .build();
    }
}
