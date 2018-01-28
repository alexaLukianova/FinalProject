package ua.nure.lukianova.SummaryTask4.web.validator;

import ua.nure.lukianova.SummaryTask4.db.bean.TestValidationBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.UNICODE_CHARACTER_CLASS;

public class TestValidator implements Validator {

    private static final int UPPER_TEXT_BOUND = 255;
    private static final int MAX_TIME_IN_MINUTES = 1000;
    private Map<String, String> errors;

    private static final String REGEX_NON_NUMERIC = "\\D";

    @Override
    public Map<String, String> validate(Object object) {
        errors = new HashMap<>();
        TestValidationBean test = (TestValidationBean) object;
        nameValidate(test);
        subjectValidate(test);
        timeValidate(test);

        return errors;
    }

    private void nameValidate(TestValidationBean test) {
        String name = test.getName();
        if (Objects.isNull(name) || name.trim().isEmpty()) {
            errors.put(FieldKeys.TEST_NAME, "error.name.required");
        } else {
            if (name.length() > UPPER_TEXT_BOUND) {
                errors.put(FieldKeys.TEST_NAME, "error.name.long");
            }
        }
    }

    private void subjectValidate(TestValidationBean test) {
        String subject = test.getSubject();
        if (Objects.isNull(subject) || subject.trim().isEmpty()) {
            errors.put(FieldKeys.TEST_SUBJECT, "error.subject.required");
        } else {
            if (subject.length() > UPPER_TEXT_BOUND) {
                errors.put(FieldKeys.TEST_SUBJECT, "error.subject.long");
            }
        }
    }

    private void timeValidate(TestValidationBean test) {
        String time = test.getDuration();
        if (Objects.isNull(time)|| time.trim().isEmpty()) {
            errors.put(FieldKeys.TEST_DURATION, "error.duration.required");
        } else {
            Pattern pattern = Pattern.compile(REGEX_NON_NUMERIC, UNICODE_CHARACTER_CLASS);
            Matcher matcher = pattern.matcher(time);
            if (matcher.find()) {
                errors.put(FieldKeys.TEST_DURATION, "error.duration.non_numeric");
            } else {
                if (Long.valueOf(time) > MAX_TIME_IN_MINUTES) {
                    errors.put(FieldKeys.TEST_DURATION, "error.duration.long");
                }
            }

        }
    }
}
