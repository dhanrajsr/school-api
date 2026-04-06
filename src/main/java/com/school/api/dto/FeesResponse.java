package com.school.api.dto;

import java.math.BigDecimal;

public record FeesResponse(
    Long id,
    BigDecimal amount,
    Long studentId,
    String studentName,
    Long courseId,
    String courseName
) {}
