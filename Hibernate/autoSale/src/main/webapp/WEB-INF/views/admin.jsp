<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Main admin page</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style><%@include file="../../css/style.css"%></style>
</head>
<body>

<div class="row justify-content-center">
    <%@include file="addOffer.html"%>
    <%@include file="BrandFilterModalForm.html"%>
    <div class="menu col-xs-12 col-sm-4 col-sm-pull-8 col-md-4 col-md-pull-8">
        <div class="wrapper">
            <div class="user_welcome_aria">
                <h3 class="modal-tittle"><b>Welcome</b> <em><c:out value=" ${currentUser.name}"/></em></h3>

                <form class="form_signOut" action="${pageContext.request.contextPath}/signOut" method="post">
                    <input aria-pressed="true" class="btn btn-link btn-xs" id="userLogOut" type="submit" name="method" value="sign out"/>
                </form>
            </div>
            <ul class="list-group navigation">
                <li class="list-group-item"><a href="${pageContext.request.contextPath}">Main</a></li>
                <li class="list-group-item"><a href="${pageContext.servletContext.contextPath}/offer" data-toggle="modal" data-target="#modalAddOfferWindow">Add offer</a></li>
                <li class="list-group-item"><a id="lastDayOffers" href="${pageContext.servletContext.contextPath}/lastAddedOffers">For last day</a></li>
                <li class="list-group-item"><a id="withAddedPhoto" href="${pageContext.servletContext.contextPath}/withPhoto">With photo</a></li>
                <li class="list-group-item"><a data-toggle="modal" data-target="#modalFilterByBrandWindow" href="${pageContext.servletContext.contextPath}/withBrands">By brands</a></li>
            </ul>
        </div>
    </div>

    <div class="main-container_user_offers col-xs-12 col-sm-8 col-sm-push-4 col-md-8 col-md-push-4">
        <div class="main_out_wrapper_user_offers">

        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
    <%@include file="../../js/offersFilteringBybrands.js"%>
    <%@include file="../../js/offersWithPhoto.js"%>
    <%@include file="../../js/lastDayOffers.js"%>
    <%@include file="../../js/loadImagePrewiev.js"%>
    <%@include file="../../js/modalWindow.js"%>
    <%@include file="../../js/mainContentInfo.js"%>
    <%@include file="../../js/offerAddingInfoToJson.js"%>
    <%@include file="../../js/validateAddOfferInputFields.js"%>
    <%@include file="../../js/getAllOffersJson.js"%>
    <%@include file="../../js/sendOfferSellStatus.js"%>
    <%@include file="../../js/moment.min.js"%>
</script>
</body>
</html>
