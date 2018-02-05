package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.bean.TestValidationBean;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.TEST_ID;

public class UpdateTestInfoCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTestInfoCommand.class);
    private static final long serialVersionUID = -2182252148445945757L;
    private Map<String, String> errors;
    private TestValidationBean testValidationBean;
    private long testId;
    private TestService testService;
    private Validator testValidator;

    public UpdateTestInfoCommand(TestService testService, Validator testValidator) {
        this.testService = testService;
        this.testValidator = testValidator;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        errors = new HashMap<>();

        if (StringUtils.isEmpty(request.getParameter(TEST_ID))) {
            throw new AppException("Invalid input");
        }

        testValidationBean = extractTestValidationBean(request);
        errors = testValidator.validate(testValidationBean);
        testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
        if (errors.isEmpty()) {
            testService.update(extractTest(testValidationBean));
        } else {
            LOGGER.trace("Found in errors: --> " + errors);
            setErrorsIntoSessionScope(request);
        }

        LOGGER.debug("Command finished");

        return getURL();
    }

    private void setErrorsIntoSessionScope(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(Parameter.ERRORS, new HashMap<>(errors));
        LOGGER.trace("Set the request attribute: errors --> " + errors);
    }

    private String getURL() {
        return new StringBuilder(Path.COMMAND_SHOW_EDIT_FORM)
                .append(DELIMITER)
                .append(Parameter.TEST_ID).append(EQUAL_SIGN).append(testId)
                .toString();
    }

    private TestValidationBean extractTestValidationBean(HttpServletRequest request) {
        TestValidationBean test = new TestValidationBean();
        test.setId(Long.valueOf(request.getParameter(Parameter.TEST_ID)));
        test.setName(request.getParameter(Parameter.TEST_NAME).trim());
        test.setSubject(request.getParameter(Parameter.TEST_SUBJECT).trim());
        test.setComplexityId(request.getParameter(Parameter.TEST_COMPLEXITY_ID).trim());
        test.setDuration(request.getParameter(Parameter.TEST_DURATION).trim());

        return test;
    }

    private static Test extractTest(TestValidationBean validTest) {
        Test test = new Test();
        test.setId(validTest.getId());
        test.setName(validTest.getName());
        test.setSubject(validTest.getSubject());
        test.setComplexityId(Integer.valueOf(validTest.getComplexityId()));
        test.setDuration(Long.valueOf(validTest.getDuration()));

        return test;
    }

}

