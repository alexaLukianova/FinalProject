package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.ListUsersCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListUsersCommandTest {

    private Command command;
    private static final String EXPECTED_PATH = Path.PAGE_LIST_USERS;

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
        command = new ListUsersCommand(userService);
        doNothing().when(request).setAttribute(eq(Parameter.USERS), userCaptor.capture());
    }

    @Test
    public void execute_ReturnValidPath_True() throws Exception {
        String actualPath = command.execute(request, response);

        assertEquals(EXPECTED_PATH, actualPath);
    }

    @Test
    public void execute_UserServiceFind_AllTests() throws Exception {
        command.execute(request, response);

        verify(userService).findAll();
    }

    @Test
    public void execute_SetUserssIntoRequest_True() throws Exception {
        List<User> users = createListUsers();
        given(userService.findAll()).willReturn(users);

        command.execute(request, response);
        List<User> actualListUsers = userCaptor.getValue();

        assertThat(actualListUsers, equalTo(users));
    }

    private List<User> createListUsers() {
        User user = new User();
        user.setId(1L);
        List<User> users = new ArrayList<>();
        users.add(user);

        return users;
    }

}
