package io.platosedu.adapter.out.persistence;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.FileUpload;
import io.platosedu.persistence.FileRepository;
import io.platosedu.domain.ContentFile;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.URL;

@Singleton
@Slf4j
public class FileRepositoryAdapter implements FileRepository {

    private final AmazonS3 s3Client;

    public FileRepositoryAdapter(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public URL save(ContentFile contentFile, CompletedFileUpload fileUpload) throws IOException {
        var inputStream = fileUpload.getInputStream();
        var key = contentFile.getName();
        var bucket = contentFile.getLocation();
        try {
            var request = new PutObjectRequest(
                    bucket,
                    key,
                    inputStream,
                    createObjectMetadata(fileUpload));
            s3Client.putObject(request);
            inputStream.close();
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("Erro ao salvar arquivo: " + e.getMessage());
                throw e;
            }
        }
        return s3Client.getUrl(bucket, key);
    }

    private ObjectMetadata createObjectMetadata(FileUpload file) {
        var objectMetadata = new ObjectMetadata();
        file.getContentType().ifPresent(contentType -> objectMetadata.setContentType(contentType.getName()));
        if (file.getSize() != 0) {
            objectMetadata.setContentLength(file.getSize());
        }
        return objectMetadata;
    }
}
