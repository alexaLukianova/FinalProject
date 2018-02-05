package ua.nure.lukianova.SummaryTask4.web;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ua.nure.lukianova.SummaryTask4.web.validator.AnswerValidatorTest;
import ua.nure.lukianova.SummaryTask4.web.validator.QuestionValidatorTest;
import ua.nure.lukianova.SummaryTask4.web.validator.TestValidatorTest;
import ua.nure.lukianova.SummaryTask4.web.validator.UserValidatorTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AnswerValidatorTest.class,
        QuestionValidatorTest.class,
        TestValidatorTest.class,
        UserValidatorTest.class
})
public class ValidatorTestSuite {
}
