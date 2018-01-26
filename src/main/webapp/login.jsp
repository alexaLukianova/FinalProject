<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/loginStyle.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/navigation.css"/>"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<div class="wrapper">
    <form class="form-signin" id="login_form" method="POST" action="controller">
        <input type="hidden" name="command" value="login">
        <h2 class="form-signin-heading">Please login</h2>
        <input class="form-control" type="text" name="username" placeholder="username"/>
        <input class="form-control" type="password" name="password" placeholder="password"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
        <a href="registration.jsp">Register</a>
    </form>

</div>

</body>
</html>
