<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<h1><fmt:message key="user.table_name"/></h1>

<table class="table table-bordered">
    <thead>
    <th><fmt:message key="id"/></th>
    <th><fmt:message key="user.first_name"/></th>
    <th><fmt:message key="user.last_name"/></th>
    <th><fmt:message key="user.username"/></th>
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

</body>
</html>