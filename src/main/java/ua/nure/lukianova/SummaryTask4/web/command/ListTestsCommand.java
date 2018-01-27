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
import java.util.Objects;

public class ListTestsCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandContainer.class);

    private static final long serialVersionUID = -1776868124132437863L;

    private static Comparator<Test> comparator;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");


        List<Test> tests = getTestService().findAllTests();
        String order = request.getParameter("order");
        String field = request.getParameter("field");

        if (Objects.nonNull(field)) {
            switch (field) {
                case "name":
                    comparator = new CompareByName();
                    break;
                case "time":
                    comparator = new CompareByTime();
                    break;
                case "subject":
                    comparator = new CompareBySubject();
                    break;
                case "complexity":
                    break;
                case "questions":
                    break;
            }

            Collections.sort(tests, comparator);
            if(Objects.nonNull(order)){
                if(order.equals("decs")){
                    Collections.reverse(tests);
                }
            }
        }


        request.setAttribute("tests", tests);

        return Path.PAGE_LIST_TESTS;
    }

    private static class CompareByName implements Comparator<Test>, Serializable {

        private static final long serialVersionUID = -2147688237145998268L;

        public int compare(Test test1, Test test2) {
            return test1.getName().compareTo(test2.getName());
        }
    }

    private static class CompareByTime implements Comparator<Test>, Serializable {


        private static final long serialVersionUID = 7639555025203948782L;

        public int compare(Test test1, Test test2) {
            long delta = test1.getDuration() - (test2.getDuration());
            return (int) delta;
        }
    }

    private static class CompareBySubject implements Comparator<Test>, Serializable {


        private static final long serialVersionUID = -5969079642574366256L;

        public int compare(Test test1, Test test2) {
            return test1.getSubject().compareTo(test2.getSubject());
        }
    }
}

