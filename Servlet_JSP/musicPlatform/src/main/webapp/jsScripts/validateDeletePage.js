var form = document.querySelector(".formWithValidation");
var userName = form.querySelector("#userName");
var userLogin = form.querySelector("#userLogin");
var fields = form.querySelectorAll(".field");
var validateDeleteBtn = form.querySelector(".validateDeleteBtn");

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
        return true;
    }
};


var userLoginValidation = function () {
    if (!(/^[A-Za-zА-Яа-яёЁіІїЇ]{1,20}$/.test(userLogin.value))) {
        var error = generateError("Login should bu from 1 to 20 characters and without whitespaces and spacial characters");
        userLogin.parentElement.insertBefore(error, userLogin);
        return false;
    } else {
        return true;
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
    var isValid = isPresent && isValidUserName && isValidUserLogin;
    if (isValid) {
        removeHandler()
    }
}

$(validateDeleteBtn).on("click", checkElements);




