<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="cache-control" content="max-age=0"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap/bootstrap.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap/bootstrap-theme.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/layout.css'/>"/>
    <script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/json2.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/main.js' />"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <tiles:insertAttribute name="header"/>
    </div>
    <div class="row">
        <security:authorize access="isFullyAuthenticated()">
            <div class="col-md-2">
                <tiles:insertAttribute name="menu"/>
            </div>
            <div class="col-md-10 well">
                <tiles:insertAttribute name="body"/>
            </div>
        </security:authorize>
        <security:authorize access="not isFullyAuthenticated()">
            <div class="col-md-12 well">
                <tiles:insertAttribute name="body"/>
            </div>
        </security:authorize>
    </div>
</div>
<security:authorize access="isFullyAuthenticated()">
    <div class="container bottom">
        <tiles:insertAttribute name="footer"/>
    </div>
</security:authorize>
</body>
</html>