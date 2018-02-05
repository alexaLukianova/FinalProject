package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.LogoutCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {

    private Command command;
    private static final String EXPECTED_PATH = Path.PAGE_LOGIN;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        command = new LogoutCommand();

        given(request.getSession(false)).willReturn(session);
    }

    @Test
    public void execute_ReturnValidPath_True() throws Exception {
        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_PATH));
    }

    @Test
    public void execute_InvalidateSession_True() throws Exception {
        command.execute(request, response);

        verify(session).invalidate();
    }

    @Test
    public void execute_InvalidateOfNullSession_False() throws Exception {
        given(request.getSession(false)).willReturn(null);

        command.execute(request, response);

        verify(session, never()).invalidate();
    }
}
