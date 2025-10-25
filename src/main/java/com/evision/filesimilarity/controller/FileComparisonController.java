package com.evision.filesimilarity.controller;

import com.evision.filesimilarity.model.ComparisonResponse;
import com.evision.filesimilarity.service.contract.FileComparisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileComparisonController {
    private final FileComparisonService comparisonService;

    @GetMapping("/compare")
    public ResponseEntity<ComparisonResponse> compareFiles() throws IOException {
        ComparisonResponse response = comparisonService.compareFiles();
        return ResponseEntity.ok(response);
    }
}
