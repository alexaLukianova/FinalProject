<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">

<c:set var="title" value="tests" scope="page"/>
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
        <th><fmt:message key="text.question_number"/></th>
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
                            <fmt:message key="test.complexity.easy"/>
                        </c:when>
                        <c:when test="${test.complexityId eq 1}">
                            <fmt:message key="test.complexity.medium"/>
                        </c:when>
                        <c:otherwise><fmt:message key="test.complexity.hard"/></c:otherwise>
                    </c:choose>

                </td>
                <td>${test.duration}</td>
                <td>${questionsCount.get(test.id)}</td>
                <c:set var="role" scope="session" value="${userRole.toString()}"/>
                <c:choose>
                    <c:when test="${'admin'.equals(role)}">
                        <td>
                            <form action="controller" method="get">
                                <input type="hidden" name="command" value="showEditForm">
                                <button class="btn btn-success btn-md" name="testId" value="${test.id}"
                                ><fmt:message key="button.view"/>
                                </button>
                            </form>
                        </td>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="deleteTest">
                                <button class="btn btn-danger btn-md" name="testId" value="${test.id}">
                                    <fmt:message key="button.delete"/>
                                </button>
                            </form>
                        </td>

                    </c:when>


                    <c:otherwise>
                        <td>
                            <form action="controller" method="get">
                                <input type="hidden" name="command" value="runTest">
                                <button class="btn btn-warning btn-md " name="testId" value="${test.id}"
                                ><fmt:message key="button.run"/>
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
            <form class="form-inline my-2 my-lg-0" action="controller" method="get">
                <ul class="navbar-nav mr-auto">


                    <input type="hidden" name="command" value="listTests">
                    <li class="nav-item active">
                        <button class="btn btn-secondary my-2 my-sm-0" type="submit">
                            <fmt:message key="button.sort"/></button>
                    </li>
                    <li class="nav-item">
                        <div id="bottomSides" class="form-group">
                            <select class="form-control" name="parameter">
                                <option value="name"><fmt:message key="text.topic"/></option>
                                <option value="duration"><fmt:message key="text.duration"/></option>
                                <option value="subject"><fmt:message key="text.subject"/></option>
                                <option value="questionsCount"><fmt:message key="text.question_quantity"/></option>
                                <option value="complexityId"><fmt:message key="text.complexity"/></option>
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
                                <option value="desc"><fmt:message key="text.decs"/></option>
                                <option value="acs"><fmt:message key="text.acs"/></option>
                            </select>
                        </div>
                    </li>


                </ul>
            </form>


            <form id="addButton" class="form-inline my-2 my-lg-0" action="controller" method="get">
                <input class="form-control mr-sm-2" type="hidden" name="command" value="addTest">
                <c:if test="${'admin'.equals(role)}">
                    <button class="btn btn-warning btn-block " type="submit"><fmt:message key="button.add_new_test"/></button>
                </c:if>
            </form>


        </div>
    </nav>

</div>


</body>
</html>