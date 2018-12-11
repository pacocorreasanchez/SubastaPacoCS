$(document).ready(function () {
    $(window).on('load', function () {
        $('#myModal').modal('show');
    });
    $("#myBtn3").click(function(){
        $('#myModal').modal('hide');
    });
});

