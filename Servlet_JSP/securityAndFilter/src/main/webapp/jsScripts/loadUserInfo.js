$(document).ready( $.ajax('./jsonUsersGet', {
        scriptCharset: 'UTF-8',
        method: 'GET',
        complete: function(data) {
            var users = JSON.parse(data.responseText);
            var html = "";
            var options = "";
            for (var i = 0; i < users.length; i++) {
                html += "<tr>" + "<td><b>Name:</b></td>" + "<td>" + users[i].name +"</td>" + "</tr>"
                    + "<tr>" + "<td><b>Login:</b></td>" + "<td>" + users[i].login + "</td>" + "</tr>"
                    + "<tr>" + "<td><b>Date:</b></td>" + "<td>" + users[i].date + "</td>" + "</tr>"
                    + "<tr>" + "<td><b>Role:</b></td>" + "<td>" + users[i].role + "</td>" + "</tr>"
                    + "<tr>" + "<td><b>Country:</b></td>" + "<td lang=\"ru\">" + users[i].country + "</td>" + "</tr>"
                    + "<tr>" + "<td><b>City:</b></td>" + "<td lang=\"ru\">" + users[i].city + "</td>" + "</tr>";
                html += "<tr><td  colspan=\"2\"><hr></td></tr>";
                var table = document.getElementById("users");
                table.innerHTML = html;
            }

            for (var j = 0; i < users.length; i++) {
                options += "<option>" + users[i].country + "</option>";
                var select = document.getElementById("selectUserCountry");
                select.innerHTML = options;
            }
        }
    })
);