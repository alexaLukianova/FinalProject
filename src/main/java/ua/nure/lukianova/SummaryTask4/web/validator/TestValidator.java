package ua.nure.lukianova.SummaryTask4.web.validator;

import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.service.TestServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TestValidator implements Validator {
    private Map<String, String> errors;
    private TestService testService;

    public TestValidator() {
        testService = new TestServiceImpl();
    }

    private static final String REGEX_NON_ALPHANUMERIC = "\\W";
    private static final String REGEX_NON_ALPHABETIC = "[^\\p{L}|\\s|-]";
    private static final String REGEX_WHITESPACE = "\\s";

    @Override
    public Map<String, String> validate(Object o) {
        errors = new HashMap<>();
        Test test = (Test) o;


        return errors;
    }

    private void nameValidate(Test test) {
        String name = test.getName();
        if (Objects.isNull(name) || name.trim().isEmpty()) {
            errors.put(FieldKeys.TEST_NAME, "error.test.required");
        } else {
            if (name.length() < 3){
                errors.put(FieldKeys.TEST_NAME, "error.test.short");
            }
            if (name.length() > 256){
                errors.put(FieldKeys.TEST_NAME, "error.test.long");
            }
        }
    }

    private void subjectValidate(Test test) {
        String subject = test.getSubject();
        if (Objects.isNull(subject) || subject.trim().isEmpty()) {
            errors.put(FieldKeys.TEST_NAME, "error.subject.required");
        } else {
            if (subject.length() < 3){
                errors.put(FieldKeys.TEST_NAME, "error.subject.short");
            }
            if (subject.length() > 256){
                errors.put(FieldKeys.TEST_NAME, "error.subject.long");
            }
        }
    }

    private void timeValidate(Test test) {
        long time = test.getDuration();
        if (Objects.isNull(time)) {
            errors.put(FieldKeys.TEST_NAME, "error.time.required");
        } else {
            if (time <= 0){
                errors.put(FieldKeys.TEST_NAME, "error.subject.short");
            }
//            if (subject.length() > 256){
//                errors.put(FieldKeys.TEST_NAME, "error.subject.long");
//            }
        }
    }
}
