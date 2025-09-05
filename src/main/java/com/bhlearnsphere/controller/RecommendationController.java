package com.bhlearnsphere.controller;

import com.bhlearnsphere.dto.*;
import com.bhlearnsphere.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@CrossOrigin(origins = "http://localhost:3000")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CourseDto>> getRecommendedCourses(@PathVariable Long userId) {
        List<CourseDto> courses = recommendationService.getRecommendedCourses(userId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<CourseDto>> getPopularCourses() {
        List<CourseDto> courses = recommendationService.getPopularCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/trending")
    public ResponseEntity<List<CourseDto>> getTrendingCourses() {
        List<CourseDto> courses = recommendationService.getTrendingCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/preferences/{userId}")
    public ResponseEntity<List<CourseDto>> getCoursesByUserPreferences(@PathVariable Long userId) {
        List<CourseDto> courses = recommendationService.getCoursesByUserPreferences(userId);
        return ResponseEntity.ok(courses);
    }
}
