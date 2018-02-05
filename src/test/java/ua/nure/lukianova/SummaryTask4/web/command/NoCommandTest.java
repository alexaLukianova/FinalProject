package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NoCommandTest {

    private static final String EXPECTED_PATH = Path.PAGE_ERROR_PAGE;
    private Command command;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        command = new NoCommand();
    }

    @Test
    public void execute_DeleteFromTestWithQuestions_True() throws Exception {
        String actualPath = command.execute(request, response);

        verify(request).setAttribute(eq(Parameter.ERROR_MESSAGE), anyString());
        assertThat(actualPath, equalTo(EXPECTED_PATH));
    }


}
