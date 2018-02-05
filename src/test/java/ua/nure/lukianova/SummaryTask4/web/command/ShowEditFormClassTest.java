package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.lukianova.SummaryTask4.db.bean.TestValidationBean;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.service.AnswerService;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.ShowEditFormCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShowEditFormClassTest {

    private static final String TEST_ID = "1";
    private static final String QUESTION_ID = "1";
    private Command command;
    private ua.nure.lukianova.SummaryTask4.db.entity.Test test;
    private TestValidationBean testValidationBean;
    private List<Question> questions;
    private List<Answer> answers;
    private List<Answer> answers2;
    private Map<String, String> errors = new HashMap<>();
    private static final String EXPECTED_PATH = Path.PAGE_EDIT_TEST;

    @Mock
    private QuestionService questionService;
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
    ArgumentCaptor<ua.nure.lukianova.SummaryTask4.db.entity.Test> testArgumentCaptor;
    @Captor
    ArgumentCaptor<Long> testIdArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        command = new ShowEditFormCommand(testService, questionService, answerService);
        createTest();
        createTestValidationBean();
        createListQuestions();
        createListAnswers();
        createListAnswers2();

        given(request.getSession()).willReturn(session);
        given(request.getParameter(eq(Parameter.TEST_ID))).willReturn(TEST_ID);
        given(questionService.findByTestId(anyLong())).willReturn(questions);
        given(answerService.findByQuestionId(2l)).willReturn(answers2);
        given(answerService.findByQuestionId(1l)).willReturn(answers);
        given(testService.findById(anyLong())).willReturn(test);


        doNothing().when(request).setAttribute(eq(Parameter.TEST_ID), testIdArgumentCaptor.capture());
        doNothing().when(request).setAttribute(eq(Parameter.TEST), testArgumentCaptor.capture());
    }

    @Test
    public void execute_EditOfExistTest_setAttibutesIntoTest() throws Exception {
        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_PATH));
        assertThat(testIdArgumentCaptor.getValue(), equalTo(test.getId()));
        assertThat(testArgumentCaptor.getValue(), equalTo(test));
        verify(questionService).findByTestId(anyLong());
        verify(answerService, times(2)).findByQuestionId(anyLong());
    }

    @Test
    public void execute_SetIfExistsErrors_True() throws Exception {
        given(session.getAttribute(eq(Parameter.ERRORS))).willReturn(errors);

        command.execute(request, response);

        verify(request).setAttribute(Parameter.ERRORS, errors);
    }

    @Test
    public void execute_SetIfExistsQuestionId_True() throws Exception {
        given(session.getAttribute(eq(Parameter.QUESTION_ID))).willReturn(QUESTION_ID);

        command.execute(request, response);

        verify(request).setAttribute(Parameter.QUESTION_ID, QUESTION_ID);
    }

    @Test
    public void execute_SetIfExistsTest_True() throws Exception {
        given(session.getAttribute(eq(Parameter.TEST))).willReturn(testValidationBean);

        command.execute(request, response);

        verify(request).setAttribute(Parameter.TEST, testValidationBean);
    }

    private void createTest() {
        test = new ua.nure.lukianova.SummaryTask4.db.entity.Test();
        test.setId(1l);
    }

    private void createListQuestions() {
        Question question = new Question();
        question.setId(1l);
        question.setText("question");
        question.setTestId(1l);


        Question question1 = new Question();
        question1.setId(2l);
        question1.setText("question1");
        question1.setTestId(1l);
        questions = new ArrayList<>();
        questions.add(question);
        questions.add(question1);
    }

    private void createListAnswers() {
        Answer answer1 = new Answer();
        answer1.setId(1l);
        Answer answer2 = new Answer();
        answer2.setId(2l);

        answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
    }

    private void createListAnswers2() {
        Answer answer1 = new Answer();
        answer1.setId(3l);
        Answer answer2 = new Answer();
        answer2.setId(4l);

        answers2 = new ArrayList<>();
        answers2.add(answer1);
        answers2.add(answer2);
    }

    private void createTestValidationBean() {
        testValidationBean = new TestValidationBean();
        testValidationBean.setId(1l);
    }
}
