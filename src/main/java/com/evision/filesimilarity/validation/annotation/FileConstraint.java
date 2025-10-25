package com.evision.filesimilarity.validation.annotation;

import com.evision.filesimilarity.validation.validator.FileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileValidator.class)
@Target({ElementType.FIELD,  ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileConstraint {
    String message() default "Invalid file";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}