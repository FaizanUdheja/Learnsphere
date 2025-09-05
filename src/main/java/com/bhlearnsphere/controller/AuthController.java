package com.bhlearnsphere.controller;

import com.bhlearnsphere.dto.*;
import com.bhlearnsphere.service.UserService;
import com.bhlearnsphere.util.JwtUtil;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            UserDto user = userService.register(request);
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());
            
            AuthResponse response = new AuthResponse(token, user, "Registration successful");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            UserDto user = userService.login(request.getEmail(), request.getPassword());
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());
            AuthResponse response = new AuthResponse(token, user, "Login successful");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Log the error for debugging
            System.err.println("Login failed: " + e.getMessage());
            // Use 401 for authentication error
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7); // Remove "Bearer " prefix
            if (jwtUtil.validateToken(jwt)) {
                String email = jwtUtil.getEmailFromToken(jwt);
                String role = jwtUtil.getRoleFromToken(jwt);
                return ResponseEntity.ok(Map.of("email", email, "role", role, "valid", true));
            } else {
                return ResponseEntity.badRequest().body(Map.of("valid", false, "error", "Invalid token"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("valid", false, "error", "Invalid token"));
        }
    }
}
