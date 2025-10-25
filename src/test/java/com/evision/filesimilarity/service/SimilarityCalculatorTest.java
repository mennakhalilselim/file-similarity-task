package com.evision.filesimilarity.service;

import com.evision.filesimilarity.service.contract.SimilarityCalculator;
import com.evision.filesimilarity.service.impl.SimilarityCalculatorImpl;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimilarityCalculatorTest {
    private final SimilarityCalculator calculator = new SimilarityCalculatorImpl();

    @Test
    void testPerfectMatch() {
        Map<String, Integer> frequency1 = new HashMap<>();
        frequency1.put("the", 2);
        frequency1.put("quick", 1);
        frequency1.put("brown", 1);

        Map<String, Integer> frequency2 = new HashMap<>();
        frequency2.put("the", 2);
        frequency2.put("quick", 1);
        frequency2.put("brown", 1);

        double score = calculator.calculateSimilarity(frequency1, frequency2);
        assertEquals(100.0, score, 0.01);
    }

    @Test
    void testNoMatch() {
        Map<String, Integer> frequency1 = new HashMap<>();
        frequency1.put("hello", 1);

        Map<String, Integer> frequency2 = new HashMap<>();
        frequency2.put("world", 1);

        double score = calculator.calculateSimilarity(frequency1, frequency2);
        assertEquals(0.0, score, 0.01);
    }

    @Test
    void testPartialMatch() {
        Map<String, Integer> frequency1 = new HashMap<>();
        frequency1.put("a", 1);
        frequency1.put("b", 1);
        frequency1.put("c", 1);

        Map<String, Integer> frequency2 = new HashMap<>();
        frequency2.put("a", 1);
        frequency2.put("b", 1);

        double score = calculator.calculateSimilarity(frequency1, frequency2);
        assertEquals(66.67, score, 0.01);
    }

    @Test
    void testExtraWords() {
        Map<String, Integer> frequency1 = new HashMap<>();
        frequency1.put("a", 1);
        frequency1.put("b", 1);

        Map<String, Integer> frequency2 = new HashMap<>();
        frequency2.put("a", 1);
        frequency2.put("b", 1);
        frequency2.put("c", 1);

        double score = calculator.calculateSimilarity(frequency1, frequency2);
        assertEquals(66.67, score, 0.01);
    }

    @Test
    void testDifferentFrequencies() {
        Map<String, Integer> frequency1 = new HashMap<>();
        frequency1.put("the", 3);

        Map<String, Integer> frequency2 = new HashMap<>();
        frequency2.put("the", 1);

        double score = calculator.calculateSimilarity(frequency1, frequency2);
        assertEquals(33.33, score, 0.01);
    }
}
