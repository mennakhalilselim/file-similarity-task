package com.evision.filesimilarity.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "file.comparison")
public class FileComparisonProperties {
    @NotBlank(message = "Reference file path is required")
    private String referenceFile;

    @NotBlank(message = "Pool directory path is required")
    private String poolDirectory;
}
