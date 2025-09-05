package com.bhlearnsphere.dto;

public class LessonDto {
    private Long id;
    private String title;
    private String content;
    private String type;
    private Long moduleId;
    private boolean completed;

    // Constructors
    public LessonDto() {}

    public LessonDto(Long id, String title, String content, String type, Long moduleId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.moduleId = moduleId;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Long getModuleId() { return moduleId; }
    public void setModuleId(Long moduleId) { this.moduleId = moduleId; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
