
$(document).ready(function(){
   init();
});

function init(){
    $('[name=nCampos]').attr('value',$('div.caracteristicasOcultas > div').length);
    $('select').on('input', function(){
        $('div.caracteristicas > div').appendTo($('.caracteristicasOcultas'));
        $('div.caracteristica[idCategoria='+$(this).val()+']').appendTo($('div.caracteristicas'));
    });
}
