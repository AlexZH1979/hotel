<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="col-md-3"></div>
<div class="col-md-6 well">
    <c:if test="${not empty errors}">
        <div class="alert alert-danger">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            ${errors}
        </div>
    </c:if>
    <h1>
        <spring:message code="title.Registration"/>
    </h1>
    <form:form action="${action}" method="post" commandName="client" modelAttribute="client" cssClass="form-horizontal">
    <div class="form-group">
        <form:label path="firstName" cssClass="col-sm-4 control-label"><spring:message code="user.FirstName"/>:
        </form:label>
        <div class="col-sm-8">
            <div class="input-group">
                <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                <form:input class="form-control" path="firstName"/>
            </div>
            <form:errors path="firstName" cssClass="alert alert-danger col-sm-12"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="lastName" cssClass="col-sm-4 control-label"><spring:message code="user.LastName"/>:
        </form:label>
        <div class="col-sm-8">
            <div class="input-group">
                <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                <form:input class="form-control" path="lastName"/>
            </div>
            <form:errors path="lastName" cssClass="alert alert-danger col-sm-12"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="login" cssClass="col-sm-4 control-label"><spring:message code="title.Login"/>:
        </form:label>
        <div class="col-sm-8">
            <div class="input-group">
                <div class="input-group-addon"><span class="glyphicon glyphicon-log-in" aria-hidden="true"></span></div>
                <form:input class="form-control" path="login"/>
            </div>
            <form:errors path="login" cssClass="alert alert-danger col-sm-12"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="password" cssClass="col-sm-4 control-label"><spring:message code="title.password"/>:
        </form:label>
        <div class="col-sm-8">
            <div class="input-group">
                <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                <form:input class="form-control" path="password" type="password"/>
            </div>
            <form:errors path="password" cssClass="alert alert-danger col-sm-12"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="email" cssClass="col-sm-4 control-label"><spring:message code="user.Email"/>:
        </form:label>
        <div class="col-sm-8">
            <div class="input-group">
                <div class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></div>
                <form:input class="form-control" path="email" type="email"/>
            </div>
            <form:errors path="email" cssClass="alert alert-danger col-sm-12"/>
        </div>
    </div>
    <div class="form-group">
        <form:label path="gender" cssClass="col-sm-4 control-label"><spring:message
                code="user.Gender"/></form:label>
        <div class="col-sm-8">
            <form:select path="gender" cssClass="form-control">
                <form:options items="${genderList}"/>
            </form:select>
            <form:errors path="gender" cssClass="alert alert-danger"/><br>
        </div>
    </div>
    <input name="submit" type="submit" class="btn btn-success" value="Save"/>
    <input name="submit" type="button" class="btn btn-sm btn-warning" value="Cancel"
           onclick="location.href='<c:url value="/hotels/search"/>'"/>
</form:form>
</div>
<div class="col-md-3"></div>