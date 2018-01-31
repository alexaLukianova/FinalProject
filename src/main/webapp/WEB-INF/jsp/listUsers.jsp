<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>


<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<div class="container ">
    <h1 align="center"><fmt:message key="user.table_name"/></h1>

    <table  class="table table-striped table-sm">
        <thead>

        <th><fmt:message key="user.first_name"/></th>
        <th><fmt:message key="user.last_name"/></th>
        <th><fmt:message key="user.username"/></th>
        <th>Role</th>
        <th></th>
        <th></th>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr <c:if test="${sessionScope.user.username eq user.username}">class="table-warning"</c:if>>

                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.username}</td>
                <td><c:choose>
                    <c:when test="${userRole.ordinal() eq user.roleId}">
                        ADMIN
                    </c:when>
                    <c:otherwise>
                        STUDENT
                    </c:otherwise>
                </c:choose>


                </td>
                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="lockUser">
                        <button class="btn btn-warning btn-md" name="login" value="${user.username}"
                                <c:if test="${sessionScope.user.username eq user.username}">disabled</c:if>>
                            <c:choose>
                                <c:when test="${user.locked}">
                                    Unlock
                                </c:when>
                                <c:otherwise>
                                    Lock
                                </c:otherwise>
                            </c:choose>

                        </button>
                    </form>
                </td>

                <td>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="deleteUser">
                        <button class="btn btn-danger btn-md" name="userId" value="${user.id}"
                                <c:if test="${sessionScope.user.username eq user.username}">disabled</c:if>>Delete
                        </button>
                    </form>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form id="bottomLast" action="/registration.jsp" method="get">
        <button class="btn btn-primary btn-md" >Add new user
        </button>
    </form>
</div>


</body>
</html>