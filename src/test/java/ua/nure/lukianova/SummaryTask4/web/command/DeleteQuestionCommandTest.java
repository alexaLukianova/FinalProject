package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DeleteQuestionCommandTest {

    private static final String EXPECTED_PATH = Path.COMMAND_SHOW_EDIT_FORM + "&testId=1";
    private Command command;

    @Mock
    private QuestionService questionService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        command = new DeleteQuestionCommand(questionService);
    }


    @Test
    public void execute_DeleteFromTestWithQuestions_True() throws Exception {
        given(questionService.findByTestId(anyLong())).willReturn(createListQuestions());

        command.execute(createRequest(), response);

        verify(questionService).delete(anyLong());
    }


    @Test
    public void execute_DeleteFromTestWithNoQuestions_False() throws Exception {
        given(questionService.findByTestId(anyLong())).willReturn(new ArrayList<>());

        command.execute(createRequest(), response);

        verify(questionService, never()).delete(anyLong());
    }

    @Test
    public void execute_ReturnValidPath_True() throws Exception {
        String actualPath = command.execute(createRequest(), response);

        assertEquals(EXPECTED_PATH, actualPath);
    }

    @Test(expected = AppException.class)
    public void execute_WithNoParameters_AppException() throws Exception {
        command.execute(request, response);
    }

    private HttpServletRequest createRequest() {
        given(request.getParameter(Parameter.TEST_ID)).willReturn("1");
        given(request.getParameter(Parameter.QUESTION_ID)).willReturn("1");

        return request;
    }

    private List<Question> createListQuestions() {
        Question question = new Question();
        question.setId(1L);

        return Arrays.asList(question);
    }

}
