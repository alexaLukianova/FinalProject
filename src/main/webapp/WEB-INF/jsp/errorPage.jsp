<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">
<c:set var="title" value="error" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<ct:navigation showMenu="false" hidden="false"/>

<div class="container">
    <br>

    <h2 class="error">
        The following error occurred
    </h2>

    <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
    <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
    <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

    <c:if test="${not empty code}">
        <h3>Error code: ${code}</h3>
    </c:if>

    <c:if test="${not empty message}">
        <h3>${message}</h3>
    </c:if>

    <c:if test="${not empty exception}">
        <%--<% exception.printStackTrace(new PrintWriter(out)); %>--%>
    </c:if>

    <c:if test="${not empty requestScope.errorMessage}">
        <h3>${requestScope.errorMessage}</h3>
    </c:if>


</div>


</body>
</html>
