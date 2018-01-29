package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.Fields;
import ua.nure.lukianova.SummaryTask4.db.entity.Entity;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class TestAddCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandContainer.class);
    private static final long serialVersionUID = 4120891911766719032L;



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        System.out.println(request.getCharacterEncoding());
        System.out.println(response.getCharacterEncoding());

        return Path.PAGE_EDIT_TEST;
    }




}

