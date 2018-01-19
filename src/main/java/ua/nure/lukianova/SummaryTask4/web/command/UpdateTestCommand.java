package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateTestCommand extends Command {




    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        getTestService();//.update(Test test);
        getAnswerService();
        getQuestionService();


        return Path.COMMAND_LIST_TESTS;
    }
}
