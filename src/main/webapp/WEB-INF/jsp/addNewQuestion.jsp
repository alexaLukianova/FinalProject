<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">

<c:set var="title" value="new question" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<ct:navigation showMenu="false" hidden="true"/>

<div class="container">
    <div class="bs-component col-lg-6">
        <h2 id="top" class="form-signin-heading">Add necessary information</h2>
        <form class="questionForm" method="post" action="controller">
            <input type="hidden" name="command" value="saveQuestion">
            <input type="hidden" name="testId" value="${testId}">

            <label class="form-control-label">Question</label>


            <ct:show text="${question}" condition="${ not empty errors}" name="question" showErrors="true"/>


            <label class="form-control-label">Answers</label>
            <c:forEach var="j" begin="1" end="${answersNumber}">
                <div>
                    <input type="checkbox" name="correct" value="${j}"
                           <c:if test="${answer.correct}">checked="checked"</c:if>>


                    <ct:show text="${answer.text}"
                             condition="${errors.containsKey('answer') || errors.containsKey('correct')}"
                             name="answer"
                             showErrors="false"/>
                </div>
            </c:forEach>

            <button class="btn btn-success" type="submit">Save</button>
            <br><br>

        </form>

        <div id="bottomBtns">
            <form method="get" action="controller">
                <input type="hidden" name="command" value="showEditForm">
                <input type="hidden" name="testId" value="${testId}">
                <input type="hidden" name="question" value="">
                <button class="btn btn-secondary" type="submit">Cancel</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>















