package com.bhlearnsphere.dto;

public class BadgeDto {
    private Long id;
    private String name;
    private String description;
    private String icon;
    private String color;
    private Integer pointsRequired;

    // Constructors
    public BadgeDto() {}

    public BadgeDto(Long id, String name, String description, String icon, String color, Integer pointsRequired) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.color = color;
        this.pointsRequired = pointsRequired;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Integer getPointsRequired() { return pointsRequired; }
    public void setPointsRequired(Integer pointsRequired) { this.pointsRequired = pointsRequired; }
}
