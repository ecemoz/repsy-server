package io.repsy.repository.controller;

import io.repsy.repository.service.DeploymentService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/{packageName}/{version}")
public class DeploymentController {

    private final DeploymentService deploymentService;

    @PostMapping
    public ResponseEntity<String> uploadPackage(
            @PathVariable @NotBlank String packageName,
            @PathVariable @NotBlank String version,
            @RequestPart("packageFile")MultipartFile packageFile,
            @RequestPart("metafile") MultipartFile metaFile
            ) {
        try{
            deploymentService.deploy(packageName,version,packageFile, metaFile);
            return ResponseEntity.status(HttpStatus.CREATED).body("Deployment successful");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deployment failed: " + e.getMessage());
        }
    }
}