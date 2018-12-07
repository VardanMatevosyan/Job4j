$(document).ready( function () {
    var lastDayOffersTag = $('#lastDayOffers');
    var allOffersInThePage = $('.main_out_wrapper_user_offers');
    var currentUserRoleName;

    if ('${currentUser.role.name}' === '' || '${currentUser.role.name}' === null) {
        currentUserRoleName = "anonymous"
    } else {
        currentUserRoleName = '${currentUser.role.name}';
    }

    lastDayOffersTag.on('click', function (e) {
        e.preventDefault();
        $.ajax({
            url: '/' + currentUserRoleName + '/lastAddedOffers',
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

