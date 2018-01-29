<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <title>Title</title>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/main.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/navigation.css"/>"/>
</head>
<body>



<div class="container">

    <div class="bs-component col-lg-6">
        <form method="post" action="controller">
            <input type="hidden" name="command" value="editTest">
            <input type="hidden" name="testId" value="${test.id}">
            <input type="hidden" name="changed" value="true">

            <h2 id="top" class="form-signin-heading">Update necessary information</h2>

            <label class="form-control-label">Topic</label>
            <c:choose>
                <c:when test="${errors.containsKey('name')}">
                    <div class="form-group has-danger">
                        <input type="text" class="form-control form-control-sm is-invalid" name="name"
                               value="${test.name}">
                        <div class="invalid-feedback"><fmt:message key="${errors.get('name')}"/></div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="form-group has-success">
                        <input type="text" class="form-control form-control-sm is-valid" name="name"
                               value="${test.name}" maxlength="255">
                    </div>
                </c:otherwise>
            </c:choose>

            <label class="form-control-label">Subject</label>
            <c:choose>
                <c:when test="${errors.containsKey('subject')}">
                    <div class="form-group has-danger">
                        <input type="text" class="form-control form-control-sm is-invalid" name="subject">
                        <div class="invalid-feedback"><fmt:message key="${errors.get('subject')}"/></div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="form-group has-success">
                        <input type="text" class="form-control form-control-sm is-valid" name="subject"
                               value="${test.subject}" maxlength="255">
                    </div>
                </c:otherwise>
            </c:choose>

            <label class="form-control-label">Complexity</label>
            <div class="form-check">
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="complexityId" value="0"
                           <c:if test="${test.complexityId eq 0}">checked="checked"</c:if>
                           <c:if test="${empty testId}">checked="checked"</c:if>>
                    Easy
                </label><br>
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="complexityId" value="1"
                           <c:if test="${test.complexityId eq 1}">checked="checked"</c:if>>
                    Medium
                </label><br>
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="complexityId" value="2"
                           <c:if test="${test.complexityId eq 2}">checked="checked"</c:if>>
                    Hard
                </label>
            </div>


            <label class="form-control-label">Time (min)</label>
            <c:choose>
                <c:when test="${errors.containsKey('duration')}">
                    <div class="form-group has-danger">
                        <input type="text" class="form-control form-control-sm is-invalid" name="duration"
                               value="${test.duration}">
                        <div class="invalid-feedback"><fmt:message key="${errors.get('duration')}"/></div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="form-group has-success">
                        <input type="text" class="form-control form-control-sm is-valid" name="duration"
                               value="${test.duration}" maxlength="255">
                    </div>
                </c:otherwise>
            </c:choose>


            <button class="btn btn-info" type="reset">Reset</button>
            <button class="btn btn-success" type="submit">Update</button>

            <hr class="my-6">
        </form>

        <c:if test="${ empty errors && not empty test}">
            <h3 class="form-signin-heading">Questions</h3>
        </c:if>


        <c:forEach var="map" items="${questAnsMap}" varStatus="questionCount">

            <div class="d-inline">
                <label class="form-control-label">Question #${questionCount.count}</label>

                <c:if test="${questAnsMap.keySet().size() > 1}">
                    <form id="deleteForm" action="controller" method="post">
                        <input type="hidden" name="command" value="deleteQuestion">
                        <input type="hidden" name="testId" value="${test.id}">
                        <button class="btn btn-outline-warning btn-sm" name="questionId" value="${map.key.id}">Delete
                        </button>
                    </form>
                </c:if>
            </div>


            <form class="questionForm" method="post" action="controller">
                <input type="hidden" name="command" value="editTest">
                <input type="hidden" name="testId" value="${test.id}">
                <input type="hidden" name="questionId" value="${map.key.id}">


                <c:choose>
                    <%--<c:when test="${errors.containsKey('question') && map.key.id eq questionId}">--%>
                    <c:when test="${not empty errors && not errors.containsKey('test')}">
                        <div class="form-group has-danger">
                            <input type="text" class="form-control form-control-sm is-invalid" name="question"
                                   value="${map.key.text}">
                            <c:if test="${errors.containsKey('question') && map.key.id eq questionId}">
                                <div class="invalid-feedback"><fmt:message key="${errors.get('question')}"/></div>
                            </c:if>
                            <c:if test="${errors.containsKey('answer') && map.key.id eq questionId}">
                                <div class="invalid-feedback"><fmt:message key="${errors.get('answer')}"/></div>
                            </c:if>
                            <c:if test="${errors.containsKey('correct') && map.key.id eq questionId}">
                                <div class="invalid-feedback"><fmt:message key="${errors.get('correct')}"/></div>
                            </c:if>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group has-success">
                            <input type="text" class="form-control form-control-sm is-valid" name="question"
                                   value="${map.key.text}" maxlength="255">
                        </div>
                    </c:otherwise>
                </c:choose>

                <br> <label class="form-control-label">Answers</label>
                <c:forEach var="answer" items="${map.value}" varStatus="answerCount">
                    <input type="hidden" name="answerId" value="${answer.id}">
                    <div>
                        <input type="checkbox" name="correct" value="${answer.id}"
                               <c:if test="${answer.correct}">checked="checked"</c:if>>
                        <c:choose>
                            <c:when test="${ errors.containsKey('answer')}">
                                <div class="form-group has-danger">
                                    <input type="text" class="form-control form-control-sm is-invalid" name="answer"
                                           value="${answer.text}">
                                    <div class="invalid-feedback"></div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="form-group has-success">
                                    <input type="text" class="form-control form-control-sm is-valid" name="answer"
                                           value="${answer.text}" maxlength="255">
                                    <div class="valid-feedback"></div>
                                </div>

                            </c:otherwise>

                        </c:choose>

                    </div>


                </c:forEach>

                <button class="btn btn-info" type="reset">Reset</button>
                <button class="btn btn-success" type="submit">Update</button>


            </form>

            <hr class="my-6">
        </c:forEach>


        <c:if test="${ empty errors && not empty test}">
            <div id="bottomBtns">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="saveQuestion">
                    <input type="hidden" name="testId" value="${test.id}">
                    <input type="hidden" name="answersNumber" value="${answersNumber}">
                    <button class="btn btn-warning" type="submit">Add question</button>
                </form>


                <a href="#top">Back to top</a>
            </div>
        </c:if>

        <div id="bottomBtns">
        <form method="post" action="controller">
            <input type="hidden" name="command" value="listTests">
            <button class="btn btn-secondary" type="submit">Return</button>
        </form>
        </div>

    </div>
</div>


</body>
</html>


