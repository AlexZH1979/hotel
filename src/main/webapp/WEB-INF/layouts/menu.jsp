<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<security:authorize access="isFullyAuthenticated()">
    <div>
        <ul id="menu" class="nav nav-pills nav-stacked">
            <li id="menu-hotel"><a href="<c:url value='/hotels/search'/>"><spring:message code="title.Hotels"/></a></li>
            <li id="menu-order"><a href="<c:url value='/orders/'/>"><spring:message code="title.Orders"/></a></li>
        </ul>
    </div>
    <security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
        <div>
            <ul id="admin_menu" class="nav nav-pills nav-stacked">
                <li id="admin-orders">
                    <a href="<c:url value='/orders/admin/'/>"><spring:message code="title.AdminOrders"/></a>
                </li>
                <li id="admin-users">
                    <a href="<c:url value='/users/admin/'/>"><spring:message code="title.AdminUsers"/></a>
                </li>
                <li id="hotel-list">
                    <a href="<c:url value='/hotels/admin/'/>"><spring:message code="title.AdminHotels"/></a>
                </li>
            </ul>
        </div>
    </security:authorize>
</security:authorize>