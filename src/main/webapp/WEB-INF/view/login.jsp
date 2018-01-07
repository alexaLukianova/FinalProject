<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
//TODO implement url select
<%--<form method="POST" action="j_security_check">--%>
<form method="POST" action="/username">
    <label for="username">Login:</label>
    <input id="username" type="text" name="j_username" size="15"><<br>
    <label for="password">Password:</label>
    <input id="password" type="password" name="j_password" size="15"><<br>
    <input type="submit" value="Login">
</form>
</body>
</html>
