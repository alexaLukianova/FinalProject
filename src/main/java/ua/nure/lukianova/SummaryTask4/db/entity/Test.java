package ua.nure.lukianova.SummaryTask4.db.entity;

public class Test extends Entity {

    private static final long serialVersionUID = -5753114648879271413L;
    private String name;
    private String subject;



    private int complexityId;
    private long duration;

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

    public int getComplexityId() {
        return complexityId;
    }

    public void setComplexityId(int complexityId) {
        this.complexityId = complexityId;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Test [name=" + name + ", subject=" + subject + ", complexity="
                + complexityId + ", duration=" + duration + ", getId()=" + getId() + "]";
    }
}
