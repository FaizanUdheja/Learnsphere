package com.bhlearnsphere.repository;

import com.bhlearnsphere.entity.Course;
import com.bhlearnsphere.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructor(User instructor);
    List<Course> findByCategory(String category);
    List<Course> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT c FROM Course c WHERE c.price = 0")
    List<Course> findFreeCourses();
    
    @Query("SELECT c FROM Course c ORDER BY c.id DESC")
    List<Course> findLatestCourses();
    
    @Query("SELECT c FROM Course c WHERE c.id IN (SELECT e.course.id FROM Enrollment e WHERE e.user.id = :userId)")
    List<Course> findEnrolledCoursesByUser(@Param("userId") Long userId);
}
