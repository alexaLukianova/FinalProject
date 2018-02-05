<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">
<c:set var="title" value="error" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>


<div class="container">
    <br>

    <h4 class="error">
        The following error occurred
    </h4>

    <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
    <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
    <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

    <c:if test="${not empty code}">
        <h5>Error code: ${code}</h5>
    </c:if>

    <c:if test="${not empty message}">
        <h5>${message}</h5>
    </c:if>

    <c:if test="${not empty exception}">
        <c:out value="${exception.getStackTrace()}"/>

    </c:if>

    <c:if test="${not empty requestScope.errorMessage}">
        <h5>${requestScope.errorMessage}</h5>
    </c:if>


</div>


</body>
</html>
