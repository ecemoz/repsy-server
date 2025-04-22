package io.repsy.repository.service;

import io.repsy.repository.model.PackageMetadata;
import io.repsy.repository.repository.PackageMetadataRepository;
import io.repsy.storageapi.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DownloadService {

    private final PackageMetadataRepository repository;
    private final StorageService storageService;

    public InputStream download(String packageName, String version, String fileName) throws IOException {
        Optional<PackageMetadata> optional = repository.findByNameAndVersion(packageName, version);
        if(optional.isEmpty()) {
            throw new RuntimeException("Package " + packageName + " not found");
        }

        PackageMetadata metadata = optional.get();
        String filePath;
        if(fileName.equals("package.rep")) {
            filePath = metadata.getFilePath();
        } else if (fileName.equals("meta.json")) {
            filePath = metadata.getMetaPath();
        } else {
            throw new IllegalArgumentException("Invalid file name: " + fileName);
        }

        return storageService.load(filePath.replace("storage/", ""));
    }
}