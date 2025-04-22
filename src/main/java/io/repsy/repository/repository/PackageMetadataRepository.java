package io.repsy.repository.repository;

import io.repsy.repository.model.PackageMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PackageMetadataRepository extends JpaRepository<PackageMetadata, Long> {

    Optional<PackageMetadata> findByNameAndVersion(String name, String version);

}