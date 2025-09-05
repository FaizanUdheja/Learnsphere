package com.bhlearnsphere.service;

import com.bhlearnsphere.dto.*;
import com.bhlearnsphere.entity.*;
import com.bhlearnsphere.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public CourseDto createCourse(CourseDto courseDto, Long instructorId) {
        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setPrice(courseDto.getPrice());
        course.setCategory(courseDto.getCategory());
        course.setInstructor(instructor);

        Course savedCourse = courseRepository.save(course);
        return convertToDto(savedCourse);
    }

    public CourseDto updateCourse(Long courseId, CourseDto courseDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setPrice(courseDto.getPrice());
        course.setCategory(courseDto.getCategory());

        Course savedCourse = courseRepository.save(course);
        return convertToDto(savedCourse);
    }

    public void deleteCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new RuntimeException("Course not found");
        }
        courseRepository.deleteById(courseId);
    }

    public CourseDto getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return convertToDto(course);
    }

    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CourseDto> getCoursesByInstructor(Long instructorId) {
        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        return courseRepository.findByInstructor(instructor).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CourseDto> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CourseDto> searchCourses(String title) {
        return courseRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CourseDto> getFreeCourses() {
        return courseRepository.findFreeCourses().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CourseDto> getLatestCourses() {
        return courseRepository.findLatestCourses().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<CourseDto> getEnrolledCoursesByUser(Long userId) {
        return courseRepository.findEnrolledCoursesByUser(userId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void enrollStudent(Long courseId, Long userId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (enrollmentRepository.existsByUserAndCourse(user, course)) {
            throw new RuntimeException("User already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setCompleted(false);
        enrollment.setProgress(0.0);

        enrollmentRepository.save(enrollment);
    }

    public List<ModuleDto> getCourseModules(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return moduleRepository.findByCourse(course).stream()
                .map(this::convertModuleToDto)
                .collect(Collectors.toList());
    }

    public ModuleDto createModule(Long courseId, ModuleDto moduleDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        com.bhlearnsphere.entity.Module module = new com.bhlearnsphere.entity.Module();
        module.setTitle(moduleDto.getTitle());
        module.setCourse(course);

        com.bhlearnsphere.entity.Module savedModule = moduleRepository.save(module);
        return convertModuleToDto(savedModule);
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

    private ModuleDto convertModuleToDto(com.bhlearnsphere.entity.Module module) {
        ModuleDto dto = new ModuleDto();
        dto.setId(module.getId());
        dto.setTitle(module.getTitle());
        dto.setCourseId(module.getCourse().getId());
        return dto;
    }
}
