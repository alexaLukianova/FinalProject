<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>


<form method="post" action="controller">
<label for="text">Question</label>
<input type="text" id="text" name="question" placeholder="Text of question" size="50"><br>

<c:forEach var="j" begin="1" end="${answers_number}">
    <label for="answer">Answer #${j}</label>
    <input type="text" id="answer" name="answer${j}" placeholder="Text of answer" size="50">
    <input type="checkbox" id="answer" name="correct${j}" value="is_correct"/><br>

</c:forEach>


    <input type="hidden" name="command" value="saveQuestion">
    <input type="hidden" name="test_id" value="${test_id}">
    <input type="hidden" name="answers_number" value="${answers_number}">
    <button class="button" type="submit">Save</button>

</form>

    <form method="post" action="controller">
        <input type="hidden" name="command" value="editTest">
        <input type="hidden" name="test_id" value="${test_id}">
        <button class="button" type="submit">Cancel</button>
    </form>

</body>
</html>
