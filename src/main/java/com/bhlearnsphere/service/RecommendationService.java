package com.bhlearnsphere.service;

import com.bhlearnsphere.dto.*;
import com.bhlearnsphere.entity.*;
import com.bhlearnsphere.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CourseDto> getRecommendedCourses(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Course> enrolledCourses = courseRepository.findEnrolledCoursesByUser(userId);
        List<Course> allCourses = courseRepository.findAll();

        // Remove already enrolled courses
        List<Course> availableCourses = allCourses.stream()
                .filter(course -> !enrolledCourses.contains(course))
                .collect(Collectors.toList());

        // Simple recommendation algorithm
        List<Course> recommendations = new ArrayList<>();

        // 1. Recommend courses from same categories as enrolled courses
        Set<String> enrolledCategories = enrolledCourses.stream()
                .map(Course::getCategory)
                .collect(Collectors.toSet());

        List<Course> categoryBased = availableCourses.stream()
                .filter(course -> enrolledCategories.contains(course.getCategory()))
                .limit(3)
                .collect(Collectors.toList());

        recommendations.addAll(categoryBased);

        // 2. Recommend free courses
        List<Course> freeCourses = availableCourses.stream()
                .filter(course -> course.getPrice() == 0 || course.getPrice() == null)
                .filter(course -> !recommendations.contains(course))
                .limit(2)
                .collect(Collectors.toList());

        recommendations.addAll(freeCourses);

        // 3. Recommend most popular courses (by enrollment count)
        List<Course> popularCourses = availableCourses.stream()
                .filter(course -> !recommendations.contains(course))
                .sorted((c1, c2) -> Integer.compare(
                        c2.getEnrollments() != null ? c2.getEnrollments().size() : 0,
                        c1.getEnrollments() != null ? c1.getEnrollments().size() : 0
                ))
                .limit(3)
                .collect(Collectors.toList());

        recommendations.addAll(popularCourses);

        // 4. If still not enough, add latest courses
        if (recommendations.size() < 5) {
            List<Course> latestCourses = availableCourses.stream()
                    .filter(course -> !recommendations.contains(course))
                    .sorted((c1, c2) -> Long.compare(c2.getId(), c1.getId())) // Assuming higher ID = newer
                    .limit(5 - recommendations.size())
                    .collect(Collectors.toList());

            recommendations.addAll(latestCourses);
        }

        return recommendations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CourseDto> getPopularCourses() {
        List<Course> courses = courseRepository.findAll();
        
        return courses.stream()
                .sorted((c1, c2) -> Integer.compare(
                        c2.getEnrollments() != null ? c2.getEnrollments().size() : 0,
                        c1.getEnrollments() != null ? c1.getEnrollments().size() : 0
                ))
                .limit(10)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CourseDto> getTrendingCourses() {
        // For now, return latest courses as trending
        return courseRepository.findLatestCourses().stream()
                .limit(10)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CourseDto> getCoursesByUserPreferences(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get user's enrolled course categories
        List<Course> enrolledCourses = courseRepository.findEnrolledCoursesByUser(userId);
        Map<String, Long> categoryCount = enrolledCourses.stream()
                .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting()));

        // Find most preferred category
        String preferredCategory = categoryCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Web Development");

        // Return courses from preferred category
        return courseRepository.findByCategory(preferredCategory).stream()
                .filter(course -> !enrolledCourses.contains(course))
                .limit(5)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CourseDto convertToDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setPrice(course.getPrice());
        dto.setCategory(course.getCategory());

        if (course.getInstructor() != null) {
            UserDto instructorDto = new UserDto();
            instructorDto.setId(course.getInstructor().getId());
            instructorDto.setName(course.getInstructor().getName());
            instructorDto.setEmail(course.getInstructor().getEmail());
            dto.setInstructor(instructorDto);
        }

        if (course.getEnrollments() != null) {
            dto.setEnrollmentCount(course.getEnrollments().size());
        }

        return dto;
    }
}
