
$(document).ready(function () {
//  
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/colegio/listacarreras/habilitadas/' + 1,
        'dataType': 'json',
        'success': procesarListaCarrerasCombo
    });
});
function procesarListaCarrerasCombo(respuesta){
    if (respuesta.success){
        var combo = "<select name='seleccion' id='seleccion' class='form-control col-md-7' >";
        $.each(respuesta.response, function (i, obj) {
            combo += "<option value='"+obj.idcarrera +","+obj.idgrupo+"'>" + obj.nombre + " / Horario: " + obj.horainicio + " - " + obj.horafin + " /Grupo: "+obj.idgrupo+ "</option>"
        });
        combo += "</select>";
        $("#combocarrera").html(combo);
    }
}
