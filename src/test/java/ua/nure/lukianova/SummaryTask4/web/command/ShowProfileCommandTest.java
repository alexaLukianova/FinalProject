package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.bean.TestResultBean;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.service.ResultService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.ShowProfileCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShowProfileCommandTest {

    private Command command;
    private User user;
    private List<TestResultBean> results = new ArrayList<>();
    private static final String EXPECTED_PATH = Path.PAGE_PROFILE;

    @Mock
    private ResultService resultService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        command = new ShowProfileCommand(resultService);
        createUser();

        given(request.getSession()).willReturn(session);
        given(session.getAttribute(eq(Parameter.USER))).willReturn(user);
        given(resultService.findByUserId(anyLong())).willReturn(results);
    }

    @Test
    public void execute_EditOfExistTest_setAttibutesIntoTest() throws Exception {
        String actualPath = command.execute(request, response);

        verify(request).setAttribute(Parameter.USER_PROGRESS, results);
        assertThat(actualPath, equalTo(EXPECTED_PATH));
    }


    private void createUser() {
        user = new User();
        user.setId(1l);
        user.setRoleId(1);
    }

}
