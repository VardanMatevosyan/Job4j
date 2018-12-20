$(document).ready(function () {

    $('input#signUp').on('click', function (e) {
        e.preventDefault();
        var pass = checkElements();
        if (pass) {
            $.ajax({
                    url: './signUp',
                    scriptCharset: 'UTF-8',
                    method: 'POST',
                    dataType: 'json',
                    mimeType: 'application/json',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        name: $("#signUpName").val(),
                        password: $("#signUpPassword").val(),
                        city: $("#signUpCity").val(),
                        phoneNumber: $("#signUpPhoneNumber").val(),
                        role: {
                            id: 1,
                            name: "user"
                        }
                    }),
                    error: function (message) {
                        console.log(message);
                    },
                    complete: function () {
                        $("#signUp").fadeOut();
                        location.reload();
                    }
            });
        }
    })
});