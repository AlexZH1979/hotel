<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/users/ajax" var="ajaxPath"/>
<c:url value="/profile/" var="profile"/>
<script type="text/javascript">
    var body_id='#tableBody';
    var f = function fillOrdersTable(id, obj) {
        //delete all
        $(id).html("");
        var i = 0;
        for (var k in obj) {
            var row = $('<tr id="c_' + i + '"></tr>');
            var link = $("<td></td>");
            link.append('<a href="' + ${profile}+obj[k].id + '"> ' +obj[k].id+ '</a>');
            row.append(link);
            //noinspection JSUnfilteredForInLoop,JSUnfilteredForInLoop,JSUnfilteredForInLoop
            row.append("<td>" + obj[k].firstName + "</td>");
            row.append("<td>" + obj[k].lastName + "</td>");
            row.append("<td>" + obj[k].login + "</td>");
            row.append("<td>" + obj[k].email + "</td>");
            row.append("<td>" +obj[k].gender + "</td>");
            row.append("<td>" +obj[k].role+ "</td>");
            $(id).append(row);
            i++;
        }
    }
    function loadTable(begin, countSize) {
        loadTableAjax("${ajaxPath}",body_id, f, begin, countSize);
    }
    window.onload = function () {
        loadTableAjax("${ajaxPath}",body_id, f, 0, $("#select_count").select().val());
    }
</script>
<select name="selected_count" id="select_count" onchange="loadTable(0,$(this).val())">
    <option value="10" selected>10</option>
    <option value="50">50</option>
    <option value="100">100</option>
    <option value="30000>">all</option>
</select>
<table id="clientsTable" border="1" cellpadding="10" cellspacing="0" class="table table-striped table-bordered">
    <thead>
    <tr>
        <th><spring:message code="title.Id"/></th>
        <th><spring:message code="user.FirstName"/></th>
        <th><spring:message code="user.LastName"/></th>
        <th><spring:message code="title.login"/></th>
        <th><spring:message code="user.Email"/></th>
        <th><spring:message code="user.Gender"/></th>
        <th><spring:message code="user.Role"/></th>
    </tr>
    </thead>
    <tbody id="tableBody">
    </tbody>
</table>