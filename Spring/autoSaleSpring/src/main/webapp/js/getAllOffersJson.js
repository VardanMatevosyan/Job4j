$(document).ready( function () {
    var allOffersInThePage = $('.main_out_wrapper_user_offers');
    var urlAddr;
    var userRoleName;
    if ('${currentUser.role.name}' === '') {
        urlAddr = '/anonymous/allOffers';
        console.log("urlAddr" + " " + urlAddr);
    } else {
        urlAddr = '/${currentUser.role.name}/allOffers';
    }

    $.ajax({
        url: urlAddr,
        scriptCharset: 'UTF-8',
        method: 'GET',
        mimeType: 'application/json',
        dataType: "text json",
        complete: function (data) {
            var obj = $.parseJSON(data.responseText);
            console.log(obj);
            allOffersInThePage.empty();
            loadOfferInfo(obj);
        }
    });

});

