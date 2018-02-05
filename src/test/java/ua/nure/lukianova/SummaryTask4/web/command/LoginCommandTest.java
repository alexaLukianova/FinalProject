package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.LoginCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {

    private Command command;
    private static final String EXPECTED_PATH = Path.COMMAND_SHOW_PROFILE;
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String WRONG_PASSWORD = "wrong_password";
    private User user;

    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Captor
    ArgumentCaptor<User> userCaptor;
    @Captor
    ArgumentCaptor<Role> roleCaptor;

    @Before
    public void setUp() throws Exception {
        command = new LoginCommand(userService);
        user = createUser();

        given(request.getParameter(eq(Parameter.USERNAME))).willReturn(LOGIN);
        given(request.getParameter(eq(Parameter.PASSWORD))).willReturn(PASSWORD);
        given(userService.findByLogin(anyString())).willReturn(user);
        given(request.getSession()).willReturn(session);


        doNothing().when(session).setAttribute(eq(Parameter.USER), userCaptor.capture());
        doNothing().when(session).setAttribute(eq(Parameter.USER_ROLE), roleCaptor.capture());
    }


    @Test(expected = AppException.class)
    public void execute_WithNoParameters_AppException() throws Exception {
        given(request.getParameter(eq(Parameter.USERNAME))).willReturn(null);
        given(request.getParameter(eq(Parameter.PASSWORD))).willReturn(null);

        command.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void execute_UserAbsentInDB_AppException() throws Exception {
        given(userService.findByLogin(anyString())).willReturn(null);

        command.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void execute_UserPasswordIsWrong_AppException() throws Exception {
        given(request.getParameter(eq(Parameter.PASSWORD))).willReturn(WRONG_PASSWORD);

        command.execute(request, response);
    }

    @Test(expected = AppException.class)
    public void execute_UserIsLocked_AppException() throws Exception {
        user.setLocked(true);

        command.execute(request, response);

        user.setLocked(false);
    }

    @Test
    public void execute_ReturnValidPath_True() throws Exception {
        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_PATH));
    }

    @Test
    public void execute_SetUserIntoSession_True() throws Exception {
        command.execute(request, response);

        assertThat(userCaptor.getValue(), equalTo(user));
    }

    @Test
    public void execute_SetUserRoleIntoSession_True() throws Exception {
        command.execute(request, response);

        assertThat(roleCaptor.getValue(), equalTo(Role.getRole(user)));
    }


    private User createUser() {
        User user = new User();
        user.setId(1l);
        user.setRoleId(1);
        user.setPassword(PASSWORD);
        user.setUsername(LOGIN);

        return user;
    }

}
