<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:url value="/hotels/ajax" var="ajaxPath"/>
<c:url value="/hotels/ajax/size" var="ajaxPathSize"/>
<c:url value="/hotels/" var="hotel"/>
<script type="text/javascript">
    var f = function fillOrdersTable(o_id, o_obj) {
        //delete all
        $(o_id).html("");
        var i = 0;
        for (var k in o_obj) {
            var row = $('<tr id="c_' + i + '"></tr>');
            var link = $("<td></td>");
            link.append('<a href="' + ${hotel} +o_obj[k].id + '"> #' + o_obj[k].id + '</a>');
            row.append(link);
            row.append("<td>" + o_obj[k].name + "</td>");
            row.append("<td>" + o_obj[k].category + "</td>");
            row.append("<td> Address: " + o_obj[k].hotelAddress.address + "<br> City: " + o_obj[k].hotelAddress.city +
                    "<br> Country: " + o_obj[k].hotelAddress.county + "</td>");
            $(o_id).append(row);
            i++;
        }
    }
    function loadTable(begin, countSize) {
        loadTableAjax("${ajaxPath}", '#tableBody', f, begin, countSize);
    }
    window.onload = function () {
        loadSizeList("${ajaxPathSize}", "#list_bar");
        loadTableAjax("${ajaxPath}", '#tableBody', f, 0, 100);
    }
</script>
<table id="list_orders" border="1" cellpadding="10" cellspacing="0" class="table table-striped table-bordered">
    <thead>
    <tr class="table-condensed">
        <td>
            <select name="selected_count" id="select_count" onchange="loadTable(0,$(this).val())">
                <option disabled selected>count</option>
                <option value="10">10</option>
                <option value="50">50</option>
                <option value="100">100</option>
                <option value="1000">1000</option>
                <option value="30000>">30000</option>
            </select>
        </td>
    </tr>
    <tr>
        <th><spring:message code="title.Hotel"/> <spring:message code="title.Id"/></th>
        <th><spring:message code="title.Name"/></th>
        <th><spring:message code="hotel.Category"/></th>
        <th><spring:message code="address.Address"/></th>
    </tr>
    </thead>
    <tbody id="tableBody">
    </tbody>
</table>