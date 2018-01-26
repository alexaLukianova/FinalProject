<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>


<body>


<%@ include file="/WEB-INF/jspf/navigation.jspf" %>

<div class="wrapper">

    <form method="POST" action="controller">
        <input type="hidden" name="command" value="register">
        <h2 class="form-signin-heading">Please register</h2>

        <br> <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName" value="${firstName}">
        <c:if test="${errors.keySet().contains('firstName')}">
            <fmt:message key="${errors.get('firstName')}"/>
        </c:if>

        <br> <label for="lastName">Last Name</label>
        <input type="text" id="lastName" name="lastName" value="${lastName}">
        <c:if test="${errors.keySet().contains('lastName')}">
            <fmt:message key="${errors.get('lastName')}"/>
        </c:if>

        <br> <label for="username">Login</label>
        <input type="text" id="username" name="username" value="${username}">
        <c:if test="${errors.keySet().contains('username')}">
            <fmt:message key="${errors.get('username')}"/>
        </c:if>

        <br> <label for="password">Password</label>
        <input type="text" id="password" name="password" value="${password}">
        <c:if test="${errors.keySet().contains('password')}">
            <fmt:message key="${errors.get('password')}"/>
        </c:if>

        <br> <label for="reenterPassword">Re-enter password</label>
        <input type="text" id="reenterPassword" name="reenterPassword" value="${reenterPassword}">
        <c:if test="${errors.keySet().contains('reenterPassword')}">
            <fmt:message key="${errors.get('reenterPassword')}"/>
        </c:if>

        <br> <input name="role" type="radio" value="student" checked>Student</p>
        <input name="role" type="radio" value="admin">Admin</p>

        <br>
        <button class="btn btn-primary btn-md" type="submit">Register</button>

    </form>
</div>

</body>
</html>
