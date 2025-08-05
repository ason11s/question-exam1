package org.skypro.questions_exam.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.questions_exam.model.Question;


import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    private Random randomMock;
    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService.clearQuestions();
    }

    @Test
    void add_shouldAddUniqueQuestion(){
        String questionText = "Что такое класс?";
        String answerText = "Проект создания объектов";
        Question newQuestion = javaQuestionService.add(questionText, answerText);
        assertNotNull(newQuestion);
        Collection<Question> allQuestions = javaQuestionService.getAllQuestion();
        assertTrue(allQuestions.contains(newQuestion));
    }
    @Test
    void add_shouldThrowExceptionForDuplicateQuestion(){
        String questionText = "Что такое класс?";
        String answerText = "Проект создания объектов";
        javaQuestionService.add(questionText, answerText);
        assertThrows(IllegalArgumentException.class, () -> {
            javaQuestionService.add(questionText, answerText);
        });
    }
    @Test
    void getRandomQuestion_shouldReturnAnyOfAddedQuestions(){
        Question q1 = javaQuestionService.add("Q1", "A1");
        Question q2 = javaQuestionService.add("Q2", "A2");
        Question q3 = javaQuestionService.add("Q3", "A3");

        when(randomMock.nextInt(3)).thenReturn(1);

        Question randomQuestion = javaQuestionService.getRandomQuestion();

        List<Question> addedQuestions = List.of(q1, q2, q3);
        assertTrue(addedQuestions.contains(randomQuestion));
    }


}