<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="${language}">
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/navigation.css"/>"/>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/button.css"/>"/>
    <title>Admin</title>




</head>
<body>

<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<%@ include file="/WEB-INF/view/header.jsp" %>

<h1><fmt:message key="test.table_name"/></h1>


<table class="table table-bordered">
    <thead>
    <th><fmt:message key="id"/></th>
    <th><fmt:message key="test.name"/></th>
    <th><fmt:message key="test.subject"/></th>
    <th><fmt:message key="test.complexity"/></th>
    <th><fmt:message key="test.time"/></th>
    <th></th>
    <th></th>
    </thead>
    <tbody>
    <c:forEach items="${tests}" var="test">
        <tr>
            <td>${test.getId()}</td>
            <td>${test.getName()}</td>
            <td>${test.getSubject()}</td>
            <td>${test.getComplexity()}</td>
            <td>${test.getTime()}</td>


            <form action="controller" method="post">
                <input type="hidden" name="command" value="editTest">
                <td><button class="button" name="edit_id" value="${test.getId()}">Edit</button></td>
            </form>



            <form action="controller" method="post">
                <input type="hidden" name="command" value="deleteTest">
                <td><button class="button" name="delete_id" value="${test.getId()}">Delete</button></td>
            </form>

        </tr>
    </c:forEach>
    </tbody>
</table>


<form action="controller" method="post">
    <input type="hidden" name="command" value="addTest">
    <button class="button">Add new test</button>
</form>



</body>
</html>