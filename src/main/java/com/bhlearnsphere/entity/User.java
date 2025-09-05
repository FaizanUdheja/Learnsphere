package com.bhlearnsphere.entity;

import com.bhlearnsphere.helper.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private int points;
    private String bio;
    private String location;
    private String website;

    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private List<Course> courses;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
        name = "user_badges",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "badge_id")
    )
    private List<Badge> badges;

    // Getters & Setters
    public Long getId(){ return id; }
    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
    public Role getRole(){ return role; }
    public int getPoints(){ return points; }
    public String getBio(){ return bio; }
    public String getLocation(){ return location; }
    public String getWebsite(){ return website; }
    public List<Badge> getBadges(){ return badges; }
    public List<Course> getCourses(){ return courses; }

    public void setId(Long id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setEmail(String email){ this.email = email; }
    public void setPassword(String password){ this.password = password; }
    public void setRole(Role role){ this.role = role; }
    public void setPoints(int points){ this.points = points; }
    public void setBio(String bio){ this.bio = bio; }
    public void setLocation(String location){ this.location = location; }
    public void setWebsite(String website){ this.website = website; }
    public void setBadges(List<Badge> badges){ this.badges = badges; }
    public void setCourses(List<Course> courses){ this.courses = courses; }
}
