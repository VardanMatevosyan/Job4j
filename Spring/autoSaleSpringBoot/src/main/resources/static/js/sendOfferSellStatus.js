function changeStatusEvent() {
    $(".buttonSellState").on("click", function () {
        var value = true;
        var buttonValue = $(this).val();
        var thisStatus = $(this).next('#offerHiddenId');
        var offerIdValue = thisStatus.val();
        var currentUserRoleName = MY_APP_VARS.currentUser.roleName;

        if (currentUserRoleName === '' || currentUserRoleName === null) {
            currentUserRoleName = "anonymous"
        }

        if (buttonValue === 'Sold') {
            value = false;
        }

        if ($(this).attr('class').indexOf('btn-primary') !== -1) {
            $(this).attr({class: 'btn btn-sm  btn-success buttonSellState', value: 'Sold'});
        } else {
            $(this).attr({class: 'btn btn-sm  btn-primary buttonSellState', value: 'Sell'});
        }

        $.ajax({
            url: "/" + currentUserRoleName + '/offerSellStatusValue',
            scriptCharset: 'UTF-8',
            method: 'PUT',
            mimeType: 'application/json',
            contentType: 'application/json',
            data: JSON.stringify({soldState: value, id: offerIdValue}),
            complete: function (data) {
                console.log("change status is Ok =)");
            }
        });

    })

}