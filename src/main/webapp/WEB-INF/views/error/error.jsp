<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="cache-control" content="max-age=0"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <title>ERROR&nbsp;PAGE&nbsp;</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap/bootstrap.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap/bootstrap-theme.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/layout.css'/>"/>
    <script type="text/javascript" src="<c:url value='/resources/js/bootstrap.js' />"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12">&nbsp</div>
    </div>
</div>
<div class="container">
    <div class="well col-lg-12">
        <div class="row">
        <div class="col-lg-6">
            <h1>Error&nbsp;Page</h1>
    <div>${pageContext.response.contentType}</div>
            <div><b>Status&Code:&nbsp</b>${pageContext.errorData.statusCode}</div>
            <c:if test="${not empty pageContext}">
                <div><b>Cause:&nbsp</b>${pageContext.exception.cause}</div>
                <div><b>Message:&nbsp;</b>${pageContext.exception.message}</div>
            </c:if>
            <c:if test="${empty returnPage}">
                <c:url value="/" var="returnPage"/>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div><p>${errorMessage}</p></div>
            </c:if>
            <c:if test="${not empty error}">
                <div><p>${error}</p></div>
            </c:if>
        </div>
        <div class="col-lg-6">
            <img src="<c:url value='/resources/img/original.jpg'/>" class="img-rounded"/>
        </div>
            </div>
        <div class="col-lg-12">
            <p>
                <button type="button" class="btn btn-warning"
                        onclick="location.href='${returnPage}'"><spring:message code="title.Return"/></button>
            </p>
        </div>
    </div>
</div>
</body>
</html>