package org.skypro.questions_exam.model.service;


import org.skypro.questions_exam.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final List<Question> questions = new ArrayList<>();
    private final Random random = new Random();

    public JavaQuestionService() {
        add("Что такое JVM?", "Java Virtual Machine - это виртуальная машина, которая выполняет Java-байткод.");
        add("Чем абстрактный класс отличается от интерфейса?", "Абстрактный класс может иметь реализованные методы и поля, интерфейс - только абстрактные методы (до Java 8) и константы.");

    }

    @Override
    public Question add(String questionText, String answerText) {
        Question newQuestion = new Question(questionText, answerText);
        if (questions.contains(newQuestion)) {
            throw new IllegalArgumentException("Вопрос '" + questionText + "' уже существует.");
        }
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Вопрос не может быть null.");
        }
        if (questions.contains(question)) {
            throw new IllegalArgumentException("Вопрос '" + question.getQuestion() + "' уже существует.");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Вопрос не может быть null.");
        }
        boolean removed = questions.remove(question);
        if (!removed) {
            throw new IllegalArgumentException("Вопрос '" + question.getQuestion() + "' для удаления не найден.");
        }
        return question;
    }

    @Override
    public Collection<Question> getAllQuestion() {
        return Collections.unmodifiableList(questions);
    }

    @Override
    public Question getRandomQuestion(){
        if (questions.isEmpty()) {
            throw new RuntimeException("Список вопросов пуст, невозможно получить случайный вопрос.");
        }
        int randomIndex = random.nextInt(questions.size());
        return questions.get(randomIndex);
    }
    public void clearQuestions() {
        questions.clear();
    }
}
