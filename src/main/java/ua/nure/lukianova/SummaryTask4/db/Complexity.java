package ua.nure.lukianova.SummaryTask4.db;

import ua.nure.lukianova.SummaryTask4.db.entity.Test;

public enum Complexity {
    EASY,
    MEDIUM,
    HARD;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static Complexity getComplexity(Test test) {
        int complexityId = test.getComplexityId();
        return Complexity.values()[complexityId];
    }
}
