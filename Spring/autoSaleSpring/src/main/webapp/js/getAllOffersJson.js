$(document).ready( function () {
    var allOffersInThePage = $('.main_out_wrapper_user_offers');

    $.ajax({
        url: '/${currentUser.role.name}/allOffers',
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

