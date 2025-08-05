package org.skypro.questions_exam.model.controller;
import org.skypro.questions_exam.model.Question;
import org.skypro.questions_exam.model.service.JavaQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService=javaQuestionService;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam("question") String questionText, @RequestParam("answer") String answerText){
        return javaQuestionService.add(questionText,answerText);
    }
    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam("question")String questionText, @RequestParam("answer") String answerText){
        Question questionToRemove = new Question(questionText,answerText);
        return javaQuestionService.remove(questionToRemove);
    }
    @GetMapping
    public Collection<Question> getAllQuestion(){
        return javaQuestionService.getAllQuestion();
    }

}
