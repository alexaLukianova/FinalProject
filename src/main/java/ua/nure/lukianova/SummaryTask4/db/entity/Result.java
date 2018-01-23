package ua.nure.lukianova.SummaryTask4.db.entity;

public class Result extends Entity {

    private static final long serialVersionUID = -994713893855006653L;

    private long testId;
    private int result;
    private long startTime;
    private long userId;

    public long getTestId() {
        return testId;
    }

    public void setTestId(long testId) {
        this.testId = testId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Result [score=" + result + ", testId()=" + testId + ", getUserId()=" + getId() + "]";
    }

}
