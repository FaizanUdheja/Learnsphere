package com.bhlearnsphere.repository;

import com.bhlearnsphere.entity.User;
import com.bhlearnsphere.helper.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    List<User> findByPointsGreaterThanEqual(int points);
    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.badges WHERE u.email = :email")
    Optional<User> findByEmailWithBadges(@Param("email") String email);
}
