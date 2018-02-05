package ua.nure.lukianova.SummaryTask4.web.command;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserCommand.class);
    private static final long serialVersionUID = 5669211631545203273L;
    private UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        if (StringUtils.isEmpty(request.getParameter(Parameter.USER_ID))) {
            throw new AppException("Invalid input");
        }
        long userId = Long.valueOf(request.getParameter(Parameter.USER_ID));
        userService.delete(userId);

        LOGGER.debug("Command finished");
        return Path.COMMAND_LIST_USERS;
    }
}
