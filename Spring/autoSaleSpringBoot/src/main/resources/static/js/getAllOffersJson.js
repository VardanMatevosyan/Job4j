$(document).ready( function () {
    var allOffersInThePage = $('.main_out_wrapper_user_offers');
    var urlAddr;
    var userRoleName;
    var currentUserRoleName = MY_APP_VARS.currentUser.roleName;
    console.log("currentUserRoleName " + currentUserRoleName);
    if (currentUserRoleName === '' || currentUserRoleName === null) {
        urlAddr = '/anonymous/allOffers';
    } else {
        urlAddr = currentUserRoleName + '/allOffers';
    }

    $.ajax({
        url: urlAddr,
        scriptCharset: 'UTF-8',
        method: 'GET',
        mimeType: 'application/json',
        dataType: "text json",
        complete: function (data) {
            var obj = $.parseJSON(data.responseText);
            allOffersInThePage.empty();
            loadOfferInfo(obj);
        }
    });

});

