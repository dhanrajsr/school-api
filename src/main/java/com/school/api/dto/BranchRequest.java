package com.school.api.dto;

import jakarta.validation.constraints.NotBlank;

public record BranchRequest(
    @NotBlank(message = "Name is required") String name,
    String location,
    String contact
) {}
