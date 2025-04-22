package io.repsy.repository.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private String author;

    @ElementCollection
    private List<String> dependencies;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String metaPath;
}