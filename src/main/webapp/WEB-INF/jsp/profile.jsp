<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>

<body>

        <div class="page-wrap">

            <%@ include file="/WEB-INF/jspf/navigation.jspf" %>
            <div class="col-sm-1 "></div>
            <div class="col-sm-10" style="padding-left:16px">
                <h2>Hello, ${userRole.toString()}!!!</h2>
                <p>Your profile:</p>
                <label>First Name: ${user.firstName}</label><br>
                <label>Last Name: ${user.lastName} </label><br>
                <label>Login: ${user.username} </label><br>

                <c:if test="${not empty userProgress}">
                    <h1 align="center">Your results</h1>
                    <table class="table table-striped">
                        <thead>
                        <th>Test</th>
                        <th>Subject</th>
                        <th>Score</th>
                        </thead>
                        <tbody>
                        <c:forEach items="${userProgress}" var="progress">
                            <tr>
                                <td>${progress.testName}</td>
                                <td>${progress.testSubject}</td>
                                <td>${progress.result}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <div class="col-sm-1 ">
                <%@ include file="/WEB-INF/jspf/header.jspf" %>
            </div>



        </div>

        <footer class="site-footer">
            <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        </footer>

</body>
</html>