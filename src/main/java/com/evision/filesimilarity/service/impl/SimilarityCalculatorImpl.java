package com.evision.filesimilarity.service.impl;

import com.evision.filesimilarity.service.contract.SimilarityCalculator;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class SimilarityCalculatorImpl implements SimilarityCalculator {
    @Override
    public double calculateSimilarity(Map<String, Integer> referenceFreq,
                                      Map<String, Integer> targetFreq) {
        if (referenceFreq.isEmpty() && targetFreq.isEmpty()) {
            return 100.0;
        }

        if (referenceFreq.isEmpty() || targetFreq.isEmpty()) {
            return 0.0;
        }

        Set<String> allWords = new HashSet<>();
        allWords.addAll(referenceFreq.keySet());
        allWords.addAll(targetFreq.keySet());

        long minSum = 0;
        long maxSum = 0;

        for (String word : allWords) {
            int refCount = referenceFreq.getOrDefault(word, 0);
            int targetCount = targetFreq.getOrDefault(word, 0);

            minSum += Math.min(refCount, targetCount);
            maxSum += Math.max(refCount, targetCount);
        }

        return maxSum == 0 ? 0.0 : (minSum * 100.0) / maxSum;
    }
}
