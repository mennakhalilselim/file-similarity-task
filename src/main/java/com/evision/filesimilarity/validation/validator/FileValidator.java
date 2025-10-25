package com.evision.filesimilarity.validation.validator;

import com.evision.filesimilarity.validation.annotation.FileConstraint;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

public class FileValidator implements ConstraintValidator<FileConstraint, MultipartFile> {
    private static final long MAX_SIZE_BYTES = 2 * 1024;
    private static final Set<String> ALLOWED_MIME_TYPES = Set.of("text/plain");
    private static final String DEFAULT_MIME_TYPE = "application/octet-stream";

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (isFileMissing(file)) {
            buildViolation(context, "File is required.");
            return false;
        }

        if (isFileTooLarge(file)) {
            buildViolation(context, "File size must not exceed 2MB.");
            return false;
        }

        if (!isValidFileType(file)) {
            buildViolation(context, "Only plain text files allowed.");
            return false;
        }

        return true;
    }

    private boolean isFileMissing(MultipartFile file) {
        return file == null || StringUtils.isBlank(file.getOriginalFilename());
    }

    private boolean isFileTooLarge(MultipartFile file) {
        return file.getSize() > MAX_SIZE_BYTES;
    }

    private boolean isValidFileType(MultipartFile file) {
        String contentType = getContentType(file);
        return ALLOWED_MIME_TYPES.contains(contentType.toLowerCase());
    }

    private String getContentType(MultipartFile file) {
        return Optional.ofNullable(file.getContentType())
                .orElse(DEFAULT_MIME_TYPE);
    }

    private void buildViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
