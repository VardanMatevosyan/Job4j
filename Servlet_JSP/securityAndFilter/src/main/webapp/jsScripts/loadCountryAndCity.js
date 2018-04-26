$(document).ready(
    $.when(getCountry()).done(getCityByCountryValue()),
    $(document).on("change", "#selectUserCountry", getCityByCountryValue)
);
check = false;
countryFromDB = "";
function getCountry() {
    var choseCountry = "выберите город...";
    $(document).ready(
        $.ajax({
            url: './jsonGetCountry',
            scriptCharset: 'UTF-8',
            method: 'POST',
            dataType: 'json',
            mimeType: 'application/json',
            contentType: 'application/json',
            complete: function (data) {
                var countries = JSON.parse(data.responseText);
                var options = "";
                for (var i = 0; i < countries.countryObj.length; i++) {
                    if (i === 0) {
                        console.log("getCityByCountryValue " + window.countryFromDB);
                        options += "<option lang='ru' value=" + "\"" + "choseCountry" + "\"" + " selected>"
                            + choseCountry +  "</option>";
                        options += "<option value=" + countries.countryObj[i].country_id + ">" + countries.countryObj[i].country + "</option>";
                    } else {
                        options += "<option value=" + countries.countryObj[i].country_id + ">" + countries.countryObj[i].country + "</option>";
                    }
                }
                var selectTag = document.getElementById("selectUserCountry");
                selectTag.innerHTML = options;
            }
        })
    );
}

function getCityByCountryValue() {
    var select = $('select#selectUserCountry[name="countrySelect"]');
    var countryValue = select.val();
    console.log("window.countryFromDB in getCity " + countryValue );
    $.ajax({
        url: "./JsonGetCity",
        scriptCharset: 'UTF-8',
        method: 'POST',
        dataType: 'json',
        mimeType: 'application/json',
        contentType: 'application/json',
        data: JSON.stringify({country:countryValue}),
        error: function (message) {
            console.log(message);
        },
        complete: function (result) {
            var input = document.getElementById("selectUserCity");
            var cities = JSON.parse(result.responseText);
            console.log("city LEN is -> " + cities.length);
            console.log("result response val is -> " + result.responseText);
            var options = "";
            for (var i = 0; i < cities.length; i++) {
                if (i === 0) {
                    options += "<option value=" + "\"" + cities[i].city_id + "\"" + " selected> " + cities[i].city + "</option>";
                } else {
                    options += "<option value=" + "\"" + cities[i].city_id + "\"" + "> " + cities[i].city + "</option>";
                }
            }
            input.innerHTML = options;
        }
    });
    return false;
}