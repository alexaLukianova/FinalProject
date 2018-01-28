package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.bean.TestValidationBean;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.validator.TestValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UpdateTestCommand extends Command {

    private static final long serialVersionUID = -3902663307391027752L;

    private Validator validator;

    public UpdateTestCommand() {
        this.validator = new TestValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        TestValidationBean testValidationBean = extractTestValidationBean(request);
        Map<String, String> errors = validator.validate(testValidationBean);

        if (errors.isEmpty()) {

            getTestService().update(extractTest(testValidationBean));
        }
        else{
            request.setAttribute(Parameter.ERRORS, errors);
        }

        //setTestAttributes(request, testValidationBean);

        request.setAttribute("test", testValidationBean);

        request.setAttribute(Parameter.TEST_ID, request.getParameter(Parameter.TEST_ID));

        return Path.COMMAND_EDIT_TEST;
    }

    private void setTestAttributes(HttpServletRequest request, TestValidationBean testValidationBean) {
        request.setAttribute(Parameter.TEST_NAME, testValidationBean.getName());
        request.setAttribute(Parameter.TEST_SUBJECT, testValidationBean.getSubject());
        request.setAttribute(Parameter.TEST_COMPLEXITY_ID, testValidationBean.getComplexityId());
        request.setAttribute(Parameter.TEST_DURATION, testValidationBean.getDuration());
        request.setAttribute(Parameter.TEST_ID, request.getParameter(Parameter.TEST_ID));
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

    private Test extractTest(TestValidationBean validTest) {
        Test test = new Test();
        test.setId(validTest.getId());
        test.setName(validTest.getName());
        test.setSubject(validTest.getSubject());
        test.setComplexityId(Integer.valueOf(validTest.getComplexityId()));
        test.setDuration(Long.valueOf(validTest.getDuration()));

        return test;
    }


}


