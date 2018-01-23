<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<div>
    <label>${message}</label><br>
</div>


</div>

<form action="controller" method="post">
    <input type="hidden" name="command" value="listTests">
    <button class="btn btn-info btn-lg">OK</button>
</form>

</body>
</html>
