package ua.nure.lukianova.SummaryTask4.web.validator;

import ua.nure.lukianova.SummaryTask4.db.entity.Question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddQuestionValidator implements Validator {

    private Map<String, String> errors;
    private static final int UPPER_TEXT_BOUND = 255;

    @Override
    public Map<String, String> validate(Object object) {
        errors = new HashMap<>();
        Question question = (Question) object;
        textValidate(question);
        return errors;
    }

    private void textValidate(Question question) {
        String text = question.getText();
        if (Objects.isNull(text) || text.trim().isEmpty()) {
            errors.put(FieldKeys.QUESTION_TEXT, "error.question.required");
        } else {
            if (text.length() > UPPER_TEXT_BOUND) {
                errors.put(FieldKeys.QUESTION_TEXT, "error.question.long");
            }
        }
    }
}
