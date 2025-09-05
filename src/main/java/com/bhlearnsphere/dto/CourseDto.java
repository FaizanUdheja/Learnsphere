package com.bhlearnsphere.dto;

import java.util.List;

public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private UserDto instructor;
    private List<ModuleDto> modules;
    private int enrollmentCount;
    private Double averageRating;

    // Constructors
    public CourseDto() {}

    public CourseDto(Long id, String title, String description, Double price, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public UserDto getInstructor() { return instructor; }
    public void setInstructor(UserDto instructor) { this.instructor = instructor; }

    public List<ModuleDto> getModules() { return modules; }
    public void setModules(List<ModuleDto> modules) { this.modules = modules; }

    public int getEnrollmentCount() { return enrollmentCount; }
    public void setEnrollmentCount(int enrollmentCount) { this.enrollmentCount = enrollmentCount; }

    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
}
