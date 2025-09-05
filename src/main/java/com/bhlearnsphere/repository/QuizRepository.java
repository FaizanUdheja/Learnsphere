package com.bhlearnsphere.repository;

import com.bhlearnsphere.entity.Quiz;
import com.bhlearnsphere.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCourse(Course course);
    List<Quiz> findByCourseId(Long courseId);
}
