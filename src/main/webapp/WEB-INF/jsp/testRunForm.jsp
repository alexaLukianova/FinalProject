<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <style>
        #overlay {
            position: fixed;
            display: none;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 2;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div id="overlay"></div>

<div>
    <h2>Chosen test</h2>
    <label>Topic: ${test.name}</label><br>
    <label>Subject: ${test.subject}</label><br>
    <label>Complexity: ${test.complexityId}</label><br>
    <label>Your duration: <p style="color:red; font-size:20px" id="demo"></p></label>


</div>

<script>
    function enable(elemId) {
        var c = document.getElementById(elemId).children;


        for (var i = 0; i < c.length; i++) {
            c[i].disabled = false;
        }
    }


</script>

<form id="finish" action="controller" method="post">
    <input type="hidden" name="command" value="evaluteResult">
    <input type="hidden" name="test_id" value="${test.id}">

    <h3>Choose correct answer or answers on each questions: </h3>
    </br>
    <c:forEach var="i" begin="0" end="${questions.size()-1}">
        <label>Question #${i+1}. ${questions[i].text}</label>
        <input type="hidden" name="question" value="${questions[i].id}"> <br>

        <c:forEach var="j" begin="0" end="${questionsAndAnswers[questions[i]].size()-1}">
            <input type="checkbox" name="correct"
                   value="${questions[i].id}&${questionsAndAnswers[questions[i]][j].getId()}">

            <label> ${questionsAndAnswers[questions[i]][j].getText()}
                <input type="hidden" name="answer" value="${questionsAndAnswers[questions[i]][j].getId()}">

            </label>
            <br>

        </c:forEach>

        <hr>
        <br>
    </c:forEach>

    <button class="button" type="submit" id="finishButton">Finish test</button>
</form>


</div>


<script>
    var endTime = ${test.duration*60*1000+startTime};
    var timer = setInterval(function () {
        var startTime = new Date().getTime();
        var duration = endTime - startTime;
        var hours = Math.floor((duration % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((duration % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((duration % (1000 * 60)) / 1000);

        if (duration <= 0) {
            document.getElementById("demo").innerHTML = "EXPIRED";
            document.getElementById("overlay").style.display = "block";
            clearInterval(timer);
            setTimeout("alert('Your duration is over')", 100);
            setTimeout(function () {
                document.getElementById('finish').submit();
            }, 100);
        }
        else {
            if (hours != 0) {
                document.getElementById("demo").innerHTML = hours + "h : "
                    + minutes + "m : " + seconds + "s ";
            }
            else {
                document.getElementById("demo").innerHTML = minutes + "m : " + seconds + "s ";
            }
        }

    }, 1000);
</script>


</body>
</html>
