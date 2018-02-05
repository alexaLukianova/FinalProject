package ua.nure.lukianova.SummaryTask4.web.command;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.ShowSaveQuestionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShowSaveQuestionCommandTest {

    private static final String QUESTION_ID = "1";
    private Command command;
    private Map<String, String> errors = new HashMap<>();
    private static final String EXPECTED_PATH = Path.PAGE_ADD_NEW_QUESTION;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        command = new ShowSaveQuestionCommand();

        given(request.getSession()).willReturn(session);
        given(session.getAttribute(eq(Parameter.ERRORS))).willReturn(errors);
        given(session.getAttribute(eq(Parameter.QUESTION))).willReturn(QUESTION_ID);
    }

    @Test
    public void execute_SetAttributeIfExistErrors_True() throws Exception {
        command.execute(request, response);

        verify(request).setAttribute(Parameter.ERRORS, errors);
        verify(session).removeAttribute(Parameter.ERRORS);
    }

    @Test
    public void execute_SetAttributeIfExistQuestion_True() throws Exception {
        command.execute(request, response);

        verify(request).setAttribute(Parameter.QUESTION, QUESTION_ID);
        verify(session).removeAttribute(Parameter.QUESTION);
    }

    @Test
    public void execute_SetAttributeIfNotExistQuestionAndErrors_True() throws Exception {
        given(session.getAttribute(eq(Parameter.ERRORS))).willReturn(null);
        given(session.getAttribute(eq(Parameter.QUESTION))).willReturn(null);

        String actualPath = command.execute(request, response);

        verify(request, times(2)).setAttribute(anyString(), anyObject());
        assertThat(actualPath, equalTo(EXPECTED_PATH));
    }

}
