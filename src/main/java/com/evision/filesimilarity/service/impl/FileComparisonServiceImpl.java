package com.evision.filesimilarity.service.impl;

import com.evision.filesimilarity.config.FileComparisonProperties;
import com.evision.filesimilarity.model.ComparisonResponse;
import com.evision.filesimilarity.model.FileSimilarityResult;
import com.evision.filesimilarity.service.contract.FileComparisonService;
import com.evision.filesimilarity.service.contract.SimilarityCalculator;
import com.evision.filesimilarity.service.contract.WordFrequencyCounter;
import com.evision.filesimilarity.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileComparisonServiceImpl implements FileComparisonService {
    private static final String REFERENCE_FILE = "Reference file";
    private static final String POOL_DIRECTORY = "Pool directory";

    private final FileComparisonProperties fileComparisonProperties;
    private final WordFrequencyCounter wordCounter;
    private final SimilarityCalculator similarityCalculator;

    @Override
    public ComparisonResponse compareFiles() throws IOException {
        Path referenceFilePath = getReferenceFilePath();
        FileUtil.validatePathExists(referenceFilePath, REFERENCE_FILE);

        Path poolDirectoryPath = getPoolDirectoryPath();
        FileUtil.validateDirectoryExists(poolDirectoryPath, POOL_DIRECTORY);

        Map<String, Integer> referenceWordFrequency = wordCounter.countWords(referenceFilePath);
        List<FileSimilarityResult> results = processPoolFiles(poolDirectoryPath, referenceWordFrequency);

        return buildResponse(results);
    }

    private Path getReferenceFilePath() {
        return Paths.get(fileComparisonProperties.getReferenceFile());
    }

    private Path getPoolDirectoryPath() {
        return Paths.get(fileComparisonProperties.getPoolDirectory());
    }

    private List<FileSimilarityResult> processPoolFiles(
            Path poolDirectory,
            Map<String, Integer> referenceWordFreq
    ) throws IOException {
        try (var paths = Files.list(poolDirectory)) {
            return paths
                    .filter(Files::isRegularFile)
                    .parallel()
                    .map(path -> compareFile(path, referenceWordFreq))
                    .filter(Objects::nonNull)
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    private FileSimilarityResult compareFile(Path filePath, Map<String, Integer> referenceWordFreq) {
        try {
            Map<String, Integer> targetWordFreq = wordCounter.countWords(filePath);
            double similarity = similarityCalculator.calculateSimilarity(referenceWordFreq, targetWordFreq);

            return new FileSimilarityResult(
                    filePath.getFileName().toString(),
                    similarity
            );
        } catch (IOException e) {
            log.error("Error processing file: {}", filePath, e);
            return null;
        }
    }

    private ComparisonResponse buildResponse(List<FileSimilarityResult> results) {
        return new ComparisonResponse(
                fileComparisonProperties.getReferenceFile(),
                results.size(),
                results
        );
    }
}
