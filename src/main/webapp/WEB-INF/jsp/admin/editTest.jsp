<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form id="testForm" action="controller" method="post">
    <input type="hidden" name="command" value="updateTest">
    <input type="hidden" name="test_id" value="${test.id}">

    <h2>Rewrite necessary information</h2>
    <label for="name">Topic</label>
    <input disabled type="text" id="name" name="name" value="${test.name}"></br>

    <label for="subject">Subject</label>
    <input disabled type="text" id="subject" name="subject" value="${test.subject}"></br>

    <label for="complexity">Complexity</label>
    <input disabled type="text" id="complexity" name="complexity" value=${test.complexity}></br>

    <label for="time">Time</label>
    <input disabled type="text" id="time" name="time" value=${test.time}></br>

    <button class="button" type="reset">Reset</button>
    <button class="button" type="submit">Save</button>

</form>





<button class="button" onclick="enable('testForm')">Edit</button>
<script>
    function enable(elemId) {
        var c = document.getElementById(elemId).children;


        for (var i = 0; i < c.length; i++) {
            c[i].disabled = false;
        }}


</script>


<h3>Test's questions: </h3>
</br>
<c:forEach var="i" begin="0" end="${questions.size()-1}">



    <form action="controller" method="post">
        <input type="hidden" name="command" value="updateQuestion">
        <input type="hidden" name="test_id" value="${test.id}">
        <input type="hidden" name="question_id" value="${questions[i].id}">


        <label for="text">Question #${i+1}</label>
        <input type="text" id="text" name="question" value="${questions[i].text}">

    <br>
    <c:forEach var="j" begin="0" end="${questionsAndAnswers[questions[i]].size()-1}">
        <label for="answer">Answer #${j+1}</label>
        <input type="text" id="answer" name="answer${j}" value="${questionsAndAnswers[questions[i]][j].getText()}">
        <input type="checkbox" id="answer" name="correct${j}" value="is_correct"
               <c:if test="${questionsAndAnswers[questions[i]][j].isCorrect()}">checked="checked"</c:if>> <br>
    </c:forEach>



        <button class="button" type="submit">Update</button>
    </form>


    <form action="controller" method="post">
        <input type="hidden" name="command" value="deleteQuestion">
        <input type="hidden" name="test_id" value="${test.id}">
        <button class="button" name="question_id" value="${questions[i].id}">Delete</button>
    </form>






</c:forEach>


<form action="controller" method="post">
    <input type="hidden" name="command" value="addNewQuestion">
    <input type="hidden" name="test_id" value="${test.id}">
    <input type="hidden" name="answers_number" value="${questionsAndAnswers[questions[0]].size()}">
    <button class="button" type="submit">Add question</button>
</form>


<form method="post" action="controller">
    <input type="hidden" name="command" value="listTests">
    <button class="button" type="submit">Return</button>
</form>


</body>
</html>
