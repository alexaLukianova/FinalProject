package ua.nure.lukianova.SummaryTask4.web.validator;

import org.apache.commons.lang3.StringUtils;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.web.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AnswersValidator implements Validator {

    private Map<String, String> errors;
    private static final int UPPER_TEXT_BOUND = 255;

    @Override
    public Map<String, String> validate(Object object) {
        errors = new HashMap<>();
        List<Answer> answers = (ArrayList<Answer>) object;
        if (textValidate(answers)) {
            textDuplicatesValidate(answers);
        }
        correctAnswerValidate(answers);
        return errors;
    }

    private boolean textValidate(List<Answer> answers) {
        for (Answer answer : answers) {
            String text = answer.getText();
            if (StringUtils.isEmpty(text)) {
                errors.put(Parameter.ANSWER, "error.answer.required");
                return false;
            } else {
                if (text.length() > UPPER_TEXT_BOUND) {
                    errors.put(Parameter.ANSWER, "error.answer.long");
                    return false;
                }
            }
        }
        return true;
    }

    private void textDuplicatesValidate(List<Answer> answers) {
        if (new HashSet<>(answers).size() != answers.size()) {
            errors.put(Parameter.ANSWER, "error.answer.dublicates");
        }
    }

    private void correctAnswerValidate(List<Answer> answers) {
        if (answers.stream().noneMatch(Answer::isCorrect)) {
            errors.put(Parameter.ANSWER_CORRECT, "error.correct.required");
        }
    }

}
