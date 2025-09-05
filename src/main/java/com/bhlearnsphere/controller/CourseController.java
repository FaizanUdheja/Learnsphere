package com.bhlearnsphere.controller;

import com.bhlearnsphere.dto.*;
import com.bhlearnsphere.service.CourseService;
import com.bhlearnsphere.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        try {
            CourseDto course = courseService.getCourseById(id);
            return ResponseEntity.ok(course);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto, @RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            String email = jwtUtil.getEmailFromToken(jwt);
            // In a real app, you'd get user ID from the token or database
            Long instructorId = 1L; // This should be extracted from the token
            
            CourseDto course = courseService.createCourse(courseDto, instructorId);
            return ResponseEntity.ok(course);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        try {
            CourseDto course = courseService.updateCourse(id, courseDto);
            return ResponseEntity.ok(course);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok(Map.of("message", "Course deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseDto>> getCoursesByInstructor(@PathVariable Long instructorId) {
        List<CourseDto> courses = courseService.getCoursesByInstructor(instructorId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<CourseDto>> getCoursesByCategory(@PathVariable String category) {
        List<CourseDto> courses = courseService.getCoursesByCategory(category);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseDto>> searchCourses(@RequestParam String title) {
        List<CourseDto> courses = courseService.searchCourses(title);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/free")
    public ResponseEntity<List<CourseDto>> getFreeCourses() {
        List<CourseDto> courses = courseService.getFreeCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/latest")
    public ResponseEntity<List<CourseDto>> getLatestCourses() {
        List<CourseDto> courses = courseService.getLatestCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/enrolled/{userId}")
    public ResponseEntity<List<CourseDto>> getEnrolledCoursesByUser(@PathVariable Long userId) {
        List<CourseDto> courses = courseService.getEnrolledCoursesByUser(userId);
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<?> enrollStudent(@PathVariable Long courseId, @RequestParam Long userId) {
        try {
            courseService.enrollStudent(courseId, userId);
            return ResponseEntity.ok(Map.of("message", "Successfully enrolled in course"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{courseId}/modules")
    public ResponseEntity<List<ModuleDto>> getCourseModules(@PathVariable Long courseId) {
        List<ModuleDto> modules = courseService.getCourseModules(courseId);
        return ResponseEntity.ok(modules);
    }

    @PostMapping("/{courseId}/modules")
    public ResponseEntity<?> createModule(@PathVariable Long courseId, @RequestBody ModuleDto moduleDto) {
        try {
            ModuleDto module = courseService.createModule(courseId, moduleDto);
            return ResponseEntity.ok(module);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
