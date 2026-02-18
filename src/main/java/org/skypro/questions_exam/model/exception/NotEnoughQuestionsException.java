package org.skypro.questions_exam.model.exception;

public class NotEnoughQuestionsException extends RuntimeException{
    public NotEnoughQuestionsException(String message){
        super(message);
    }

}
