$(document).ready( function () {
    var allOffersInThePage = $('.main_out_wrapper_user_offers');
    $('#sendBrandMarks').on('click', function (e) {
        e.preventDefault();
        var $brands = document.querySelectorAll("input.brands:checked");
        var arrayOfChecked = Array.from($brands).map(cb => cb.value);
        var $form = $("#brandMarks");
        $.ajax({
            url: './withBrands',
            scriptCharset: 'UTF-8',
            method: 'GET',
            mimeType: 'application/json',
            contentType: 'application/json',
            dataType: "json",
            data: $form.serialize(),
            complete: function (data) {
                var array = $.parseJSON(data.responseText);
                $("#closeAddCarsBrandModalWindow").click();
                allOffersInThePage.empty();
                loadOfferInfo(array);
            }
        });
    });
});

