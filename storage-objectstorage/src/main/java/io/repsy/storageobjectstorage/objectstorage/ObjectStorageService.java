package io.repsy.storageobjectstorage.objectstorage;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetObjectArgs;
import io.repsy.storageapi.StorageService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "storage.strategy", havingValue = "object-storage")
public class ObjectStorageService implements StorageService {

    @Value("${minio.url}")
    private String minioUrl;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucket;

    private MinioClient minioClient;

    @PostConstruct
    public void init() {
        this.minioClient = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(accessKey, secretKey)
                .build();
    }

    @Override
    public String store(String path, InputStream content) throws IOException {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(path)
                            .stream(content, -1, 10485760)
                            .contentType("application/octet-stream")
                            .build()
            );
            return bucket + "/" + path;
        } catch (Exception e) {
            throw new IOException("MinIO upload failed", e);
        }
    }

    @Override
    public InputStream load(String path) throws IOException {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(path)
                            .build()
            );
        } catch (Exception e) {
            throw new IOException("MinIO download failed", e);
        }
    }
}
