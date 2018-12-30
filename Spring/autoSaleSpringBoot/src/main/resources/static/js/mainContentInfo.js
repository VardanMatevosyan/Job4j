function loadOfferInfo(data) {
    var statusButtonDiv= "";
    var pictureDiv = "";
    var description = "";
    var offerDetails = "";
    var carInfo = "";
    var currentUserId = MY_APP_VARS.currentUser.id;
    var currentUserRoleName = MY_APP_VARS.currentUser.roleName;
    for (var i = 0; i < data.length; i++) {
        var picture = data[i].picture;
        offersData = "";

        var buttonOwner = currentUserId == data[i].userId || currentUserRoleName == "ROLE_ADMIN";
        var phoneNumber = data[i].phoneNumber;
        var  formatPhoneNumber = "+7 " + phoneNumber.substring(0, 3) + " - " + phoneNumber.substring(3, 6) +
            " - " +  phoneNumber.substring(6, 8) + " - " + phoneNumber.substring(8, phoneNumber.length);

        offersData += "<div class=\"inner_main_out_wrapper_user_offers\"> "
                        + "<div class=\"main_info_car\" id=\"offer_main_info_car\">"
                            + "<div class=\"main_info_car_wrapper\">";

        if (buttonOwner) {
            offersData += "<div class=\"func-div-offer-icon-wrapper\">"
                            + "<p class=\"func-div-offer-icon func-div-offer-icon-second\" data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\">"
                                + "<button class=\"btn btn-xs btn-danger radius-btn deleteOfferButton\" data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#delete\">"
                                    + "<span class=\"fa fa-trash\">"
                                    + "</span>"
                                + "</button>"
                            + "                <input id=\"offerHiddenIdForDeleting\" type=\"hidden\" value=\"" + data[i].offerId + "\">"
                            + "</p>"

                            + "<p class=\"func-div-offer-icon\" title=\"Edit\">"
                                + "<button class=\"btn btn-xs btn-primary radius-btn editOfferButton\" data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#edit\">"
                                    + "<span class=\"fa fa-pencil\">"
                                    + "</span>"
                                + "</button>"
                                + "                <input id=\"offerHiddenIdForEdit\" type=\"hidden\" value=\"" + data[i].offerId + "\">"
                            + "</p>"
                        + "</div>"
        }

        offersData +=  "<div id=\"top_offer_info\">"
            + "<h3 id=\"offerModalTittle\" class=\"modal-tittle\"><b id=\"offerTittle\"  class=\"description\">" + data[i].tittle + "</b></h3>"
            + "<p><b class=\"description\">Price: </b><em id=\"offerPrice\">" + data[i].price + "</em></p>"
            + "<p><b class=\"description\">Posting date: </b><em id=\"postingDate\">" + moment(data[i].postingDate).format('dddd, DD MM YYYY, h:mm') + "</em></p>"
            + "<p><b class=\"description\">Address: </b><em id=\"offeraddress\">" + data[i].address + "</em></p>"
            + "</div>";


        statusButtonDiv = "<div class=\"status center-block\" id=\"offerStatus\">";
        if (data[i].soldState === true) {
            if (buttonOwner) {
                statusButtonDiv += "<input class=\"btn btn-sm  btn-success buttonSellState\" type=\"button\" value=\"" + "Sold" + "\">"
                    + "                <input id=\"offerHiddenId\" type=\"hidden\" value=\"" + data[i].offerId + "\">";
            } else if (!buttonOwner) {
                statusButtonDiv += "<input class=\"btn btn-sm  btn-success buttonSellState\" type=\"button\" value=\"" + "Sold" + "\" disabled>"
                    + "            <input id=\"offerHiddenId\" type=\"hidden\" value=\"" + data[i].offerId + "\">";
            }

        } else {
            if (buttonOwner) {
                statusButtonDiv += "<input class=\"btn btn-sm  btn-primary buttonSellState\" type=\"button\" value=\"" + "Sell" + "\">"
                    + "                <input id=\"offerHiddenId\" type=\"hidden\" value=\"" + data[i].offerId + "\">";
            } else if (!buttonOwner)
                statusButtonDiv += "<input class=\"btn btn-sm  btn-primary buttonSellState\" type=\"button\" value=\"" + "Sell" + "\" disabled>"
                    + "            <input id=\"offerHiddenId\" type=\"hidden\" value=\"" + data[i].offerId + "\">";
        }

        statusButtonDiv += "</div>";


        pictureDiv = "<div id=\"photoCar\">"
            + "<div id=\"photoCar_wrapper\">";

        if (picture.indexOf("default.jpeg") !== -1) {
            pictureDiv += "<div class=\"load_default_img\" id=\"image_preview_addOffer\" >"
                + "<img id=\"carPicture\" class=\"img-thumbnail\" src=\"images/default.jpeg\" alt=\"photo car\" />"
                + "</div>";
        } else {
            pictureDiv += "<img id=\"carPicture\" class=\"img-thumbnail\" src= " + "\"" + picture + "\"" + " " + "th:src='@{/" + picture +"}'" + " alt=\"photo car\"/>"
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
                                    + "<li id=\"carBrand\" class=\"list-group-item\">" + "<b class=\"description\">Brand: </b>" + "<span class=\"data\">" + data[i].brand + "</span>" + "</li>"
                                    + "<li id=\"carVehicle\" class=\"list-group-item\">"  + "<b class=\"description\">Vehicle: </b>" + "<span class=\"data\">" + data[i].modelVehicle + "</span>" + "</li>"
                                    + "<li id=\"carYerOfManufacture\" class=\"list-group-item\">"
                                        + "<b class=\"description\">Year of manufacture: </b>" + "<span class=\"data\" id = \"carYearOfManufacture\">" + moment(data[i].yearOfManufacture).format('DD/MM/YYYY') + "</span></li>"
                                    + "</li>"
                                    + "<li id=\"carBodyType\" class=\"list-group-item\">" + "<b class=\"description\">Body type: </b>" + "<span class=\"data\">" + data[i].bodyType + "</span>" + "</li>"
                                    + "<li id=\"carGearBox\" class=\"list-group-item\">" + "<b class=\"description\">Gear box: </b>" +  "<span class=\"data\">" + data[i].gearBox + "</span>" + "</li>"
                                    + "<li id=\"carEngineCapacity\" class=\"list-group-item\">"
                                        + "<b class=\"description\">Engine capacity: </b>" + "<span class=\"data\" id = \"carEngineCapacity\">" + data[i].engineCapacity + "</span>"
                                    + "</li>"
                                + "</ul>"
                            + "</div>";

        carInfo = "<div id=\"car_user_info\">"
                    + "<ul class=\"list-group\" id=\"userDetail\">"
                        + "<li class=\"list-group-item\"><b>Car owner: </b></li>"
                        + "<li id=\"userName\" class=\"list-group-item\">" + "<b class=\"description\">Name: </b>" +  "<span class=\"data\">" + data[i].name + "</span>" + "</li>"
                        + "<li id=\"userCity\" class=\"list-group-item\">" + "<b class=\"description\">City: </b>" + "<span class=\"data\">" + data[i].city + "</span>" + "</li>"
                        + "<li id=\"userPhoneNumber\" class=\"list-group-item\">" + "<b class=\"description\">Phone number: </b>" + "<span class=\"data\">" + formatPhoneNumber + "</span>" + "</li>"
                    + "</ul>"
                + "</div>";

        description = "<div class=\"offer_description\" id=\"offerDescription\">"
            + "<div id=\"offer_description_wrapper\">"
            + "<h3 class=\"modal-tittle\"><b class=\"description\">Description</b></h3>"
            + "<span><em id=\"offerDescription\">" + data[i].description + "</em></span>"
            + "</div> "
            + "</div>";

        offersData += offerDetails + carInfo + description
            + "</div>"
            + "</div>"
            + "</div>"
            + "</div>"
            + "</div>";

        if ($('.main_out_wrapper_user_offers > div').length >= 1 && data.length <= 1) {
            $('.main_out_wrapper_user_offers > div:nth-child(1)').before(offersData);
        } else {
            $('.main_out_wrapper_user_offers').append(offersData);
        }
    }
    changeStatusEvent();
    eventToLoadDataToTheEditModalWindow();
    getHiddenOfferId();
    eventToLoadDataFromEditFormToTheObject();
}