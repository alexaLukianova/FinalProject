package ua.nure.lukianova.SummaryTask4.db.entity;

public class Test extends Entity {

    private static final long serialVersionUID = -5753114648879271413L;
    private String name;
    private String subject;
    private String complexity;
    private long time;

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

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Test [name=" + name + ", subject=" + subject + ", complexity="
                + complexity + ", time=" + time + ", getId()=" + getId() + "]";
    }
}
