<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:url value="/orders/ajax/admin/rooms/${displayedOrder.id}" var="ajaxPath"/>
<c:url value="/orders/admin/confirm/" var="confirm"/>
<c:url value="/orders/admin/disconfirm/" var="disconfirm"/>
<c:set value="tableBody" var="tableBody"/>
<c:if test="${not empty error}">
    <div class="alert alert-danger">
        <p>${error}</p>
    </div>
</c:if>
<c:if test="${not empty displayedOrder}">
<script type="text/javascript">
    var f = function fillOrdersTable(o_id, o_obj) {
        //delete all
        $(o_id).html("");

        var i = 0;
        for (var k in o_obj) {
            var row = $('<tr id=\"o_' + o_obj[k].id + '\"></tr>');
            row.append("<td><input name='room' value=" + o_obj[k].id + " type=\'radio\'></td>");
            row.append("<td>" + o_obj[k].id + "</td>");
            row.append("<td>" + o_obj[k].roomName + "</td>");
            row.append("<td>" + o_obj[k].category + "</td>");
            row.append("<td>" + o_obj[k].size + "</td>");
            row.append("<td>" + o_obj[k].pricePerDay + "</td>");
            $(o_id).append(row);
            i++;
        }
        $(o_id + '>tr>td>input').first().prop('checked', 'checked');
       // TODO hide button
        var hide;
        var confirmed=${displayedOrder.confirmed}+"q";
        switch (confirmed){
            case 'trueq':
                hide=true;
                break;
            case 'falseq':
                hide=false;
                $('#disconfirm').hide();
                break;
            default :
                hide=false;
        }
        if(i!=0){
            if(!hide) {
                    $('#confirm').show();
                }
        }
    };

    function confirmOrder() {
        var roomId = $("\#${tableBody}>tr>td>input:radio:checked").val();
        location.href = "${confirm}?orderId=${displayedOrder.id}&roomId=" + roomId;
    }

    function disconfirmOrder() {
        location.href = "${disconfirm}?orderId=${displayedOrder.id}";
    }

    function loadTable(begin, countSize) {
        loadTableAjax("${ajaxPath}", '\#${tableBody}', f, begin, countSize);
    }
    window.onload = function () {
        loadTableAjax("${ajaxPath}", '\#${tableBody}', f, 0, 100);
    }
</script>
    <security:authorize access="isFullyAuthenticated()">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="well col-md-6">
                <p>
                    <b>
                        <spring:message code="title.Order"/>&nbsp<spring:message code="title.Id"/>:&nbsp;
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
                <div >
                    <div  class="col-md-3" id="confirm" hidden="hidden">
                        <button type="button" class="btn bg-primary" onclick="confirmOrder();">
                            <spring:message code="order.Confurm"/>
                        </button>
                    </div>
                    <div class="col-md-3" id="disconfirm">
                        <button type="button" class="btn btn-danger" onclick="disconfirmOrder(false);">
                            <spring:message code="order.Disconfurm"/>
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-md-3"></div>
        </div>
        <div class="col-md-12 well" id="rooms">
            <table id="free_rooms" cellpadding="10" cellspacing="0"
                   class="table table-striped">
                <caption>
                    <h3>
                        <spring:message code="title.FreeRooms"/>
                    </h3>
                </caption>
                <thead>
                <tr>
                    <th colspan="2" class="col-md-1"><spring:message code="title.Id"/></th>
                    <th><spring:message code="title.Name"/></th>
                    <th><spring:message code="title.Room_Category"/></th>
                    <th><spring:message code="title.Places"/></th>
                    <th><spring:message code="order.Price"/></th>
                </tr>
                </thead>
                <tbody id="${tableBody}">
                </tbody>
            </table>
        </div>
    </security:authorize>
</c:if>