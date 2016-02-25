<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:url value="${path}" var="ajaxPath"/>
<c:url value="/profile/" var="profile"/>
<c:url value="/hotels/" var="hotel"/>
<c:url value="/orders/admin/" var="orders"/>
<script type="text/javascript">
    var body_id = '#tableBody';
    var hiddenOrders="false";

    var f = function fillOrdersTable(o_id, o_obj) {
        //delete all
        $(o_id).html("");

        var i = 0;
        var confirm;
        var clazz;
        for (var k in o_obj) {
            switch (o_obj[k].confirmed) {
                case true:
                    confirm="<spring:message code='order.Confurm.True'/>";
                    clazz='success';
                    break;
                case false:
                    confirm="<spring:message code='order.Confurm.False'/>";
                    clazz='danger';
                    break;
                default:
                    confirm="<spring:message code='order.Confurm.Unknown'/>";
                    clazz='';
                    break;
            };

            var row = $("<tr class="+clazz+" id=\'c_\'" + i + "\'></tr>");
            var link = $("<td></td>");
            link.append("<a href=\'${orders}"+o_obj[k].id + "\'> #" +o_obj[k].id+ "</a>");
            row.append(link);
            var link1 = $("<td></td>");
            link1.append('<a href=\"${hotel}'+o_obj[k].hotelId + '\"> ' +o_obj[k].hotelName+ '</a>');
            row.append(link1);
            row.append("<td>"+o_obj[k].roomCategory+"</td>");
            row.append("<td>" + o_obj[k].startDate + "</td>");
            row.append("<td>" + o_obj[k].endDate + "</td>");
            link = $("<td></td>");
            link.append('<a href=\"${profile}'+o_obj[k].customerId + '\">'+o_obj[k].userFullName+'</a>');
            row.append(link);
            row.append('<td>' +confirm+'</td>');
            $(o_id).append(row);
            i++;
        }
    };

    function loadTable(begin, countSize) {
        loadTableAjax("${ajaxPath}?hiden="+hiddenOrders, body_id, f, begin, countSize);
    }

    window.onload = function () {
        loadTableAjax("${ajaxPath}?hiden="+hiddenOrders, body_id, f, 0, 100);
    }
</script>
<c:if test="${not empty error}">
    <div class="alert alert-danger"><p>${error}</p></div>
</c:if>
<div class="col-md-12 well">
<c:if test="${not empty item and not empty nameItem}">
    <h1><spring:message code="title.${nameItem}"/>:&nbsp${item}</h1>
</c:if>
<select name="selected_count" id="select_count" onchange="loadTable(0,$(this).val())">
    <option disabled selected>count</option>
    <option value="10">10</option>
    <option value="50">50</option>
    <option value="100">100</option>
    <option value="1000000">100000</option>
</select>
<table id="list_orders" cellpadding="10" cellspacing="0" class="table table-striped">
    <thead>
    <tr>
        <th><spring:message code="title.Id"/> </th>
        <th><spring:message code="title.Hotel"/> </th>
        <th><spring:message code="title.Room_Category"/> </th>
        <th><spring:message code="order.Start_Date"/> </th>
        <th><spring:message code="order.End_Date"/> </th>
        <th><spring:message code="order.Customer"/> </th>
        <th><spring:message code="order.Status"/> </th>
    </tr>
    </thead>
    <tbody id="tableBody">
    </tbody>
</table>
</div>
<div class="col-md-6"></div>
</div>