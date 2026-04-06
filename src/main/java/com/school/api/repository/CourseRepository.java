package com.school.api.repository;

import com.school.api.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByBranchId(Long branchId);
}
