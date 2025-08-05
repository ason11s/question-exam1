package org.skypro.questions_exam.model.controller;


import org.skypro.questions_exam.model.Question;
import org.skypro.questions_exam.model.exception.NotEnoughQuestionsException;
import org.skypro.questions_exam.model.service.ExaminerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount") int amount){
        return examinerService.getQuestions(amount);
    }
    @ControllerAdvice
    public class ExamControllerAdvice {

        @ExceptionHandler(NotEnoughQuestionsException.class)
        public ResponseEntity<String> handleNotEnoughQuestions(NotEnoughQuestionsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
