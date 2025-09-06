package com.bhlearnsphere.service;

import com.bhlearnsphere.dto.*;
import com.bhlearnsphere.entity.*;
import com.bhlearnsphere.repository.*;
import com.bhlearnsphere.helper.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setPoints(0);

        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    
    @Transactional
    public UserDto login(String email, String password) {
        User user = userRepository.findByEmailWithBadges(email)
           .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
    
        return convertToDto(user);
    }


    public UserDto updateProfile(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(userDto.getName());
        user.setBio(userDto.getBio());
        user.setLocation(userDto.getLocation());
        user.setWebsite(userDto.getWebsite());

        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    public UserDto addPoints(Long userId, int points) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPoints(user.getPoints() + points);
        User savedUser = userRepository.save(user);

        // Check for new badges
        checkAndAwardBadges(savedUser);

        return convertToDto(savedUser);
    }

    public List<BadgeDto> getUserBadges(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getBadges().stream()
                .map(this::convertBadgeToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> getUsersByRole(Role role) {
        return userRepository.findByRole(role).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private void checkAndAwardBadges(User user) {
        List<Badge> availableBadges = badgeRepository.findByPointsRequiredLessThanEqual(user.getPoints());
        
        for (Badge badge : availableBadges) {
            if (!user.getBadges().contains(badge)) {
                user.getBadges().add(badge);
            }
        }
        
        userRepository.save(user);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setPoints(user.getPoints());
        dto.setBio(user.getBio());
        dto.setLocation(user.getLocation());
        dto.setWebsite(user.getWebsite());
        
        if (user.getBadges() != null) {
            dto.setBadges(user.getBadges().stream()
                    .map(this::convertBadgeToDto)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }

    private BadgeDto convertBadgeToDto(Badge badge) {
        BadgeDto dto = new BadgeDto();
        dto.setId(badge.getId());
        dto.setName(badge.getName());
        dto.setDescription(badge.getDescription());
        dto.setIcon(badge.getIcon());
        dto.setColor(badge.getColor());
        dto.setPointsRequired(badge.getPointsRequired());
        return dto;
    }
}
