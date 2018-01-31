package ua.nure.lukianova.SummaryTask4.web.validator;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;

import java.util.*;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.ANSWER;
import static ua.nure.lukianova.SummaryTask4.web.Parameter.ANSWER_CORRECT;

public class AddAnswersValidator implements Validator {

    private Map<String, String> errors;
    private static final int UPPER_TEXT_BOUND = 255;

    @Override
    public Map<String, String> validate(Object object) {
        errors = new HashMap<>();
        List<Answer> answers = (ArrayList<Answer>) object;
        answers.stream().forEach(answer -> textValidate(answer));
        correctAnswerValidate(answers);
        return errors;
    }

    private void textValidate(Answer answer) {
        String text = answer.getText();
        if (Objects.isNull(text) || text.trim().isEmpty()) {
            errors.put(ANSWER, "error.answer.required");
        } else {
            if (text.length() > UPPER_TEXT_BOUND) {
                errors.put(ANSWER, "error.answer.long");
            }
        }
    }

    private void correctAnswerValidate(List<Answer> answers) {
        if (answers.stream().noneMatch(Answer::isCorrect)) {
            errors.put(ANSWER_CORRECT, "error.correct.required");
        }
    }
}
