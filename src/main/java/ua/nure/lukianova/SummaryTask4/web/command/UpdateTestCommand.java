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

        long testId = Long.valueOf(request.getParameter("test_id"));

        request.setAttribute("test_id", request.getParameter("test_id"));

        getTestService().update(testId,
                request.getParameter("name"),
                request.getParameter("subject"),
                request.getParameter("complexity"),
                Long.valueOf(request.getParameter("time")));

        return Path.COMMAND_EDIT_TEST;
    }
}
