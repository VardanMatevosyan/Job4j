function changeStatusEvent() {
    var currentButton;
    $(".buttonSellState").on("click", function () {
        var value = true;
        var buttonValue = $(this).val();
        var thisStatus = $(this).next('#offerHiddenId');
        var offerIdValue = thisStatus.val();
        var currentUserRoleName = MY_APP_VARS.currentUser.roleName;
        currentButton = $(this);
        if (currentUserRoleName === '' || currentUserRoleName === null) {
            currentUserRoleName = "anonymous"
        }

        if (buttonValue === 'Sold') {
            value = false;
        }

        $.ajax({
            url: "/" + currentUserRoleName + '/offerSellStatusValue',
            scriptCharset: 'UTF-8',
            method: 'PUT',
            mimeType: 'application/json',
            contentType: 'application/json',
            Accept: "json",
            data: JSON.stringify({soldState: value, id: offerIdValue}),
            success: function (data, textStatus) {
                if (data === true) {
                    if (currentButton.attr('class').indexOf('btn-primary') !== -1) {
                        currentButton.attr({class: 'btn btn-sm  btn-success buttonSellState', value: 'Sold'});
                    } else {
                        currentButton.attr({class: 'btn btn-sm  btn-primary buttonSellState', value: 'Sell'});
                    }
                }
            }
        });

    })

}