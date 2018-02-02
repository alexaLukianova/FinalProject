<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>
<%@ attribute name="errors" type="java.util.Map" required="true" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:if test="${errors.containsKey('question')}">
    <div class="invalid-feedback"><fmt:message key="${errors.get('question')}"/></div>
</c:if>
<c:if test="${errors.containsKey('answer')}">
    <div class="invalid-feedback"><fmt:message key="${errors.get('answer')}"/></div>
</c:if>
<c:if test="${errors.containsKey('correct')}">
    <div class="invalid-feedback"><fmt:message key="${errors.get('correct')}"/></div>
</c:if>