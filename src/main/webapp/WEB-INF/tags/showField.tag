<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>
<%@ attribute name="text" required="true" rtexprvalue="true" %>
<%@ attribute name="condition" type="java.lang.Boolean" required="true" rtexprvalue="true" %>
<%@ attribute name="showErrors" type="java.lang.Boolean" required="true" rtexprvalue="true" %>
<%@ attribute name="name" required="true" rtexprvalue="true" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" uri="/WEB-INF/customTag.tld" %>


<c:choose>
    <c:when test="${condition}">
        <div class="form-group has-danger">
            <input type="text" class="form-control form-control-sm is-invalid" name="${name}"
                   value="${text}">
            <div class="invalid-feedback"></div>
            <c:if test="${showErrors}">
                <ct:errMessage errors="${errors}"/>
            </c:if>
        </div>
    </c:when>
    <c:otherwise>
        <div class="form-group has-success">
            <input type="text" class="form-control form-control-sm is-valid" name="${name}"
                   value="${text}" maxlength="255">
            <div class="valid-feedback"></div>
        </div>
    </c:otherwise>
</c:choose>




