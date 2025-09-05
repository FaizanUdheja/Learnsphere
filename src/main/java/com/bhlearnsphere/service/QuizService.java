package com.bhlearnsphere.service;

import com.bhlearnsphere.dto.*;
import com.bhlearnsphere.entity.*;
import com.bhlearnsphere.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CourseRepository courseRepository;

    public QuizDto createQuiz(Long courseId, QuizDto quizDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setCourse(course);

        Quiz savedQuiz = quizRepository.save(quiz);
        return convertToDto(savedQuiz);
    }

    public QuizDto updateQuiz(Long quizId, QuizDto quizDto) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        quiz.setTitle(quizDto.getTitle());

        Quiz savedQuiz = quizRepository.save(quiz);
        return convertToDto(savedQuiz);
    }

    public void deleteQuiz(Long quizId) {
        if (!quizRepository.existsById(quizId)) {
            throw new RuntimeException("Quiz not found");
        }
        quizRepository.deleteById(quizId);
    }

    public QuizDto getQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        return convertToDto(quiz);
    }

    public List<QuizDto> getQuizzesByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return quizRepository.findByCourse(course).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<QuizDto> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public QuestionDto createQuestion(Long quizId, QuestionDto questionDto) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Question question = new Question();
        question.setText(questionDto.getText());
        question.setType(questionDto.getType());
        question.setOptions(String.join(",", questionDto.getOptions()));
        question.setCorrectAnswer(questionDto.getCorrectAnswer());
        question.setQuiz(quiz);

        Question savedQuestion = questionRepository.save(question);
        return convertQuestionToDto(savedQuestion);
    }

    public List<QuestionDto> getQuestionsByQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        return questionRepository.findByQuiz(quiz).stream()
                .map(this::convertQuestionToDto)
                .collect(Collectors.toList());
    }

    public QuizResultDto submitQuiz(QuizSubmissionDto submission) {
        Quiz quiz = quizRepository.findById(submission.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        List<Question> questions = questionRepository.findByQuiz(quiz);
        int totalQuestions = questions.size();
        int correctAnswers = 0;

        for (Question question : questions) {
            String userAnswer = submission.getAnswers().get(question.getId());
            if (userAnswer != null && userAnswer.equals(question.getCorrectAnswer())) {
                correctAnswers++;
            }
        }

        int incorrectAnswers = totalQuestions - correctAnswers;
        double percentage = totalQuestions > 0 ? (double) correctAnswers / totalQuestions * 100 : 0;
        boolean passed = percentage >= 70; // 70% passing threshold

        return new QuizResultDto(
                submission.getQuizId(),
                submission.getUserId(),
                totalQuestions,
                correctAnswers,
                incorrectAnswers,
                percentage,
                passed,
                submission.getTimeSpent()
        );
    }

    private QuizDto convertToDto(Quiz quiz) {
        QuizDto dto = new QuizDto();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());
        dto.setCourseId(quiz.getCourse().getId());
        return dto;
    }

    private QuestionDto convertQuestionToDto(Question question) {
        QuestionDto dto = new QuestionDto();
        dto.setId(question.getId());
        dto.setText(question.getText());
        dto.setType(question.getType());
        dto.setOptions(List.of(question.getOptions().split(",")));
        dto.setCorrectAnswer(question.getCorrectAnswer());
        dto.setQuizId(question.getQuiz().getId());
        return dto;
    }
}
