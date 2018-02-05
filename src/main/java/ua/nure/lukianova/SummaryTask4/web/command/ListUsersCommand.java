package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListUsersCommand extends Command {

    private static final long serialVersionUID = -7175960445433846535L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ListUsersCommand.class);
    private UserService userService;

    public ListUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");
        request.setAttribute(Parameter.USERS, userService.findAll());

        LOGGER.debug("Command finished");
        return Path.PAGE_LIST_USERS;
    }
}

