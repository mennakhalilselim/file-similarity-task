package com.evision.filesimilarity.service.impl;

import com.evision.filesimilarity.model.ComparisonResponse;
import com.evision.filesimilarity.model.FileCompareRequest;
import com.evision.filesimilarity.model.FileSimilarityResult;
import com.evision.filesimilarity.service.contract.FileComparisonService;
import com.evision.filesimilarity.service.contract.SimilarityCalculator;
import com.evision.filesimilarity.service.contract.WordFrequencyCounter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileComparisonServiceImpl implements FileComparisonService {

    private final WordFrequencyCounter wordCounter;
    private final SimilarityCalculator similarityCalculator;

    @Override
    public ComparisonResponse compareFiles(FileCompareRequest fileCompareRequest) throws IOException {
        MultipartFile referenceFile = fileCompareRequest.getReferenceFile();
        List<MultipartFile> poolFiles = fileCompareRequest.getPoolFiles();

        Map<String, Integer> referenceWordFrequency = wordCounter.countWords(referenceFile.getInputStream());

        List<FileSimilarityResult> results = processPoolFiles(poolFiles, referenceWordFrequency);

        return buildResponse(results, referenceFile.getOriginalFilename());
    }

    private List<FileSimilarityResult> processPoolFiles(
            List<MultipartFile> poolFiles,
            Map<String, Integer> referenceWordFreq
    ) {
        return poolFiles.stream()
                .parallel()
                .map(file -> compareFile(file, referenceWordFreq))
                .filter(Objects::nonNull)
                .sorted()
                .collect(Collectors.toList());
    }

    private FileSimilarityResult compareFile(MultipartFile file, Map<String, Integer> referenceWordFreq) {
        try {
            Map<String, Integer> targetWordFreq = wordCounter.countWords(file.getInputStream());
            double similarity = similarityCalculator.calculateSimilarity(referenceWordFreq, targetWordFreq);

            return new FileSimilarityResult(
                    file.getOriginalFilename(),
                    similarity
            );
        } catch (IOException e) {
            log.error("Error processing file: {}", file.getOriginalFilename(), e);
            return null;
        }
    }

    private ComparisonResponse buildResponse(List<FileSimilarityResult> results, String referenceFile) {
        return new ComparisonResponse(
                referenceFile,
                results.size(),
                results
        );
    }
}
