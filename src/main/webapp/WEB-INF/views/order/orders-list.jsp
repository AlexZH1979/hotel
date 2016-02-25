<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:url value="/orders/ajax" var="ajaxPath"/>
<c:url value="/orders/ajax/delete" var="ajaxPathDelete"/>
<c:url value="/profile/" var="profile"/>
<c:url value="/hotels/" var="hotel"/>
<c:url value="/orders/" var="orders"/>

<c:set value="tableBody" var="tableBody"/>
<script type="text/javascript">
    var currentBegin=0;
    var page=0;
    var currentParam;
    var currentValue;
    var currentCount=10;
    var f = function fillOrdersTable(o_id, o_obj) {
        //delete all
        $(o_id).html("");

        var i = 0;
        for (var k in o_obj) {
            var color;
            var confirm;
            var clazz;
            switch (o_obj[k].confirmed) {
                case true:
                    color = "#0C0";
                    confirm="<spring:message code="order.Confurm.True"/>";
                    clazz='success';
                    break;
                case false:
                    color = "#C00";
                    confirm="<spring:message code="order.Confurm.False"/>";
                    clazz='danger';
                    break;
                default:
                    color = "";
                    confirm="<spring:message code="order.Confurm.Unknown"/>";
                    clazz='';
                    break;
            }
            var row = $('<tr id=\"o_' + o_obj[k].id + '\"  class='+clazz+'></tr>');
            row.append("<td><input value="+o_obj[k].id+" type=\'checkbox\'></td>");
            var orderLink=$("<td></td>");
            orderLink.append("<a href=\'${orders}"+o_obj[k].id + "\'> #" +o_obj[k].id+ "</a>");
            row.append(orderLink);
            var link = $("<td></td>");
            link.append('<a href=\"${hotel}'+o_obj[k].hotelId + '\"> ' +o_obj[k].hotelName+ '</a>');
            row.append(link);
            row.append("<td>"+o_obj[k].places+"</td>");
            row.append("<td>"+o_obj[k].roomCategory+"</td>");
            row.append("<td>" + o_obj[k].startDate + "</td>");
            row.append("<td>" + o_obj[k].endDate + "</td>");
            //TODO visual check
            row.append("<td style='background: "+color+";'>" + confirm + "</td>");
            $(o_id).append(row);
            i++;
        }
    }

    function loadTable(begin, countSize) {
        loadTableAjax("${ajaxPath}", '\#${tableBody}', f, begin, countSize);
    }

    function deleteOrders(){
        var jsonDelete=$.map( $("\#${tableBody}>tr>td>input:checkbox:checked"), function(el){ return $(el).val(); });

        console.log(jsonDelete);
        $.ajax({
            url:"${ajaxPathDelete}",
            data:JSON.stringify(jsonDelete),
            type:"POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function(delId){
                for(var i in delId){
                    var tr = $('#o_'+delId[i]);
                    tr.css("background-color","#FF3700");

                    tr.fadeOut(1000, function(){
                        tr.remove();
                    });
                }
            },
            error: function () {
                console.log("error witch ajax "+jsonDelete);
            }
        })

    }

    window.onload = function () {
        loadTableAjax("${ajaxPath}",  '\#${tableBody}', f, 0, 10);
    }
</script>
    <security:authorize access="isFullyAuthenticated()">
<select name="selected_count" id="select_count" onchange="loadTable(currentBegin,$(this).val())">
    <option selected value="10">10</option>
    <option value="50">50</option>
    <option value="100">100</option>
    <option value="30000>">30000</option>
</select>
 <button onclick="deleteOrders();"><spring:message code="title.delete"/></button>
<table id="list_orders" cellpadding="10" cellspacing="0" class="table table-striped table-bordered">
    <thead>
    <tr>
        <th colspan="2"><spring:message code="title.Id"/></th>
        <th><spring:message code="title.Hotel"/></th>
        <th><spring:message code="title.Places"/></th>
        <th><spring:message code="title.Room_Category"/></th>
        <th><spring:message code="order.Start_Date"/></th>
        <th><spring:message code="order.End_Date"/></th>
        <th><spring:message code="order.Status"/></th>
    </tr>
    </thead>
    <tbody id=${tableBody}>
    </tbody>
</table>
</security:authorize>