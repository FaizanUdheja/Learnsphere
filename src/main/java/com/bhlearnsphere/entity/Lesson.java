package com.bhlearnsphere.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 5000)
    private String content;
    private String type;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    // Getters & Setters
    public Long getId(){ return id; }
    public String getTitle(){ return title; }
    public String getContent(){ return content; }
    public String getType(){ return type; }
    public Module getModule(){ return module; }

    public void setId(Long id){ this.id = id; }
    public void setTitle(String title){ this.title = title; }
    public void setContent(String content){ this.content = content; }
    public void setType(String type){ this.type = type; }
    public void setModule(Module module){ this.module = module; }
}
