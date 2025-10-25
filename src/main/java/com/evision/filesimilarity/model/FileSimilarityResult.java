package com.evision.filesimilarity.model;

public record FileSimilarityResult(String fileName,
                                   double similarityScore) implements Comparable<FileSimilarityResult> {
    @Override
    public int compareTo(FileSimilarityResult other) {
        return Double.compare(other.similarityScore, this.similarityScore);
    }
}
