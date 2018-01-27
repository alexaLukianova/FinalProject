package ua.nure.lukianova.SummaryTask4.web.validator;

import ua.nure.lukianova.SummaryTask4.db.bean.UserValidatorBean;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.service.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;


public class AddUserValidator implements Validator {

    private static final int PASSWORD_LOWER_BOUND = 6;
    private static final int PASSWORD_UPPER_BOUND = 12;
    private static final int TEXT_UPPER_BOUND = 45;
    private static final int TEXT_LOWER_BOUND = 3;
    private Map<String, String> errors;
    private UserService userService;

    private static final String REGEX_NON_ALPHANUMERIC = "\\W";
    private static final String REGEX_NON_ALPHABETIC = "[^\\p{L}|\\s|-]";
    private static final String REGEX_WHITESPACE = "\\s";


    public AddUserValidator() {

        userService = new UserServiceImpl();

    }

    @Override
    public Map<String, String> validate(Object object) {
        errors = new HashMap<>();
        UserValidatorBean user = (UserValidatorBean) object;
        usernameValidate(user);
        firstNameValidate(user);
        lastNameValidate(user);
        passwordValidate(user);
        reenterPasswordValidate(user);

        return errors;
    }

    private void usernameValidate(UserValidatorBean user) {
        String username = user.getUsername();
        if (Objects.isNull(username) || username.trim().isEmpty()) {
            errors.put(FieldKeys.USER_USERNAME, "error.username.required");
        } else {
            if (Objects.nonNull(userService.findByLogin(username))) {
                errors.put(FieldKeys.USER_USERNAME, "error.username.occupied");
            } else {
                if (username.length() < TEXT_LOWER_BOUND) {
                    errors.put(FieldKeys.USER_USERNAME, "error.username.short");
                } else {
                    if (username.length() > TEXT_UPPER_BOUND) {
                        errors.put(FieldKeys.USER_USERNAME, "error.username.long");
                    } else {
                        Pattern pattern = Pattern.compile(REGEX_NON_ALPHANUMERIC, UNICODE_CHARACTER_CLASS);
                        Matcher matcher = pattern.matcher(username);
                        if (matcher.find()) {
                            errors.put(FieldKeys.USER_USERNAME, "error.username.non_alphanumeric");
                        }
                    }

                }
            }
        }
    }

    private void firstNameValidate(UserValidatorBean user) {
        String firstName = user.getFirstName();
        if (Objects.isNull(firstName) || firstName.trim().isEmpty()) {
            errors.put(FieldKeys.USER_FIRST_NAME, "error.first_name.required");
        } else {
            if (firstName.length() < TEXT_LOWER_BOUND) {
                errors.put(FieldKeys.USER_FIRST_NAME, "error.first_name.short");
            } else {
                if (firstName.length() > TEXT_UPPER_BOUND) {
                    errors.put(FieldKeys.USER_FIRST_NAME, "error.first_name.long");
                } else {
                    Pattern pattern = Pattern.compile(REGEX_NON_ALPHABETIC, UNICODE_CHARACTER_CLASS);
                    Matcher matcher = pattern.matcher(firstName);
                    if (matcher.find()) {
                        errors.put(FieldKeys.USER_FIRST_NAME, "error.first_name.non_alphabetic");
                    }
                }

            }
        }
    }

    private void lastNameValidate(UserValidatorBean user) {
        String lastName = user.getLastName();
        if (Objects.isNull(lastName) || lastName.trim().isEmpty()) {
            errors.put(FieldKeys.USER_LAST_NAME, "error.last_name.required");
        } else {
            if (lastName.length() < TEXT_LOWER_BOUND) {
                errors.put(FieldKeys.USER_LAST_NAME, "error.last_name.short");
            } else {
                if (lastName.length() > TEXT_UPPER_BOUND) {
                    errors.put(FieldKeys.USER_LAST_NAME, "error.last_name.long");
                } else {
                    Pattern pattern = Pattern.compile(REGEX_NON_ALPHABETIC, UNICODE_CHARACTER_CLASS);
                    Matcher matcher = pattern.matcher(lastName);
                    if (matcher.find()) {
                        errors.put(FieldKeys.USER_LAST_NAME, "error.last_name.non_alphabetic");
                    }
                }

            }
        }
    }

    private void passwordValidate(UserValidatorBean user) {
        String password = user.getPassword();
        if (Objects.isNull(password) || password.trim().isEmpty()) {
            errors.put(FieldKeys.USER_PASSWORD, "error.password.required");
        } else {
            if (password.length() < PASSWORD_LOWER_BOUND || password.length() > PASSWORD_UPPER_BOUND) {
                errors.put(FieldKeys.USER_PASSWORD, "error.password.invalid_length");
            } else {
                Pattern pattern = Pattern.compile(REGEX_WHITESPACE, UNICODE_CHARACTER_CLASS);
                Matcher matcher = pattern.matcher(password);
                if (matcher.find()) {
                    errors.put(FieldKeys.USER_PASSWORD, "error.password.has_whitespace");
                }
            }
        }
    }

    private void reenterPasswordValidate(UserValidatorBean user) {
        String reenterPassword = user.getReenterPassword();
        if (Objects.isNull(reenterPassword) || reenterPassword.trim().isEmpty()) {
            errors.put(FieldKeys.USER_REENTER_PASSWORD, "error.reenterPassword.required");
        } else {
            if (!reenterPassword.equals(user.getPassword())) {
                errors.put(FieldKeys.USER_REENTER_PASSWORD, "error.reenter.non_equal");
            }
        }
    }
}

