package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.bean.UserValidatorBean;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.ShowRegisterCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShowRegisterCommandTest {

    private Command command;
    private UserValidatorBean user;
    private Map<String, String> errors = new HashMap<>();
    private static final String EXPECTED_PATH = Path.PAGE_REGISTRATION;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        command = new ShowRegisterCommand();
        createUser();

        given(request.getSession()).willReturn(session);
        given(session.getAttribute(eq(Parameter.ERRORS))).willReturn(errors);
        given(session.getAttribute(eq(Parameter.USER_BEAN))).willReturn(user);
    }

    @Test
    public void execute_SetAttributesIfExistsErrors_True() throws Exception {
        command.execute(request, response);

        verify(request).setAttribute(Parameter.FIRST_NAME, user.getFirstName());
        verify(request).setAttribute(Parameter.LAST_NAME, user.getLastName());
        verify(request).setAttribute(Parameter.USERNAME, user.getUsername());
        verify(request).setAttribute(Parameter.PASSWORD, user.getPassword());
        verify(request).setAttribute(Parameter.REENTER_PASSWORD, user.getReenterPassword());
        verify(request).setAttribute(Parameter.USER_ROLE, user.getRoleId());
    }

    @Test
    public void execute_IfNoErrors_Path() throws Exception {
        given(session.getAttribute(eq(Parameter.ERRORS))).willReturn(null);

        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_PATH));
    }

    private void createUser() {
        user = new UserValidatorBean();
        user.setFirstName("");
        user.setLastName("");
        user.setUsername("");
        user.setPassword("");
        user.setReenterPassword("");
        user.setRoleId(1);
    }

}
