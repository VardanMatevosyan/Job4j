$(document).ready( function () {
    var lastDayOffersTag = $('#lastDayOffers');
    var allOffersInThePage = $('.main_out_wrapper_user_offers');

    lastDayOffersTag.on('click', function (e) {
        e.preventDefault();
        $.ajax({
            url: './lastAddedOffers',
            scriptCharset: 'UTF-8',
            method: 'GET',
            mimeType: 'application/json',
            dataType: "text json",
            complete: function (data) {
                var array = $.parseJSON(data.responseText);
                allOffersInThePage.empty();
                loadOfferInfo(array);
            }
        });
    });
});

