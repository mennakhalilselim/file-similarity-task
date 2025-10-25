package com.evision.filesimilarity.service.contract;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface WordFrequencyCounter {
    /**
     * Counts the frequency of words in a given text file.
     * @param filePath path to the text file to process
     * @return a map of words to their frequency counts
     * @throws IOException if reading the file fails
     */
    Map<String, Integer> countWords(Path filePath) throws IOException;
}
