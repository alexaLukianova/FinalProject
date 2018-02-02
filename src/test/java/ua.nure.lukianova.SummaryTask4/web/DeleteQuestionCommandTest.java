package ua.nure.lukianova.SummaryTask4.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.DeleteQuestionCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class DeleteQuestionCommandTest {

    private Command command;

    private QuestionService questionService = mock(QuestionService.class);

    @Before
    public void setUp() throws Exception {
        command = new DeleteQuestionCommand(questionService);
    }


    @Test
    public void execute_DeleteFromTestWithQuestions_True() throws Exception {
        given(questionService.findByTestId(anyLong())).willReturn(createListQuestions());

        command.execute(createRequest(), mock(HttpServletResponse.class));

        verify(questionService).delete(anyLong());
    }


    @Test
    public void execute_DeleteFromTestWithNoQuestions_False() throws Exception {
        given(questionService.findByTestId(anyLong())).willReturn(new ArrayList<>());

        command.execute(createRequest(), mock(HttpServletResponse.class));

        verify(questionService, never()).delete(anyLong());
    }

    @Test
    public void execute_ReturnValidPath_True() throws Exception {
        String path = "/controller?command=showEditForm&testId=";
//        given(command.execute(createRequest(), mock(HttpServletResponse.class))
//                .willReturn(path+anyLong());

        verify(command).execute(createRequest(), mock(HttpServletResponse.class));
    }

    private HttpServletRequest createRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter(Parameter.TEST_ID)).thenReturn("1");
        when(request.getParameter(Parameter.QUESTION_ID)).thenReturn("1");
        return request;
    }

    private List<Question> createListQuestions() {
        Question question = new Question();
        question.setId(1L);
        return Arrays.asList(question);
    }


}
