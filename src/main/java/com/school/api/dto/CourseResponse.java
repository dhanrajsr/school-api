package com.school.api.dto;

public record CourseResponse(
    Long id,
    String name,
    String duration,
    String subjects,
    Long branchId,
    String branchName
) {}
