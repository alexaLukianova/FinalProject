package ua.nure.lukianova.SummaryTask4.db.entity;

public class Answer extends Entity {

    private static final long serialVersionUID = 5781817631105380846L;

    private String text;
    private boolean correct;
    private Long questionId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "Answer [text=" + text + ", correct=" + correct + ", getId()=" + getId() + "]";
    }

}
