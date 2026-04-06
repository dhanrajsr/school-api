package com.school.api.service;

import com.school.api.dto.CourseRequest;
import com.school.api.dto.CourseResponse;
import com.school.api.entity.Branch;
import com.school.api.entity.Course;
import com.school.api.repository.BranchRepository;
import com.school.api.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final BranchRepository branchRepository;

    public CourseService(CourseRepository courseRepository, BranchRepository branchRepository) {
        this.courseRepository = courseRepository;
        this.branchRepository = branchRepository;
    }

    public List<CourseResponse> findAll() {
        return courseRepository.findAll().stream().map(this::toResponse).toList();
    }

    public CourseResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public CourseResponse create(CourseRequest request) {
        Branch branch = branchRepository.findById(request.branchId())
                .orElseThrow(() -> new EntityNotFoundException("Branch not found: " + request.branchId()));
        Course course = new Course();
        course.setName(request.name());
        course.setDuration(request.duration());
        course.setSubjects(request.subjects());
        course.setBranch(branch);
        return toResponse(courseRepository.save(course));
    }

    public CourseResponse update(Long id, CourseRequest request) {
        Course course = getOrThrow(id);
        Branch branch = branchRepository.findById(request.branchId())
                .orElseThrow(() -> new EntityNotFoundException("Branch not found: " + request.branchId()));
        course.setName(request.name());
        course.setDuration(request.duration());
        course.setSubjects(request.subjects());
        course.setBranch(branch);
        return toResponse(courseRepository.save(course));
    }

    public void delete(Long id) {
        courseRepository.delete(getOrThrow(id));
    }

    private Course getOrThrow(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found: " + id));
    }

    public CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getName(),
                course.getDuration(),
                course.getSubjects(),
                course.getBranch().getId(),
                course.getBranch().getName()
        );
    }
}
