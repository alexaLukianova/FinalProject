package ua.nure.lukianova.SummaryTask4.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("Filter initialization starts");
        encoding = filterConfig.getInitParameter("encoding");
        LOGGER.trace("Encoding from web.xml --> " + encoding);
        LOGGER.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("Filter starts");

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        LOGGER.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            LOGGER.trace("Request encoding = null, set encoding --> " + encoding);
            request.setCharacterEncoding(encoding);
        }

        LOGGER.debug("Filter finished");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("Filter destruction starts");
        // no op
        LOGGER.debug("Filter destruction finished");
    }
}
