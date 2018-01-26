package ua.nure.lukianova.SummaryTask4.web.validator;

import java.util.Map;

public interface Validator {

    Map<String, String> validate(Object o);

    interface FieldKeys {
        String USER_USERNAME = "username";
        String USER_FIRST_NAME = "firstName";
        String USER_LAST_NAME = "lastName";
        String USER_PASSWORD = "password";
        String USER_REENTER_PASSWORD = "reenterPassword";
    }
}
