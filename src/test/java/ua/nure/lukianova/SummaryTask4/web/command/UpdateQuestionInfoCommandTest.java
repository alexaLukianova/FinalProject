package ua.nure.lukianova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import ua.nure.lukianova.SummaryTask4.web.command.UpdateQuestionInfoCommand;
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
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UpdateQuestionInfoCommandTest {

    private static final String QUESTION_ID = "1";
    private static final String TEST_ID = "1";
    private Command command;
    private Map<String, String> errors = new HashMap<>();
    private static final String EXPECTED_PATH = Path.COMMAND_SHOW_EDIT_FORM + "&testId=";

    @Mock
    private Validator answerValidator;
    @Mock
    private Validator questionValidator;
    @Mock
    private QuestionService questionService;
    @Mock
    private AnswerService answerService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        command = new UpdateQuestionInfoCommand(answerValidator, questionValidator,
                questionService, answerService);
        createErrors();

        given(request.getSession()).willReturn(session);
        given(request.getParameter(eq(Parameter.TEST_ID))).willReturn(TEST_ID);
        given(request.getParameter(eq(Parameter.QUESTION_ID))).willReturn(QUESTION_ID);
        given(request.getParameter(eq(Parameter.QUESTION))).willReturn("question");
        given(request.getParameterValues(eq(Parameter.ANSWER_ID))).willReturn(new String[]{"1", "2"});
        given(request.getParameterValues(eq(Parameter.ANSWER))).willReturn(new String[]{"answer1", "answer2"});
        given(request.getParameterValues(eq(Parameter.ANSWER_CORRECT))).willReturn(new String[]{"1"});
        given(request.getParameterValues(eq(Parameter.ANSWER_CORRECT))).willReturn(new String[]{"1"});
        given(questionValidator.validate(any(Question.class))).willReturn(errors);
        given(answerValidator.validate(any(Answer.class))).willReturn(errors);

    }

    @Test(expected = AppException.class)
    public void execute_NoTestId_AppException() throws Exception {
        given(request.getParameter(eq(Parameter.TEST_ID))).willReturn(null);

        command.execute(request, response);
    }

    @Test
    public void execute_NoErrors_Update() throws Exception {
        errors.clear();

        String actualPath = command.execute(request, response);

        verify(questionService).update(any(Question.class));
        verify(answerService).updateAll(anyListOf(Answer.class));
        assertThat(actualPath, equalTo(EXPECTED_PATH + TEST_ID));
    }

    @Test
    public void execute_HasErrors_SetAttributesIntoSession() throws Exception {
        String actualPath = command.execute(request, response);

        verify(session).setAttribute(Parameter.ERRORS, errors);
        verify(session).setAttribute(Parameter.QUESTION_ID, QUESTION_ID);
        assertThat(actualPath, equalTo(EXPECTED_PATH + TEST_ID));
    }


    private void createErrors() {
        errors.put(Parameter.ANSWER, "error.answer.dublicates");
        errors.put(Parameter.QUESTION, "error.question.required");
    }

}
