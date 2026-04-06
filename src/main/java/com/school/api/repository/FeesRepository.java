package com.school.api.repository;

import com.school.api.entity.Fees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeesRepository extends JpaRepository<Fees, Long> {
    List<Fees> findByStudentId(Long studentId);
    List<Fees> findByCourseId(Long courseId);
}
