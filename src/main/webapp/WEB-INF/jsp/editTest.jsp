<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>


<html  lang="${language}">

<c:set var="title" value="edit" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<ct:navigation showMenu="false" hidden="true"/>

<div class="container">

    <div class="bs-component col-lg-6">

        <form method="post" action="controller">
            <input type="hidden" name="command"
            <c:choose>
            <c:when test="${empty testId}">
                   value="saveNewTest"
            </c:when>
            <c:otherwise>
                   value="updateTestForm"
            </c:otherwise>
            </c:choose>>
            <input type="hidden" name="testId" value="${test.id}">

            <h2 id="top" class="form-signin-heading">Update necessary information</h2>


            <ct:validateInput errors="${errors}" value="${test.name}" name="name" label="Topic" placeholder="Topic">
                <fmt:message key="${errors.get('name')}"/>
            </ct:validateInput>

            <ct:validateInput errors="${errors}" value="${test.subject}" name="subject" label="Subject"
                              placeholder="Subject">
                <fmt:message key="${errors.get('subject')}"/>
            </ct:validateInput>


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

            <ct:validateInput errors="${errors}" value="${test.duration}" name="duration" label="Time (min)"
                              placeholder="10">
                <fmt:message key="${errors.get('duration')}"/>
            </ct:validateInput>

            <button class="btn btn-info" type="reset">Reset</button>
            <button class="btn btn-success" type="submit">
                <c:choose>
                    <c:when test="${empty testId}">
                        Save
                    </c:when>
                    <c:otherwise>
                        Update
                    </c:otherwise>
                </c:choose>
            </button>
            <hr class="my-6">
        </form>

        <c:if test="${ empty errors && not empty test}">
            <h3 class="form-signin-heading">Questions</h3>
        </c:if>
        <c:forEach var="map" items="${questAnsMap}" varStatus="questionCount">
            <div class="d-inline">
                <label class="form-control-label">Question #${questionCount.count}</label>
                <form id="deleteForm" action="controller" method="post">
                    <input type="hidden" name="command" value="deleteQuestion">
                    <input type="hidden" name="testId" value="${test.id}">
                    <button class="btn btn-outline-warning btn-sm" name="questionId" value="${map.key.id}">Delete
                    </button>
                </form>
            </div>

            <form class="questionForm" method="post" action="controller">
                <input type="hidden" name="command" value="updateQuestionForm">
                <input type="hidden" name="testId" value="${test.id}">
                <input type="hidden" name="questionId" value="${map.key.id}">

                <ct:show text="${map.key.text}"
                         condition="${ not empty errors && not errors.containsKey('test') && map.key.id eq questionId}"
                         name="question"
                         showErrors="true"/>


                <br> <label class="form-control-label">Answers</label>
                <c:forEach var="answer" items="${map.value}" varStatus="answerCount">
                    <input type="hidden" name="answerId" value="${answer.id}">
                    <div>
                        <input type="checkbox" name="correct" value="${answer.id}"
                               <c:if test="${answer.correct}">checked="checked"</c:if>>

                        <ct:show text="${answer.text}"
                                 condition="${ (errors.containsKey('answer'))&& map.key.id eq questionId}"
                                 name="answer"
                                 showErrors="false"/>
                    </div>
                </c:forEach>
                <button class="btn btn-info" type="reset">Reset</button>
                <button class="btn btn-success" type="submit">Update</button>

            </form>
            <hr class="my-6">
        </c:forEach>

        <c:if test="${ empty errors && not empty test}">
            <div id="bottomBtns">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="showSaveForm">
                    <input type="hidden" name="testId" value="${test.id}">
                    <input type="hidden" name="answersNumber" value="${answersNumber}">
                    <button class="btn btn-warning" type="submit">Add question</button>
                </form>


                <a href="#top">Back to top</a>
            </div>
        </c:if>

        <div id="bottomBtns">
            <form method="get" action="controller">
                <input type="hidden" name="command" value="listTests">
                <button class="btn btn-secondary" type="submit">Return</button>
            </form>
        </div>

    </div>
</div>


</body>
</html>


