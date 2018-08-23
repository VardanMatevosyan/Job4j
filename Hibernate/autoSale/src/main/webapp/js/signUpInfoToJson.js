$(document).ready(
    $("#signUp").on("click", function () {
        if (validateSignUpFields()) {
            $.ajax({
                    url: "./signUp",
                    scriptCharset: 'UTF-8',
                    method: 'POST',
                    dataType: 'json',
                    mimeType: 'application/json',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        name:$("#signUpName").val(),
                        password:$("#signUpPassword").val(),
                        city:$("#signUpCity").val(),
                        phoneNumber:$("#signUpPhoneNumber").val()
                    }),
                    error: function (message) {
                        console.log(message);
                    }
                }
            );
        }
    })
);

function validateSignUpFields() {
    return checkElements();
}