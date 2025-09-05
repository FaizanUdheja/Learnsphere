package com.bhlearnsphere.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 2000)
    private String description;
    private Double price;
    private String category;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;

    @OneToMany(mappedBy = "course")
    private List<Module> modules;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    // Getters & Setters
    public Long getId(){ return id; }
    public String getTitle(){ return title; }
    public String getDescription(){ return description; }
    public Double getPrice(){ return price; }
    public String getCategory(){ return category; }
    public User getInstructor(){ return instructor; }
    public List<Module> getModules(){ return modules; }
    public List<Enrollment> getEnrollments(){ return enrollments; }

    public void setId(Long id){ this.id = id; }
    public void setTitle(String title){ this.title = title; }
    public void setDescription(String description){ this.description = description; }
    public void setPrice(Double price){ this.price = price; }
    public void setCategory(String category){ this.category = category; }
    public void setInstructor(User instructor){ this.instructor = instructor; }
    public void setModules(List<Module> modules){ this.modules = modules; }
    public void setEnrollments(List<Enrollment> enrollments){ this.enrollments = enrollments; }
}
