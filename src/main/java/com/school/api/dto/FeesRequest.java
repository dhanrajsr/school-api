package com.school.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record FeesRequest(
    @NotNull(message = "Amount is required") @Positive(message = "Amount must be positive") BigDecimal amount,
    @NotNull(message = "Student ID is required") Long studentId,
    @NotNull(message = "Course ID is required") Long courseId
) {}
