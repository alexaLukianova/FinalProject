<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">
<head>
    <title>Login</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/loginStyle.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/navigation.css"/>"/>
</head>
<body>

<div class="topnav">

    <div class="lang-selector">
        <form class="form-group" action="/login.jsp" method="get">
            <select class="form-control" id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
            </select>
        </form>
    </div>
</div>

<div class="wrapper">
    <form class="form-signin" id="login_form" method="POST" action="controller">
        <input type="hidden" name="command" value="login">
        <h2 class="form-signin-heading"><fmt:message key="text.please_login"/></h2>
        <input class="form-control" type="text" name="username" placeholder="username"/>
        <input class="form-control" type="password" name="password" placeholder="password"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="buttom.login"/></button>
        <a href="registration.jsp"><fmt:message key="text.register"/></a>
    </form>

</div>

</body>
</html>
