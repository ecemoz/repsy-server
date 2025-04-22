package io.repsy.repository.storage;

import java.io.IOException;
import java.io.InputStream;

public interface StorageService {
    String store(String path, InputStream content) throws IOException;
    InputStream load(String path) throws IOException;
}