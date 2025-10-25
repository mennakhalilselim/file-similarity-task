package com.evision.filesimilarity.service.impl;

import com.evision.filesimilarity.service.contract.WordFrequencyCounter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WordFrequencyCounterImpl implements WordFrequencyCounter {
    private static final Pattern WORD_PATTERN = Pattern.compile("[a-zA-Z]+");

    @Override
    public Map<String, Integer> countWords(Path filePath) throws IOException {
        Map<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            reader.lines()
                    .forEach(line -> countWordsInLine(line, wordFrequency));
        }

        return wordFrequency;
    }

    private void countWordsInLine(String line, Map<String, Integer> wordFrequency) {
        Matcher matcher = WORD_PATTERN.matcher(line);
        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            wordFrequency.merge(word, 1, Integer::sum);
        }
    }
}
