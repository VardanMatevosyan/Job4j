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
            + "<div class=\"main_info_car_wrapper\">"

            + "<div id=\"top_offer_info\">"
            + "<h3 id=\"offerModalTittle\" class=\"modal-tittle\"><b>" + data[i].tittle + "</b></h3>"
            + "<p><b>Price: </b><em>" + data[i].price + "</em></p>"
            + "<p><b>Posting date: </b><em id=\"postingDate\">" + moment(data[i].postingDate).format('dddd, DD MM YYYY, h:mm') + "</em></p>"
            + "<p><b>Address: </b><em>" + data[i].address + "</em></p>"
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
                + "<img class=\"img-thumbnail\" src=\"images/default.jpeg\" alt=\"photo car\" />"
                + "</div>";
        } else {
            pictureDiv += "<img class=\"img-thumbnail\" src= " + "\"" + picture + "\"" + " " + "th:src='@{/" + picture +"}'" + " alt=\"photo car\"/>"
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
            + "<li class=\"list-group-item\">" + data[i].brand +" </li>"
            + "<li class=\"list-group-item\">" + "Vehicle: " + data[i].modelVehicle + "</li>"
            + "<li class=\"list-group-item\">"

            + "<span>" + "Year of manufacture: " + moment(data[i].yearOfManufacture).format('DD/MM/YYYY') + "</span></li>"
            + "</li>"
            + "<li class=\"list-group-item\">" + "Body type: " + data[i].bodyType + "</li>"
            + "<li class=\"list-group-item\">" + "Gear box: " + data[i].gearBox + "</li>"
            + "<li class=\"list-group-item\">"
            + "<span>Engine capacity: </span>" + data[i].engineCapacity
            + "</li>"
            + "</ul>"
            + "</div>";

        carInfo = "<div id=\"car_user_info\">"
            + "<ul class=\"list-group\" id=\"userDetail\">"
            + "<li class=\"list-group-item\"><b>Car owner</b>:</li>"
            + "<li class=\"list-group-item\">" + "Name: " + data[i].name + "</li>"
            + "<li class=\"list-group-item\">" + "City: " + data[i].city + "</li>"
            + "<li class=\"list-group-item\">"
            + "<span>" + "Phone number: " + formatPhoneNumber + "</span>"
            + "</li>"
            + "</ul>"
            + "</div>";

        description = "<div class=\"offer_description\" id=\"offerDescription\">"
            + "<div id=\"offer_description_wrapper\">"
            + "<h3 class=\"modal-tittle\"><b>Description</b></h3>"
            + "<span><em>" + data[i].description + "</em></span>"
            + "</div> "
            + "</div>";

        offersData += offerDetails + carInfo + description
            + "</div>"
            + "</div>"
            + "</div>"
            + "</div>"
            + "</div>";

        if (data.length <= 1) {
            $('.main_out_wrapper_user_offers > div:nth-child(1)').before(offersData);
        } else {
            $('.main_out_wrapper_user_offers').append(offersData);
        }
    }
    changeStatusEvent();
}