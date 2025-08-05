package org.skypro.questions_exam.model.service;

import org.skypro.questions_exam.model.Question;
import org.skypro.questions_exam.model.exception.NotEnoughQuestionsException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAllQuestion().size()) {
            throw new NotEnoughQuestionsException("Запрошено " + amount + " вопросов, но в наличии только " + questionService.getAllQuestion().size() + ".");
        }

        Set<Question> examQuestions = new HashSet<>();
        while (examQuestions.size() < amount) {
            examQuestions.add(questionService.getRandomQuestion());
        }

        return examQuestions;
    }
}
