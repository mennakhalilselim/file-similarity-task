package com.evision.filesimilarity.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@UtilityClass
public class FileUtil {
    public void validatePathExists(Path path, String description) throws IOException {
        if (!Files.exists(path)) {
            throw new IOException(String.format("%s does not exist: %s", description, path));
        }
    }

    public void validateDirectoryExists(Path path, String description) throws IOException {
        validatePathExists(path, description);

        if (!Files.isDirectory(path)) {
            throw new IOException(String.format("%s is not a directory: %s", description, path));
        }
    }
}
