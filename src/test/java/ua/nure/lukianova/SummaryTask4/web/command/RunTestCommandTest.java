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
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.AnswerService;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.service.ResultService;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.RunTestCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class RunTestCommandTest {

    private Command command;
    private User user;
    private ua.nure.lukianova.SummaryTask4.db.entity.Test test;
    private Result result;
    private List<Question> questions;
    private List<Answer> answers;
    private Map<Question, List<Answer>> questAnsMap = new HashMap<>();
    private static final String EXPECTED_PATH = Path.PAGE_TEST_FORM;


    @Mock
    private TestService testService;
    @Mock
    private QuestionService questionService;
    @Mock
    private AnswerService answerService;
    @Mock
    private ResultService resultService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Captor
    ArgumentCaptor<ua.nure.lukianova.SummaryTask4.db.entity.Test> testArgumentCaptor;
    @Captor
    ArgumentCaptor<Long> timeArgumentCaptor;
    @Captor
    ArgumentCaptor<Long> resultArgumentCaptor;
    @Captor
    ArgumentCaptor<Map<Question, List<Answer>>> mapArgumentCaptor;


    @Before
    public void setUp() throws Exception {
        command = new RunTestCommand(testService, questionService, answerService, resultService);
        createUser();
        createTest();
        createQuestAnsMap();
        createResult();

        given(request.getSession()).willReturn(session);
        given(request.getParameter(eq(Parameter.TEST_ID))).willReturn("1");
        given(session.getAttribute(eq(Parameter.USER))).willReturn(user);
        given(testService.findById(anyLong())).willReturn(test);
        given(questionService.findByTestId(anyLong())).willReturn(questions);
        given(answerService.findByQuestionId(anyLong())).willReturn(answers);
        given(resultService.create(anyObject())).willReturn(result.getStartTime());

        doNothing().when(request).setAttribute(eq(Parameter.TEST), testArgumentCaptor.capture());
        doNothing().when(request).setAttribute(eq(Parameter.QUEST_ANS_MAP), mapArgumentCaptor.capture());
        doNothing().when(request).setAttribute(eq(Parameter.START_TIME), timeArgumentCaptor.capture());
        doNothing().when(request).setAttribute(eq(Parameter.RESULT_ID), resultArgumentCaptor.capture());
    }

    @Test(expected = AppException.class)
    public void execute_WithNoParameters_AppException() throws Exception {
        given(request.getParameter(eq(Parameter.TEST_ID))).willReturn(null);
        command.execute(request, response);
    }

    @Test
    public void execute_SetParametersIntoRequest_True() throws Exception {
        command.execute(request, response);

        assertThat(testArgumentCaptor.getValue(), equalTo(test));
        assertTrue(timeArgumentCaptor.getValue() instanceof Long);
        assertThat(mapArgumentCaptor.getValue(), equalTo(questAnsMap));
        assertTrue(resultArgumentCaptor.getValue() instanceof Long);
    }

    @Test
    public void execute_Return_True() throws Exception {
        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_PATH));
    }

    private void createUser() {
        user = new User();
        user.setId(1l);
    }

    private void createTest() {
        test = new ua.nure.lukianova.SummaryTask4.db.entity.Test();
        test.setId(1l);
    }

    private void createListQuestions() {
        questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setId(1l);
        question1.setText("question1");
        Question question2 = new Question();
        question2.setId(2l);
        question2.setText("question2");

        questions.add(question1);
        questions.add(question2);
    }

    private void createListAnswers() {
        answers = new ArrayList<>();
        Answer answer1 = new Answer();
        answer1.setId(1l);
        answer1.setText("answer1");
        Answer answer2 = new Answer();
        answer2.setId(2l);
        answer2.setText("answer2");

        answers.add(answer1);
        answers.add(answer2);
    }

    private void createQuestAnsMap() {
        createListAnswers();
        createListQuestions();
        for (Question question : questions) {
            questAnsMap.put(question, answers);
        }
    }

    private void createResult() {
        result = new Result();
        result.setId(1l);
        result.setStartTime(0l);
    }
}
