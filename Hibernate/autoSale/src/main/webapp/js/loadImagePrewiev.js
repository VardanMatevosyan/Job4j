$(document).on('change', '#file', function(evt) {
     var imagePreview = $('.image_preview');
    imagePreview.empty();
     var files = evt.target.files;
     var file = files[0];
     if (file.type.match('image.*')) {
         var reader = new FileReader();
         reader.readAsDataURL(file);
         reader.onload = (function (event) {
             var fileUrl = event.target.result;
             imagePreview.append('<img class="previewing img-thumbnail" src="' + fileUrl + '" />');
         });
     }
});
