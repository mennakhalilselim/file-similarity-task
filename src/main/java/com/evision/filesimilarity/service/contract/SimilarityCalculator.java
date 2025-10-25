package com.evision.filesimilarity.service.contract;

import java.util.Map;

public interface SimilarityCalculator {
    /**
     * Calculates the similarity percentage between two word frequency maps.
     * @param referenceFreq word frequency map of the reference file
     * @param targetFreq    word frequency map of the target file
     * @return similarity score as a percentage (0.0–100.0)
     */
    double calculateSimilarity(Map<String, Integer> referenceFreq,
                               Map<String, Integer> targetFreq);
}
