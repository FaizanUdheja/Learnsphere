package com.bhlearnsphere.service;

import com.bhlearnsphere.entity.*;
import com.bhlearnsphere.helper.Role;
import com.bhlearnsphere.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DataInitializationService implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;  

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Create badges
        Badge firstCourse = new Badge();
        firstCourse.setName("First Course");
        firstCourse.setDescription("Completed your first course");
        firstCourse.setIcon("fas fa-graduation-cap");
        firstCourse.setColor("green");
        firstCourse.setPointsRequired(100);
        badgeRepository.save(firstCourse);

        Badge quizMaster = new Badge();
        quizMaster.setName("Quiz Master");
        quizMaster.setDescription("Scored 100% on a quiz");
        quizMaster.setIcon("fas fa-trophy");
        quizMaster.setColor("gold");
        quizMaster.setPointsRequired(200);
        badgeRepository.save(quizMaster);

        Badge activeLearner = new Badge();
        activeLearner.setName("Active Learner");
        activeLearner.setDescription("Completed 5 courses");
        activeLearner.setIcon("fas fa-book");
        activeLearner.setColor("blue");
        activeLearner.setPointsRequired(500);
        badgeRepository.save(activeLearner);

        // Create users
        User admin = new User();
        admin.setName("Admin User");
        admin.setEmail("admin@learnsphere.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);
        admin.setPoints(1000);
        admin.setBio("System Administrator");
        admin.setLocation("New York, NY");
        admin.setWebsite("https://learnsphere.com");
        userRepository.save(admin);

        User instructor = new User();
        instructor.setName("John Instructor");
        instructor.setEmail("instructor@learnsphere.com");
        instructor.setPassword(passwordEncoder.encode("instructor123"));
        instructor.setRole(Role.INSTRUCTOR);
        instructor.setPoints(500);
        instructor.setBio("Experienced Software Developer");
        instructor.setLocation("San Francisco, CA");
        instructor.setWebsite("https://johninstructor.dev");
        instructor.setBadges(Arrays.asList(firstCourse, activeLearner));
        userRepository.save(instructor);

        User student = new User();
        student.setName("Jane Student");
        student.setEmail("student@learnsphere.com");
        student.setPassword(passwordEncoder.encode("student123"));
        student.setRole(Role.STUDENT);
        student.setPoints(150);
        student.setBio("Aspiring Developer");
        student.setLocation("Boston, MA");
        student.setBadges(Arrays.asList(firstCourse));
        userRepository.save(student);

        // Create courses
        Course reactCourse = new Course();
        reactCourse.setTitle("React Fundamentals");
        reactCourse.setDescription("Learn React from scratch with hands-on projects");
        reactCourse.setPrice(49.99);
        reactCourse.setCategory("Web Development");
        reactCourse.setInstructor(instructor);
        courseRepository.save(reactCourse);

        Course springCourse = new Course();
        springCourse.setTitle("Spring Boot Mastery");
        springCourse.setDescription("Master Spring Boot for enterprise applications");
        springCourse.setPrice(79.99);
        springCourse.setCategory("Backend Development");
        springCourse.setInstructor(instructor);
        courseRepository.save(springCourse);

        Course freeCourse = new Course();
        freeCourse.setTitle("JavaScript Basics");
        freeCourse.setDescription("Free introduction to JavaScript programming");
        freeCourse.setPrice(0.0);
        freeCourse.setCategory("Programming");
        freeCourse.setInstructor(instructor);
        courseRepository.save(freeCourse);

        // Create modules for React course
        com.bhlearnsphere.entity.Module module1 = new com.bhlearnsphere.entity.Module();
        module1.setTitle("Getting Started with React");
        module1.setCourse(reactCourse);
        moduleRepository.save(module1);

        com.bhlearnsphere.entity.Module module2 = new com.bhlearnsphere.entity.Module();
        module2.setTitle("Components and Props");
        module2.setCourse(reactCourse);
        moduleRepository.save(module2);

        // Create lessons
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("Introduction to React");
        lesson1.setContent("React is a JavaScript library for building user interfaces...");
        lesson1.setType("video");
        lesson1.setModule(module1);
        lessonRepository.save(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("Setting up Development Environment");
        lesson2.setContent("Install Node.js, create-react-app, and set up your first project...");
        lesson2.setType("text");
        lesson2.setModule(module1);
        lessonRepository.save(lesson2);

        Lesson lesson3 = new Lesson();
        lesson3.setTitle("Understanding Components");
        lesson3.setContent("Components are the building blocks of React applications...");
        lesson3.setType("video");
        lesson3.setModule(module2);
        lessonRepository.save(lesson3);

        // Create quiz for React course
        Quiz quiz = new Quiz();
        quiz.setTitle("React Fundamentals Quiz");
        quiz.setCourse(reactCourse);
        quizRepository.save(quiz);

        // Create questions
        Question question1 = new Question();
        question1.setText("What is React?");
        question1.setType("MCQ");
        question1.setOptions("A library for building user interfaces,A database,A programming language,A framework");
        question1.setCorrectAnswer("A library for building user interfaces");
        question1.setQuiz(quiz);
        questionRepository.save(question1);

        Question question2 = new Question();
        question2.setText("React uses a virtual DOM.");
        question2.setType("TF");
        question2.setOptions("True,False");
        question2.setCorrectAnswer("True");
        question2.setQuiz(quiz);
        questionRepository.save(question2);

        Question question3 = new Question();
        question3.setText("Which hook is used to manage state in functional components?");
        question3.setType("MCQ");
        question3.setOptions("useEffect,useState,useContext,useReducer");
        question3.setCorrectAnswer("useState");
        question3.setQuiz(quiz);
        questionRepository.save(question3);
    }
}
