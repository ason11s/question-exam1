package org.skypro.questions_exam.model.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.questions_exam.model.Question;
import org.skypro.questions_exam.model.exception.NotEnoughQuestionsException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionServiceMock;
    @InjectMocks
    private ExaminerServiceImpl out;

    @Test
    void getQuestions_whenEnoughQuestionsAreAvailable_shouldReturnCorrectAmountOfUniqueQuestions() {

        Question q1 = new Question("Q1", "A1");
        Question q2 = new Question("Q2", "A2");
        Question q3 = new Question("Q3", "A3");
        Question q4 = new Question("Q4", "A4");

        when(questionServiceMock.getAllQuestion()).thenReturn(List.of(q1, q2, q3, q4));
        when(questionServiceMock.getRandomQuestion()).thenReturn(q1, q2, q3, q4);
        Collection<Question> result = out.getQuestions(3);
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(List.of(q1, q2, q3)));

    }

    @Test
    void getQuestions_whenNotEnoughQuestionsAreAvailable_shouldThrowException() {
        when(questionServiceMock.getAllQuestion()).thenReturn(List.of(new Question("Q1", "A1"), new Question("Q2", "A2")));
        assertThrows(NotEnoughQuestionsException.class, () -> out.getQuestions(3));
    }
}