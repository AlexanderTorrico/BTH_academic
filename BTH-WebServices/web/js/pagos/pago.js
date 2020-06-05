$(document).ready(function () {
});
function buscarEstudiante() {
    var ci = $("#registroci").val();
    if (ci) {
        // con un ajax, obtenes la cancion por id
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
            },
            'type': 'GET',
            'url': 'api/pago/' + ci,
            'dataType': 'json', // lo que se recibe 
            'success': procesarObtenerEstudiante
        });
    }
    return false;
}
function procesarObtenerEstudiante(respuesta) {
    if (respuesta.success) {
        var objEstudiante = respuesta.response;
        $("#ci").text(objEstudiante.ci);
        $("#nombre").text(objEstudiante.nombre);
        $("#paterno").text(objEstudiante.apaterno);
        $("#materno").text(objEstudiante.amaterno);
        $("#carrera").text(objEstudiante.carrera);
        $("#grupo").text(objEstudiante.grupo);
        localStorage.setItem('grupoId', JSON.stringify(objEstudiante.grupo));

    }
    return false;
}
function registrarPago() {
    var monto = $("#monto").val();
    var mes = $("#mes").val();
    var idgrupo = localStorage.getItem("grupoId");
    // validar que no esten vacios
    var obj = new Object();
    obj.monto = parseInt(monto);
    obj.mes = mes;
    obj.fecha = "20200406";
    obj.idEstudiantes_grupos = idgrupo;
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/pago/registrar',
        'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarPago
    });
}
function procesarPago(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        alert("pago de mensualidad exitoso");
    } else {
        alert(respuesta.message);
    }
}
function getParameterByName(name, url) {
    if (!url)
        url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
    if (!results)
        return null;
    if (!results[2])
        return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}