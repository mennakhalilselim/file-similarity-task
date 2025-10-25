package com.evision.filesimilarity.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements ConstraintValidator<FileConstraint, MultipartFile> {
    private static final long MAX_SIZE = 1024 * 1024 * 2; // 2MB
    private static final String[] ALLOWED_TYPES = {"text/plain"};

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext ctx) {
        if (file == null || file.isEmpty()) {
            return true;
        }
        for (String type : ALLOWED_TYPES) {
            if (type.equalsIgnoreCase(file.getContentType())) return true;
        }
        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate("Only plain text files allowed").addConstraintViolation();
        return false;
    }
}
