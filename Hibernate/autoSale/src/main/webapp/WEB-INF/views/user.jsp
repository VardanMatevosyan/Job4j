<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style><%@include file="../../css/style.css"%></style>
    <link rel="import" href="addOffer.html">
</head>
<body>

<div class="row justify-content-center">
    <%@include file="addOffer.html"%>
    <div class="menu col-xs-12 col-sm-4 col-sm-pull-8 col-md-4 col-md-pull-8">
        <div class="wrapper">
            <div class="user_welcome_aria">
                <h3 class="modal-tittle"><b>Welcome</b> <em><c:out value=" ${currentUser.name}"/></em></h3>

                <form class="form_signOut" action="${pageContext.request.contextPath}/signOut" method="post">
                    <input aria-pressed="true" class="btn btn-link btn-xs" id="userLogOut" type="submit" name="method" value="sign out"/>
                </form>
            </div>
            <ul class="list-group navigation">

                <li class="list-group-item"><a href="${pageContext.servletContext.contextPath}/offer" data-toggle="modal" data-target="#modalAddOfferWindow">Add offer</a></li>
                <li class="list-group-item"><a href="#">My offers</a></li>
                <li class="list-group-item"><a href="#">Other</a></li>
            </ul>
        </div>
    </div>

    <div class="main-container_user_offers col-xs-12 col-sm-8 col-sm-push-4 col-md-8 col-md-push-4">
        <div class="main_out_wrapper_user_offers">

            <c:forEach items="${offers}" var="offer">
                <div class="inner_main_out_wrapper_user_offers">
                    <div class="main_info_car" id="offer_main_info_car">
                        <div class="main_info_car_wrapper">

                            <div id="top_offer_info">
                                <h3 id="offerModalTittle" class="modal-tittle"><b><c:out value="${offer.tittle}"/></b></h3>
                                <p><b>Price: </b><em> <fmt:formatNumber value="${offer.price}" pattern="#,##"/></em></p>
                                <p><b>Posting date: </b><em id="postingDate"> <fmt:formatDate value="${offer.postingDate}" pattern="yyyy.MM.dd HH:mm"/></em></p>
                                <p><b>Address: </b><em> <c:out value="${offer.address}"/></em></p>
                            </div>


                            <div class="status center-block" id="offerStatus">

                                <c:set var="ownerOrAdmin" value="${offer.user.id eq sessionScope.currentUser.id || sessionScope.currentUser.role.name eq 'admin'}"/>
                                <c:choose>
                                    <c:when test="${offer.soldState == true}">
                                            <c:if test="${ownerOrAdmin}">
                                                <button class="btn btn-sm  btn-success buttonSellState">Sold</button>
                                                <input id="offerHiddenId" type="hidden" value="${offer.id}">
                                            </c:if>
                                        <c:if test="${!ownerOrAdmin}">
                                            <button class="btn btn-sm  btn-success buttonSellState" disabled>Sold</button>
                                            <input id="offerHiddenId" type="hidden" value="${offer.id}">
                                        </c:if>
                                    </c:when>

                                    <c:otherwise>
                                        <c:if test="${ownerOrAdmin}">
                                            <button class="btn btn-sm  btn-primary buttonSellState">Sell</button>
                                            <input id="offerHiddenId" type="hidden" value="${offer.id}">
                                        </c:if>
                                        <c:if test="${!ownerOrAdmin}">
                                            <button class="btn btn-sm  btn-primary buttonSellState" disabled>Sell</button>
                                            <input id="offerHiddenId" type="hidden" value="${offer.id}">
                                        </c:if>
                                    </c:otherwise>

                                </c:choose>

                            </div>

                            <div id="photoCar">
                                <div id="photoCar_wrapper">
                                    <c:choose>
                                        <c:when test="${offer.picture  eq ''}">
                                            <div class="load_default_img" id="image_preview_addOffer" >
                                                <img class="img-thumbnail" src="${pageContext.request.contextPath}/images/default.jpeg" alt="photo car" />
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="picture" value="${offer.picture}"/>
                                            <img class="img-thumbnail" src="${pageContext.request.contextPath}/images${fn:substring(picture, 78, picture.length())}" alt="photo car"/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="offer_details">
                        <div class="offer_details_wrapper">
                            <div id="offer_add_info">
                                    <ul class="list-group" id="carDetail">
                                            <li class="list-group-item"><b>Car details</b></li>
                                            <li class="list-group-item"><c:out value="Brand: ${offer.car.brand}"/></li>
                                            <li class="list-group-item"><c:out value="Vehicle: ${offer.car.modelVehicle}"/></li>
                                            <li class="list-group-item">
                                                <jsp:text>Year of manufacture: </jsp:text><fmt:formatDate type="date" value="${offer.car.yearOfManufacture}"/>
                                                </li>
                                            <li class="list-group-item"><c:out value="Body type: ${offer.car.bodyType}"/></li>
                                            <li class="list-group-item"><c:out value="Gear box: ${offer.car.gearBox}"/></li>
                                            <li class="list-group-item">
                                                    <jsp:text>Engine capacity: </jsp:text><fmt:formatNumber type="number" maxFractionDigits="2" value="${offer.car.engineCapacity}"/>
                                            </li>
                                    </ul>
                            </div>
                            <div id="car_user_info">
                                    <ul class="list-group" id="userDetail">
                                        <li class="list-group-item"><b>Car owner</b>:</li>
                                        <li class="list-group-item"><c:out value="Name: ${offer.user.name}"/></li>
                                        <li class="list-group-item"><c:out value="City: ${offer.user.city}"/></li>

                                        <c:set var="phone" value="${offer.user.phoneNumber}"/>
                                        <li class="list-group-item">
                                                <c:out value="Phone number: +7 (${fn:substring(phone, 0, 3)}) - ${fn:substring(phone, 3, 6)}
                                                - ${fn:substring(phone, 6, 8)} - ${fn:substring(phone, 8, fn:length(phone))}"/>
                                        </li>
                                    </ul>
                            </div>

                            <div class="offer_description" id="offerDescription">
                                <div id="offer_description_wrapper">
                                    <h3 class="modal-tittle"><b>Description</b></h3>
                                    <span><em><c:out value="${offer.description}"/></em></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
    <%@include file="../../js/loadImagePrewiev.js"%>
    <%@include file="../../js/modalWindow.js"%>
    <%@include file="../../js/sendOfferSellStatus.js"%>
    <%@include file="../../js/offerAddingInfoToJson.js"%>
    <%@include file="../../js/validateAddOfferInputFields.js"%>
</script>
</body>
</html>