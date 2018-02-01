<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@taglib prefix="ct" uri="/WEB-INF/customTag.tld" %>


<head>

    <title>
        ${title}
    </title>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/navigation.css"/>"/>
    <%--<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>--%>


</head>
<body>

<div class="topnav">
    <div class="lang-selector">
        <form class="form-group" action="/registration.jsp" method="get">
            <select class="form-control" id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
            </select>
        </form>
    </div>
</div>


<div class="container">


    <div class="row">
        <div class="bs-component col-lg-6">
            <br>

            <form method="POST" action="controller">
                <input type="hidden" name="command" value="register">
                <h2 class="form-signin-heading">Please register</h2>

                <ct:validateInput errors="${errors}" label="First Name" value="${firstName}" name="firstName"
                                  placeholder="Anna">
                    <fmt:message key="${errors.get('firstName')}"/>
                </ct:validateInput>

                <ct:validateInput errors="${errors}" label="Last Name" value="${lastName}" name="lastName"
                                  placeholder="Petrenko">
                    <fmt:message key="${errors.get('lastName')}"/>
                </ct:validateInput>

                <ct:validateInput errors="${errors}" label="Login" value="${username}" name="username"
                                  placeholder="login">
                    <fmt:message key="${errors.get('username')}"/>
                </ct:validateInput>

                <ct:validateInput errors="${errors}" label="Password" value="${password}" name="password"
                                  placeholder="password" passwordType="true">
                    <fmt:message key="${errors.get('password')}"/>
                </ct:validateInput>

                <ct:validateInput errors="${errors}" label="Re-enter password" value="${reenterPassword}"
                                  name="reenterPassword"
                                  placeholder="reenterPassword" passwordType="true">
                    <fmt:message key="${errors.get('reenterPassword')}"/>
                </ct:validateInput>


                <div class="form-check"
                        <c:if test="${not 'admin'.equals(userRole.toString())}"> style="display: none"
                        </c:if>>
                    <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="userRole" value="1" checked="checked">
                        Student
                    </label><br>
                    <label class="form-check-label">
                        <input type="radio" class="form-check-input" name="userRole" value="0">
                        Admin
                    </label>
                </div>


                <br>
                <button class="btn btn-primary btn-lg" type="submit">Register</button>

                <c:if test="${empty sessionScope.user}">
                    <a href="login.jsp">I'm already registered</a>
                </c:if>


            </form>


        </div>

        <div class="col-lg-6">
            <br>
            <br>
            <div class="alert alert-dismissible alert-warning">
                <h4 class="alert-heading">Warning!</h4>
                <p class="mb-0">First name and last name should contain only letters, whitespaces and dashes.</p>
                <p class="mb-0">Login should contain only latin letters and numbers.</p>
                <p class="mb-0">Password must be more then 6 and less the 12 symbols.</p>
                <p class="mb-0">All fields are mandatory.</p>
            </div>

            <br>

            <c:if test="${'admin'.equals(sessionScope.userRole.toString())}">
                <form method="get" action="controller">
                    <input type="hidden" name="command" value="listUsers">
                    <button class="btn btn-lg btn-outline-secondary btn-block" type="submit">Return</button>
                </form>

            </c:if>
            <c:if test="${empty sessionScope.userRole}">
                <form method="get" action="/login.jsp">
                    <button class="btn btn-lg btn-outline-secondary btn-block" type="submit">Return</button>
                </form>

            </c:if>

        </div>


    </div>


</div>

</body>
</html>
