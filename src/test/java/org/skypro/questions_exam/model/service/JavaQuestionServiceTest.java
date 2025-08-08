package org.skypro.questions_exam.model.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.questions_exam.model.Question;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private final QuestionService out = new JavaQuestionService();

    private static final Question QUESTION_1 = new Question("Q1", "A1");
    private static final Question QUESTION_2 = new Question("Q2", "A2");
    private static final Question QUESTION_3 = new Question("Q3", "A3");

    @BeforeEach
    public void beforeEach() {
        out.add(QUESTION_1);
        out.add(QUESTION_2);
        out.add(QUESTION_3);
    }

    @Test
    void add_shouldAddUniqueQuestion(){
        String questionText = "Что такое класс?";
        String answerText = "Проект создания объектов";
        Question newQuestion = out.add(questionText, answerText);

        assertNotNull(newQuestion);
        Collection<Question> allQuestions = out.getAll();
        assertTrue(allQuestions.contains(newQuestion));
        assertEquals(4, allQuestions.size());
    }

    @Test
    void add_shouldThrowExceptionForDuplicateQuestion(){
        String questionText = QUESTION_1.getQuestion();
        String answerText = QUESTION_1.getAnswer();

        assertThrows(IllegalArgumentException.class, () -> {
            out.add(questionText, answerText);
        });
        assertEquals(3, out.getAll().size());
    }

    @Test
    void getRandomQuestion_shouldReturnAnyOfAddedQuestions(){
        Question randomQuestion = out.getRandomQuestion();

        assertNotNull(randomQuestion);
        assertTrue(out.getAll().contains(randomQuestion));
    }

}

