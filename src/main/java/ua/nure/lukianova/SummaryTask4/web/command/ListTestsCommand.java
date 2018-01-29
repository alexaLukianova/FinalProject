package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.entity.Test;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.*;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.*;

public class ListTestsCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandContainer.class);

    private static final long serialVersionUID = -1776868124132437863L;

    Map<String, Comparator<Test>> comparatorMap = new HashMap<>();

    {
        comparatorMap.put(TEST_NAME, new CompareByName());
        comparatorMap.put(TEST_DURATION, new CompareByTime());
        comparatorMap.put(TEST_SUBJECT, new CompareBySubject());
        comparatorMap.put(TEST_COMPLEXITY_ID, new CompareByComplexityId());
        comparatorMap.put(QUESTIONS_COUNT, new CompareByQuestionNumber());
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        List<Test> tests = getTestService().findAllTests();

        String order = request.getParameter(ORDER);
        String parameter = request.getParameter(PARAMETER);

        Map<Long, Integer> questionsCount = tests.stream()
                .collect(Collectors.toMap(Test::getId, test -> getQuestionService().findByTestId(test.getId()).size()));


        request.setAttribute(QUESTIONS_COUNT, questionsCount);

        sort(tests, order, parameter);

        request.setAttribute(TESTS, tests);

        return Path.PAGE_LIST_TESTS;
    }

    private void sort(List<Test> tests, String order, String parameter) {
        if (Objects.nonNull(parameter)) {
            Collections.sort(tests, comparatorMap.get(parameter));
            if (Objects.nonNull(order) && order.equals(DESC_ORDER)) {
                Collections.sort(tests, comparatorMap.get(parameter).reversed());
            }
        }
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

    private static class CompareByComplexityId implements Comparator<Test>, Serializable {


        private static final long serialVersionUID = 2334971618951515480L;

        public int compare(Test test1, Test test2) {
            return test1.getComplexityId() - (test2.getComplexityId());
        }
    }

    private static class CompareByQuestionNumber implements Comparator<Test>, Serializable {

        private static final long serialVersionUID = -8246503531662910478L;

        private QuestionService questionService = new QuestionServiceImpl();

        public int compare(Test test1, Test test2) {
            int questionNumber1 = questionService.findByTestId(test1.getId()).size();
            int questionNumber2 = questionService.findByTestId(test2.getId()).size();
            return questionNumber1 - questionNumber2;
        }
    }


}

