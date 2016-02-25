<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:if test="${not empty currentUser}">
    <security:authorize access="isFullyAuthenticated()">
        <c:url value="/orders/admin/user/${currentUser.id}" var="showOrders"/>
        <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
            <div id="admin-menu" class="alert alert-success col-md-12">
            <button class="btn btn-sm btn-primary" type="button" onclick="location.href='${showOrders}'">
                <spring:message code='title.Orders'/>
            </button>
            </div>
        </security:authorize>
        <div class="col-md-3"></div>
        <div class="well col-md-6">
            <h3><spring:message code="title.profile"/></h3>
            <b><spring:message code="title.Id"/> </b> ${currentUser.id}<br>
            <b><spring:message code="title.User"/>: </b>${currentUser.firstName} ${currentUser.lastName}
            (<b><spring:message code="title.login"/>: </b>${currentUser.login})<br>
            <b><spring:message code="user.Gender"/>: </b><spring:message code="gender.${currentUser.gender}"/><br>
            <b><spring:message code="user.Role"/>: </b><spring:message code="role.${currentUser.role}"/><br>
            <b><spring:message code="user.Email"/>: </b><a href="mailto:">${currentUser.email}</a><br>
        </div>
        <div class="col-md-3"></div>
    </security:authorize>
</c:if>