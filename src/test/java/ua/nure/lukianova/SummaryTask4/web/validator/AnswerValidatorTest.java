package ua.nure.lukianova.SummaryTask4.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AnswerValidatorTest {

    private static final int UNDER_LIMIT_TEXT_BOUND = 256;
    private Validator validator;
    private List<Answer> answers;
    private Map<String, String> errors;
    private static final String REQUIRED = "error.answer.required";
    private static final String LONG = "error.answer.long";
    private static final String DUPLICATES = "error.answer.dublicates";
    private static final String CORRECT = "error.correct.required";

    @Before
    public void setUp() throws Exception {
        validator = new AnswersValidator();
    }

    @After
    public void tearDown() throws Exception {
        errors.clear();
    }

    @Test
    public void validate_EmptyAnswers_ErrorRequired() throws Exception {
        createEmptyAnswers();

        errors = validator.validate(answers);
        assertTrue(errors.containsValue(REQUIRED));
    }

    @Test
    public void validate_NoCorrectAnswers_ErrorCorrectRequired() throws Exception {
        createEmptyAnswers();

        errors = validator.validate(answers);
        assertTrue(errors.containsValue(CORRECT));
    }

    @Test
    public void validate_DuplicatesAnswers_ErrorDuplicates() throws Exception {
        createDuplicateAnswers();

        errors = validator.validate(answers);
        assertTrue(errors.containsValue(DUPLICATES));
    }

    @Test
    public void validate_LongAnswers_ErrorLong() throws Exception {
        createLongAnswers();

        errors = validator.validate(answers);
        assertTrue(errors.containsValue(LONG));
    }

    @Test
    public void validate_ValidAnswers_NoError() throws Exception {
        createValidAnswers();

        errors = validator.validate(answers);
        assertTrue(errors.isEmpty());
    }

    private void createEmptyAnswers() {
        Answer answer1 = new Answer();
        answer1.setId(1l);
        answer1.setCorrect(false);
        Answer answer2 = new Answer();
        answer2.setId(2l);
        answer2.setCorrect(false);

        answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
    }

    private void createDuplicateAnswers() {
        Answer answer1 = new Answer();
        answer1.setId(1l);
        answer1.setText("answer");
        answer1.setCorrect(false);

        Answer answer2 = new Answer();
        answer2.setText("answer");
        answer2.setId(2l);
        answer2.setCorrect(true);

        answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
    }

    private void createLongAnswers() {
        Answer answer1 = new Answer();
        answer1.setId(1l);
        answer1.setText(StringUtils.rightPad("answer", UNDER_LIMIT_TEXT_BOUND, '*'));
        answer1.setCorrect(false);

        Answer answer2 = new Answer();
        answer2.setText("answer");
        answer2.setId(2l);
        answer2.setCorrect(true);

        answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
    }

    private void createValidAnswers() {
        Answer answer1 = new Answer();
        answer1.setId(1l);
        answer1.setText("answer1");
        answer1.setCorrect(false);

        Answer answer2 = new Answer();
        answer2.setText("answer2");
        answer2.setId(2l);
        answer2.setCorrect(true);

        answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
    }
}
