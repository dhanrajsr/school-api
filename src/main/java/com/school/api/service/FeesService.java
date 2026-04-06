package com.school.api.service;

import com.school.api.dto.FeesRequest;
import com.school.api.dto.FeesResponse;
import com.school.api.entity.Course;
import com.school.api.entity.Fees;
import com.school.api.entity.Student;
import com.school.api.repository.CourseRepository;
import com.school.api.repository.FeesRepository;
import com.school.api.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeesService {

    private final FeesRepository feesRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<FeesResponse> findAll() {
        return feesRepository.findAll().stream().map(this::toResponse).toList();
    }

    public FeesResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public List<FeesResponse> findByStudent(Long studentId) {
        return feesRepository.findByStudentId(studentId).stream().map(this::toResponse).toList();
    }

    public FeesResponse create(FeesRequest request) {
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + request.studentId()));
        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found: " + request.courseId()));
        Fees fees = Fees.builder()
                .amount(request.amount())
                .student(student)
                .course(course)
                .build();
        return toResponse(feesRepository.save(fees));
    }

    public FeesResponse update(Long id, FeesRequest request) {
        Fees fees = getOrThrow(id);
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + request.studentId()));
        Course course = courseRepository.findById(request.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found: " + request.courseId()));
        fees.setAmount(request.amount());
        fees.setStudent(student);
        fees.setCourse(course);
        return toResponse(feesRepository.save(fees));
    }

    public void delete(Long id) {
        feesRepository.delete(getOrThrow(id));
    }

    private Fees getOrThrow(Long id) {
        return feesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fees not found: " + id));
    }

    private FeesResponse toResponse(Fees fees) {
        return new FeesResponse(
                fees.getId(),
                fees.getAmount(),
                fees.getStudent().getId(),
                fees.getStudent().getName(),
                fees.getCourse().getId(),
                fees.getCourse().getName()
        );
    }
}
