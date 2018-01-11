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

<form method="POST" action="/test_add">
    <h2>Add necessary information</h2>
    <label>Name:</label>
    <input type="text" name="new_test_name" size="20" placeholder="Topic"/><br>

    <label>Subject:</label>
    <input type="text" name="new_test_subject" size="20" placeholder="Subject"/><br>

    <label>Complexity:</label>
    <input type="text" name="new_test_complexity" size="20" placeholder="Complexity"/><br>

    <label>Quantity of questions:</label>
    <input type="text" name="new_test_quantity_of_questions" size="20" placeholder="Quantity"/><br>

    <label>Maximum quantity of answers:</label>
    <input type="text" name="new_test_quantity_of_answers" size="20" placeholder="Minimum is 2"/><br>

    <button type="submit">Add</button>

    <a href="/question_add">Add questions</a>
</form>


</table>
</body>
</html>
