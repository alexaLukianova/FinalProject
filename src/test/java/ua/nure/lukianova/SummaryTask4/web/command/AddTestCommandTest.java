package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.AddTestCommand;
import ua.nure.lukianova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AddTestCommandTest {

    private static final String EXPECTED_PATH = Path.COMMAND_SHOW_EDIT_FORM;
    private Command command;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        command = new AddTestCommand();
    }

    @Test
    public void execute_ReturnValidPath_True() throws Exception {
        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_PATH));
    }
}
