package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.LockUserCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LockUserCommandTest {

    private static final String LOGIN = "login";
    private Command command;
    private static final String EXPECTED_PATH = Path.COMMAND_LIST_USERS;

    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Captor
    ArgumentCaptor<List<User>> userCaptor;


    @Before
    public void setUp() throws Exception {
        command = new LockUserCommand(userService);
    }

    @Test(expected = AppException.class)
    public void execute_WithNoParameters_AppException() throws Exception {
        command.execute(request, response);
    }

    @Test
    public void execute_InverseLockState_True() throws Exception {
        given(request.getParameter(eq(Parameter.LOGIN))).willReturn(LOGIN);

        command.execute(request, response);

        verify(userService).inverseLockState(LOGIN);
    }

    @Test
    public void execute_ReturnValidPath_True() throws Exception {
        given(request.getParameter(eq(Parameter.LOGIN))).willReturn(LOGIN);

        String actualPath = command.execute(request, response);

        assertEquals(EXPECTED_PATH, actualPath);
    }
}
