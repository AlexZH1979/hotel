<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:if test="${not empty hotel}">
    <c:url value="/orders/register/param" var="registerPath"/>
    <link rel="stylesheet" type="text/css" media="all"
          href="<c:url value='/resources/calendar/daterangepicker-bs3.css'/>"/>
    <script type="text/javascript" src="<c:url value='/resources/calendar/moment.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/calendar/daterangepicker.js'/>"></script>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <c:set value="reportrange2" var="calendar"/>
    <c:set value="places" var="showPlaces"/>
    <c:set value="category" var="showCategory"/>
    <c:url value="/orders/admin/hotel/${hotel.id}" var="showOrders"/>
    <style>
        #map-canvas {
            height: 300%;
            width: 100%;
            padding: 300px
        }
    </style>
    <script>
        var orderDetails = {
            start: new Date(),
            end: new Date(),
            places: 1,
            roomCategory: "ECONOMY"
        };
        $(document).ready(function () {
            $('#place_1').attr('checked', 'checked');
            $('#category_ECONOMY').toggle('toggle');

            $('\#${calendar} span').html(moment().format('D MMMM, YYYY') + ' - ' + moment().format('D MMMM, YYYY'));
            $('\#${calendar}').daterangepicker({
                opens: 'center',
                startDate: orderDetails.start,
                format: 'D MMMM, YYYY',
                minDate: orderDetails.start

            }, function (start, end) {
                $('\#${calendar} span').html(start.format('D MMMM, YYYY') + ' - ' + end.format('D MMMM, YYYY'));
                orderDetails.start = start;
                orderDetails.end = end;
            });
        });
        function sendOrder() {
            location.href = '${registerPath}?hotelId=${hotel.id}&startDate=' + orderDetails.start.valueOf() +
                    '&endDate=' + orderDetails.end.valueOf() + '&places=' + orderDetails.places +
                    '&roomCategory=' + orderDetails.roomCategory;
        }
        function setPlaces(places) {
            orderDetails.places = places;
            $('\#${showPlaces}').html(places);
        }

        function setCategory(category) {
            orderDetails.roomCategory = category;
            $('\#${showCategory}').html(category);
        }
    </script>
    <script type="text/javascript">
        function initialize() {
            var mapOptions = {
                zoom: 12,
                center: new google.maps.LatLng(${hotel.hotelLocation.locLat}, ${hotel.hotelLocation.locLong}),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);
        }
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
    <security:authorize access="isFullyAuthenticated() and hasRole('ROLE_ADMINISTRATOR')">
        <div id="admin-menu" class="alert alert-success col-md-12">
            <button class="btn btn-sm btn-primary" type="button"
                    onclick="location.href='<c:url value="/hotels/admin/edit/${hotel.id}"/>'">
                <spring:message code='title.EditHotel'/>
            </button>
            <button class="btn btn-sm btn-primary" type="button"
                    onclick="location.href='<c:url value="/hotels/admin/edit/${hotel.id}/address/state/"/>'">
                <spring:message code='title.EditAddress'/>
            </button>
            <button class="btn btn-sm btn-primary" type="button"
                    onclick="location.href='<c:url value="/orders/admin/hotel/${hotel.id}"/>'">
                <spring:message code='title.Orders'/>
            </button>
        </div>
    </security:authorize>
    <div class="well">
        <div class="row">
            <c:if test="${not empty error}">
                <div>
                    <p>
                            ${error}
                    </p>
                </div>
            </c:if>
            <c:if test="${not empty hotel}">
            <div class="col-md-5">
                <h3><b><spring:message code="title.Hotel"/></b></h3>
                <address>
                    <h3><strong><i>${hotel.name}</i></strong></h3>
                    <h4><spring:message code="hotel.Category"/>: ${hotel.category}</h4>
                    <h4><b><spring:message code="address.Address"/>:</b></h4>
                    <c:if test="${not empty hotel.hotelAddress.state}">
                        <b><spring:message code="address.State"/>: </b>${hotel.hotelAddress.state}<br>
                    </c:if>
                    <c:if test="${not empty hotel.hotelAddress.county}">
                        <b><spring:message code="address.County"/>: </b> ${hotel.hotelAddress.county}<br>
                    </c:if>
                    <c:if test="${not empty hotel.hotelAddress.city}">
                        <b><spring:message code="address.Sity"/>: </b>${hotel.hotelAddress.city}<br>
                    </c:if>
                    <c:if test="${not empty hotel.hotelAddress.address}">
                        <b><spring:message code="address.Address"/>: </b>${hotel.hotelAddress.address}<br>
                    </c:if>
                    <c:if test="${not empty hotel.hotelAddress.zip}">
                        <b><spring:message code="address.ZIP"/>: </b>${hotel.hotelAddress.zip}<br>
                    </c:if>
                </address>
            </div>
            <div class="col-md-7">
                <div class="well">
                    <label style="text-align: center;"><spring:message code="order.Create"/></label>

                    <div>
                    <div>
                        <label>
                            <c:forEach begin="1" end="4" var="place">
                                <input id="place_${place}" type="radio" name="places" value="${place}"
                                       onchange="setPlaces($(this).val());"/>
                            </c:forEach>
                            <spring:message code="title.Places"/>: <span id="${showPlaces}">1</span>
                        </label>
                    </div>
                    <div class="btn-group" data-toggle="buttons" style="display: inline-block;">
                        <label></label>
                        <c:forEach items="${roomCategory}" var="category">
                            <label class="btn btn-sm btn-success">
                                <input id="category_${category}" type="radio" name="category" value="${category}"
                                       onchange="setCategory($(this).val());"/>${category}
                            </label>
                        </c:forEach>
                        <p><spring:message code="title.Room_Category"/>: <span id="${showCategory}">ECONOMY</span></p>
                    </div>
                </div>
                <div style="display: inline-block;">
                    <input type="button" class="btn btn-sm btn-primary"
                           onclick="sendOrder();"
                           value="<spring:message code='send.Order'/>"/>
                </div>
                <div id="${calendar}" class="btn"
                     style="display: inline-block; background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                    <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                    <span></span>
                    <b class="caret"></b>
                </div>
                </div>
            </div>
            <div>
                <div id="map-canvas" class="col-md-4 well"></div>
            </div>
        </c:if>
    </div>
</c:if>