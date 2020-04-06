$(document).ready(function () {

});
function verificarCuenta() {
    var tipo = getParameterByName("tipo");
    var token = getParameterByName("token");

    if (tipo=="docente") {
        // con un ajax, obtenes la cancion por id
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
            },
            'type': 'GET',
            'url': 'api/docente/' + token,
            'dataType': 'json', // lo que se recibe 
            'success': procesarActivacion
        });
    }if (tipo=="colegio") {
        // con un ajax, obtenes la cancion por id
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
            },
            'type': 'GET',
            'url': 'api/colegio/' + token,
            'dataType': 'json', // lo que se recibe 
            'success': procesarActivacion
        });
    }
}

function procesarActivacion(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)      
        alert("su cuenta ya esta activada");
//        $(location).attr('href', 'index.html');
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

