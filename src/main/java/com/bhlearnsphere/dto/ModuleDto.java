package com.bhlearnsphere.dto;

import java.util.List;

public class ModuleDto {
    private Long id;
    private String title;
    private Long courseId;
    private List<LessonDto> lessons;

    // Constructors
    public ModuleDto() {}

    public ModuleDto(Long id, String title, Long courseId) {
        this.id = id;
        this.title = title;
        this.courseId = courseId;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public List<LessonDto> getLessons() { return lessons; }
    public void setLessons(List<LessonDto> lessons) { this.lessons = lessons; }
}
