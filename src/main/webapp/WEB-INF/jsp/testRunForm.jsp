<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">

<c:set var="title" value="home" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<div id="overlay"></div>

<ct:navigation showMenu="false" hidden="true"/>
<div class="container">
    <div>
        <h2 class="text-primary">
            <small class="text-muted">Test</small>
            ${test.name}</h2>
        <small class="text-muted">Choose correct answer or answers on each questions:</small>
        <hr class="my-4">
    </div>


    <form id="finish" action="controller" method="post">
        <input type="hidden" name="command" value="evaluateResult">
        <input type="hidden" name="testId" value="${test.id}">
        <input type="hidden" name="resultId" value="${resultId}">

        <c:forEach var="map" items="${questAnsMap}" varStatus="questionCount">
            <strong>Question #${questionCount.count}. ${map.key.text}</strong>
            <input type="hidden" name="question" value="${map.key.id}"> <br>

            <c:forEach var="answer" items="${map.value}">
                <input type="checkbox" name="correct"
                       value="${map.key.id}&${answer.getId()}">

                <label> ${answer.text}
                    <input type="hidden" name="answer" value="${answer.id}">

                </label>

                <br>
            </c:forEach>
            <hr class="my-4">
        </c:forEach>

        <div id="bottomLast">
            <button class="btn btn-success btn-lg btn-block" type="submit">Finish test</button>
        </div>

    </form>


</div>


<nav class="navbar navbar-expand-lg navbar-dark bg-light fixed-bottom">
    <h4 class="text-secondary"> ${test.name}</h4>

    <div class="collapse navbar-collapse" id="navbarColor02">
        <ul class="navbar-nav mr-auto">
        </ul>
        <div class="my-2 my-lg-0">
            <h4 class="nav-link text-danger" id="timer"></h4>
        </div>
    </div>
</nav>


<script>
    var endTime = ${test.duration*60*1000+startTime};
    var timer = setInterval(function () {
        var startTime = new Date().getTime();
        var duration = endTime - startTime;
        var hours = Math.floor((duration % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((duration % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((duration % (1000 * 60)) / 1000);

        if (duration <= 0) {
            document.getElementById("timer").innerHTML = "EXPIRED";
            document.getElementById("overlay").style.display = "block";
            clearInterval(timer);
            setTimeout("alert('Your duration is over')", 100);
            setTimeout(function () {
                document.getElementById('finish').submit();
            }, 100);
        }
        else {
            if (hours != 0) {
                document.getElementById("timer").innerHTML = hours + "h : "
                    + minutes + "m : " + seconds + "s ";
            }
            else {
                document.getElementById("timer").innerHTML = minutes + "m : " + seconds + "s ";
            }
        }

    }, 1000);
</script>


</body>
</html>
