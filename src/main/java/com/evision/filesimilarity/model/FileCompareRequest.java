package com.evision.filesimilarity.model;

import com.evision.filesimilarity.validation.annotation.FileConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class FileCompareRequest {
    @FileConstraint
    private MultipartFile referenceFile;

    @NotEmpty(message = "At least one file is required.")
    private List<@FileConstraint MultipartFile> poolFiles;
}
