<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<html>
<head>
    <title>All tests</title>
</head>
<body>
<fmt:setLocale value = "ru"/>
<fmt:setBundle basename = "localization" var = "lang"/>

<h1><fmt:message key = "test.table_name" bundle = "${lang}"/></h1>
<table>
    <thead>
    <th><fmt:message key = "id" bundle = "${lang}"/></th>
    <th><fmt:message key = "test.name" bundle = "${lang}"/></th>
    <th><fmt:message key = "test.subject" bundle = "${lang}"/></th>
    <th><fmt:message key = "test.complexity" bundle = "${lang}"/></th>
    </thead>
    <tbody>
    <c:forEach items="${tests}" var="test">
        <tr>
            <td>${test.getId()}</td>
            <td>${test.getName()}</td>
            <td>${test.getSubject()}</td>
            <td>${test.getComplexity()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="/test_add">Add new test</a>

</body>
</html>
