<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/button.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/forFields.css"/>"/>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"; pageEncoding="UTF-8">

    <title>Add test</title>
</head>
<body>






<div class="container">

    <form action="controller" method="post">
        <input type="hidden" name="command" value="addQuestions">

    <h2>Add necessary information</h2>

    <label for="name">Topic</label>
    <input type="text" id="name" name="test_name" placeholder="Topic">

    <label for="subject">Subject</label>
    <input type="text" id="subject" name="test_subject" placeholder="Subject">

    <label for="complexity">Complexity</label>
    <input type="text" id="complexity" name="test_complexity" placeholder="Complexity">

    <label for="time">Time</label>
    <input type="text" id="time" name="test_time" placeholder="Time">

    <label for="questionQuantity">Question quantity</label>
    <input type="text" id="questionQuantity" name="test_questionQuantity" placeholder="10">

    <label for="answerQuantity">Answer quantity</label>
    <input type="text" id="answerQuantity" name="test_answerQuantity" placeholder="4">

        <button class="button" type="submit">Add questions</button>
    </form>


</div>





</body>
</html>
