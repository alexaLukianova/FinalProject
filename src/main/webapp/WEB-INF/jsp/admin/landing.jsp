<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>


<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div style="padding-left:16px">
    <h2>Hello, ${userRole.toString()}!!!</h2>
    <p>Your profile:</p>

    <label for="firstName">First Name: </label>
    <input type="text" id="firstName" name="firstName" size="50" value="${user.firstName}"><br>

    <label for="lastName">Last Name: </label>
    <input type="text" id="lastName" name="lastName" size="50" value="${user.lastName}"><br>

    <label for="username">Login: </label>
    <input type="text" id="username" name="username" size="50" value="${user.username}"><br>
</div>

<div class="col-sm-1 "></div>
<div class="col-sm-10 ">
    <h1 align="center">Your results</h1>

    <table class="table table-striped">
        <thead>
        <th>Test</th>
        <th>Subject</th>
        <th>Score</th>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="col-sm-1 "></div>

</body>
</html>