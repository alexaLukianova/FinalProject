package ua.nure.lukianova.SummaryTask4.db.bean;

import ua.nure.lukianova.SummaryTask4.db.entity.Entity;

public class TestResultBean extends Entity {

    private static final long serialVersionUID = -978119869981368579L;

    private String testName;
    private String testSubject;
    private int result;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestSubject() {
        return testSubject;
    }

    public void setTestSubject(String testSubject) {
        this.testSubject = testSubject;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "UserTestResult [testName=" + testName
                + ", testSubject=" + testSubject + ", result=" + result
                + "]";
    }
}