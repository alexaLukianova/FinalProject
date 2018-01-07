<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Admin</title>
</head>
<body>
<h1>Hello, admin!</h1>

<table>
    <thead>
    <th>ID</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Username</th>
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

<p>All tests</p>
<table>
    <thead>
    <th>ID</th>
    <th>name</th>
    <th>subject</th>
    <th>complexity</th>
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







</body>
</html>