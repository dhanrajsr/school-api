package com.school.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequest(
    @NotBlank(message = "Name is required") String name,
    String duration,
    String subjects,
    @NotNull(message = "Branch ID is required") Long branchId
) {}
