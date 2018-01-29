<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>


<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<div class="container">
    <h1 align="center"><fmt:message key="test.table_name"/></h1>

    <table id="bottomLast" class="table table-striped table-sm">
        <thead>
        <th><fmt:message key="test.name"/></th>
        <th><fmt:message key="test.subject"/></th>
        <th><fmt:message key="test.complexity"/></th>
        <th><fmt:message key="test.duration"/></th>
        <th>Question number</th>
        <th colspan="2"></th>
        </thead>
        <tbody>
        <c:forEach var="test" items="${tests}">
            <tr>
                <td>${test.name}</td>
                <td>${test.subject}</td>
                <td>
                    <c:choose>
                        <c:when test="${test.complexityId eq 0}">
                            Easy
                        </c:when>
                        <c:when test="${test.complexityId eq 1}">
                            Medium
                        </c:when>
                        <c:otherwise>Hard</c:otherwise>
                    </c:choose>

                </td>
                <td>${test.duration}</td>
                <td>${questionsCount.get(test.id)}</td>
                <c:set var="role" scope="session" value="${userRole.toString()}"/>
                <c:choose>
                    <c:when test="${'admin'.equals(role)}">
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="editTest">
                                <button class="btn btn-success btn-md" name="testId" value="${test.id}"
                                >View
                                </button>
                            </form>
                        </td>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="deleteTest">
                                <button class="btn btn-danger btn-md" name="testId" value="${test.id}">
                                    Delete
                                </button>
                            </form>
                        </td>

                    </c:when>


                    <c:otherwise>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="runTest">
                                <button class="btn btn-warning btn-md " name="testId" value="${test.id}"
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


    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-bottom">
        <div class="collapse navbar-collapse" id="navbarColor01">
            <form class="form-inline my-2 my-lg-0" action="controller" method="post">
                <ul class="navbar-nav mr-auto">


                    <input type="hidden" name="command" value="listTests">
                    <li class="nav-item active">
                        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Sort</button>
                    </li>
                    <li class="nav-item">
                        <div id="bottomSides" class="form-group">
                            <select class="form-control" name="parameter">
                                <option value="name">topic</option>
                                <option value="duration">duration</option>
                                <option value="subject">subject</option>
                                <option value="questionsCount">question quantity</option>
                                <option value="complexityId">complexity</option>
                            </select>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div>
                            <input class="form-control mr-sm-2" type="hidden">
                        </div>
                    </li>
                    <li class="nav-item">
                        <div class="form-group">
                            <select class="form-control" name="order">
                                <option value="desc">DECS</option>
                                <option value="acs">ACS</option>
                            </select>
                        </div>
                    </li>


                </ul>
            </form>


            <form id="addButton" class="form-inline my-2 my-lg-0" action="controller" method="post">
                <input class="form-control mr-sm-2" type="hidden" name="command" value="editTest">
                <c:if test="${'admin'.equals(role)}">
                    <button class="btn btn-warning btn-block " type="submit">Add new test</button>
                </c:if>
            </form>



        </div>
    </nav>

</div>


</body>
</html>