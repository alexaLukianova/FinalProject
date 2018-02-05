<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="registration" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

</head>
<body>


<div class="container">


    <div class="row">
        <div class="bs-component col-lg-6">
            <br>

            <form method="POST" action="controller">
                <input type="hidden" name="command" value="register">
                <h2 class="form-signin-heading"><fmt:message key="text.please_register"/></h2>

                <label class="form-control-label"><fmt:message key="user.first_name"/></label>
                <ct:validateInput errors="${errors}" value="${firstName}" name="firstName" placeholder="Anna">
                    <fmt:message key="${errors.get('firstName')}"/>
                </ct:validateInput>

                <label class="form-control-label"><fmt:message key="user.last_name"/></label>
                <ct:validateInput errors="${errors}" value="${lastName}" name="lastName" placeholder="Petrenko">
                    <fmt:message key="${errors.get('lastName')}"/>
                </ct:validateInput>

                <label class="form-control-label"><fmt:message key="user.username"/></label>
                <ct:validateInput errors="${errors}" value="${username}" name="username" placeholder="login">
                    <fmt:message key="${errors.get('username')}"/>
                </ct:validateInput>

                <label class="form-control-label"><fmt:message key="user.password"/></label>
                <ct:validateInput errors="${errors}" value="${password}" name="password"
                                  placeholder="password" passwordType="true">
                    <fmt:message key="${errors.get('password')}"/>
                </ct:validateInput>

                <label class="form-control-label"><fmt:message key="user.reenter_password"/></label>
                <ct:validateInput errors="${errors}" value="${reenterPassword}" name="reenterPassword"
                                  placeholder="password" passwordType="true">
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
                <button class="btn btn-primary btn-lg" type="submit"><fmt:message key="text.register"/></button>

                <c:if test="${empty sessionScope.user}">
                    <a href="login.jsp"><fmt:message key="text.already_registered"/> </a>
                </c:if>


            </form>


        </div>

        <div class="col-lg-6">
            <br>
            <br>
            <div class="alert alert-dismissible alert-warning">
                <h4 class="alert-heading"><fmt:message key="text.warning"/></h4>
                <p class="mb-0"><fmt:message key="text.warning.first_name"/></p>
                <p class="mb-0"><fmt:message key="text.warning.login"/></p>
                <p class="mb-0"><fmt:message key="text.warning.password"/></p>
                <p class="mb-0"><fmt:message key="text.warning.all"/></p>
            </div>

            <br>

            <c:if test="${'admin'.equals(sessionScope.userRole.toString())}">
                <form method="get" action="controller">
                    <input type="hidden" name="command" value="listUsers">
                    <button class="btn btn-lg btn-outline-secondary btn-block" type="submit">
                        <fmt:message key="button.return"/></button>
                </form>

            </c:if>
            <c:if test="${empty sessionScope.userRole}">
                <form method="get" action="/login.jsp">
                    <button class="btn btn-lg btn-outline-secondary btn-block" type="submit">
                        <fmt:message key="button.return"/></button>
                </form>

            </c:if>

        </div>


    </div>


</div>

</body>
</html>
