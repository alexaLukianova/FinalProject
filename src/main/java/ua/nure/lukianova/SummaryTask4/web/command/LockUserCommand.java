package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.LOGIN;

public class LockUserCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(LockUserCommand.class);
    private static final long serialVersionUID = -6027367205478158811L;
    private UserService userService;

    public LockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        if (StringUtils.isEmpty(request.getParameter(LOGIN))) {
            throw new AppException("Invalid input");
        }

        userService.inverseLockState(request.getParameter(LOGIN));

        LOGGER.debug("Command finished");

        return Path.COMMAND_LIST_USERS;
    }
}
