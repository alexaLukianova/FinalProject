<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>


<%@ include file="/WEB-INF/jspf/navigation.jspf" %>


<div class="row">
    <div class="col-sm-1 ">

        <%@ include file="/WEB-INF/jspf/header.jspf" %>

    </div>
    <div class="col-sm-10">


        <h1 align="center"><fmt:message key="test.table_name"/></h1>

        <table class="table table-striped">
            <thead>
            <th><fmt:message key="id"/></th>
            <th><fmt:message key="test.name"/></th>
            <th><fmt:message key="test.subject"/></th>
            <th><fmt:message key="test.complexity"/></th>
            <th><fmt:message key="test.time"/></th>
            <th colspan="2"></th>

            </thead>
            <tbody>
            <c:forEach items="${tests}" var="test">
                <tr>
                    <td>${test.id}</td>
                    <td>${test.name}</td>
                    <td>${test.subject}</td>
                    <td>${test.complexity}</td>
                    <td>${test.time}</td>

                    <c:set var="role" scope="session" value="${userRole.toString()}"/>
                    <c:choose>
                        <c:when test="${'admin'.equals(role)}">
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="editTest">
                                    <button class="btn btn-warning btn-md" name="test_id" value="${test.id}"
                                    >View
                                    </button>
                                </form>
                            </td>
                            <td>

                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="deleteTest">
                                    <button class="btn btn-danger btn-md" name="delete_id" value="${test.id}">

                                        Delete
                                    </button>
                                </form>
                            </td>

                        </c:when>


                        <c:otherwise>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="command" value="runTest">
                                    <button class="btn btn-primary btn-md" name="test_id" value="${test.id}">Run</button>
                                </form>
                            </td>
                            <td></td>
                        </c:otherwise>
                    </c:choose>


                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form action="controller" method="post">
            <input type="hidden" name="command" value="addTest">
            <button class="btn btn-info btn-lg">Add new test</button>
        </form>

    </div>
    <div class="col-sm-1 "></div>
</div>


</body>
</html>