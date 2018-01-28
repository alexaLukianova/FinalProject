package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SaveQuestionCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
        int answersNumber = Integer.valueOf(request.getParameter(Parameter.ANSWERS_NUMBER));


       // request.setAttribute("test_id", request.getParameter("test_id"));

        Question question = Utils.extractNonExistInDBQuestion(request);
        List<Answer> answers = Utils.extractAnswers(request);
        if (isValidQuestionInfo(question, answers)) {
            updateQuestionInfo(question, answers);
            questAnsMap = extractQuestionInfoFromDB(testId);
        }




        Question question = new Question();
        question.setText(request.getParameter("question"));
        question.setTestId(testId);
        long questionId = getQuestionService().create(question);

        Answer answer = new Answer();
        for (int i = 1; i <= answersNumber; i++) {
            answer.setText(request.getParameter("answer" + i));
            boolean correct = "is_correct".equals(request.getParameter("correct" + i));
            answer.setCorrect(correct);
            answer.setQuestionId(questionId);
            getAnswerService().create(answer);
        }

        return Path.COMMAND_EDIT_TEST;
    }
}

