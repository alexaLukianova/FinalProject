package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.bean.TestValidationBean;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.validator.TestValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.*;

public class SaveNewTestCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveNewTestCommand.class);
    private static final long serialVersionUID = 8894172038521714145L;
    private Validator testValidator;
    private TestService testService;
    private Map<String, String> errors;
    private TestValidationBean testValidationBean;
    private long testId;

    public SaveNewTestCommand(Validator testValidator, TestService testService) {
        this.testValidator = testValidator;
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        errors = new HashMap<>();
        testValidationBean = extractTestValidationBean(request);
        errors = testValidator.validate(testValidationBean);

        if (MapUtils.isEmpty(errors)) {
            Test test = extractTest(testValidationBean);
            testId = testService.create(test);
        } else {
            setErrorsIntoSessionScope(request);
        }

        return getURL(request);
    }


    private void setErrorsIntoSessionScope(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(ERRORS, new HashMap<>(errors));
        session.setAttribute(TEST, testValidationBean);

    }

    private String getURL(HttpServletRequest request) {
        StringBuilder url = new StringBuilder(Path.COMMAND_SHOW_EDIT_FORM);
        if (MapUtils.isEmpty(errors)) {
            url.append(DELIMITER).append(TEST_ID).append(EQUAL_SIGN).append(testId);
        }

        return url.toString();
    }

    private TestValidationBean extractTestValidationBean(HttpServletRequest request) {
        TestValidationBean test = new TestValidationBean();
        test.setName(request.getParameter(TEST_NAME).trim());
        test.setSubject(request.getParameter(TEST_SUBJECT).trim());
        test.setComplexityId(request.getParameter(TEST_COMPLEXITY_ID).trim());
        test.setDuration(request.getParameter(TEST_DURATION).trim());

        return test;
    }

    private static Test extractTest(TestValidationBean validTest) {
        Test test = new Test();
        test.setName(validTest.getName());
        test.setSubject(validTest.getSubject());
        test.setComplexityId(Integer.valueOf(validTest.getComplexityId()));
//        Optional.ofNullable(validTest.getDuration())
//                .map(Long::valueOf)
//                .ifPresent(test::setDuration);
        if (Objects.nonNull(validTest.getDuration())) {
            test.setDuration(Long.valueOf(validTest.getDuration()));
        }

        return test;
    }
}
