package ua.nure.lukianova.SummaryTask4.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.db.bean.TestResultBean;
import ua.nure.lukianova.SummaryTask4.db.entity.User;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.service.ResultService;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowProfileCommand extends Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowProfileCommand.class);
    private static final long serialVersionUID = 6461429052811362341L;
    private ResultService resultService;

    public ShowProfileCommand(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameter.USER);

        if (Role.getRole(user) == Role.STUDENT) {
            List<TestResultBean> userProgress = resultService.findByUserId(user.getId());
            request.setAttribute(Parameter.USER_PROGRESS, userProgress);
        }

        return Path.PAGE_PROFILE;
    }
}
