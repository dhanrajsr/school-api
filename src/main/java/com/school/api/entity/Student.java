package com.school.api.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer age;
    private LocalDate dob;
    private String address;

    @ManyToMany
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fees> fees = new ArrayList<>();

    public Student() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getAge() { return age; }
    public LocalDate getDob() { return dob; }
    public String getAddress() { return address; }
    public List<Course> getCourses() { return courses; }
    public List<Fees> getFees() { return fees; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(Integer age) { this.age = age; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public void setAddress(String address) { this.address = address; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
    public void setFees(List<Fees> fees) { this.fees = fees; }
}
