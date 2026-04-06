package com.school.api.dto;

import java.time.LocalDate;
import java.util.List;

public record StudentResponse(
    Long id,
    String name,
    Integer age,
    LocalDate dob,
    String address,
    List<CourseResponse> courses
) {}
