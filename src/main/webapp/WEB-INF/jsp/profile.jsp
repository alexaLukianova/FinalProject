<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<div id="profile" class="container">
    <br>
    <div class="card border-info mb-3">
        <div class="card-header"><fmt:message key="text.hello"/>, ${userRole.toString()}!!!</div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="text.your_profile"/>:</h4>
            <p class="card-text"><fmt:message key="user.first_name"/>: ${user.firstName}</p>
            <p class="card-text"><fmt:message key="user.last_name"/>: ${user.lastName}</p>
            <p class="card-text"><fmt:message key="user.username"/>: ${user.username}</p>
        </div>
    </div>

    <c:if test="${not empty userProgress}">
        <h1 align="center"><fmt:message key="text.your_results"/></h1>
        <table id="bottomLast" class="table table-striped table-sm">
            <thead>
            <th><fmt:message key="test.name"/></th>
            <th><fmt:message key="test.subject"/></th>
            <th><fmt:message key="test.result"/> (%)</th>
            <th><fmt:message key="test.date"/></th>
            </thead>
            <tbody>
            <ct:show list="${userProgress}"></ct:show>
            </tbody>
        </table>
    </c:if>
</div>


</body>
</html>