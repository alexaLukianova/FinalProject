package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.TestService;
import ua.nure.lukianova.SummaryTask4.service.TestServiceImpl;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.service.UserServiceImpl;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListTestsCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandContainer.class);

    private static final long serialVersionUID = -1776868124132437863L;

    private static Comparator<Test> compareByName = new CompareByName();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        request.getParameter("timer");



        List<Test> tests = getTestService().findAllTests();

        Collections.sort(tests, compareByName);

        request.setAttribute("tests", tests);

        return Path.PAGE_LIST_TESTS;
    }

    private static class CompareByName implements Comparator<Test>, Serializable {


        private static final long serialVersionUID = -2147688237145998268L;

        public int compare(Test test1, Test test2) {
            return test1.getName().compareTo(test2.getName());
        }
    }
}

