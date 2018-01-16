<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="${language}">
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/navigation.css"/>"/>

    <title>Admin</title>

</head>
<body>

<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<div style="padding-left:16px">
    <h2>Hello, admin!!!</h2>
    <p>You are welcome!!</p>
</div>

</body>
</html>