$(document).ready(function () {
    var idCancion = getParameterByName("id");

    if (idCancion) {
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
            },
            'type': 'GET',
            'url': 'api/cancion/' + idCancion,
            'dataType': 'json',
            'success': procesarCancion
        });
        listaComentarios(idCancion); //  TESTEAR
    } else {
        $(location).attr('href', 'index.html');
    }
});

function procesarCancion(respuesta) {
    if (respuesta.success) {
        var obj = respuesta.response;

        $("#titulo-cancion").html(obj.titulo);
        $("#letra-cancion").html(obj.letra);
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

function like() {
    var idCancion = getParameterByName("id");
    if (localStorage.getItem('objUsuario')) {
        var objUsuario = JSON.parse(localStorage.getItem('objUsuario'));
        var obj = new Object();
        obj.usuarioId = objUsuario.usuarioId;
        obj.cancionId = idCancion;
        obj.esLike = 1;
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': 'api/puntuacion/puntuar',
            'data': JSON.stringify(obj),
            'dataType': 'json',
            'success': procesarLike
        });
    }else{
        alert("para dar like necesita iniciar sesion o registrarse");
    }


}

function procesarLike(respuesta) {
    if (respuesta.success) {
        alert(respuesta.message);
    } else {
        alert(respuesta.message);
    }
}

function comentar() {
    if (localStorage.getItem('objUsuario')) {
        var objUsuario = JSON.parse(localStorage.getItem('objUsuario'));
        var obj = new Object();
        var idCancion = getParameterByName("id");
        obj.cancionId = idCancion;
        obj.usuarioId = objUsuario.usuarioId;
        obj.textoComentario = $("#texto").val();
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'POST',
            'url': 'api/comentarios/crear',
            'data': JSON.stringify(obj),
            'dataType': 'json',
            'success': procesarComentario
        });
    }else{
         alert("para comentar necesita iniciar sesion o registrarse");
    }
}

function procesarComentario(respuesta) {
    if (respuesta.success) {
        alert(respuesta.message);
    } else {
        alert(respuesta.message);
    }
}

function listaComentarios(id) {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/comentarios/cancion/' + id,
        'dataType': 'json', // lo que se recibe 
        'success': procesarListaComentario
    });
}

function procesarListaComentario(respuesta) {
    if (respuesta.success) {
        var html = "";
        $.each(respuesta.response, function (i, obj) {
            html += " <h6>" + "fecha de publicacion :" + obj.fecha + "</h6>";
            html += " <h5>" + obj.textoComentario + "</h5>";
            html += "<p>----------------</p>"
        });

        $("#listaComentarios").html(html);
    } else {

    }
}






