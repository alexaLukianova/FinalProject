package ua.nure.lukianova.SummaryTask4.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterConnect implements Filter {

    private FilterConfig filterConfig = null;
    private boolean isCacheDisable = false;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
        isCacheDisable = "true".equals(filterConfig.getInitParameter("active").toLowerCase());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException,
            ServletException {
        if (isCacheDisable) {
            //TODO no cache for definite pages, filterConfig in property files
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
            httpResponse.setDateHeader("Expires", 0); // Proxies.
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
