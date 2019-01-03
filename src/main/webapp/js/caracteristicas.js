
$(document).ready(function(){
    console.log(2);
   init();
});

function init(){
    $('select').on('input', function(){
        $('div.caracteristicas > div').appendTo($('.caracteristicasOcultas'));
        $('div.caracteristica[idCategoria='+$(this).val()+']').appendTo($('div.caracteristicas'));
    });
}
