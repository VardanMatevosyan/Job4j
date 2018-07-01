var form = document.querySelector(".formWithValidation");
var userName = form.querySelector("#userName");
var userLogin = form.querySelector("#userLogin");
var userPassword = form.querySelector("#userPassword");
var userEmail = form.querySelector("#userEmail");
var inputUserStreet = form.querySelector("#inputUserStreet");
var inputUserHomeNumber = form.querySelector("#inputUserHomeNumber");
var fields = form.querySelectorAll(".field");
var validateBtn = form.querySelector(".validateBtn");

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
            form[i].parentElement.insertBefore(error, fields[i]);
            isPresent = false;
        }
    }
    return isPresent;
};


var userNameValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]{1,20}$/.test(userName.value))) {
        var error = generateError("Name should bu from 1 to 20 characters and without whitespaces and spacial characters");
        userName.parentElement.insertBefore(error, userName);
        return false;
    } else {
        return true
    }
};

var userLoginValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]{1,20}$/.test(userLogin.value))) {
        var error = generateError("Login should bu from 1 to 20 characters and without whitespaces and spacial characters");
        userLogin.parentElement.insertBefore(error, userLogin);
        return false;
    } else {
        return true
    }
};

var userPasswordValidation = function () {
    if (!(/^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8}$/.test(userPassword.value))) {
        var error = generateError("Password should be maximum 8 decimal");
        userPassword.parentElement.insertBefore(error, userPassword);
        return false;
    } else {
        return true
    }
};

var inputUserStreetValidation = function () {
    if (!(/^([0-9]{0,}[A-Za-zА-Яа-яёЁіІїЇ][0-9]{0,}){1,20}$/.test(inputUserStreet.value))) {
        var error = generateError("Street should start with letter and may have decimal inside");
        inputUserStreet.parentElement.insertBefore(error, inputUserStreet);
        return false;
    } else {
        return true
    }
};

var inputUserHomeNumberValidation = function () {
    if (!(/^\d{1,4}$/.test(inputUserHomeNumber.value))) {
        var error = generateError("Number should be maximum 4 decimal");
        inputUserHomeNumber.parentElement.insertBefore(error, inputUserHomeNumber);
        return false;
    } else {
        return true
    }
};

var userEmailValidation = function () {
    if (!(/^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/.test(userEmail.value))) {
        var error = generateError("Email should start with letter, split with @ sign, have number inside and end up with letter");
        userEmail.parentElement.insertBefore(error, userEmail);
        return false;
    } else {
        return true
    }
};

function preventDef(event) {
    event.preventDefault();
}

function addHandler() {
    form.addEventListener("submit", preventDef, false);
}

function removeHandler() {
    form.removeEventListener("submit", preventDef, false);
}

function checkElements() {
    addHandler()
    removeValidation()
    var isPresent = checkFieldsPresent()
    var isValidUserName = userNameValidation()
    var isValidUserLogin = userLoginValidation()
    var isValidUserPassword = userPasswordValidation()
    var isValidUserStreet = inputUserStreetValidation()
    var isValidUserHomeNumber = inputUserHomeNumberValidation()
    var isValidUserEmail = userEmailValidation()
    if (isPresent && isValidUserName && isValidUserLogin && isValidUserPassword
        && isValidUserStreet && isValidUserHomeNumber && isValidUserEmail) {
        removeHandler()
    }
}

$(validateBtn).on("click", checkElements);




