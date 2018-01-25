package ua.nure.lukianova.SummaryTask4.web.validator;

import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.service.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddUserValidator implements Validator {

    private Map<String, String> errors;
    private UserService userService;

    public AddUserValidator() {

        userService = new UserServiceImpl();
    }

    @Override
    public Map<String, String> validate(Object object) {
        User user = (User) object;
        errors = new HashMap<>();
        if (Objects.isNull(user.getUsername()) || user.getUsername().trim().isEmpty()) {
           errors.put(FieldKeys.USERNAME, "Username is required.");
        }
        return errors;
    }
}
