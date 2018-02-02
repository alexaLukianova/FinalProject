<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>


<html lang="${language}">

<c:set var="title" value="edit" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>


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

            <c:choose>
                <c:when test="${empty testId}">
                    <h2 id="top" class="form-signin-heading"><fmt:message key="text.add_necessary_information"/></h2>
                </c:when>
                <c:otherwise>
                    <h2 id="top" class="form-signin-heading"><fmt:message key="text.update_necessary_information"/></h2>
                </c:otherwise>
            </c:choose>


            <label class="form-control-label"><fmt:message key="test.name"/></label>
            <ct:validateInput errors="${errors}" value="${test.name}" name="name" placeholder="Topic">
                <fmt:message key="${errors.get('name')}"/>
            </ct:validateInput>

            <label class="form-control-label"><fmt:message key="test.subject"/></label>
            <ct:validateInput errors="${errors}" value="${test.subject}" name="subject" placeholder="Subject">
                <fmt:message key="${errors.get('subject')}"/>
            </ct:validateInput>

            <label class="form-control-label"><fmt:message key="test.complexity"/></label>
            <div class="form-check">
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="complexityId" value="0"
                           <c:if test="${test.complexityId eq 0}">checked="checked"</c:if>
                           <c:if test="${empty testId}">checked="checked"</c:if>>
                    <fmt:message key="test.complexity.easy"/>
                </label><br>
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="complexityId" value="1"
                           <c:if test="${test.complexityId eq 1}">checked="checked"</c:if>>
                    <fmt:message key="test.complexity.medium"/>
                </label><br>
                <label class="form-check-label">
                    <input type="radio" class="form-check-input" name="complexityId" value="2"
                           <c:if test="${test.complexityId eq 2}">checked="checked"</c:if>>
                    <fmt:message key="test.complexity.hard"/>
                </label>
            </div>

            <label class="form-control-label"><fmt:message key="test.time_min"/></label>
            <ct:validateInput errors="${errors}" value="${test.duration}" name="duration" placeholder="10">
                <fmt:message key="${errors.get('duration')}"/>
            </ct:validateInput>

            <button class="btn btn-info" type="reset"><fmt:message key="button.reset"/></button>
            <button class="btn btn-success" type="submit">
                <c:choose>
                    <c:when test="${empty testId}">
                        <fmt:message key="button.save"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="button.update"/>
                    </c:otherwise>
                </c:choose>
            </button>
            <hr class="my-6">
        </form>

        <c:if test="${ empty errors && not empty test}">
            <h3 class="form-signin-heading"><fmt:message key="questions"/></h3>
        </c:if>
        <c:forEach var="map" items="${questAnsMap}" varStatus="questionCount">
            <div class="d-inline">
                <label class="form-control-label"><fmt:message key="question"/> #${questionCount.count}</label>
                <form id="deleteForm" action="controller" method="post">
                    <input type="hidden" name="command" value="deleteQuestion">
                    <input type="hidden" name="testId" value="${test.id}">
                    <button class="btn btn-outline-warning btn-sm" name="questionId" value="${map.key.id}">
                        <fmt:message key="button.delete"/>
                    </button>
                </form>
            </div>

            <form class="questionForm" method="post" action="controller">
                <input type="hidden" name="command" value="updateQuestionForm">
                <input type="hidden" name="testId" value="${test.id}">
                <input type="hidden" name="questionId" value="${map.key.id}">

                <c:choose>
                    <c:when test="${ map.key.id eq questionId}">
                        <ct:validateInput errors="${errors}" value="${map.key.text}" name="question">
                        </ct:validateInput></c:when>
                    <c:otherwise>
                        <ct:validateInput value="${map.key.text}" name="question">
                        </ct:validateInput>
                    </c:otherwise>
                </c:choose>


                <c:if test="${ map.key.id eq questionId}">
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

                </c:if>


                <br> <label class="form-control-label"><fmt:message key="answers"/></label>
                <c:forEach var="answer" items="${map.value}" varStatus="answerCount">
                    <input type="hidden" name="answerId" value="${answer.id}">

                    <div>
                        <input type="checkbox" name="correct" value="${answer.id}"
                               <c:if test="${answer.correct}">checked="checked"</c:if>>

                        <c:choose>
                            <c:when test="${ map.key.id eq questionId}">
                                <ct:validateInput errors="${errors}" value="${answer.text}" name="answer">
                                </ct:validateInput></c:when>
                            <c:otherwise>
                                <ct:validateInput value="${answer.text}" name="answer">
                                </ct:validateInput>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </c:forEach>
                <button class="btn btn-info" type="reset"><fmt:message key="button.reset"/></button>
                <button class="btn btn-success" type="submit"><fmt:message key="button.update"/></button>

            </form>
            <hr class="my-6">
        </c:forEach>

        <c:if test="${ empty errors && not empty test}">
            <div id="bottomBtns">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="showSaveForm">
                    <input type="hidden" name="testId" value="${test.id}">
                    <input type="hidden" name="answersNumber" value="${answersNumber}">
                    <button class="btn btn-warning" type="submit"><fmt:message key="button.add_question"/></button>
                </form>


                <a href="#top"><fmt:message key="text.back_to_top"/></a>
            </div>
        </c:if>

        <div id="bottomBtns">
            <form method="get" action="controller">
                <input type="hidden" name="command" value="listTests">
                <button class="btn btn-secondary" type="submit"><fmt:message key="button.return"/></button>
            </form>
        </div>

    </div>
</div>


</body>
</html>


