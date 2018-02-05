package ua.nure.lukianova.SummaryTask4.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.bean.TestValidationBean;

import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TestValidatorTest {

    private static final int UNDER_LIMIT_TEXT_BOUND = 256;
    private static final String UNDER_MAX_TIME_IN_MINUTES = "2000";
    private Validator validator;
    private TestValidationBean test;
    private Map<String, String> errors;
    private static final String NAME_REQUIRED = "error.name.required";
    private static final String NAME_LONG = "error.name.long";
    private static final String SUBJECT_REQUIRED = "error.subject.required";
    private static final String SUBJECT_LONG = "error.subject.long";
    private static final String DURATION_REQUIRED = "error.duration.required";
    private static final String DURATION_NON_NUMERIC = "error.duration.non_numeric";
    private static final String DURATION_LONG = "error.duration.long";


    @Before
    public void setUp() throws Exception {
        validator = new TestValidator();
    }

    @After
    public void tearDown() throws Exception {
        errors.clear();
    }

    @Test
    public void validate_EmptyTest_ErrorRequired() throws Exception {
        createEmptyTest();

        errors = validator.validate(test);
        assertTrue(errors.containsValue(NAME_REQUIRED));
        assertTrue(errors.containsValue(SUBJECT_REQUIRED));
        assertTrue(errors.containsValue(DURATION_REQUIRED));
    }

    @Test
    public void validate_LongTest_ErrorLong() throws Exception {
        createLongTest();

        errors = validator.validate(test);
        assertTrue(errors.containsValue(NAME_LONG));
        assertTrue(errors.containsValue(SUBJECT_LONG));
        assertTrue(errors.containsValue(DURATION_LONG));
    }

    @Test
    public void validate_NonNumericDuration_ErrorNonNumeric() throws Exception {
        createNonNumericDurationTest();

        errors = validator.validate(test);
        assertTrue(errors.containsValue(DURATION_NON_NUMERIC));
    }

    @Test
    public void validate_ZeroDuration_ErrorRequired() throws Exception {
        createZeroDurationTest();

        errors = validator.validate(test);
        assertTrue(errors.containsValue(DURATION_REQUIRED));
    }

    @Test
    public void validate_ValidTest_NoErrors() throws Exception {
        createValidTest();

        errors = validator.validate(test);
        assertTrue(errors.isEmpty());
    }

    private void createEmptyTest() {
        test = new TestValidationBean();
        test.setId(1l);
    }

    private void createLongTest() {
        test = new TestValidationBean();
        test.setId(1l);
        test.setDuration(UNDER_MAX_TIME_IN_MINUTES);
        test.setName(StringUtils.rightPad("name", UNDER_LIMIT_TEXT_BOUND, '*'));
        test.setSubject(StringUtils.rightPad("subject", UNDER_LIMIT_TEXT_BOUND, '*'));
    }

    private void createNonNumericDurationTest() {
        test = new TestValidationBean();
        test.setId(1l);
        test.setDuration("one");
        test.setName("name");
        test.setSubject("subject");
    }

    private void createZeroDurationTest() {
        test = new TestValidationBean();
        test.setId(1l);
        test.setDuration("0");
        test.setName("name");
        test.setSubject("subject");
    }

    private void createValidTest() {
        test = new TestValidationBean();
        test.setId(1l);
        test.setDuration("10");
        test.setName("name");
        test.setSubject("subject");
    }
}
