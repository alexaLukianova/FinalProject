<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<div>
    <h2>Chosen test</h2>
    <label>Topic: ${test.name}</label><br>
    <label>Subject: ${test.subject}</label><br>
    <label>Complexity: ${test.complexity}</label><br>
    <label>Your time: <p style="color:red; font-size:20px" id="demo"></p></label>


</div>

<script>
    function enable(elemId) {
        var c = document.getElementById(elemId).children;


        for (var i = 0; i < c.length; i++) {
            c[i].disabled = false;
        }
    }


</script>

<form action="controller" method="post">
    <input type="hidden" name="command" value="evaluteResult">
    <input type="hidden" name="test_id" value="${test.id}">

    <h3>Choose correct answer or answers on each questions: </h3>
    </br>
    <c:forEach var="i" begin="0" end="${questions.size()-1}">
        <label>Question #${i+1}. ${questions[i].text}</label>
        <input type="hidden" name="question" value="${questions[i].id}"> <br>

        <c:forEach var="j" begin="0" end="${questionsAndAnswers[questions[i]].size()-1}">
            <input type="checkbox" name="correct" value="${questions[i].id}&${questionsAndAnswers[questions[i]][j].getId()}">

            <label>${j+1}. ${questionsAndAnswers[questions[i]][j].getText()}
                <input type="hidden" name="answer" value="${questionsAndAnswers[questions[i]][j].getId()}">

            </label>
            <br>

        </c:forEach>

        <hr>
        <br>
    </c:forEach>

    <button class="button" type="submit">Finish test</button>
</form>


</div>


<script>
    var countDownDate =
    ${test.time*60*1000+time}
    var x = setInterval(function () {
        var now = new Date().getTime();
        var distance = countDownDate - now;

        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        if (hours != 0) {
            document.getElementById("demo").innerHTML = hours + "h "
                + minutes + "m " + seconds + "s ";
        }
        else {
            document.getElementById("demo").innerHTML = minutes + "m " + seconds + "s ";
        }
        document.getElementById('timer').value = distance;


        if (distance < 0) {
            clearInterval(x);
            document.getElementById("demo").innerHTML = "EXPIRED";
        }
    }, 1000);
</script>


</body>
</html>
