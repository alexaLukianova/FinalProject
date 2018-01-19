<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<h2>Rewrite necessary information</h2>
<label for="name">Topic</label>
<input type="text" id="name" name="test_name" value="${test_name}"></br>

<label for="subject">Subject</label>
<input type="text" id="subject" name="test_subject" value="${test_subject}"></br>

<label for="complexity">Complexity</label>
<input type="text" id="complexity" name="test_complexity" value=${test_complexity}></br>

<label for="time">Time</label>
<input type="text" id="time" name="test_time" value=${test_time}></br>


<h3>Test's questions: </h3>
</br>
<c:forEach var="i" begin="0" end="${questionList.size()-1}">
    <label for="question">Question #${i+1}</label>
    <input type="text" id="question" name="question${i}" value="${questionList[i].getQuestion()}">

<c:if test="${questionList[i].id!=null}">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="deleteQuestion">
        <td><button class="button" name="delete_question_text" value="${questionList[i].getQuestion()}">Delete</button></td>
    </form>
</c:if>



    <br>
    <c:forEach var="j" begin="0" end="${testInfo[questionList[i]].size()-1}">
        <label for="answer">Answer #${j+1}</label>
        <input type="text" id="answer" name="answer${i}${j}" value="${testInfo[questionList[i]][j].getText()}">
        <input type="checkbox" id="answer" name="correct${i}${j}"
               <c:if test="${testInfo[questionList[i]][j].isCorrect()}">checked="checked"</c:if>> <br>
    </c:forEach>
    <br>
    <br>
</c:forEach>


<form action="controller" method="post">
    <input type="hidden" name="command" value="addNewQuestion">
    <button class="button" type="submit">Add new question</button>
</form>

<form action="controller" method="post">
    <input type="hidden" name="command" value="updateTest">
<button class="button" type="submit">Update test</button>
</form>






</body>
</html>
