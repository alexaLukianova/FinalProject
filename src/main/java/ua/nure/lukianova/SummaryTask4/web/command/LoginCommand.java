package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.UserService;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static ua.nure.lukianova.SummaryTask4.web.Parameter.*;
import static ua.nure.lukianova.SummaryTask4.web.Path.PAGE_ERROR_PAGE;

public class LoginCommand extends Command {

    private static final long serialVersionUID = 1778223731675825249L;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginCommand.class);
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        HttpSession session = request.getSession();


        String login = request.getParameter(USERNAME);
        LOGGER.trace("Request parameter: loging --> " + login);

        String password = request.getParameter(PASSWORD);
        if (Objects.isNull(login) || Objects.isNull(password)
                || login.isEmpty() || password.isEmpty()) {
            // throw new AppException("Login/password cannot be empty");
        }

        User user = userService.findByLogin(login);
        LOGGER.trace("Found in DB: user --> " + user);

        if (Objects.isNull(user) || !password.equals(user.getPassword())) {
            throw new AppException("Cannot find user with such login/password");
        }

        String forward = PAGE_ERROR_PAGE;
        ;
        if (user.isLocked()) {
            throw new AppException("User locked by admin");
        } else {
            Role userRole = Role.getRole(user);
            LOGGER.trace("userRole --> " + userRole);

            forward = Path.PAGE_ERROR_PAGE;

            if (userRole == Role.ADMIN) {
                forward = Path.COMMAND_SHOW_PROFILE;
            }

            if (userRole == Role.STUDENT) {
                forward = Path.COMMAND_SHOW_PROFILE;
            }

            session.setAttribute(USER, user);
            LOGGER.trace("Set the session attribute: user --> " + user);

            session.setAttribute(USER_ROLE, userRole);
            LOGGER.trace("Set the session attribute: userRole --> " + userRole);


            LOGGER.info("User " + user + " logged as " + userRole.toString().toLowerCase());

            LOGGER.debug("Command finished");
        }
        return forward;
    }
}

