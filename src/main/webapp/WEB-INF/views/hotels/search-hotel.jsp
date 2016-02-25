<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:url value="/hotels/search"  var="search"/>
<c:url value="/hotels/search/length" var="searchLength"/>
<c:url value="/hotels/search" var="ajaxSearchHotel"/>
<script type="text/javascript">
    var currentBegin=0;
    var page=0;
    var currentParam;
    var currentValue;
    var currentCount=10;

    var f = function fillHotelsTable(o_id, o_obj) {
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
            row.append("<td> Address: " + o_obj[k].hotelAddress.address
                    + "<br> City: " + o_obj[k].hotelAddress.city +
                    "<br> Country: " + o_obj[k].hotelAddress.county + "</td>");
            $(o_id).append(row);
            i++;
        }
        $(o_id).parent().parent().show();
    }
    //get length hotels by param
    function loadTables(parameter, value) {
        loadTableByParamAjax("${ajaxSearchHotel}", '#tableBody', f, parameter, value,currentBegin,currentCount);
        currentParam=parameter;
        currentValue=value;
    }

    var listSizes = function listHotelSizes(e_id, parameter, v, find) {
        console.log("id "+ e_id+' parameter '+parameter+ ' value ' +v+ ' find '+find);
        var message;
        switch (parameter) {
            case "state":
                message = "<spring:message code='title.by_state'/>";
                break;
            case "county":
                message = "<spring:message code='title.by_county'/>";
                break;
            case "city":
                message = "<spring:message code='title.by_city'/>";
                break;
            case "address":
                message = "<spring:message code='title.by_address'/>";
                break;
            case "name":
                message = "<spring:message code='title.by_name'/>";
                break;
        }
        var btn = $('<button type="button" class="btn btn-primary col-md-12" onclick="loadTables(\'' + parameter +
                '\', \'' + v + '\','+currentBegin+','+currentCount+');"></button>');
        btn.append('<spring:message code="title.Findded_by"/>&nbsp' + message + ': ' + v + ' ' + find + ' </p>');
        $(e_id).append(btn);
        $(e_id).show();
    };


    function listSizesResult(target_id, value) {
        var searchParameters = ["state", "county", "city", "address", "name"];
        $('#butt').button('loading')
        $(target_id).html("");
        $("#tableBody").html("");
        console.log("target_id "+target_id);
        console.log("value "+value);
        for (var k in searchParameters) {
            console.log("searchParameters "+searchParameters[k]);
            searchByParam("${searchLength}", searchParameters[k], value, target_id, listSizes, '#butt');
        }
    }
    //reload table when select count
    function reloadTables(count){
        currentCount=count;
        loadTables(currentParam,currentValue);
    }
</script>
<div class="well-lg col-lg-12">
    <div>
        <h3><spring:message code="title.Search"/></h3>

        <div class="input-group">
            <div class="input-group-addon">
                <div class="col-sm-12">
                    <label class="col-sm-3 control-label"><spring:message code="title.destination"/></label>
                    <input type="text" id="state" name="value" class="input-sm col-sm-7"
                           placeholder="<spring:message code="title.search_placeholder"/> ">
                    <button id="butt" type="button" class="btn btn-primary"
                            onclick="listSizesResult('#finder',$('#state').val())"><span
                            class="glyphicon glyphicon-search"></span>
                        <spring:message code='title.Search'/></button>
                </div>
            </div>
        </div>
    </div>
    <div id="finder" class="row" hidden="hidden">
    </div>
    <div class="well" hidden="hidden">
        <select name="selected_count" id="select_count" onchange="reloadTables($(this).val())">
            <option selected value="10">10</option>
            <option value="50">50</option>
            <option value="100">100</option>
            <option value="300000">300000</option>
        </select>
        <table class="table table-striped">
            <thead/>
            <tbody id="tableBody"></tbody>
        </table>
    </div>
</div>