package ua.nure.lukianova.SummaryTask4.db.entity;

public class Question extends Entity {

    private static final long serialVersionUID = 3435578259315105194L;

    private String question;
    private Long testId;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }


}
