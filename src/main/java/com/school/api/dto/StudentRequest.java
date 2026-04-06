package com.school.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public record StudentRequest(
    @NotBlank(message = "Name is required") String name,
    Integer age,
    LocalDate dob,
    String address,
    List<Long> courseIds
) {}
