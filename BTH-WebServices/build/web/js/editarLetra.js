$(document).ready(function () {
    
    var idLetra = getParameterByName("id");

    if (idLetra){
        // con un ajax, obtenes la cancion por id
        jQuery.ajax({
            headers:{
                'Accept': 'application/json',
            },
            'type': 'GET',
            'url': 'api/cancion/' + idLetra,
            'dataType': 'json', // lo que se recibe 
            'success': procesarObtenerCancion
        });
    }
});

function procesarObtenerCancion(respuesta) {
    if (respuesta.success) {
        var objLetra = respuesta.response;
        $("#nombreLetra").val(objLetra.titulo);
        $("#letra").html(objLetra.letra);
        $("#genero").val(objLetra.generoId);
    } else {
       alert(respuesta.message);
    }
}

function guardarLetra() {
    var idLetra = getParameterByName("id");
    var objUsuario = JSON.parse(localStorage.getItem('objUsuario'));
    var usuarioId = objUsuario.usuarioId;

    var nombreLetra = $("#nombreLetra").val();
    var letra = $("#letra").val();
    var generoSeleccionado = $("#genero").val();

    if (idLetra) { // hay que actualizar
        var obj = new Object();
        obj.cancionId = idLetra;
        obj.generoId = generoSeleccionado;
        obj.titulo = nombreLetra;
        obj.letra = letra;
        obj.usuarioCreador = usuarioId;

        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': 'api/cancion/modificar',
            'data': JSON.stringify(obj),
            'dataType': 'json', // lo que se recibe 
            'success': procesarActualizarLetra
        });
    } else { // hay que insertar
        var obj = new Object();
        obj.generoId = generoSeleccionado;
        obj.titulo = nombreLetra;
        obj.letra = letra;
        obj.usuarioCreador = usuarioId;

        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': 'api/cancion/crear',
            'data': JSON.stringify(obj),
            'dataType': 'json', // lo que se recibe 
            'success': procesarInsertarLetra
        });
    }
}

function procesarActualizarLetra(respuesta) {
    if (respuesta.success) {
        alert("cancion actualizada");
    } else {

    }
}

function procesarInsertarLetra(respuesta) {
    if (respuesta.success) {
        alert("cancion agregada");
    } else {

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
