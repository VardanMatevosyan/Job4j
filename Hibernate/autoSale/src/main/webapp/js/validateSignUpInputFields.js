var div = document.querySelector("#inputWrapperDiv");
var userName = div.querySelector("#signUpName");
var userPassword = div.querySelector("#signUpPassword");
var userCity = div.querySelector("#signUpCity");
var inputUserPhoneNumber = div.querySelector("#signUpPhoneNumber");
var fields = div.querySelectorAll(".field");

var generateError = function (text) {
    var error = document.createElement('div');
    error.className = 'error';
    error.style.color = 'red';
    error.innerHTML = text;
    return error
};

var removeValidation = function() {
    var errors = div.querySelectorAll(".error");
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


var userNameValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]{1,8}$/.test(userName.value))) {
        var error = generateError("Name should be from 1 to 8 characters and without whitespaces and spacial characters");
        userName.parentElement.insertBefore(error, userName);
        return false;
    } else {
        return true
    }
};

var userPasswordValidation = function () {
    if (!(/^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8}$/.test(userPassword.value))) {
        var error = generateError("Password should be maximum 8 decimal and 1 character");
        userPassword.parentElement.insertBefore(error, userPassword);
        return false;
    } else {
        return true
    }
};

var inputUserCityValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]{1,15}$/.test(userCity.value))) {
        var error = generateError("Street should start with letter");
        userCity.parentElement.insertBefore(error, userCity);
        return false;
    } else {
        return true
    }
};

var inputUserHomeNumberValidation = function () {
    if (!(/^[8]\d{10}$/.test(inputUserPhoneNumber.value))) {
        var error = generateError("Number should start with 8 and contain 11 decimal");
        inputUserPhoneNumber.parentElement.insertBefore(error, inputUserPhoneNumber);
        return false;
    } else {
        return true
    }
};

function checkElements() {
    removeValidation();
    var isPresent = checkFieldsPresent();
    var isValidUserName = userNameValidation();
    var isValidUserPassword = userPasswordValidation();
    var isValidUserStreet = inputUserCityValidation();
    var isValidUserHomeNumber = inputUserHomeNumberValidation();
    if (isPresent && isValidUserName && isValidUserPassword
        && isValidUserStreet && isValidUserHomeNumber) {
        return true;
    }
}





