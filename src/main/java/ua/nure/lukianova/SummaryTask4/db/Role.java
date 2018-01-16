package ua.nure.lukianova.SummaryTask4.db;

import ua.nure.lukianova.SummaryTask4.db.entity.User;

public enum Role {
    ADMIN,
    STUDENT;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }
}
