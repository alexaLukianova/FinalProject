package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.db.bean.UserValidatorBean;
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
import java.util.Map;
import java.util.Objects;

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

        UserValidatorBean userValidatorBean = new UserValidatorBean();
        userValidatorBean.setFirstName(request.getParameter("firstName").trim());
        userValidatorBean.setLastName(request.getParameter("lastName").trim());
        userValidatorBean.setUsername(request.getParameter("username").trim());
        userValidatorBean.setPassword(request.getParameter("password").trim());
        userValidatorBean.setPassword(request.getParameter("password").trim());
        userValidatorBean.setReenterPassword(request.getParameter("reenterPassword").trim());
        userValidatorBean.setRoleId(Integer.valueOf(request.getParameter("role")));



        Map<String, String> errors = validator.validate(userValidatorBean);

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("username", userValidatorBean.getUsername());
            request.setAttribute("firstName", userValidatorBean.getFirstName());
            request.setAttribute("lastName", userValidatorBean.getLastName());
            request.setAttribute("password", userValidatorBean.getPassword());
            request.setAttribute("reenterPassword", userValidatorBean.getReenterPassword());

            return Path.PAGE_REGISTRATION;
        }

        getUserService().create(userValidatorBean);
        User user = getUserService().findByLogin(userValidatorBean.getUsername());


        HttpSession session = request.getSession();


        if (Objects.nonNull(session.getAttribute("userRole"))) {
            return Path.COMMAND_LIST_USERS;
        }

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
