package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Path;
import ua.nure.lukianova.SummaryTask4.web.validator.AddUserValidator;
import ua.nure.lukianova.SummaryTask4.web.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RegisterCommand extends Command {

    private static final long serialVersionUID = 8266438385891590070L;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCommand.class);
    private Validator validator;

    public RegisterCommand() {
        this.validator = new AddUserValidator();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOGGER.debug("Command starts");

        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setRoleId(Role.STUDENT.ordinal());

        List<String> errors = validator.validate(user);

        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
           return Path.PAGE_REGISTRATION;
        }




        getUserService().create(user);
        user = getUserService().findByLogin(user.getUsername());

        HttpSession session = request.getSession();


        String forward = Path.COMMAND_SHOW_PROFILE;

        Role userRole = Role.getRole(user);

        session.setAttribute("user", user);
        LOGGER.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", userRole);
        LOGGER.trace("Set the session attribute: userRole --> " + userRole);

        LOGGER.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        LOGGER.debug("Command finished");

        return forward;
    }
}
