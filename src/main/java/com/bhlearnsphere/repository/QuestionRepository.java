package com.bhlearnsphere.repository;

import com.bhlearnsphere.entity.Question;
import com.bhlearnsphere.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuiz(Quiz quiz);
    List<Question> findByQuizId(Long quizId);
    List<Question> findByType(String type);
}
