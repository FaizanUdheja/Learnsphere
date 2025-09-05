package com.bhlearnsphere.repository;

import com.bhlearnsphere.entity.Module;
import com.bhlearnsphere.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByCourse(Course course);
    List<Module> findByCourseId(Long courseId);
}
