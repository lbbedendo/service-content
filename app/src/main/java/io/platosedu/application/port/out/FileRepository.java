package io.platosedu.application.port.out;

import io.platosedu.domain.FileAddress;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface FileRepository {
    URL upload(FileAddress fileAddress, InputStream inputStream) throws IOException;
}
