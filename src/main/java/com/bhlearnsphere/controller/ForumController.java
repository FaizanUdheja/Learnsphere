package com.bhlearnsphere.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/forum")
@CrossOrigin(origins = "http://localhost:3000")
public class ForumController {

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody Map<String, String> post) {
        // Mock implementation - in real app, you'd have a ForumService
        return ResponseEntity.ok(Map.of(
            "message", "Post created successfully",
            "postId", 1L,
            "title", post.get("title"),
            "content", post.get("content")
        ));
    }

    @PostMapping("/reply")
    public ResponseEntity<?> createReply(@RequestBody Map<String, String> reply) {
        // Mock implementation
        return ResponseEntity.ok(Map.of(
            "message", "Reply posted successfully",
            "replyId", 1L,
            "content", reply.get("content")
        ));
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts() {
        // Mock implementation
        return ResponseEntity.ok(Map.of(
            "posts", new Object[]{
                Map.of("id", 1, "title", "How to learn React?", "author", "John Doe"),
                Map.of("id", 2, "title", "Best practices for Spring Boot", "author", "Jane Smith")
            }
        ));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable Long postId) {
        // Mock implementation
        return ResponseEntity.ok(Map.of(
            "id", postId,
            "title", "Sample Post",
            "content", "This is a sample post content",
            "author", "John Doe",
            "replies", new Object[]{}
        ));
    }
}
