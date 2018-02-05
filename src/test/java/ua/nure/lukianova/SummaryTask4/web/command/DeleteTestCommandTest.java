package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.DeleteTestCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DeleteTestCommandTest {

    private static final String EXPECTED_PATH = Path.COMMAND_LIST_TESTS;
    private Command command;

    @Mock
    private TestService testService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        command = new DeleteTestCommand(testService);
    }

    @Test
    public void execute_DeleteExistTest_True() throws Exception {
        command.execute(createRequest(), response);

        verify(testService).delete(anyLong());
    }

    @Test
    public void execute_ReturnValidPath_True() throws Exception {
        given(request.getParameter(Parameter.TEST_ID)).willReturn("1");

        String actualPath = command.execute(request, response);

        assertEquals(EXPECTED_PATH, actualPath);
    }

    @Test(expected = AppException.class)
    public void execute_WithNoParameters_AppException() throws Exception {
        command.execute(request, response);
    }

    private HttpServletRequest createRequest() {
        given(request.getParameter(Parameter.TEST_ID)).willReturn("1");
        return request;
    }
}
