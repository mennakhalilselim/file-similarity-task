package com.evision.filesimilarity.service.impl;

import com.evision.filesimilarity.service.contract.WordFrequencyCounter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
    public Map<String, Integer> countWords(InputStream inputStream) throws IOException {
        Map<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
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
