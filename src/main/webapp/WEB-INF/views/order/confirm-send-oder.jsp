<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty error}">
    <c:url value="/hotels/${displayedOrder.hotelId}" var="cancel"/>
    <c:url value="/orders/register/send" var="send"/>
<div>
    <div class="col-md-2"></div>
    <div class="well col-md-6">
        <div>
        <p><b><spring:message code="title.Hotel"/>: </b>${displayedOrder.hotelName}</p>
        <p><b><spring:message code="title.User"/>: </b>${displayedOrder.userFullName}</p>
        <p><b><spring:message code="title.Room_Category"/>: </b>${displayedOrder.roomCategory}</p>
        <p><b><spring:message code="title.Places"/>: </b>${displayedOrder.places}</p>
        <p><b><spring:message code="order.Start_Date"/>: </b>${displayedOrder.startDate}
            <b><spring:message code="order.End_Date"/>: </b>${displayedOrder.endDate}</p>
    </div>
        <input class="btn btn-sm btn-success" type="button" onclick="location.href='${send}'"
               value="<spring:message code='send.Order'/>"/>
        <input class="btn btn-sm btn-danger" type="button" onclick="location.href='${cancel}'"
               value="<spring:message code='title.Cancel'/>"/>
    </div>
    <div class="col-md-2"></div>
</div>
</c:if>
<c:if test="${not empty error}">
    <h3><spring:message code="order.Confurm"/></h3>
    <div class="col-md-2"></div>
    <div class="well col-md-6">
        <div class="row">
            <div class="alert alert-danger">
                <p>${error}</p>
            </div>
            <div>
                <button type="reset" class="btn btn-primary" onclick="window.history.back()"/>
            </div>
        </div>
    </div>
    <div class="col-md-2"></div>
</c:if>