<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>


<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<div class="container">
    <h1 align="center"><fmt:message key="test.table_name"/></h1>

    <table class="table table-striped">
        <thead>
        <th><fmt:message key="test.name"/></th>
        <th><fmt:message key="test.subject"/></th>
        <th><fmt:message key="test.complexity"/></th>
        <th><fmt:message key="test.duration"/></th>
        <th colspan="2"></th>
        </thead>
        <tbody>
        <c:forEach items="${tests}" var="test">
            <tr>
                <td>${test.name}</td>
                <td>${test.subject}</td>
                <td>${test.complexityId}</td>
                <td>${test.duration}</td>
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
                                <button class="btn btn-primary btn-md " name="test_id" value="${test.id}"
                                        title="Attention!!!Each test can be run only once!">Run
                                </button>
                            </form>
                        </td>
                        <td></td>
                    </c:otherwise>
                </c:choose>


            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${'admin'.equals(role)}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="addTest">
            <button class="btn btn-info btn-lg">Add new test</button>
        </form>
    </c:if>


    <form action="controller" method="post">
        <input type="hidden" name="command" value="listTests">
        <button class="btn btn-success btn-md" name="sortBy" value="name">Sort by</button>
        <div class="form-group">
            <select class="form-control" name="order">
                <option value="decs">DECS</option>
                <option value="acs">ACS</option>
            </select>
        </div>

        <div class="form-group">
            <select class="form-control" name="field">
                <option value="name">topic</option>
                <option value="time">time</option>
                <option value="subject">subject</option>
                <option value="questions">question quantity</option>
                <option value="complexity">complexity</option>
            </select>
        </div>

    </form>
</div>


</body>
</html>