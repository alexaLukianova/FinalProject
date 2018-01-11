<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<html lang="${language}">
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <title>Admin</title>
</head>
<body>

<%@ include file = "header.jsp" %>

<h1><fmt:message key = "user.table_name" /></h1>
<div class="table table-striped">
    <table class="table table-bordered">
        <thead>
        <th><fmt:message key = "id" /></th>
        <th><fmt:message key = "user.first_name" /></th>
        <th><fmt:message key = "user.last_name" /></th>
        <th><fmt:message key = "user.username" /></th>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getUsername()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<a href="/test">All tests</a>


</body>
</html>