<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:url value="/hotels/" var="returnPage"/>
<security:authorize access="isFullyAuthenticated() and hasRole('ROLE_ADMINISTRATOR')">
    <script type="text/javascript">

    </script>
    <div class="row well">
        <div class="col-md-6">
            <h1><spring:message code="title.EditHotel"/></h1>
            <form:form action="${action}" method="post" commandName="hotel" modelAttribute="hotel"
                       cssClass="form-horizontal">
            <div class="form-group">
                <form:label path="name" cssClass="col-sm-2 control-label"><spring:message code="hotel.Name"/>:
                </form:label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-home"></span></div>
                        <form:input class="form-control" path="name"/>
                    </div>
                    <form:errors path="name" cssClass="alert alert-danger col-sm-10"/>
                </div>
            </div>
                <div class="form-group">
                    <form:label path="category" cssClass="col-sm-2 control-label"><spring:message
                            code="hotel.Category"/></form:label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-certificate"></span></div>
                            <form:select path="category" cssClass="form-control">
                                <form:options items="${categoryList}"/>
                            </form:select>
                        </div>
                        <form:errors path="category" cssClass="alert alert-danger"/><br>
                    </div>
                </div>
                <input name="submit" type="submit" class="btn btn-success" value="Save"/>
                <input name="button" type="button" class="btn btn-warning" value="Cancel"
                       onclick="location.href='${returnPage}${hotel.id}'"/>
                </form:form>
            </div>
        </div>
    </div>
</security:authorize>