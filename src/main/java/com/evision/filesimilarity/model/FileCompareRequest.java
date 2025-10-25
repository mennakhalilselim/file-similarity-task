package com.evision.filesimilarity.model;

import com.evision.filesimilarity.validation.FileConstraint;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class FileCompareRequest {
    @FileConstraint
    private MultipartFile referenceFile;
    private List<@FileConstraint MultipartFile> poolFiles;
}
