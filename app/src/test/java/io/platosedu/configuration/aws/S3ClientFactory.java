package io.platosedu.configuration.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;

import javax.inject.Singleton;

@Factory
public class S3ClientFactory {

    @Singleton
    @Requires(beans = S3Configuration.class)
    public AmazonS3 amazonS3(S3Configuration s3Configuration) {
        var endpoint = new AwsClientBuilder.EndpointConfiguration("http://localhost:8001",
                s3Configuration.getRegion());
        return AmazonS3ClientBuilder
                .standard()
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(endpoint)
                .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
                .build();
    }
}
