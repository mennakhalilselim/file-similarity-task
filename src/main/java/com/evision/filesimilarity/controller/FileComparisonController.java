package com.evision.filesimilarity.controller;

import com.evision.filesimilarity.model.ComparisonResponse;
import com.evision.filesimilarity.model.FileCompareRequest;
import com.evision.filesimilarity.service.contract.FileComparisonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileComparisonController {
    private final FileComparisonService comparisonService;

    @PostMapping("/compare")
    public ResponseEntity<ComparisonResponse> compareFiles(
            @Valid
            @NotNull
            @ModelAttribute
            FileCompareRequest fileCompareRequest
    )
            throws IOException {
        ComparisonResponse response = comparisonService.compareFiles(fileCompareRequest);
        return ResponseEntity.ok(response);
    }
}
