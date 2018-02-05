package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.bean.TestValidationBean;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.UpdateTestInfoCommand;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UpdateTestInfoCommandTest {

    private static final String TEST_ID = "1";
    private Command command;
    private Map<String, String> errors = new HashMap<>();
    private static final String EXPECTED_PATH = Path.COMMAND_SHOW_EDIT_FORM + "&testId=";

    @Mock
    private Validator testValidator;
    @Mock
    private TestService testService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        command = new UpdateTestInfoCommand(testService, testValidator);
        createErrors();

        given(request.getSession()).willReturn(session);
        given(request.getParameter(anyString())).willReturn("");
        given(request.getParameter(eq(Parameter.TEST_ID))).willReturn(TEST_ID);
        given(request.getParameter(eq(Parameter.TEST_COMPLEXITY_ID))).willReturn("1");
        given(request.getParameter(eq(Parameter.TEST_DURATION))).willReturn("1");
        given(testValidator.validate(any(TestValidationBean.class))).willReturn(errors);


    }

    @Test(expected = AppException.class)
    public void execute_NoTestId_AppException() throws Exception {
        given(request.getParameter(eq(Parameter.TEST_ID))).willReturn(null);

        command.execute(request, response);
    }

    @Test
    public void execute_NoErrors_Update() throws Exception {
        errors.clear();

        String actualPath = command.execute(request, response);

        verify(testService).update(any(ua.nure.lukianova.SummaryTask4.db.entity.Test.class));
        assertThat(actualPath, equalTo(EXPECTED_PATH + TEST_ID));
    }

    @Test
    public void execute_HasErrors_SetErrorsIntoSession() throws Exception {
        String actualPath = command.execute(request, response);

        verify(session).setAttribute(Parameter.ERRORS, errors);
        assertThat(actualPath, equalTo(EXPECTED_PATH + TEST_ID));
    }


    private void createErrors() {
        errors.put(Parameter.TEST_NAME, "error.name.required");
    }

}
