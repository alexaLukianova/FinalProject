<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="list" type="java.util.List" required="true" rtexprvalue="true" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:forEach items="${list}" var="progress">
    <tr>
        <td>${progress.testName}</td>
        <td>${progress.testSubject}</td>
        <td>${progress.result}</td>
        <td> ${progress.date}</td>
    </tr>
</c:forEach>




