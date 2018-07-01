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
                + "<tr>" + "<td valign=\"top\"><b>Music prepare:</b></td><td>"
                + "<ul style=\"list-style-type: none; padding:0; margin:0\">";
                var mType = "+";
                var firstTime = true;
                var firstMusicType = users[i]["musicType1"];
                    for (var count = 1; (mType !== "" || mType !== undefined)
                    && (firstMusicType !== "" || firstMusicType !== undefined || firstMusicType !== null); count++) {
                        var musicTypeKey = "musicType" + count;
                        mType = users[i][musicTypeKey];
                        if (mType === "" || mType === undefined) {
                            break;
                        } else {
                            if (firstTime) {
                                html +=  "<li >" + mType + "</li>";
                                firstTime = false;
                            } else {
                                html += "<li >" + mType + "</li>";

                            }
                        }
                    }
                html += "</ul></td></tr>";
                html += "<tr>" + "<td><b>Country:</b></td>" + "<td lang=\"ru\">" + users[i].country + "</td>" + "</tr>"
                + "<tr>" + "<td><b>City:</b></td>" + "<td lang=\"ru\">" + users[i].city + "</td>" + "</tr>"
                + "<tr>" + "<td><b>Street:</b></td>" + "<td lang=\"ru\">" + users[i].street + "</td>" + "</tr>"
                + "<tr>" + "<td><b>Number:</b></td>" + "<td lang=\"ru\">" + users[i].number + "</td>" + "</tr>";
                html += "<tr><td  colspan=\"2\"><hr></td></tr>";
                var table = document.getElementById("users");
                table.innerHTML = html;
            }
        }
    })
);