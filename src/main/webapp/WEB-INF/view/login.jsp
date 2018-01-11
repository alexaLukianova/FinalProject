<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/loginStyle.css"/>"/>
</head>
<body>
<div class="wrapper">
    <%--<form method="POST" action="j_security_check">--%>
    <form class="form-signin" method="POST" action="/login">
        <h2 class="form-signin-heading">Please login</h2>
        <input class="form-control" type="text" name="j_username" size="15" placeholder="username"/>
        <input class="form-control" type="password" name="j_password" size="15" placeholder="password"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
    </form>
</div>

</body>
</html>
