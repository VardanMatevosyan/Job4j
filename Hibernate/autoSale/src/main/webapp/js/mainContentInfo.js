function loadOfferInfo(data) {
    var statusButtonDiv= "";
    var pictureDiv = "";
    var description = "";
    var offerDetails = "";
    var carInfo = "";

    for (var i = 0; i < data.offers.length; i++) {
        var picture = data.offers[i].picture;
        offersData = "";
        var buttonOwner = (data.currentUserId === data.offers[i].userId) || (data.currentUserRoleName === "admin");
        var phoneNumber = data.offers[i].phoneNumber;
        var  formatPhoneNumber = "+7 " + phoneNumber.substring(0, 3) + " - " + phoneNumber.substring(3, 6) +
            " - " +  phoneNumber.substring(6, 8) + " - " + phoneNumber.substring(8, phoneNumber.length);

        offersData += "<div class=\"inner_main_out_wrapper_user_offers\"> "
            + "<div class=\"main_info_car\" id=\"offer_main_info_car\">"
            + "<div class=\"main_info_car_wrapper\">"

            + "<div id=\"top_offer_info\">"
            + "<h3 id=\"offerModalTittle\" class=\"modal-tittle\"><b>" + data.offers[i].tittle + "</b></h3>"
            + "<p><b>Price: </b><em>" + data.offers[i].price + "</em></p>"
            + "<p><b>Posting date: </b><em id=\"postingDate\">" + moment(data.offers[i].postingDate).format('dddd, DD MM YYYY, h:mm') + "</em></p>"
            + "<p><b>Address: </b><em>" + data.offers[i].address + "</em></p>"
            + "</div>";


        statusButtonDiv = "<div class=\"status center-block\" id=\"offerStatus\">";
        if (data.offers[i].soldState === true) {
            if (buttonOwner) {
                statusButtonDiv += "<input class=\"btn btn-sm  btn-success buttonSellState\" type=\"button\" value=\"" + "Sold" + "\">"
                    + "                <input id=\"offerHiddenId\" type=\"hidden\" value=\"" + data.offers[i].offerId + "\">";
            } else if (!buttonOwner) {
                statusButtonDiv += "<input class=\"btn btn-sm  btn-success buttonSellState\" type=\"button\" value=\"" + "Sold" + "\" disabled>"
                    + "            <input id=\"offerHiddenId\" type=\"hidden\" value=\"" + data.offers[i].offerId + "\">";
            }

        } else {
            if (buttonOwner) {
                statusButtonDiv += "<input class=\"btn btn-sm  btn-primary buttonSellState\" type=\"button\" value=\"" + "Sell" + "\">"
                    + "                <input id=\"offerHiddenId\" type=\"hidden\" value=\"" + data.offers[i].offerId + "\">";
            } else if (!buttonOwner)
                statusButtonDiv += "<input class=\"btn btn-sm  btn-primary buttonSellState\" type=\"button\" value=\"" + "Sell" + "\" disabled>"
                    + "            <input id=\"offerHiddenId\" type=\"hidden\" value=\"" + data.offers[i].offerId + "\">";
        }

        statusButtonDiv += "</div>";


        pictureDiv = "<div id=\"photoCar\">"
            + "<div id=\"photoCar_wrapper\">";

        if (picture.indexOf("default.jpeg") !== -1) {
            pictureDiv += "<div class=\"load_default_img\" id=\"image_preview_addOffer\" >"
                + "<img class=\"img-thumbnail\" src=\"images/default.jpeg\" alt=\"photo car\" />"
                + "</div>";
        } else {
            var pictureName = picture.substring(78, picture.length);
            pictureDiv += "<img class=\"img-thumbnail\" src=\"images" + pictureName + "\"" + " alt=\"photo car\"/>"
        }
        pictureDiv += "</div></div>";

        offersData += statusButtonDiv + pictureDiv;

        offersData += "</div>"
            + "</div>";

        offerDetails = "<div class=\"offer_details\">"
            + "<div class=\"offer_details_wrapper\">"
            + "<div id=\"offer_add_info\">"
            + "<ul class=\"list-group\" id=\"carDetail\">"
            + "<li class=\"list-group-item\"><b>Car details</b></li>"
            + "<li class=\"list-group-item\">" + data.offers[i].brand +" </li>"
            + "<li class=\"list-group-item\">" + "Vehicle: " + data.offers[i].modelVehicle + "</li>"
            + "<li class=\"list-group-item\">"

            + "<span>" + "Year of manufacture: " + moment(data.offers[i].yearOfManufacture).format('DD/MM/YYYY') + "</span></li>"
            + "</li>"
            + "<li class=\"list-group-item\">" + "Body type: " + data.offers[i].bodyType + "</li>"
            + "<li class=\"list-group-item\">" + "Gear box: " + data.offers[i].gearBox + "</li>"
            + "<li class=\"list-group-item\">"
            + "<span>Engine capacity: </span>" + data.offers[i].engineCapacity
            + "</li>"
            + "</ul>"
            + "</div>";

        carInfo = "<div id=\"car_user_info\">"
            + "<ul class=\"list-group\" id=\"userDetail\">"
            + "<li class=\"list-group-item\"><b>Car owner</b>:</li>"
            + "<li class=\"list-group-item\">" + "Name: " + data.offers[i].name + "</li>"
            + "<li class=\"list-group-item\">" + "City: " + data.offers[i].city + "</li>"
            + "<li class=\"list-group-item\">"
            + "<span>" + "Phone number: " + formatPhoneNumber + "</span>"
            + "</li>"
            + "</ul>"
            + "</div>";

        description = "<div class=\"offer_description\" id=\"offerDescription\">"
            + "<div id=\"offer_description_wrapper\">"
            + "<h3 class=\"modal-tittle\"><b>Description</b></h3>"
            + "<span><em>" + data.offers[i].description + "</em></span>"
            + "</div> "
            + "</div>";

        offersData += offerDetails + carInfo + description
            + "</div>"
            + "</div>"
            + "</div>"
            + "</div>"
            + "</div>";

        $('.main_out_wrapper_user_offers').append(offersData);
    }
    changeStatusEvent();
}