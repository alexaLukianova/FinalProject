package ua.nure.lukianova.SummaryTask4.db.bean;

import ua.nure.lukianova.SummaryTask4.db.entity.Entity;

public class TestValidationBean extends Entity {

    private static final long serialVersionUID = -3734111574456362503L;

    private String name;
    private String subject;
    private String complexityId;
    private String duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComplexityId() {
        return complexityId;
    }

    public void setComplexityId(String complexityId) {
        this.complexityId = complexityId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}
