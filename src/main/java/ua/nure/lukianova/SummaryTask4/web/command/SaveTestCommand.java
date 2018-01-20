package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.Answer;
import ua.nure.lukianova.SummaryTask4.db.entity.Question;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.AnswerService;
import ua.nure.lukianova.SummaryTask4.service.AnswerServiceImpl;
import ua.nure.lukianova.SummaryTask4.service.QuestionService;
import ua.nure.lukianova.SummaryTask4.service.QuestionServiceImpl;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SaveTestCommand extends Command {

    private QuestionService questionService;
    private AnswerService answerService;

    private Question question;
    private Answer answer;

    {
        questionService = new QuestionServiceImpl();
        answerService = new AnswerServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        int questionsNumber = Integer.valueOf((String) session.getAttribute("questionsNumber"));
        int answersNumber = Integer.valueOf((String) session.getAttribute("answersNumber"));
        long testId = (Long) session.getAttribute("testId");
        long questionId;
        for (int i = 1; i <= questionsNumber; i++) {

            question = new Question();
            question.setText(decode(request.getParameter("question" + i)));
            question.setTestId(testId);
            questionId = questionService.create(question);

            answer = new Answer();
            for (int j = 1; j <= answersNumber; j++) {
                answer.setText(decode(request.getParameter("answer" + i + j)));
                boolean correct = "is_correct".equals(request.getParameter("correct" + i + j));
                answer.setCorrect(correct);
                answer.setQuestionId(questionId);
                answerService.create(answer);
            }
        }
        return Path.COMMAND_LIST_TESTS;
    }


    private String decode(String item) {
        byte[] bytes = item.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
