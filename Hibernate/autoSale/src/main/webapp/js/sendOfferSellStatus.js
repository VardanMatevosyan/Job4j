$(document).ready( function () {
    var statusButton = $('.buttonSellState');
    var value = true;

    statusButton.on('click', function () {
        var buttonValue = $(this).text();
        var thisStatus = $(this).next('#offerHiddenId');
        var offerIdValue = thisStatus.val();
        if (buttonValue === 'Sold') {
            value = false;
        }

        if ($(this).attr('class').indexOf('btn-primary') !== -1) {
            $(this).attr({class: 'btn btn-sm  btn-success buttonSellState', value: 'Sold'});
            $(this).text('Sold');
        } else {
            $(this).attr({class: 'btn btn-sm  btn-primary buttonSellState', value: 'Sell'});
            $(this).text('Sell');
        }
        console.log("value status  " + value);
        console.log("value id  " + offerIdValue);
        $.ajax({
            url: './offerSellStatusValue',
            scriptCharset: 'UTF-8',
            method: 'POST',
            dataType: 'json',
            mimeType:'application/json',
            contentType: 'application/json',
            data: JSON.stringify({statusButton:value, offerId:offerIdValue})
    });
    });
});