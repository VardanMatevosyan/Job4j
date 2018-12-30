$("#deleteOffer").on("click", function () {
    var currentOfferInputId = $(this).next('#offerHiddenIdForDeletingINModalWindow');
    var offerIdValue = currentOfferInputId.val();
    var currentUserRoleName = MY_APP_VARS.currentUser.roleName;

    if (currentUserRoleName === '' || currentUserRoleName === null) {
        currentUserRoleName = "anonymous"
    }

    $.ajax({
        url: "/" + currentUserRoleName + '/delete' + "/" + offerIdValue,
        scriptCharset: 'UTF-8',
        method: 'DELETE',
        success: function (data, textStatus, jqXHR) {
            if (jqXHR.status === 204 || jqXHR.status === 200) {
                var id = data.id;
                var selector = ".main_out_wrapper_user_offers input#offerHiddenId[value=\""+id+"\"]";
                var hiddenInputWithOfferId = $(selector);
                var parent = hiddenInputWithOfferId.closest("div.inner_main_out_wrapper_user_offers");
                parent.detach();
                $("#no").click();
            } else {
                alert("Can't delete the offer, something goes wrong on the server side");
            }
        },
        error: function (data, textStatus, jqXHR) {
            var div = $(document.createElement('div'));
            var col = $(document.createElement('div'));
            var span = $("<span>"+data.responseText+"</span>");
            div.addClass("modal-footer");
            div.addClass("errorFromDeleteOffer");
            div.append(span);
            $("input#deleteOffer").closest("div.modal-content").append(div);
            div.show(1000);
            div.delay(5000);
            div.hide(1000);
        }
    });

});
