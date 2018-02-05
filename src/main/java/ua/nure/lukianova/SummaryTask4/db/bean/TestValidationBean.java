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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestValidationBean that = (TestValidationBean) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (complexityId != null ? !complexityId.equals(that.complexityId) : that.complexityId != null) return false;
        return duration != null ? duration.equals(that.duration) : that.duration == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (complexityId != null ? complexityId.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }
}
