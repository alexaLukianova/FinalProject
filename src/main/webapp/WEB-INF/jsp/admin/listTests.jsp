
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>



<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<%@ include file="/WEB-INF/jspf/header.jspf" %>


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
            <td>${test.id}</td>
            <td>${test.name}</td>
            <td>${test.subject}</td>
            <td>${test.complexity}</td>
            <td>${test.time}</td>


            <form action="controller" method="post">
                <input type="hidden" name="command" value="editTest">
                <td><button class="button" name="test_id" value="${test.id}">Edit</button></td>
            </form>



            <form action="controller" method="post">
                <input type="hidden" name="command" value="deleteTest">
                <td><button class="button" name="delete_id" value="${test.id}">Delete</button></td>
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