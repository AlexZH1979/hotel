<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:if test="${not empty error}">${error}</c:if>
<c:if test="${not empty displayedOrder}">
    <security:authorize access="isFullyAuthenticated()">
        <div class="col-md-3"></div>
        <div class="well col-md-6">
            <p>
                <b>
                    <spring:message code="title.Order"/>&nbsp<spring:message  code="title.Id"/>:&nbsp;
                </b>
                ${displayedOrder.id}
            </p>
            <p>
                <b>
                    <spring:message code="title.Hotel"/>:&nbsp;
                </b>
                ${displayedOrder.hotelName}
            </p>
            <p>
                <b>
                    <spring:message code="title.User"/>:&nbsp;
                </b>
                ${displayedOrder.userFullName}
            </p>
            <p>
                <b>
                    <spring:message code="title.Room_Category"/>:&nbsp;
                </b>
                ${displayedOrder.roomCategory}
            </p>
            <p>
                <b>
                    <spring:message code="title.Places"/>:&nbsp;
                </b>
                ${displayedOrder.places}
            </p>
            <p>
                <b>
                    <spring:message code="order.Start_Date"/>:&nbsp;
                </b>
                ${displayedOrder.startDate}
            </p>
            <p>
                <b>
                    <spring:message code="order.End_Date"/>:&nbsp;
                </b>${displayedOrder.endDate}
            </p>
            <div>
                <b>
                    <spring:message code="order.Status"/>:&nbsp;
                </b>
                <c:if test="${displayedOrder.confirmed}">
                    <spring:message code="order.Confurm.True"/>
                    <p>
                        <b>
                            <spring:message code="order.Price"/>:&nbsp;
                        </b>
                        ${displayedOrder.price}&euro;
                    </p>
                </c:if>
                <c:if test="${displayedOrder.confirmed eq false}">
                    <spring:message code="order.Confurm.False"/>
                </c:if>
                <c:if test="${empty displayedOrder.confirmed}">
                    <spring:message code="order.Confurm.Unknown"/>
                </c:if>
            </div>
        </div>
        <div class=col-md-3"></div>
    </security:authorize>
</c:if>
