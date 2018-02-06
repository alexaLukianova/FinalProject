package ua.nure.lukianova.SummaryTask4.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.exception.AppException;
import ua.nure.lukianova.SummaryTask4.web.command.Command;
import ua.nure.lukianova.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;


public class Controller extends HttpServlet {

    private static final long serialVersionUID = -6211102184308431495L;

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    private String path;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        path = process(request, response);

        LOGGER.debug("Go to forward address --> " + path);

        request.getRequestDispatcher(path).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        path = process(request, response);

        LOGGER.debug("Go to sendRedirect address --> " + path);

        response.sendRedirect(path);
    }


    private String process(HttpServletRequest request,
                           HttpServletResponse response) throws IOException, ServletException {

        LOGGER.debug("Controller starts");
        HttpSession session = request.getSession();
        // extract command name from the request
        String commandName = request.getParameter("command");
        LOGGER.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);

        session.setAttribute(Parameter.COMMAND, commandName);


        LOGGER.trace("Obtained command --> " + command);

        // execute command and get forward address

        String path = Path.COMMAND_ERROR;
        try {
            path = command.execute(request, response);
        } catch (AppException ex) {
            session.setAttribute(Parameter.ERROR_MESSAGE, ex);
        }
        LOGGER.trace("Forward address -->" + path);

        LOGGER.debug("Controller finished, now go to forward address --> " + path);

        return path;
    }
}
