package io.repsy.repository.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class MetaJsonDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String version;

    @NotBlank
    private String author;

    @NotEmpty
    private List<@NotBlank String> dependencies;
}
