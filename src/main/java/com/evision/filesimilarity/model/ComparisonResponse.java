package com.evision.filesimilarity.model;

import java.util.List;

public record ComparisonResponse(String referenceFile, int filesCompared, List<FileSimilarityResult> results) {
    public FileSimilarityResult getBestMatch() {
        return results.isEmpty() ? null : results.getFirst();
    }
}
