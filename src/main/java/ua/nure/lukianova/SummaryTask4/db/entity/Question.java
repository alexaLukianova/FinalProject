package ua.nure.lukianova.SummaryTask4.db.entity;

public class Question extends Entity {

    private static final long serialVersionUID = 3435578259315105194L;

    private String text;
    private Long testId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return text.equals(question.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
