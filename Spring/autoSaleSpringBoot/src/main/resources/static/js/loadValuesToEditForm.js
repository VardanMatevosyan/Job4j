function eventToLoadDataToTheEditModalWindow() {
    $(".editOfferButton").on("click", function (e) {
        e.preventDefault();
        loadData($(this));
    });

    function loadData(thisButton) {
        var $parent = thisButton.closest("div.inner_main_out_wrapper_user_offers");

        var $offerTittle = $parent.find("b#offerTittle");
        $("input#tittleEdit").attr("value", $offerTittle.text());
        var $offerPrice = $parent.find("#offerPrice");
        $("input#priceEdit").attr("value", $offerPrice.text());
        var $offerAddress = $parent.find("#offeraddress");
        $("input#offerAddressEdit").attr("value", $offerAddress.text());
        var $offerInputPicture = $parent.find("img#carPicture");
        var $AttrSRC = $offerInputPicture.attr("src");
        $("input#file").attr({name: $AttrSRC});
        $(".pictureEdit").attr({src: $AttrSRC});
        var $carBrand = $parent.find("li#carBrand > span.data");
        $("input#CarBrandEdit").attr("value", $carBrand.text());
        var $carVehicle = $parent.find("li#carVehicle > span.data");
        $("input#CarModelVehicleEdit").attr("value", $carVehicle.text());
        var $carYerOfManufacture = $parent.find("li#carYerOfManufacture > span.data");
        $("input#CarYearOfManufactureEdit").attr("value", $carYerOfManufacture.text().split('/').join('.'));
        var $carBodyType = $parent.find("li#carBodyType > span.data");
        $("input#CarBodyTypeEdit").attr("value", $carBodyType.text());
        var $carGearBox = $parent.find("li#carGearBox > span.data");
        $("input#CarGearBoxEdit").attr("value", $carGearBox.text());
        var $carEngineCapacity = $parent.find("li#carEngineCapacity > span.data");
        $("input#CarEngineCapacityEdit").attr("value", $carEngineCapacity.text());
        var $offerDescription = $parent.find("em#offerDescription");
        $("input#offerDescriptionEdit").attr("value", $offerDescription.text());
    }
}





