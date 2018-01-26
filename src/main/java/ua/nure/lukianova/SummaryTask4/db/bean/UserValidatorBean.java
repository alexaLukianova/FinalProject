package ua.nure.lukianova.SummaryTask4.db.bean;

import ua.nure.lukianova.SummaryTask4.db.entity.User;

public class UserValidatorBean extends User {

    private String reenterPassword;

    public String getReenterPassword() {
        return reenterPassword;
    }

    public void setReenterPassword(String reenterPassword) {
        this.reenterPassword = reenterPassword;
    }

}
