package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.db.bean.UserValidatorBean;
import ua.nure.lukianova.SummaryTask4.db.entity.Entity;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.RegisterCommand;
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
public class RegisterCommandTest {

    private Command command;
    private static final String EXAMPLE_USER_VALIDATE_ERROR = "error.username.short";
    private static final String EXPECTED_PATH_REGISTER = Path.COMMAND_SHOW_REGISTER_FORM;
    private static final String EXPECTED_PATH_LIST_USERS = Path.COMMAND_LIST_USERS;
    private static final String EXPECTED_PATH_PROFILE = Path.COMMAND_SHOW_PROFILE;
    private Map<String, String> errors = new HashMap<>();
    private UserValidatorBean userValidatorBean;
    private User user;

    @Mock
    private UserService userService;
    @Mock
    private Validator userValidator;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Captor
    private ArgumentCaptor<Entity> userArgumentCaptor;
    @Captor
    private ArgumentCaptor<Role> roleArgumentCaptor;
    @Captor
    private ArgumentCaptor<Map<String, String>> errorsArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        command = new RegisterCommand(userValidator, userService);
        createErrors();
        createUserValidationBean();
        createUser();

        given(request.getSession()).willReturn(session);
        given(request.getParameter(anyString())).willReturn("");
        given(request.getParameter(eq(Parameter.USER_ROLE))).willReturn(String.valueOf(userValidatorBean.getRoleId()));
        given(userValidator.validate(anyObject())).willReturn(errors);

        doNothing().when(session).setAttribute(eq(Parameter.USER_BEAN), userArgumentCaptor.capture());
        doNothing().when(session).setAttribute(eq(Parameter.USER), userArgumentCaptor.capture());
        doNothing().when(session).setAttribute(eq(Parameter.ERRORS), errorsArgumentCaptor.capture());
        doNothing().when(session).setAttribute(eq(Parameter.USER_ROLE), roleArgumentCaptor.capture());
    }

    @Test
    public void execute_UserInfoIsNotValid_RedirectPath() throws Exception {
        String actualPath = command.execute(request, response);

        assertThat(errorsArgumentCaptor.getValue(), equalTo(errors));
        assertThat(userArgumentCaptor.getValue(), equalTo(userValidatorBean));
        assertThat(actualPath, equalTo(EXPECTED_PATH_REGISTER));
    }

    @Test
    public void execute_UserInfoIsValidCreateByAdmin_True() throws Exception {
        errors.clear();
        given(userService.findByLogin(anyString())).willReturn(user);
        given(session.getAttribute(anyString())).willReturn("admin");

        String actualPath = command.execute(request, response);

        verify(userService).create(userValidatorBean);
        assertThat(actualPath, equalTo(EXPECTED_PATH_LIST_USERS));

        createErrors();
    }

    @Test
    public void execute_UserInfoIsValidCreateNewStudent_True() throws Exception {
        errors.clear();
        given(userService.findByLogin(anyString())).willReturn(user);
        given(session.getAttribute(anyString())).willReturn(null);

        String actualPath = command.execute(request, response);

        verify(userService).create(userValidatorBean);
        assertThat(userArgumentCaptor.getValue(), equalTo(user));
        assertThat(roleArgumentCaptor.getValue(), equalTo(Role.getRole(user)));
        assertThat(actualPath, equalTo(EXPECTED_PATH_PROFILE));

        createErrors();
    }


    private void createErrors() {
        errors.put(Parameter.USERNAME, EXAMPLE_USER_VALIDATE_ERROR);
    }

    private void createUserValidationBean() {
        userValidatorBean = new UserValidatorBean();
        userValidatorBean.setFirstName("");
        userValidatorBean.setLastName("");
        userValidatorBean.setUsername("");
        userValidatorBean.setPassword("");
        userValidatorBean.setReenterPassword("");
        userValidatorBean.setRoleId(0);
    }

    private void createUser() {
        user = new User();
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setUsername("username");
        user.setPassword("password");
        user.setRoleId(0);
    }


}
