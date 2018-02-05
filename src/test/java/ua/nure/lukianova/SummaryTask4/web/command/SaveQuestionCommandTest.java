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
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.AnswerService;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.SaveQuestionCommand;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SaveQuestionCommandTest {

    private static final String QUESTION = "question";
    private static final String TEST_ID = "1";
    private Command command;
    private Map<String, String> questionErrors = new HashMap<>();
    private Map<String, String> answersErrors = new HashMap<>();
    private Map<String, String> errors = new HashMap<>();

    private static final String EXPECTED_VALIDATE_ERROR_PATH = Path.COMMAND_SHOW_SAVE_QUESTION_FORM + "&testId=";
    private static final String EXPECTED_PATH = Path.COMMAND_SHOW_EDIT_FORM + "&testId=";

    @Mock
    private QuestionService questionService;
    @Mock
    private AnswerService answerService;
    @Mock
    private Validator answerValidator;
    @Mock
    private Validator questionValidator;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Captor
    ArgumentCaptor<Map<String, String>> errorsArgumentCaptor;
    @Captor
    ArgumentCaptor<String> questionArgumentCaptor;

    @Before
    public void setUp() throws Exception {
        command = new SaveQuestionCommand(answerValidator, questionValidator, questionService, answerService);
        createAnswersErrors();
        createQuestionError();
        createErrors();

        given(request.getSession()).willReturn(session);
        given(request.getParameterValues(anyString())).willReturn(new String[]{"", ""});
        given(request.getParameter(eq(Parameter.TEST_ID))).willReturn(TEST_ID);
        given(request.getParameter(eq(Parameter.QUESTION))).willReturn(QUESTION);
        given(questionValidator.validate(any(Question.class))).willReturn(questionErrors);
        given(answerValidator.validate(anyListOf(Answer.class))).willReturn(answersErrors);


        doNothing().when(session).setAttribute(eq(Parameter.ERRORS), errorsArgumentCaptor.capture());
        doNothing().when(session).setAttribute(eq(Parameter.QUESTION), questionArgumentCaptor.capture());
    }

    @Test(expected = AppException.class)
    public void execute_WithNoParameters_AppException() throws Exception {
        given(request.getParameter(eq(Parameter.TEST_ID))).willReturn(null);

        command.execute(request, response);
    }

    @Test
    public void execute_QuestionInfoIsNotValid_RedirectPath() throws Exception {
        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_VALIDATE_ERROR_PATH + TEST_ID));
        assertThat(errorsArgumentCaptor.getValue(), equalTo(errors));
        assertThat(questionArgumentCaptor.getValue(), equalTo(QUESTION));
    }

    @Test
    public void execute_QuestionInfoIsValid_RedirectPath() throws Exception {
        questionErrors.clear();
        answersErrors.clear();

        String actualPath = command.execute(request, response);

        assertThat(actualPath, equalTo(EXPECTED_PATH + TEST_ID));
        verify(questionService).create(any(Question.class));
        verify(answerService).createAll(anyListOf(Answer.class));
    }


    private void createAnswersErrors() {
        answersErrors.put(Parameter.ANSWER, "error.answer.required");
    }

    private void createQuestionError() {
        questionErrors.put(Parameter.QUESTION, "error.question.required");
    }

    private void createErrors() {
        errors.putAll(answersErrors);
        errors.putAll(questionErrors);
    }
}
