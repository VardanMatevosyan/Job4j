function getHiddenOfferId() {
    $(".deleteOfferButton").on("click", function (e) {
        e.preventDefault();
        var hiddenIdValue = $(this).next("#offerHiddenIdForDeleting").val();
        console.log("val hidden id");
        console.log(hiddenIdValue);
        $("#offerHiddenIdForDeletingINModalWindow").attr("value", hiddenIdValue);
    });
}