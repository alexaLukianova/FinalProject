package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.bean.TestValidationBean;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.SaveNewTestCommand;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SaveNewTestCommandTest {

    private Command command;
    private TestValidationBean testValidationBean;
    private Map<String, String> errors = new HashMap<>();

    private static final String EXAMPLE_TEST_VALIDATE_ERROR = "error.name.required";
    private static final String EXPECTED_PATH = Path.COMMAND_SHOW_EDIT_FORM;

    @Mock
    private TestService testService;
    @Mock
    private Validator testValidator;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Captor
    ArgumentCaptor<Map<String, String>> errorsArgumentCaptor;
    @Captor
    ArgumentCaptor<TestValidationBean> testValidationBeanArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        command = new SaveNewTestCommand(testValidator, testService);
        errors.clear();
        createTestValidationBean();

        given(request.getSession()).willReturn(session);
        given(request.getParameter(anyString())).willReturn("");
        given(request.getParameter(eq(Parameter.TEST_COMPLEXITY_ID))).willReturn("0");
        given(request.getParameter(eq(Parameter.TEST_DURATION))).willReturn("0");
        given(testValidator.validate(any(TestValidationBean.class))).willReturn(errors);

        doNothing().when(session).setAttribute(eq(Parameter.ERRORS), errorsArgumentCaptor.capture());
        doNothing().when(session).setAttribute(eq(Parameter.TEST), testValidationBeanArgumentCaptor.capture());
    }

    @Test
    public void execute_TestInfoIsNotValid_False() throws Exception {
        createErrors();

        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_PATH));
        assertThat(errorsArgumentCaptor.getValue(), equalTo(errors));
        assertThat(testValidationBeanArgumentCaptor.getValue(), equalTo(testValidationBean));
    }

    @Test
    public void execute_TestInfoIsValid_NewTest() throws Exception {
        command.execute(request, response);

        verify(testService).create(any(ua.nure.lukianova.SummaryTask4.db.entity.Test.class));
    }

    @Test
    public void execute_TestInfoIsValid_ReturnPath() throws Exception {

        given(testService.create(any(ua.nure.lukianova.SummaryTask4.db.entity.Test.class))).willReturn(1l);

        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_PATH + "&testId=1"));
    }


    private void createTestValidationBean() {
        testValidationBean = new TestValidationBean();
        testValidationBean.setName("");
        testValidationBean.setSubject("");
        testValidationBean.setComplexityId("0");
        testValidationBean.setDuration("0");
    }

    private void createErrors() {
        errors.put(Parameter.TEST_NAME, EXAMPLE_TEST_VALIDATE_ERROR);
    }

}
