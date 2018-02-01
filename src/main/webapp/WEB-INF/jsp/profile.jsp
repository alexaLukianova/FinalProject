<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html  lang="${language}">

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>

<body>


<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<div id="profile" class="container">
    <br>
    <div class="card border-info mb-3">
        <div class="card-header">Hello, ${userRole.toString()}!!!</div>
        <div class="card-body">
            <h4 class="card-title">Your profile:</h4>
            <p class="card-text">First Name: ${user.firstName}</p>
            <p class="card-text">Last Name: ${user.lastName}</p>
            <p class="card-text">Login: ${user.username}</p>
        </div>
    </div>

    <c:if test="${not empty userProgress}">
        <h1 align="center">Your results</h1>
        <table id="bottomLast" class="table table-striped table-sm">
            <thead>
            <th>Test</th>
            <th>Subject</th>
            <th>Score</th>
            <th>Date of test</th>
            </thead>
            <tbody>
            <c:forEach items="${userProgress}" var="progress">
                <tr>
                    <td>${progress.testName}</td>
                    <td>${progress.testSubject}</td>
                    <td>${progress.result}</td>
                    <td> ${progress.date}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>


</body>
</html>