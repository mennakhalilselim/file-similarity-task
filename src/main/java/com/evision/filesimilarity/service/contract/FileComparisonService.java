package com.evision.filesimilarity.service.contract;

import com.evision.filesimilarity.model.ComparisonResponse;

import java.io.IOException;

public interface FileComparisonService {
    /**
     * Compares the reference file with all files in the pool directory to find similar files.
     * @return a {@link ComparisonResponse} containing similarity results
     * @throws IOException if there are issues reading files
     */
    ComparisonResponse compareFiles() throws IOException;
}
