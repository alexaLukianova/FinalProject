<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">
<c:set var="title" value="error" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>


<div class="container">
    <br>

    <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
    <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
    <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

    <h4>The following error occurred:</h4>
    ${errorMessage.getMessage()}


    <c:if test="${not empty exception}">
        <ul>
            <li>Exception: <c:out value="${requestScope['javax.servlet.error.exception']}"/></li>
            <li>Exception type: <c:out value="${requestScope['javax.servlet.error.exception_type']}"/></li>
            <li>Exception message: <c:out value="${requestScope['javax.servlet.error.message']}"/></li>
            <li>Request URI: <c:out value="${requestScope['javax.servlet.error.request_uri']}"/></li>
            <li>Servlet name: <c:out value="${requestScope['javax.servlet.error.servlet_name']}"/></li>
            <li>Status code: <c:out value="${requestScope['javax.servlet.error.status_code']}"/></li>
            <li>Stack trace:
                <pre>${pageContext.out.flush()}${exception.printStackTrace(pageContext.response.writer)}</pre>
            </li>
        </ul>
    </c:if>


</div>


</body>
</html>
