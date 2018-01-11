<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Add test</title>
</head>
<body>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="localization" var="lang"/>

<form method="POST" action="/question_add">
    <h2>Add necessary information</h2>

    <c:forEach var="i" begin="1" end="${q}">

        <label>Question text:</label>
        <input type="text" name="new_question" size="50" placeholder="Question"/><br>
        <c:forEach var="i" begin="1" end="${a}">
            <label>Answer text <c:out value="${i}"/>:</label>
            <input type="text" name="new_answer1" size="50" placeholder="Answer"/>
            <input type="checkbox" name="correct1" value="is_correct"/><br>
        </c:forEach>
        <br/>
        <br/>
    </c:forEach>


    <button type="submit">Add</button>
</form>

</body>
</html>
