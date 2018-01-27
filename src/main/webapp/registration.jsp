<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

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
    <div class="bs-component col-lg-6">
        <form method="POST" action="controller">
            <input type="hidden" name="command" value="register">
            <h2 class="form-signin-heading">Please register</h2>


            <label class="form-control-label">First Name*</label>
            <c:choose>
                <c:when test="${not errors.containsKey('firstName') && not empty firstName}">
                    <div class="form-group has-success">
                        <input type="text" class="form-control form-control-sm is-valid" name="firstName"
                               value="${firstName}">
                        <div class="valid-feedback">Success!</div>
                    </div>
                </c:when>
                <c:when test="${ empty errors &&  empty firstName}">
                    <div class="form-group">
                        <input type="text" class="form-control form-control-sm" name="firstName" placeholder="Anna">
                        <small class="form-text text-muted">First name can consists of any letters, whitespaces and "-"
                        </small>
                    </div>
                </c:when>
                <c:when test="${errors.containsKey('firstName')}">
                    <div class="form-group has-danger">
                        <input type="text" class="form-control form-control-sm is-invalid" name="firstName"
                               value="${firstName}">
                        <div class="invalid-feedback"><fmt:message key="${errors.get('firstName')}"/></div>
                    </div>
                </c:when>
            </c:choose>

            <label class="form-control-label">Last Name*</label>
            <c:choose>
                <c:when test="${not errors.containsKey('lastName') && not empty lastName}">
                    <div class="form-group has-success">
                        <input type="text" class="form-control form-control-sm is-valid" name="lastName"
                               value="${lastName}">
                        <div class="valid-feedback">Success!</div>
                    </div>
                </c:when>
                <c:when test="${ empty errors &&  empty lastName}">
                    <div class="form-group">
                        <input type="text" class="form-control form-control-sm" name="lastName" placeholder="Petrenko">
                        <small class="form-text text-muted">Last name can consists of any letters, whitespaces and "-"
                        </small>
                    </div>
                </c:when>
                <c:when test="${errors.containsKey('lastName')}">
                    <div class="form-group has-danger">
                        <input type="text" class="form-control form-control-sm is-invalid" name="lastName"
                               value="${lastName}">
                        <div class="invalid-feedback"><fmt:message key="${errors.get('lastName')}"/></div>
                    </div>
                </c:when>
            </c:choose>

            <label class="form-control-label">Login*</label>
            <c:choose>
                <c:when test="${not errors.containsKey('username') && not empty username}">
                    <div class="form-group has-success">
                        <input type="text" class="form-control form-control-sm is-valid" name="username"
                               value="${username}">
                        <div class="valid-feedback">Success!</div>
                    </div>
                </c:when>
                <c:when test="${ empty errors &&  empty username}">
                    <div class="form-group">
                        <input type="text" class="form-control form-control-sm" name="username" placeholder="myLogin">
                        <small class="form-text text-muted">Login can consists of latin letters and any numbers
                        </small>
                    </div>
                </c:when>
                <c:when test="${errors.containsKey('username')}">
                    <div class="form-group has-danger">
                        <input type="text" class="form-control form-control-sm is-invalid" name="username"
                               value="${username}">
                        <div class="invalid-feedback"><fmt:message key="${errors.get('username')}"/></div>
                    </div>
                </c:when>
            </c:choose>

            <label class="form-control-label">Password*</label>
            <c:choose>
                <c:when test="${not errors.containsKey('password') && not empty password}">
                    <div class="form-group has-success">
                        <input type="password" class="form-control form-control-sm is-valid" name="password"
                               value="${password}">
                        <div class="valid-feedback">Success!</div>
                    </div>
                </c:when>
                <c:when test="${ empty errors &&  empty password}">
                    <div class="form-group">
                        <input type="password" class="form-control form-control-sm" name="password"
                               placeholder="Password">
                        <small class="form-text text-muted">Password must be more then 6 and less the 12 symbols
                        </small>
                    </div>
                </c:when>
                <c:when test="${errors.containsKey('password')}">
                    <div class="form-group has-danger">
                        <input type="password" class="form-control form-control-sm is-invalid" name="password"
                               value="${password}">
                        <div class="invalid-feedback"><fmt:message key="${errors.get('password')}"/></div>
                    </div>
                </c:when>
            </c:choose>

            <label class="form-control-label">Re-enter password*</label>
            <c:choose>
                <c:when test="${not errors.containsKey('reenterPassword') && not empty reenterPassword}">
                    <div class="form-group has-success">
                        <input type="password" class="form-control form-control-sm is-valid" name="reenterPassword"
                               value="${reenterPassword}">
                        <div class="valid-feedback">Success!</div>
                    </div>
                </c:when>
                <c:when test="${ empty errors &&  empty reenterPassword}">
                    <div class="form-group">
                        <input type="password" class="form-control form-control-sm" name="reenterPassword"
                               placeholder="Password">
                        <small class="form-text text-muted">Password must be more then 6 and less the 12 symbols
                        </small>
                    </div>
                </c:when>
                <c:when test="${errors.containsKey('reenterPassword')}">
                    <div class="form-group has-danger">
                        <input type="password" class="form-control form-control-sm is-invalid" name="reenterPassword"
                               value="${reenterPassword}">
                        <div class="invalid-feedback"><fmt:message key="${errors.get('reenterPassword')}"/></div>
                    </div>
                </c:when>
            </c:choose>
            <div class="form-check"
                    <c:if test="${not 'admin'.equals(userRole.toString())}"> style="display: none"
                    </c:if>>
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="role" value="1" checked="checked">
                    Student
                </label><br>
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="role" value="0">
                    Admin
                </label>
            </div>


            <br>
            <button class="btn btn-primary btn-lg" type="submit">Register</button>

            <c:if test="${not 'admin'.equals(userRole.toString())}">
                <a href="login.jsp">I'm already registered</a>
            </c:if>


        </form>
    </div>
</div>

</body>
</html>
