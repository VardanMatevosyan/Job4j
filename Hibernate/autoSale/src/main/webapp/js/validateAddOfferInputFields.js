var form = document.querySelector("#addOfferForm");
var userTittle = form.querySelector("#tittle");
var userAddress = form.querySelector("#offerAddress");
var userCarBrand = form.querySelector("#CarBrand");
var userCarModelVehicle = form.querySelector("#CarModelVehicle");
var userCarBodyType = form.querySelector("#CarBodyType");
var userCarGearBox = form.querySelector("#CarGearBox");
var userOfferDescription = form.querySelector("#offerDescription");
var fields = form.querySelectorAll(".field");

var generateError = function (text) {
    var error = document.createElement('div');
    error.className = 'error';
    error.style.color = 'red';
    error.innerHTML = text;
    return error
};

var removeValidation = function() {
    var errors = form.querySelectorAll(".error");
    for (var j = 0; j < errors.length; j++) {
        errors[j].remove()
    }
};

var checkFieldsPresent = function () {
    var isPresent = true;
    for (var i = 0; i < fields.length; i++) {
        if (!fields[i].value) {
            var error = generateError("Cannot be empty");
            fields[i].parentElement.insertBefore(error, fields[i]);
            isPresent = false;
        }
    }
    return isPresent;
};


var userTittleValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]+(?:(?:\s+|-)[A-Za-zА-Яа-яёЁіІїЇ]+)*$/.test(userTittle.value))) {
        var error = generateError("Name should be from 5 to 20 characters and without whitespaces and spacial characters");
        userTittle.parentElement.insertBefore(error, userTittle);
        return false;
    } else {
        return true
    }
};


var inputUserAddressValidation = function () {
    if (!(/^(.+)\s+(\S+?)(-(\d+))?$/.test(userAddress.value))) {
        var error = generateError("Address should start with letter");
        userAddress.parentElement.insertBefore(error, userAddress);
        return false;
    } else {
        return true
    }
};

var userCarBrandValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]{2,15}$/.test(userCarBrand.value))) {
        var error = generateError("Name should be from 2 to 15 characters and without whitespaces and spacial characters");
        userCarBrand.parentElement.insertBefore(error, userCarBrand);
        return false;
    } else {
        return true
    }
};


var userCarModelVehicleValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]{5,15}$/.test(userCarModelVehicle.value))) {
        var error = generateError("Name should be from 5 to 15 like 'passenger' characters and without whitespaces and spacial characters");
        userCarModelVehicle.parentElement.insertBefore(error, userCarModelVehicle);
        return false;
    } else {
        return true
    }
};


var userCarBodyValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]{5,15}$/.test(userCarBodyType.value))) {
        var error = generateError("Name should be from 5 to 15 like 'sedan' characters and without whitespaces and spacial characters");
        userCarBodyType.parentElement.insertBefore(error, userCarBodyType);
        return false;
    } else {
        return true
    }
};



var userCarGearBoxValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]{4,10}$/.test(userCarGearBox.value))) {
        var error = generateError("Name should be from 4 to 10 like 'auto' characters and without whitespaces and spacial characters");
        userCarGearBox.parentElement.insertBefore(error, userCarGearBox);
        return false;
    } else {
        return true
    }
};



var userOfferDescriptionValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]+(?:(?:\s+|-)[A-Za-zА-Яа-яёЁіІїЇ]+)*$/.test(userOfferDescription.value))) {
        var error = generateError("Name should be from 10 characters and without whitespaces and spacial characters");
        userOfferDescription.parentElement.insertBefore(error, userOfferDescription);
        return false;
    } else {
        return true
    }
};

function checkAddOfferInputFields() {
    removeValidation();
    var isPresent = checkFieldsPresent();
    var isValidTittleValidation = userTittleValidation();
    var isValidUserAddress = inputUserAddressValidation();
    var isValidUserCarBrand = userCarBrandValidation();
    var isValidUserCarModelVehicle = userCarModelVehicleValidation();
    var isValidUserCarBody = userCarBodyValidation();
    var isValidateUserCarGearBoxValidation = userCarGearBoxValidation();
    var isValidUserOfferDescription = userOfferDescriptionValidation();
    if (isPresent && isValidTittleValidation && isValidUserAddress && isValidUserCarBrand && isValidateUserCarGearBoxValidation
        && isValidUserCarModelVehicle && isValidUserCarBody && isValidUserOfferDescription) {
        return true;
    }
}





