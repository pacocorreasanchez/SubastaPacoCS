$(document).ready(function(){
    $("#myBtn1").click(function(){
        $("#myModal1").modal();
    });
    $("#myBtn4").click(function(){
        $("#myModal1").modal();
        $('#myModal2').modal('hide');
    });
    $("#myBtn3").click(function(){
        $("#myModal2").modal();
        $('#myModal1').modal('hide');
    });
    
});
