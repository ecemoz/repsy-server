package io.repsy.repository.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.repsy.repository.dto.MetaJsonDTO;
import io.repsy.repository.model.PackageMetadata;
import io.repsy.repository.repository.PackageMetadataRepository;
import io.repsy.storageapi.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DeploymentService {

    private final PackageMetadataRepository repository;
    private final StorageService storageService;
    private final ObjectMapper objectMapper;

    public void deploy(String packageName, String version, MultipartFile packageFile, MultipartFile metaFile) throws IOException {

        MetaJsonDTO meta = objectMapper.readValue(metaFile.getBytes(), MetaJsonDTO.class);

        if (!packageName.equals(meta.getName()) || !version.equals(meta.getVersion())) {
            throw new IllegalArgumentException("Path and meta.json version/name mismatch");
        }

        String basePath = packageName + "/" + version;
        String packagePath = storageService.store(basePath + "/package.rep", packageFile.getInputStream());
        String metaPath = storageService.store(basePath + "/meta.json", metaFile.getInputStream());


        PackageMetadata entity = new PackageMetadata();
        entity.setName(meta.getName());
        entity.setVersion(meta.getVersion());
        entity.setAuthor(meta.getAuthor());
        entity.setDependencies(meta.getDependencies());
        entity.setFilePath(packagePath);
        entity.setMetaPath(metaPath);

        repository.save(entity);
    }
}