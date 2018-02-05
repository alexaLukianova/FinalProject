package ua.nure.lukianova.SummaryTask4.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.lukianova.SummaryTask4.db.Role;
import ua.nure.lukianova.SummaryTask4.web.Parameter;
import ua.nure.lukianova.SummaryTask4.web.Path;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class CommandAccessFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandAccessFilter.class);

    private Map<Role, List<String>> accessMap = new HashMap<>();
    private List<String> commons = new ArrayList<>();
    private List<String> outOfControl = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Filter initialization starts");
        accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin")));
        accessMap.put(Role.STUDENT, asList(filterConfig.getInitParameter("student")));
        LOGGER.trace("Access map --> " + accessMap);

        commons = asList(filterConfig.getInitParameter("common"));
        LOGGER.trace("Common commands --> " + commons);

        // out of control
        outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
        LOGGER.trace("Out of control commands --> " + outOfControl);

        LOGGER.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("Filter starts");

        if (accessAllowed(request)) {
            LOGGER.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            request.setAttribute(Parameter.ERROR_MESSAGE, errorMessage);
            LOGGER.trace("Set the request attribute: errorMessage --> " + errorMessage);


            request.getRequestDispatcher(Path.PAGE_ERROR_PAGE)
                    .forward(request, response);
        }
    }

    @Override
    public void destroy() {
        LOGGER.debug("Filter destruction starts");
        LOGGER.debug("Filter destruction finished");
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("command");
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }

        if (outOfControl.contains(commandName)) {
            return true;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        Role userRole = (Role) session.getAttribute("userRole");
        if (userRole == null) {
            return false;
        }

        return accessMap.get(userRole).contains(commandName)
                || commons.contains(commandName);
    }

    private List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
