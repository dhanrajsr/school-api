package com.school.api.controller;

import com.school.api.dto.FeesRequest;
import com.school.api.dto.FeesResponse;
import com.school.api.service.FeesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeesController {

    private final FeesService feesService;

    public FeesController(FeesService feesService) {
        this.feesService = feesService;
    }

    @GetMapping
    public List<FeesResponse> findAll() {
        return feesService.findAll();
    }

    @GetMapping("/{id}")
    public FeesResponse findById(@PathVariable Long id) {
        return feesService.findById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<FeesResponse> findByStudent(@PathVariable Long studentId) {
        return feesService.findByStudent(studentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FeesResponse create(@Valid @RequestBody FeesRequest request) {
        return feesService.create(request);
    }

    @PutMapping("/{id}")
    public FeesResponse update(@PathVariable Long id, @Valid @RequestBody FeesRequest request) {
        return feesService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        feesService.delete(id);
    }
}
