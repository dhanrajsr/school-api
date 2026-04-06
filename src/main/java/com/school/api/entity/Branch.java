package com.school.api.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String location;
    private String contact;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();

    public Branch() {}

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getContact() { return contact; }
    public List<Course> getCourses() { return courses; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setContact(String contact) { this.contact = contact; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
}
