$(document).ready(function(){
    $("#myBtn2").click(function(){
        $("#myModal2").modal();
    });
    $("#myBtn3").click(function(){
        $("#myModal2").modal();
        $('#myModal1').modal('hide');
    });
});


