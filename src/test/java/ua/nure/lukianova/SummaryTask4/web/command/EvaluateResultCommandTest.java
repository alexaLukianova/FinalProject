package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.db.entity.Result;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.AnswerService;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.service.ResultService;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.EvaluateResultCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EvaluateResultCommandTest {

    private static final int MAX_MARK = 100;
    private static final String[] USER_ANSWERS = {"1&1", "1&2", "2&2", "2&1"};
    private static final String EXPECTED_PATH = Path.COMMAND_SHOW_RESULT_FORM;
    private Command command;

    @Mock
    private QuestionService questionService;
    @Mock
    private ResultService resultService;
    @Mock
    private AnswerService answerService;
    @Mock
    private TestService testService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Captor
    private ArgumentCaptor<Integer> markCaptor;


    @Before
    public void setUp() throws Exception {
        command = new EvaluateResultCommand(resultService, testService, questionService, answerService);
        doNothing().when(session).setAttribute(anyString(), markCaptor.capture());
    }

    @Test(expected = AppException.class)
    public void execute_WithNoParameters_AppException() throws Exception {
        command.execute(request, response);
    }

    @Test
    public void execute_NoAnswers_Zero() throws Exception {
        setParameters();
        given(request.getParameterValues(Parameter.ANSWER_CORRECT)).willReturn(new String[]{});

        command.execute(request, response);

        assertThat(markCaptor.getValue(), equalTo(0));
    }


    @Test
    public void execute_RightAnswers_Maximum() throws Exception {
        setParameters();
        given(request.getParameterValues(Parameter.ANSWER_CORRECT)).willReturn(USER_ANSWERS);
        given(answerService.findCorrectByQuestionId(anyLong())).willReturn(createListAnswers());

        command.execute(request, response);

        assertThat(markCaptor.getValue(), equalTo(MAX_MARK));
    }


    @Test
    public void execute_ResultUpdate_True() throws Exception {
        setParameters();
        given(request.getParameterValues(Parameter.ANSWER_CORRECT)).willReturn(USER_ANSWERS);
        given(answerService.findCorrectByQuestionId(anyLong())).willReturn(createListAnswers());

        command.execute(request, response);

        verify(resultService).update(anyLong(), anyInt());
    }


    @Test
    public void execute_ReturnValidPath_True() throws Exception {
        setParameters();
        given(request.getParameterValues(Parameter.ANSWER_CORRECT)).willReturn(USER_ANSWERS);
        given(answerService.findCorrectByQuestionId(anyLong())).willReturn(createListAnswers());

        String actualPath = command.execute(request, response);

        assertEquals(EXPECTED_PATH, actualPath);
    }


    private List<Question> createListQuestions() {
        Question question1 = new Question();
        question1.setId(1L);
        Question question2 = new Question();
        question2.setId(2l);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);

        return questions;
    }

    private List<Answer> createListAnswers() {
        Answer answer1 = new Answer();
        answer1.setId(1l);
        Answer answer2 = new Answer();
        answer2.setId(2l);
        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);

        return answers;
    }

    private void setParameters() {
        Result result = new Result();
        result.setId(1l);
        ua.nure.lukianova.SummaryTask4.db.entity.Test test = new ua.nure.lukianova.SummaryTask4.db.entity.Test();
        test.setId(1l);

        given(request.getParameter(Parameter.TEST_ID)).willReturn("1");
        given(request.getParameter(Parameter.RESULT_ID)).willReturn("1");
        given(request.getSession()).willReturn(session);

        given(resultService.findById(anyLong())).willReturn(result);
        given(testService.findById(anyLong())).willReturn(test);
        given(questionService.findByTestId(anyLong())).willReturn(createListQuestions());
    }


}
