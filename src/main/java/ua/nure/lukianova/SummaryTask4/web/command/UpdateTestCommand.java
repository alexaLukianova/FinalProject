package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateTestCommand extends Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        request.setAttribute("test_id", request.getParameter("test_id"));

        getTestService().update(extractTest(request));

        return Path.COMMAND_EDIT_TEST;
    }

    private Test extractTest(HttpServletRequest request) {
        Test test = new Test();
        test.setId(Long.valueOf(request.getParameter("test_id")));
        test.setName(request.getParameter("name"));
        test.setSubject(request.getParameter("subject"));
        test.setComplexityId(Integer.valueOf(request.getParameter("complexity_id")));
        test.setDuration(Long.valueOf(request.getParameter("time")));

        return test;
    }
}
