<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>
<%@ attribute name="showMenu" type="java.lang.Boolean" required="true" rtexprvalue="true" %>
<%@ attribute name="action" rtexprvalue="true" %>
<%@ attribute name="hidden" type="java.lang.Boolean" required="true" rtexprvalue="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<div class="topnav">
    <c:if test="${showMenu}">
        <a class="active" href="/controller?command=showProfile">Profile</a>
        <c:if test="${'admin'.equals(userRole.toString())}">
            <a href="/controller?command=listUsers">Users </a>
        </c:if>
        <a href="/controller?command=listTests">Tests</a>
        <a href="/controller?command=logout">Logout</a>
    </c:if>

    <div class="lang-selector">
        <form class="form-group"
                <c:choose>
                    <c:when test="${empty action}">
                        action="/controller?command=${command}"
                    </c:when>
                    <c:otherwise>
                        action="${action}"
                    </c:otherwise>
                </c:choose>
              method="get">
            <input type="hidden" name="command" value="${command}">
            <select class="form-control" id="language" name="language" onchange="submit()"
                    <c:if test="${hidden}">style="visibility: hidden"</c:if>>
                <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
            </select>
        </form>
    </div>
</div>