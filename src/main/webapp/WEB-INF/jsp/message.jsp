<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">
<c:set var="title" value="message" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>


<div class="container">
    <br>

    <div class="card text-white bg-secondary ">
        <div class="card-header"><fmt:message key="test.result"/></div>
        <div class="card-body">
            <h4 class="card-title"><fmt:message key="text.your_result"/> ${mark}%.</h4>
            <p class="card-text"><fmt:message key="text.nice_try"/>.</p>
        </div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="listTests">
            <center>
                <button class="btn btn-success btn-md"><fmt:message key="button.ok"/></button>
            </center>
            <br>
        </form>

    </div>


</div>


</body>
</html>
