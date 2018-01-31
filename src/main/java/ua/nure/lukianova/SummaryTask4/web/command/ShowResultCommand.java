package ua.nure.lukianova.SummaryTask4.web.command;

import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class ShowResultCommand extends Command {
    private static final long serialVersionUID = 2992095752648799140L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        HttpSession session = request.getSession();

        if (Objects.nonNull(session.getAttribute(Parameter.MARK))) {
            int mark = (Integer) (session.getAttribute(Parameter.MARK));
            request.setAttribute(Parameter.MARK, mark);
            session.removeAttribute(Parameter.MARK);
        }
        return Path.PAGE_MESSAGE;
    }
}
