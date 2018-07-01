$(document).ready(
    $.ajax({
        url: './jsonGetMusicTypes',
        scriptCharset: 'UTF-8',
        method: 'POST',
        dataType: 'json',
        mimeType: 'application/json',
        contentType: 'application/json',
        complete: function (data) {
            var listOfMusicTypes = JSON.parse(data.responseText);
            var options = "";
            for (var i = 0; i < listOfMusicTypes.musicTypes.length; i++) {
                options +=  "<option value=" + listOfMusicTypes.musicTypes[i].musicTypeId + ">"
                    + listOfMusicTypes.musicTypes[i].musicTypeName + "</option>";
            }
            var musicTypesSelectElement = document.getElementById("selectMusicTypes");
            musicTypesSelectElement.innerHTML = options;
        }

    })
);