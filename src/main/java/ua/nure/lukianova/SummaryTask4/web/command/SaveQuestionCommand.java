package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.validator.AddAnswersValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.AddQuestionValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class SaveQuestionCommand extends Command {


    private static final long serialVersionUID = 6397119223393351766L;
    private Validator answerValidator;
    private Validator questionValidator;
    private Map<String, String> errors;


    public SaveQuestionCommand() {
        this.questionValidator = new AddQuestionValidator();
        this.answerValidator = new AddAnswersValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        errors = new LinkedHashMap<>();

        long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
        int answersNumber = Integer.valueOf(request.getParameter(Parameter.ANSWERS_NUMBER));

        request.setAttribute(Parameter.TEST_ID, testId);
        request.setAttribute(Parameter.ANSWERS_NUMBER, answersNumber);

        if (isChangedInfo(request)) {
            Question question = extractNewQuestion(request);
            List<Answer> answers = extractNewAnswers(request);
            if (isValidQuestionInfo(question, answers)) {
                setQuestionInfo(question, answers);
                return Path.COMMAND_EDIT_TEST;

            } else {
                request.setAttribute(Parameter.ERRORS, errors);
                request.setAttribute(Parameter.QUESTION, question.getText());
            }
        }

         return Path.PAGE_ADD_NEW_QUESTION;
    }

    private Question extractNewQuestion(HttpServletRequest request) {
        Question question = new Question();
        question.setText(request.getParameter(Parameter.QUESTION));
        question.setTestId(Long.valueOf(request.getParameter(Parameter.TEST_ID)));
        return question;
    }

    private List<Answer> extractNewAnswers(HttpServletRequest request) {
        List<Answer> answers = new ArrayList<>();
        String[] text = request.getParameterValues(Parameter.ANSWER);
        String[] correct = request.getParameterValues(Parameter.ANSWER_CORRECT);
        List<String> correctAnswers = new ArrayList<>();
        if (Objects.nonNull(correct)) {
            correctAnswers = new ArrayList<>(Arrays.asList(correct));
        }
        for (int i = 0; i < text.length; i++) {
            Answer answer = new Answer();
            answer.setText(text[i]);
            answer.setCorrect(correctAnswers.contains(String.valueOf(i + 1)));
            answers.add(answer);
        }

        return answers;
    }

    private boolean isValidQuestionInfo(Question question, List<Answer> answers) {
        Map<String, String> questionErrors = new LinkedHashMap<>();
        questionErrors.putAll(questionValidator.validate(question));
        questionErrors.putAll(answerValidator.validate(answers));
        errors.putAll(questionErrors);
        return questionErrors.isEmpty();
    }

    private void setQuestionInfo(Question question, List<Answer> answers) {
        long questionId = getQuestionService().create(question);
        answers.forEach(answer -> answer.setQuestionId(questionId));
        getAnswerService().createAll(answers);
    }

    private boolean isChangedInfo(HttpServletRequest request) {
        return Objects.nonNull(request.getParameter(Parameter.ADD));
    }
}

