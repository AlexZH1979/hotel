<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<security:authorize access="isAuthenticated()">
    <c:redirect url="/profile/"/>
</security:authorize>
<c:url value="/registration" var="registration"/>
<c:if test="${not empty error}">
    <div class="danger">
        <p class="alert alert-danger">${error}</p>
    </div>
</c:if>
<div class="col-md-3"></div>
<div class="col-md-6 well">
    <h3><spring:message code="title.signin"/></h3>

    <form name='loginForm' action="<c:url value='/j_spring_security_check'/> " method='POST' class="form-horizontal"
          role="form">
    <form:errors path="*" cssClass="errorblock" element="div"/>
    <div class="form-group">
        <div class="col-sm-8">
            <div class="input-group">
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-log-in"
                          title="<spring:message code='title.Login'/>"></span>
                </div>
                <input type="text" id="username" name="username" class="form-control"
                       placeholder="<spring:message code='title.enter_login'/>">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-8">
            <div class="input-group">
                <div class="input-group-addon">
                    <span class="glyphicon glyphicon-lock"
                          title="<spring:message code='title.password'/>"></span>
                </div>
                <input type="password" id="password" name="password" class="form-control"
                       placeholder="<spring:message code='title.enter_password'/>">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-8">
            <input name="submit" type="submit" class="btn btn-success" value="<spring:message code='title.signin'/>"/>
            <input name="submit" type="button" class="btn btn-primary" value="<spring:message code='title.registration'/>"
                   onclick="location.href='${registration}'">
        </div>
    </div>
</form>
</div>
<div class="col-md-3"></div>