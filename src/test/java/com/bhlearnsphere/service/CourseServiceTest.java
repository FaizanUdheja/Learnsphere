/*package com.bhlearnsphere.service;

import com.bhlearnsphere.dto.CourseDto;
import com.bhlearnsphere.entity.Course;
import com.bhlearnsphere.entity.User;
import com.bhlearnsphere.helper.Role;
import com.bhlearnsphere.repository.CourseRepository;
import com.bhlearnsphere.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CourseService courseService;

    private User instructor;
    private Course course;
    private CourseDto courseDto;

    @BeforeEach
    void setUp() {
        instructor = new User();
        instructor.setId(1L);
        instructor.setName("John Doe");
        instructor.setEmail("john@example.com");
        instructor.setRole(Role.INSTRUCTOR);

        course = new Course();
        course.setId(1L);
        course.setTitle("Test Course");
        course.setDescription("Test Description");
        course.setPrice(49.99);
        course.setCategory("Programming");
        course.setInstructor(instructor);

        courseDto = new CourseDto();
        courseDto.setTitle("Test Course");
        courseDto.setDescription("Test Description");
        courseDto.setPrice(49.99);
        courseDto.setCategory("Programming");
    }

    @Test
    void createCourse_ShouldReturnCourseDto_WhenValidInput() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(instructor));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // Act
        CourseDto result = courseService.createCourse(courseDto, 1L);

        // Assert
        assertNotNull(result);
        assertEquals("Test Course", result.getTitle());
        assertEquals("Test Description", result.getDescription());
        assertEquals(49.99, result.getPrice());
        assertEquals("Programming", result.getCategory());
        
        verify(userRepository).findById(1L);
        verify(courseRepository).save(any(Course.class));
    }

    @Test
    void createCourse_ShouldThrowException_WhenInstructorNotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> courseService.createCourse(courseDto, 1L));
        
        assertEquals("Instructor not found", exception.getMessage());
        verify(userRepository).findById(1L);
        verify(courseRepository, never()).save(any(Course.class));
    }

    @Test
    void getCourseById_ShouldReturnCourseDto_WhenCourseExists() {
        // Arrange
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        // Act
        CourseDto result = courseService.getCourseById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Test Course", result.getTitle());
        assertEquals("Test Description", result.getDescription());
        assertEquals(49.99, result.getPrice());
        assertEquals("Programming", result.getCategory());
        
        verify(courseRepository).findById(1L);
    }

    @Test
    void getCourseById_ShouldThrowException_WhenCourseNotFound() {
        // Arrange
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> courseService.getCourseById(1L));
        
        assertEquals("Course not found", exception.getMessage());
        verify(courseRepository).findById(1L);
    }

    @Test
    void enrollStudent_ShouldEnrollStudent_WhenValidInput() {
        // Arrange
        User student = new User();
        student.setId(2L);
        student.setName("Jane Doe");
        student.setEmail("jane@example.com");
        student.setRole(Role.STUDENT);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(userRepository.findById(2L)).thenReturn(Optional.of(student));
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // Act
        courseService.enrollStudent(1L, 2L);

        // Assert
        verify(courseRepository).findById(1L);
        verify(userRepository).findById(2L);
    }

    @Test
    void enrollStudent_ShouldThrowException_WhenCourseNotFound() {
        // Arrange
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> courseService.enrollStudent(1L, 2L));
        
        assertEquals("Course not found", exception.getMessage());
        verify(courseRepository).findById(1L);
        verify(userRepository, never()).findById(any());
    }

    @Test
    void enrollStudent_ShouldThrowException_WhenStudentNotFound() {
        // Arrange
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, 
            () -> courseService.enrollStudent(1L, 2L));
        
        assertEquals("User not found", exception.getMessage());
        verify(courseRepository).findById(1L);
        verify(userRepository).findById(2L);
    }
}
*/