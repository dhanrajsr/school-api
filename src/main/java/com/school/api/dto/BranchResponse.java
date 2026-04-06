package com.school.api.dto;

public record BranchResponse(
    Long id,
    String name,
    String location,
    String contact
) {}
