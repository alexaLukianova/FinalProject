package ua.nure.lukianova.SummaryTask4.web;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static Question extractQuestion(HttpServletRequest request) {
        Question question = new Question();
        question.setTestId(Long.valueOf(request.getParameter(Parameter.TEST_ID)));
        question.setId(Long.valueOf(request.getParameter(Parameter.QUESTION_ID)));
        question.setText(request.getParameter(Parameter.QUESTION));
        return question;
    }

    public static Question extractNonExistInDBQuestion(HttpServletRequest request) {
        Question question = new Question();
        question.setTestId(Long.valueOf(request.getParameter(Parameter.TEST_ID)));
        question.setText(request.getParameter(Parameter.QUESTION));
        return question;
    }

    public static List<Answer> extractAnswers(HttpServletRequest request) {
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

    public static List<Answer> extractNonExistInDBAnswers(HttpServletRequest request) {
        List<Answer> answers = new ArrayList<>();
        String[] text = request.getParameterValues(Parameter.ANSWER);
        String[] correct = request.getParameterValues(Parameter.ANSWER_CORRECT);
        List<String> correctAnswers = new ArrayList<>();
        if (Objects.nonNull(correct)) {
            correctAnswers = new ArrayList<>(Arrays.asList(correct));
        }
        for (int i = 0; i < text.length; i++) {
            Answer answer = new Answer();
            answer.setId(Long.valueOf(id[i]));
            answer.setText(text[i]);
            answer.setCorrect(correctAnswers.contains(id[i]));
            answers.add(answer);
        }

        return answers;
    }
}
