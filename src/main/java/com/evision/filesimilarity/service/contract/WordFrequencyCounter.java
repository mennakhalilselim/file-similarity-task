package com.evision.filesimilarity.service.contract;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface WordFrequencyCounter {

    /**
     * Counts the frequency of each word in the provided input stream.
     * Words are normalized to lowercase for case-insensitive counting.
     *
     * @param inputStream the input stream containing text content to analyze
     * @return a map where keys are lowercase words and values are their occurrence counts
     * @throws IOException if an I/O error occurs while reading from the input stream
     */
    Map<String, Integer> countWords(InputStream inputStream) throws IOException;
}
