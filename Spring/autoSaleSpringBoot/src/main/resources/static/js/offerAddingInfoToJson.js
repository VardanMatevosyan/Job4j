$(document).ready( function() {
        $("#addOfferToTheSystem").on("click", function (e) {
            e.preventDefault();
            if (validateFields()) {
                var currentUserRoleName = MY_APP_VARS.currentUser.roleName;
                var inputTittle = $("#tittle").val();
                var inputPrice = $("#price").val();
                var inputOfferAddress = $("#offerAddress").val();
                var inputImage_preview_addOffer;

                if (document.getElementById('file').files.item(0) === null || document.getElementById('file').files.item(0) === undefined) {
                    inputImage_preview_addOffer = "default.jpeg";
                } else {
                    inputImage_preview_addOffer = document.getElementById('file').files.item(0).name;
                }

                var inputCarBrand = $("#CarBrand").val();
                var inputCarModelVehicle = $("#CarModelVehicle").val();
                var inputCarYearOfManufacture = moment($("#CarYearOfManufacture").val()).format('YYYY-MM-DD');
                var inputCarBodyType = $("#CarBodyType").val();
                var inputCarGearBox = $("#CarGearBox").val();
                var inputCarEngineCapacity = $("#CarEngineCapacity").val();
                var inputOfferDescription = $("#offerDescription").val();

                var formData = new FormData();
                var file = document.getElementById('file').files.item(0);
                if (file !== null && file !== undefined && (file.type.match(/image.*/))) {
                    formData.append("file", file);
                }

                var json = JSON.stringify({
                    picture: inputImage_preview_addOffer,
                    car: {
                        brand: inputCarBrand,
                        modelVehicle: inputCarModelVehicle
                    }
                });

                var offerObj = {
                    tittle: inputTittle,
                    price: inputPrice,
                    address: inputOfferAddress,
                    description: inputOfferDescription,
                    picture: inputImage_preview_addOffer,
                    car: {
                        yearOfManufacture: inputCarYearOfManufacture,
                        brand: inputCarBrand,
                        modelVehicle: inputCarModelVehicle,
                        bodyType: inputCarBodyType,
                        gearBox: inputCarGearBox,
                        engineCapacity: inputCarEngineCapacity
                    },
                    user: {
                        id: MY_APP_VARS.currentUser.id,
                        name: MY_APP_VARS.currentUser.name,
                        city: MY_APP_VARS.currentUser.city,
                        phoneNumber: MY_APP_VARS.currentUser.phoneNumber,
                        role: {
                            id: MY_APP_VARS.currentUser.roleId,
                            name: MY_APP_VARS.currentUser.roleName
                        }
                    }
                };


                var jsonBlobFile = new Blob([json], {type: "application/json"});
                formData.append("jsonData", jsonBlobFile);

                $.ajax({
                    url: "/"+ currentUserRoleName + '/offer',
                    method: 'POST',
                    contentType: "application/json",
                    dataType:"application/json",
                    data: JSON.stringify(offerObj),
                    error: function (message) {
                        console.log(message);
                    },
                    complete: function (data) {
                        var jsonObj = data.responseText;
                        var jObj = $.parseJSON("[ " + jsonObj + " ]");
                        clearAllInputFields();
                        $("#closeAddOfferModalWindow").click();
                        loadOfferInfo(jObj);
                    }
                });

                function clearAllInputFields() {
                    var form = document.querySelector("#addOfferForm");
                    var allNotEmptyFields = form.querySelectorAll(".field");
                    allNotEmptyFields.forEach(function(item, idx) {
                        item.value = "";
                    });

                    var imgField = $("input#file");
                    var previewing = $("img.previewing");
                    imgField.val(null);
                    previewing.attr("src", "/images/default.jpeg");

                }

                if (!(inputImage_preview_addOffer.includes("default.jpeg"))) {
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
            }
        })
    }
);

function validateFields() {
    return checkAddOfferInputFields();
}


