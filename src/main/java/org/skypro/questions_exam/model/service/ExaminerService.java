package org.skypro.questions_exam.model.service;

import org.skypro.questions_exam.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
