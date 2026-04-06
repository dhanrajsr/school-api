package com.school.api.service;

import com.school.api.dto.StudentRequest;
import com.school.api.dto.StudentResponse;
import com.school.api.entity.Course;
import com.school.api.entity.Student;
import com.school.api.repository.CourseRepository;
import com.school.api.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseService courseService;

    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream().map(this::toResponse).toList();
    }

    public StudentResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public StudentResponse create(StudentRequest request) {
        List<Course> courses = resolveCourses(request.courseIds());
        Student student = Student.builder()
                .name(request.name())
                .age(request.age())
                .dob(request.dob())
                .address(request.address())
                .courses(courses)
                .build();
        return toResponse(studentRepository.save(student));
    }

    public StudentResponse update(Long id, StudentRequest request) {
        Student student = getOrThrow(id);
        student.setName(request.name());
        student.setAge(request.age());
        student.setDob(request.dob());
        student.setAddress(request.address());
        student.setCourses(resolveCourses(request.courseIds()));
        return toResponse(studentRepository.save(student));
    }

    public void delete(Long id) {
        studentRepository.delete(getOrThrow(id));
    }

    private Student getOrThrow(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found: " + id));
    }

    private List<Course> resolveCourses(List<Long> courseIds) {
        if (courseIds == null || courseIds.isEmpty()) return List.of();
        return courseIds.stream()
                .map(cid -> courseRepository.findById(cid)
                        .orElseThrow(() -> new EntityNotFoundException("Course not found: " + cid)))
                .toList();
    }

    public StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getDob(),
                student.getAddress(),
                student.getCourses().stream().map(courseService::toResponse).toList()
        );
    }
}
