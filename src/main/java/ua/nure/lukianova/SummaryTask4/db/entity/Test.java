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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (complexityId != test.complexityId) return false;
        if (duration != test.duration) return false;
        if (name != null ? !name.equals(test.name) : test.name != null) return false;
        return subject != null ? subject.equals(test.subject) : test.subject == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + complexityId;
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        return result;
    }
}
