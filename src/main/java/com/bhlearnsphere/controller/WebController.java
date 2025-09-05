package com.bhlearnsphere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/courses-page")
    public String courses() {
        return "courses";
    }

    @GetMapping("/quizzes-page")
    public String quizzes() {
        return "quizzes";
    }
}
