package org.skypro.questions_exam.model.service;

import org.skypro.questions_exam.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question,String answer);
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAllQuestion();
    Question getRandomQuestion();
}
