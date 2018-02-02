<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html lang="${language}">
<c:set var="title" value="message" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<ct:navigation showMenu="false" hidden="true"/>

<div class="container">
    <br>

    <div class="card text-white bg-secondary ">
        <div class="card-header">Result</div>
        <div class="card-body">
            <h4 class="card-title"> You result is ${mark}%.</h4>
            <p class="card-text">Nice try.</p>
        </div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="listTests">
            <center>
                <button class="btn btn-success btn-md">OK</button>
            </center>

        </form>

    </div>


</div>


</body>
</html>
