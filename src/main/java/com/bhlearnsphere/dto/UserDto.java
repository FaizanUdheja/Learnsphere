package com.bhlearnsphere.dto;

import com.bhlearnsphere.helper.Role;
import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private int points;
    private String bio;
    private String location;
    private String website;
    private List<BadgeDto> badges;

    // Constructors
    public UserDto() {}

    public UserDto(Long id, String name, String email, Role role, int points, String bio, String location, String website) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.points = points;
        this.bio = bio;
        this.location = location;
        this.website = website;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public List<BadgeDto> getBadges() { return badges; }
    public void setBadges(List<BadgeDto> badges) { this.badges = badges; }
}
