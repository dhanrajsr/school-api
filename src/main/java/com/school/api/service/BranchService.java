package com.school.api.service;

import com.school.api.dto.BranchRequest;
import com.school.api.dto.BranchResponse;
import com.school.api.entity.Branch;
import com.school.api.repository.BranchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<BranchResponse> findAll() {
        return branchRepository.findAll().stream().map(this::toResponse).toList();
    }

    public BranchResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public BranchResponse create(BranchRequest request) {
        Branch branch = new Branch();
        branch.setName(request.name());
        branch.setLocation(request.location());
        branch.setContact(request.contact());
        return toResponse(branchRepository.save(branch));
    }

    public BranchResponse update(Long id, BranchRequest request) {
        Branch branch = getOrThrow(id);
        branch.setName(request.name());
        branch.setLocation(request.location());
        branch.setContact(request.contact());
        return toResponse(branchRepository.save(branch));
    }

    public void delete(Long id) {
        branchRepository.delete(getOrThrow(id));
    }

    private Branch getOrThrow(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found: " + id));
    }

    public BranchResponse toResponse(Branch branch) {
        return new BranchResponse(branch.getId(), branch.getName(), branch.getLocation(), branch.getContact());
    }
}
