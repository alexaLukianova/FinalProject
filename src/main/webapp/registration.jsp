<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>

</head>
<body>
<div class="wrapper">

    <form method="POST" action="controller">
        <input type="hidden" name="command" value="register">
        <h2 class="form-signin-heading">Please register</h2>

        <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName"><br>
        <label name="errorFirstName" value="${errorFirstName}"></label><br>

        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" name="lastName"><br>
        <label name="errorLastName" value="${errorLastName}"></label>

       <br> <label for="username">Login</label>
        <input type="text" id="username" name="username">
        <c:if test="${not empty errors}"><label name="errorUsername">${errors[0]}</label></c:if>


        <br><label for="password">Password</label>
        <input type="text" id="password" name="password"><br>
        <label name="errorPassword" value="${errorPassword}"></label><br>

        <label for="passwordCheck">Repeat password:</label>
        <input type="text" id="passwordCheck" name="passwordCheck"><br>
        <label name="errorPasswordCheck" value="${errorPasswordCheck}"></label><br>


        <button class="btn btn-primary btn-md" type="submit">Register</button>

    </form>
</div>

</body>
</html>
