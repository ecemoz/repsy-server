package io.repsy.repository.storage.filesystem;

import io.repsy.repository.storage.StorageService;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class FileSystemStorageService implements StorageService {

    private static final String ROOT_PATH = "storage";

    @Override
    public String store(String path, InputStream inputStream) throws IOException {

        File file  = new File(ROOT_PATH + File.separator + path);
        file.getParentFile().mkdirs();

        try (OutputStream outputStream = new FileOutputStream(file)) {
            inputStream.transferTo(outputStream);
        }
        return file.getAbsolutePath();
    }

    @Override
    public InputStream load(String path) throws IOException {
        File file = new File(ROOT_PATH + File.separator + path);
        return new FileInputStream(file);
    }
}
