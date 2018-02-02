<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">

<c:set var="title" value="new question" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>


<div class="container">
    <div class="bs-component col-lg-6">
        <h2 id="top" class="form-signin-heading"><fmt:message key="text.add_necessary_information"/></h2>
        <form class="questionForm" method="post" action="controller">
            <input type="hidden" name="command" value="saveQuestion">
            <input type="hidden" name="testId" value="${testId}">

            <label class="form-control-label"><fmt:message key="question"/></label>

            <ct:validateInput errors="${errors}" value="${question}" name="question">
            </ct:validateInput>

            <small class="text-danger">
                <c:if test="${errors.containsKey('question')}">
                    <fmt:message key="${errors.get('question')}"/><br>
                </c:if>
                <c:if test="${errors.containsKey('answer')}">
                    <fmt:message key="${errors.get('answer')}"/><br>
                </c:if>
                <c:if test="${errors.containsKey('correct')}">
                    <fmt:message key="${errors.get('correct')}"/><br>
                </c:if>
            </small>


            <label class="form-control-label"><fmt:message key="answers"/></label>
            <c:forEach var="j" begin="1" end="${answersNumber}">
                <div>
                    <input type="checkbox" name="correct" value="${j}"
                           <c:if test="${answer.correct}">checked="checked"</c:if>>

                    <ct:validateInput errors="${errors}" value="${answer.text}" name="answer">
                    </ct:validateInput>


                </div>

            </c:forEach>

            <button class="btn btn-success" type="submit"><fmt:message key="button.save"/></button>
            <br><br>

        </form>

        <div id="bottomBtns">
            <form method="get" action="controller">
                <input type="hidden" name="command" value="showEditForm">
                <input type="hidden" name="testId" value="${testId}">
                <input type="hidden" name="question" value="">
                <button class="btn btn-secondary" type="submit"><fmt:message key="button.cancel"/></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>















