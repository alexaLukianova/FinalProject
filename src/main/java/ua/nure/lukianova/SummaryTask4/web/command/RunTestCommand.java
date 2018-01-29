package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.*;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class RunTestCommand extends Command {
    private static final long serialVersionUID = 166914390870487363L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        Result result;

        long testId = Long.valueOf(request.getParameter(Parameter.TEST_ID));
        HttpSession session = request.getSession();
        long userId = ((User) session.getAttribute(Parameter.USER)).getId();
        Test test = getTestService().findById(testId);




        List<Question> questions = new ArrayList<>(getQuestionService().findByTestId(testId));
        Collections.shuffle(questions);
        Map<Question, List<Answer>> questAnsMap = new HashMap<>();
        for (Question question : questions) {
            List<Answer> answers = new ArrayList<>(getAnswerService().findByQuestionId(question.getId()));
            Collections.shuffle(answers);
            questAnsMap.put(question, answers);
        }

        request.setAttribute(Parameter.TEST, test);
//        request.setAttribute(Parameter.QUESTIONS, questions);
        request.setAttribute(Parameter.QUEST_ANS_MAP, questAnsMap);



        result = new Result();
        result.setUserId(userId);
        result.setTestId(testId);
        result.setStartTime(System.currentTimeMillis());
       long resultId =  getResultService().create(result);

        request.setAttribute( Parameter.START_TIME, result.getStartTime());
        request.setAttribute(Parameter.RESULT_ID, resultId);

        return Path.PAGE_TEST_FORM;


    }

}
