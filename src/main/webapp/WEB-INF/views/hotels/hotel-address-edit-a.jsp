<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<security:authorize access="isFullyAuthenticated() and hasRole('ROLE_ADMINISTRATOR')">
    <div class="row well">
    <div class="col-md-6">
    <h1><spring:message code="title.EditAddress"/></h1>
    <h3><spring:message code="title.Select"/>&nbsp;${parameterForSelect}</h3>
    <input id="selectParam" type="text" min="3" max="50">
    <button type="button" class="btn btn-success" onclick="location.href='<c:url value="${beginPath}${endPath}"/>/?value='+$('#selectParam').val()"></button>

</security:authorize>
