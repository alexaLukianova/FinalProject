<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:set var="language" value="${not empty param.language ? param.language :
not empty language ? language : en}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization"/>

<html lang="${language}">
<head>
</head>
<body>

<form method="POST" action="/logout">
    <button type="submit">Logout</button>
</form>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
    </select>
</form>


</body>
</html>
