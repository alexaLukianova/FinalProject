package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.bean.TestValidationBean;
import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.exception.DBException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.validator.AddAnswersValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.AddQuestionValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.TestValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EditTestCommand extends Command {

    private static final long serialVersionUID = -7228884081045892629L;
    public static final int ANSWERS_NUMBER = 4;
    private Validator testValidator;
    private Validator answerValidator;
    private Validator questionValidator;
    private Map<String, String> errors;


    public EditTestCommand() {
        this.testValidator = new TestValidator();
        this.questionValidator = new AddQuestionValidator();
        this.answerValidator = new AddAnswersValidator();

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException, DBException {
        errors = new LinkedHashMap<>();
        long testId;
        Test test;
        TestValidationBean testValidationBean;
        Map<Question, List<Answer>> questAnsMap;


        if (isNewTest(request) && isChangedTestInfo(request)) {
            testValidationBean = extractTestValidationBean(request);
            if (isValidTestInfo(testValidationBean)) {
                test = extractTest(testValidationBean);
                testId = getTestService().create(test);
                test = getTestService().findById(testId);

                request.setAttribute(Parameter.TEST, test);
                request.setAttribute(Parameter.TEST_ID, testId);
            } else {
                request.setAttribute(Parameter.TEST, testValidationBean);
            }
        }


        if (!isNewTest(request) && !isChangedTestInfo(request)) {
            testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
            test = getTestService().findById(testId);

            checkQuestionInfo(request);
            questAnsMap = extractQuestionInfoFromDB(testId);

            request.setAttribute(Parameter.QUEST_ANS_MAP, questAnsMap);
            request.setAttribute(Parameter.TEST, test);
            request.setAttribute(Parameter.TEST_ID, testId);
        }


        if (!isNewTest(request) && isChangedTestInfo(request)) {
            testValidationBean = extractTestValidationBean(request);
            testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
            if (isValidTestInfo(testValidationBean)) {
                test = extractTest(testValidationBean);
                test = getTestService().update(test);

                checkQuestionInfo(request);
                questAnsMap = extractQuestionInfoFromDB(testId);

                request.setAttribute(Parameter.QUEST_ANS_MAP, questAnsMap);
                request.setAttribute(Parameter.TEST, test);
            } else {
                request.setAttribute(Parameter.TEST, testValidationBean);
            }
            request.setAttribute(Parameter.TEST_ID, testId);
        }


        request.setAttribute(Parameter.ERRORS, errors);
        request.setAttribute(Parameter.ANSWERS_NUMBER, ANSWERS_NUMBER);
        request.setAttribute(Parameter.QUESTION_ID, request.getParameter(Parameter.QUESTION_ID));

        return Path.PAGE_EDIT_TEST;
    }

    private void checkQuestionInfo(HttpServletRequest request) {
        if (isChangedQuestionInfo(request)) {
            Question question = extractQuestion(request);
            List<Answer> answers = extractAnswers(request);
            if (isValidQuestionInfo(question, answers)) {
                updateQuestionInfo(question, answers);
            }
        }
    }


    private TestValidationBean extractTestValidationBean(HttpServletRequest request) {
        TestValidationBean test = new TestValidationBean();
        if (!isNewTest(request)) {
            test.setId(Long.valueOf(request.getParameter(Parameter.TEST_ID)));
        }
        test.setName(request.getParameter(Parameter.TEST_NAME).trim());
        test.setSubject(request.getParameter(Parameter.TEST_SUBJECT).trim());
        test.setComplexityId(request.getParameter(Parameter.TEST_COMPLEXITY_ID).trim());
        test.setDuration(request.getParameter(Parameter.TEST_DURATION).trim());

        return test;
    }


    private boolean isChangedQuestionInfo(HttpServletRequest request) {
        return Objects.nonNull(request.getParameter(Parameter.QUESTION_ID))
                && Objects.nonNull(request.getParameterValues(Parameter.ANSWER_ID));
    }

    private boolean isChangedTestInfo(HttpServletRequest request) {
        return Objects.nonNull(request.getParameter(Parameter.CHANGED));
    }


    private boolean isValidQuestionInfo(Question question, List<Answer> answers) {
        Map<String, String> questionErrors = new LinkedHashMap<>();
        questionErrors.putAll(questionValidator.validate(question));
        questionErrors.putAll(answerValidator.validate(answers));
        errors.putAll(questionErrors);
        return questionErrors.isEmpty();
    }

    private void updateQuestionInfo(Question question, List<Answer> answers) throws DBException {
        getQuestionService().update(question);
        getAnswerService().updateAll(answers);
    }

    private boolean isValidTestInfo(TestValidationBean testValidationBean) {
        Map<String, String> testErrors = new LinkedHashMap<>();
        testErrors.putAll(testValidator.validate(testValidationBean));
        errors.putAll(testErrors);
        return testErrors.isEmpty();
    }

    private Map<Question, List<Answer>> extractQuestionInfoFromDB(long testId) {
        Map<Question, List<Answer>> map =
                getQuestionService().findByTestId(testId)
                        .stream()
                        .collect(Collectors.toMap(Function.identity(),
                                question -> getAnswerService().findByQuestionId(question.getId())
                                , (a, b) -> a,
                                LinkedHashMap::new));

        return map;
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
        String[] id = request.getParameterValues(Parameter.ANSWER_ID);
        String[] text = request.getParameterValues(Parameter.ANSWER);
        String[] correct = request.getParameterValues(Parameter.ANSWER_CORRECT);
        List<String> correctAnswers = new ArrayList<>();
        if (Objects.nonNull(correct)) {
            correctAnswers = new ArrayList<>(Arrays.asList(correct));
        }


        for (int i = 0; i < id.length; i++) {
            Answer answer = new Answer();
            answer.setId(Long.valueOf(id[i]));
            answer.setText(text[i]);
            answer.setCorrect(correctAnswers.contains(id[i]));
            answers.add(answer);
        }

        return answers;
    }

    private boolean isNewTest(HttpServletRequest request) {
        String testId = request.getParameter(Parameter.TEST_ID);
        return Objects.isNull(testId) || testId.trim().isEmpty();
    }

    private static Test extractTest(TestValidationBean validTest) {
        Test test = new Test();
        test.setId(validTest.getId());
        test.setName(validTest.getName());
        test.setSubject(validTest.getSubject());
        test.setComplexityId(Integer.valueOf(validTest.getComplexityId()));
        test.setDuration(Long.valueOf(validTest.getDuration()));

        return test;
    }

}
