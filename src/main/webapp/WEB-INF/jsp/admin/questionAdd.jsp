<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Add test</title>
</head>
<body>

<h2>Add necessary information</h2>

<div class="container">
    <form method="post" action="controller">
        <input type="hidden" name="command" value="saveTest">

        <c:forEach var="i" begin="1" end="${sessionScope.questionsNumber}">


            <label for="text">Question #${i}</label>
            <input type="text" id="text" name="question${i}" placeholder="Text of text" size="50"><br>



        <c:forEach var="j" begin="1" end="${sessionScope.answersNumber}">

            <label for="answer">Answer #${j}</label>
            <input type="text" id="answer" name="answer${i}${j}" placeholder="Text of answer" size="50">
            <input type="checkbox" id="answer" name="correct${i}${j}" value="is_correct"/><br>

        </c:forEach>
        <br/>
        <br/>
        </c:forEach>


        <button class="button" type="submit">Save</button>
    </form>
</div>

</body>
</html>
