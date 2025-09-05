package com.bhlearnsphere.repository;

import com.bhlearnsphere.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    List<Badge> findByPointsRequiredLessThanEqual(Integer points);
    List<Badge> findByNameContainingIgnoreCase(String name);
}
