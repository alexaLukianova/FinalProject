<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>


<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<div class="col-sm-1 "></div>
<div class="col-sm-10 ">
    <h1 align="center"><fmt:message key="user.table_name"/></h1>

    <table class="table table-striped">
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
            <tr>

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
                        <button class="btn btn-warning btn-md" name="user_username" value="${user.username}"
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
                                <c:if test="${sessionScope.user.username.equals(user.username)}">disabled</c:if>>Delete
                        </button>
                    </form>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="controller" method="post">
        <input type="hidden" name="command" value="addUser">
        <button class="btn btn-primary btn-md" name="userId" value="${user.id}">Add new user
        </button>
    </form>
</div>
<div class="col-sm-1 ">

</div>


</body>
</html>