# File Similarity Comparison Application

A Spring Boot application that calculates similarity scores between text files based on word frequency matching. The application compares a reference file against a pool of files and identifies the best matches.

## ✨ Features

- **Intelligent Word Matching**: Compares files based on word frequency, ignoring word order
- **Parallel Processing**: Utilizes multi-core processors for fast batch comparisons
- **RESTful API**: Easy integration with other systems
- **Configurable**: File paths managed through application properties
- **Automatic Scoring**: Returns similarity scores from 0% to 100%

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/mennakhalilselim/file-similarity-task.git
cd file-similarity-task
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Verify Installation

```bash
mvn test
```

## 🎯 Usage
### Method 1: REST API

Start the application:
```bash
mvn spring-boot:run
```

Call the comparison endpoint:
```bash
curl http://localhost:8080/api/files/compare
```

### Method 2: With Docker

You can build and run the application directly using Docker:

1. **Build the image**

    ```bash
    docker build -t <image-name> .
   ```

1. **Run the container**

    ```bash
    docker run \
    -e reference_file_path=<your-file-path> \
    -e pool_directory_path=<your-dir-path> \
    -p 8080:8080 \
    --name <container-name> \
    <image-name>
   ```

## 📡 API Reference

### Compare Files Endpoint

**Endpoint**: `GET /api/files/compare`

**Response**: JSON with similarity results

```json
{
  "referenceFile": "/data/reference.txt",
  "filesCompared": 3,
  "results": [
    {
      "fileName": "perfect-match.txt",
      "similarityScore": 100.0
    },
    {
      "fileName": "partial-match.txt",
      "similarityScore": 75.5
    }
  ],
  "bestMatch": {
    "fileName": "perfect-match.txt",
    "similarityScore": 100.0
  }
}
```

**Status Codes**:
- `200 OK`: Comparison successful
- `500 Internal Server Error`: File reading error or configuration issue

## 🧪 Testing

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

## 📁 Project Structure

```
file-similarity-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/evision/filesimilarity/
│   │   │       ├── FileSimilarityApplication.java
│   │   │       ├── config/
│   │   │       │   └── FileComparisonProperties.java
│   │   │       ├── controller/
│   │   │       │   └── FileComparisonController.java
│   │   │       ├── error/
│   │   │       │   ├── model
│   │   │       │   |   └── ErrorResponse.java
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       ├── model/
│   │   │       │   ├── ComparisonResponse.java
│   │   │       │   └── FileSimilarityResult.java
│   │   │       ├── service/
│   │   │       |   ├── contract
│   │   │       |   |   ├── FileComparisonService.java
│   │   │       |   |   ├── SimilarityCalculator.java
│   │   │       |   |   └── WordFrequencyCounter.java
│   │   │       |   └── impl
│   │   │       |       ├── FileComparisonServiceImpl.java
│   │   │       |       ├── SimilarityCalculatorImpl.java
│   │   │       |       └── WordFrequencyCounterImpl.java
│   │   │       └── util/
│   │   │           └── FileUtil.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/evision/filesimilarity/
│               └── service/
│                   └── SimilarityCalculatorTest.java
├── pom.xml
└── README.md
```