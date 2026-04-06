package com.school.api.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String duration;
    private String subjects;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public Course() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDuration() { return duration; }
    public String getSubjects() { return subjects; }
    public Branch getBranch() { return branch; }
    public List<Student> getStudents() { return students; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setSubjects(String subjects) { this.subjects = subjects; }
    public void setBranch(Branch branch) { this.branch = branch; }
    public void setStudents(List<Student> students) { this.students = students; }
}
