package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.exception.DBException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.validator.AddAnswersValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.AddQuestionValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class UpdateQuestionInfoCommand extends Command {

    private static final long serialVersionUID = 5548030734944296696L;
    private Validator answerValidator;
    private Validator questionValidator;
    private Map<String, String> errors;
    private long testId;


    public UpdateQuestionInfoCommand() {
        this.questionValidator = new AddQuestionValidator();
        this.answerValidator = new AddAnswersValidator();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        errors = new LinkedHashMap<>();
        testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));

        Question question = extractQuestion(request);
        List<Answer> answers = extractAnswers(request);

        errors.putAll(questionValidator.validate(question));
        errors.putAll(answerValidator.validate(answers));

        if (MapUtils.isEmpty(errors)) {
            updateQuestionInfo(question, answers);
        } else {
            setAttributesIntoSessionScope(request);
        }

        return getURL();
    }

    private void setAttributesIntoSessionScope(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(Parameter.ERRORS, new HashMap<>(errors));
        session.setAttribute(Parameter.QUESTION_ID, request.getParameter(Parameter.QUESTION_ID));
    }

    private String getURL() {
        return new StringBuilder(Path.COMMAND_SHOW_EDIT_FORM)
                .append(DELIMITER)
                .append(Parameter.TEST_ID).append(EQUAL_SIGN).append(testId)
                .toString();
    }

    private Question extractQuestion(HttpServletRequest request) {
        Question question = new Question();
        question.setTestId(Long.valueOf(request.getParameter(Parameter.TEST_ID)));
        question.setId(Long.valueOf(request.getParameter(Parameter.QUESTION_ID)));
        question.setText(request.getParameter(Parameter.QUESTION));
        return question;
    }

    private List<Answer> extractAnswers(HttpServletRequest request) {
        List<Answer> answers = new ArrayList<>();
        String[] ids = request.getParameterValues(Parameter.ANSWER_ID);
        String[] texts = request.getParameterValues(Parameter.ANSWER);
        String[] corrects = request.getParameterValues(Parameter.ANSWER_CORRECT);

        List<String> correctAnswers = ArrayUtils.isNotEmpty(corrects)
                ? Arrays.asList(corrects)
                : Collections.emptyList();

        for (int i = 0; i < ids.length; i++) {
            Answer answer = new Answer();
            answer.setId(Long.valueOf(ids[i]));
            answer.setText(texts[i]);
            answer.setCorrect(correctAnswers.contains(ids[i]));
            answers.add(answer);
        }

        return answers;
    }

    private void updateQuestionInfo(Question question, List<Answer> answers) throws DBException {
        getQuestionService().update(question);
        getAnswerService().updateAll(answers);
    }

}
