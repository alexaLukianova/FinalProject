package ua.nure.lukianova.entity;

import java.util.List;

public class Test {

    private long id;
    private String name;
    private String subject;
    private String complexity;
    private List<Question> questions;
    private long time;

    public Test(long id, String name, String subject, String complexity) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.complexity = complexity;
    }

    public Test(String name, String subject, String complexity) {
        this.name = name;
        this.subject = subject;
        this.complexity = complexity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
