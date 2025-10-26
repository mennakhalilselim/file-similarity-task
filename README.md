# File Similarity Comparison Application

A high-performance Spring Boot application that calculates similarity scores between text files based on word frequency matching. Upload a reference file and multiple pool files to identify the best matches.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [API Reference](#api-reference)
- [Testing](#testing)
- [Project Structure](#project-structure)

## Features

- **File Upload Support**: Upload reference and pool files directly via multipart/form-data
- **Intelligent Word Matching**: Compares files based on word frequency, ignoring word order
- **Parallel Processing**: Utilizes multi-core processors for fast batch comparisons
- **RESTful API**: Easy integration with other systems
- **Performance Optimized**: Handles files with up to 10M words efficiently
- **Automatic Scoring**: Returns similarity scores from 0% to 100%
- **Comprehensive Error Handling**: Detailed error responses for troubleshooting

## Requirements

### Native Installation
- **Java**: 17 or higher
- **Maven**: 3.6+
- **Spring Boot**: 3.5.7

### Docker Installation
- **Docker**: 20.10+ or higher

## Installation

### Method 1: Native Installation

#### 1. Clone the Repository

```bash
git clone https://github.com/mennakhalilselim/file-similarity-task.git
cd file-similarity-task
```

#### 2. Build the Project

```bash
mvn clean install
```

#### 3. Verify Installation

```bash
mvn test
```

### Method 2: Docker Installation

#### 1. Clone the Repository

```bash
git clone https://github.com/mennakhalilselim/file-similarity-task.git
cd file-similarity-task
```

#### 2. Build Docker Image

```bash
docker build -t file-similarity-app .
```

#### 3. Run Container

```bash
docker run -p 8080:8080 --name file-similarity file-similarity-app
```

## API Reference

### Compare Files Endpoint

**Endpoint**: `POST /api/files/compare`

**Content-Type**: `multipart/form-data`

#### Request Parameters

| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| `referenceFile` | File | Yes | The reference file to compare against |
| `poolFiles` | File[] | Yes | Array of pool files |

#### Success Response (200 OK)

```json
{
  "referenceFile": "reference.txt",
  "filesCompared": 3,
  "results": [
    {
      "fileName": "file1.txt",
      "similarityScore": 100.0
    },
    {
      "fileName": "file2.txt",
      "similarityScore": 75.5
    },
    {
      "fileName": "file3.txt",
      "similarityScore": 23.1
    }
  ],
  "bestMatch": {
    "fileName": "file1.txt",
    "similarityScore": 100.0
  }
}
```

#### Error Responses

**400 Bad Request**
```json
{
  "timestamp": "2025-10-26T12:34:56.789",
  "status": 400,
  "message": "Validation failed",
  "errors": []
}
```

**500 Internal Server Error**
```json
{
  "timestamp": "2025-10-26T12:34:56.789",
  "status": 500,
  "message": "File operation failed",
  "errors": []
}
```

## Testing

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=SimilarityCalculatorTest
```

### Test Coverage

The project includes unit tests for:
- ✅ Perfect matches (100% score)
- ✅ No matches (0% score)
- ✅ Partial matches
- ✅ Extra words scenarios
- ✅ Different word frequencies
- ✅ Empty files
- ✅ Edge cases
- ✅ File upload handling
- ✅ Error scenarios

## Project Structure

```
file-similarity-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/evision/filesimilarity/
│   │   │       ├── FileSimilarityApplication.java
│   │   │       ├── controller/
│   │   │       │   └── FileComparisonController.java
│   │   │       ├── error/
│   │   │       │   ├── model/
│   │   │       │   │   └── ErrorResponse.java
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       ├── model/
│   │   │       │   ├── ComparisonResponse.java
│   │   │       │   ├── FileComparisonRequest.java
│   │   │       │   └── FileSimilarityResult.java
│   │   │       ├── service/
│   │   │       │   ├── contract/
│   │   │       │   │   ├── FileComparisonService.java
│   │   │       │   │   ├── SimilarityCalculator.java
│   │   │       │   │   └── WordFrequencyCounter.java
│   │   │       │   └── impl/
│   │   │       │       ├── FileComparisonServiceImpl.java
│   │   │       │       ├── SimilarityCalculatorImpl.java
│   │   │       │       └── WordFrequencyCounterImpl.java
│   │   │       └── validation/
│   │   │           ├── annotation/
│   │   │           │   └── FileConstraint.java
│   │   │           └── validator/
│   │   │               └── FileValidator.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/evision/filesimilarity/
│               └── service/
│                   └── SimilarityCalculatorTest.java
├── Dockerfile
├── pom.xml
└── README.md
```