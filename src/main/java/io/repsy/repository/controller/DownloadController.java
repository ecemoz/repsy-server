package io.repsy.repository.controller;

import io.repsy.repository.service.DownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{packageName}/{version}")
public class DownloadController {

    private final DownloadService downloadService;

    @GetMapping("/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(
            @PathVariable String packageName,
            @PathVariable String version,
            @PathVariable String fileName
    ) throws IOException {

        InputStream inputStream = downloadService.download(packageName, version, fileName);
        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(getContentType(fileName))
                .body(resource);
    }

    private MediaType getContentType(String fileName) {
        if (fileName.endsWith(".json")) {
            return MediaType.APPLICATION_JSON;
        } else if (fileName.endsWith(".rep") || fileName.endsWith(".zip")) {
            return MediaType.APPLICATION_OCTET_STREAM;
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}