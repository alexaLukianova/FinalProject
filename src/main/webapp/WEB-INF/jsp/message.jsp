<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/navigation.css"/>"/>
</head>
<body>


<div class="container">
    <br>

    <div class="card text-white bg-secondary ">
        <div class="card-header">Result</div>
        <div class="card-body">
            <h4 class="card-title">${message}</h4>
            <p class="card-text">Ask admin to permit your new attempt.</p>
        </div>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="listTests">
            <center>
                <button class="btn btn-success btn-md"> OK</button>
            </center>

        </form>

    </div>


</div>


</body>
</html>
