package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.ShowResultCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShowResultCommandTest {

    private static final int MARK = 100;
    private Command command;
    private static final String EXPECTED_PATH = Path.PAGE_MESSAGE;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        command = new ShowResultCommand();

        given(request.getSession()).willReturn(session);
        given(session.getAttribute(eq(Parameter.MARK))).willReturn(MARK);
    }

    @Test
    public void execute_SetAttributesIfExistsMark_True() throws Exception {
        command.execute(request, response);

        verify(request).setAttribute(Parameter.MARK, MARK);
        verify(session).removeAttribute(Parameter.MARK);
    }

    @Test
    public void execute_IfNoMark_Path() throws Exception {
        given(session.getAttribute(eq(Parameter.MARK))).willReturn(null);

        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_PATH));
    }
}
