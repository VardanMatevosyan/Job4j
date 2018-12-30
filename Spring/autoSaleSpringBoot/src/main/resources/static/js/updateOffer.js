
    var currentOfferButton;
    var editFormButton;
    var offerCurrentObj;
    var offerEditId;
    var $parent;
    var $offerTittle;

    function eventToLoadDataFromEditFormToTheObject() {
        $(".editOfferButton").on("click", function () {
            currentOfferButton = $(this);
            offerEditId = $(this).closest("p").find("#offerHiddenIdForEdit").val();
            $parent = $(this).closest("div.inner_main_out_wrapper_user_offers");
            $offerTittle  = $parent.find("b#offerTittle");

            offerCurrentObj = {
                offerTittle: $offerTittle.text()
            };
        });
    }
    $("#editOfferToTheSystem").on("click", function (e) {
        e.preventDefault();
        editFormButton = $(this);
        var currentUserRoleName = MY_APP_VARS.currentUser.roleName;
        var inputTittle = $("#tittleEdit").val();
        var inputPrice = $("#priceEdit").val();
        var inputOfferAddress = $("#offerAddressEdit").val();
        var inputImage_preview_editOffer;

        var currentName = $(".editFile").attr("name");
        if (document.querySelector('.editFile').files === undefined || document.querySelector('.editFile').files.item(0) === null
            || document.querySelector('.editFile').files.item(0) === undefined) {
            inputImage_preview_editOffer = currentName;
        } else {
            inputImage_preview_editOffer = document.querySelector('.editFile').files.item(0).name;
        }

        var inputCarBrand = $("#CarBrandEdit").val();
        var inputCarModelVehicle = $("#CarModelVehicleEdit").val();
        var inputCarYearOfManufacture = $("#CarYearOfManufactureEdit").val().split(".").reverse().join("-");
        var inputCarBodyType = $("#CarBodyTypeEdit").val();
        var inputCarGearBox = $("#CarGearBoxEdit").val();
        var inputCarEngineCapacity = $("#CarEngineCapacityEdit").val();
        var inputOfferDescription = $("#offerDescriptionEdit").val();

        var formData = new FormData();

        var file = document.querySelector('.editFile').files.item(0);
        if (file !== null && file !== undefined && (file.type.match(/image.*/))) {
            formData.append("file", file);
            console.log("image append formdata");
        }

        var json = JSON.stringify({
            picture: inputImage_preview_editOffer,
            car: {
                brand: inputCarBrand,
                modelVehicle: inputCarModelVehicle
            }
        });

        var offerChangedObj = {
            tittle: inputTittle,
            price: inputPrice,
            address: inputOfferAddress,
            description: inputOfferDescription,
            picture: inputImage_preview_editOffer,
            car: {
                yearOfManufacture: inputCarYearOfManufacture,
                brand: inputCarBrand,
                modelVehicle: inputCarModelVehicle,
                bodyType: inputCarBodyType,
                gearBox: inputCarGearBox,
                engineCapacity: inputCarEngineCapacity
            }
        };

        var jsonBlobFile = new Blob([json], {type: "application/json"});
        formData.append("jsonData", jsonBlobFile);

        if (currentUserRoleName === '' || currentUserRoleName === null) {
            currentUserRoleName = "anonymous"
        }

        $.ajax({
            url: "/" + currentUserRoleName + '/update/' + offerEditId,
            scriptCharset: 'UTF-8',
            method: 'PUT',
            mimeType: "application/json",
            contentType: "application/json",
            Accept: "json",
            data: JSON.stringify(offerChangedObj),
            success: function (data, textStatus, jqXHR) {
                $("#closeEditOfferModalWindow").click();
                $parent.find("b#offerTittle").text(data.tittle);
                $parent.find("b#offerPrice").text(data.price);
                $parent.find("em#offerDescription").text(data.description);
                $parent.find("img#carPicture").attr("src", data.picture);
                $parent.find("b#carBrand").text(data.car.brand);
                $parent.find("span#carYearOfManufacture").text(moment(data.car.yearOfManufacture).format("DD/MM/YYYY"));
                $parent.find("b#carmModelVehicle").text(data.car.modelVehicle);
                $parent.find("b#carBodyType").text(data.car.bodyType);
                $parent.find("b#carGearBox").text(data.car.gearBox);
                $parent.find("span#carEngineCapacity").text(data.car.engineCapacity);
            },
            error: function (data, textStatus, jqXHR) {
                alert(textStatus);
            }
        });


        if ((inputImage_preview_editOffer !== undefined || inputImage_preview_editOffer !== null
            || inputImage_preview_editOffer !== '' || inputImage_preview_editOffer !== currentName)) {
            $.ajax({
                url: "/"+ currentUserRoleName + '/uploadFile',
                method: 'POST',
                enctype: 'multipart/form-data',
                cache: false,
                contentType: false,
                processData: false,
                headers: {
                    "Content-Type": undefined
                },
                data: formData,
                error: function (message) {
                    console.log(message);
                }
            });
        }

        });