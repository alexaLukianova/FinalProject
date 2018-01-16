<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/button.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/forFields.css"/>"/>

    <title>Add test</title>
</head>
<body>



<div class="container">
    <form method="POST" action="/question_add">
        <input type="hidden" name="command" value="addQuestion">

        <h2>Add necessary information</h2>


        <label for="name">Topic</label>
        <input type="text" id="name" name="new_test_name" placeholder="Topic">

        <label for="subject">Subject</label>
        <input type="text" id="subject" name="new_test_subject" placeholder="Subject">

        <label for="complexity">Complexity</label>
        <input type="text" id="complexity" name="new_test_complexity" placeholder="Complexity">

        <label for="time">Time</label>
        <input type="text" id="time" name="new_test_time" placeholder="Time">

        <label for="questionQuantity">Question quantity</label>
        <input type="text" id="questionQuantity" name="new_test_questionQuantity" placeholder="10">

        <label for="answerQuantity">Answer quantity</label>
        <input type="text" id="answerQuantity" name="new_test_answerQuantity" placeholder="4">

        <button class="button">Add questions</button>
    </form>
</div>






</body>
</html>
