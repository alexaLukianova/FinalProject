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

        <form class="questionForm" method="post" action="controller">
            <input type="hidden" name="command" value="saveQuestion">
            <input type="hidden" name="testId" value="${testId}">
            <input type="hidden" name="add" value="${true}">

            <label class="form-control-label">Question</label>


            <c:choose>
                <c:when test="${not empty errors}">

                    <div class="form-group has-danger">
                        <input type="text" class="form-control form-control-sm is-invalid" name="question"
                               value="${question}">

                        <c:if test="${errors.containsKey('question')}">
                            <div class="invalid-feedback"><fmt:message key="${errors.get('question')}"/></div>
                        </c:if>
                        <c:if test="${errors.containsKey('answer')}">
                            <div class="invalid-feedback"><fmt:message key="${errors.get('answer')}"/></div>
                        </c:if>
                        <c:if test="${errors.containsKey('correct')}">
                            <div class="invalid-feedback"><fmt:message key="${errors.get('correct')}"/></div>
                        </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="form-group has-success">
                        <input type="text" class="form-control form-control-sm is-valid" name="question"
                               value="${question}" maxlength="255">
                    </div>
                </c:otherwise>
            </c:choose>


            <label class="form-control-label">Answers</label>
            <c:forEach var="j" begin="1" end="${answersNumber}">
                <input type="hidden" name="answersNumber" value="${answersNumber}">

                <div>
                    <input type="checkbox" name="correct" value="${j}"
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

            <button class="btn btn-success" type="submit">Save</button>
            <br><br>

        </form>

        <div id="bottomBtns">
            <form method="get" action="controller">
                <input type="hidden" name="command" value="showEditForm">
                <input type="hidden" name="testId" value="${testId}">
                <button class="btn btn-secondary" type="submit">Cancel</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>















