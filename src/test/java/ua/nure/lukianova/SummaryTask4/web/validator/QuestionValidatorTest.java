package ua.nure.lukianova.SummaryTask4.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;

import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class QuestionValidatorTest {

    private static final int UNDER_LIMIT_TEXT_BOUND = 256;
    private Validator validator;
    private Question question;
    private Map<String, String> errors;
    private static final String REQUIRED = "error.question.required";
    private static final String LONG = "error.question.long";

    @Before
    public void setUp() throws Exception {
        validator = new QuestionValidator();
    }

    @After
    public void tearDown() throws Exception{
        errors.clear();
    }

    @Test
    public void validate_EmptyQuestion_ErrorRequired() throws Exception {
        createEmptyQuestion();

        errors = validator.validate(question);
        assertTrue(errors.containsValue(REQUIRED));
    }

    @Test
    public void validate_LongQuestion_ErrorLong() throws Exception {
        createLongQuestion();

        errors = validator.validate(question);
        assertTrue(errors.containsValue(LONG));
    }

    @Test
    public void validate_ValidQuestion_NoError() throws Exception {
        createValidQuestion();

        errors = validator.validate(question);
        assertTrue(errors.isEmpty());
    }


    private void createEmptyQuestion() {
        question = new Question();
        question.setId(1l);
        question.setTestId(1l);
    }

    private void createLongQuestion() {
        question = new Question();
        question.setId(1l);
        question.setTestId(1l);
        question.setText(StringUtils.rightPad("question", UNDER_LIMIT_TEXT_BOUND, '*'));
    }

    private void createValidQuestion() {
        question = new Question();
        question.setId(1l);
        question.setTestId(1l);
        question.setText("question");
    }
}
