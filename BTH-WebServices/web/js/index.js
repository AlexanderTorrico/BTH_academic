var direccion = "index";

$(document).ready(function () {
    if (localStorage.getItem('idDocente') == null || localStorage.getItem('nombre') == null) {
        $("#loginIndex").show();
        $("#registroIndex").show();
        $("#perfilIndex").hide();
        $("#cerrarIndex").hide();
    } else {
        $("#loginIndex").hide();
        $("#registroIndex").hide();
        $("#perfilIndexUsuario").html(localStorage.getItem('nombre'));
        $("#cerrarSesion").html('Cerrar Sesión');
        $("#perfilIndex").show();
        $("#cerrarIndex").show();
    }
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/index/',
        'dataType': 'json',
        'success': procesarIndex
    });
});

function procesarIndex(respuesta) {
    console.log(respuesta);
    if (respuesta.success) {
        var html = "";
        html += "<br><div class='container main-container'>";
        html += "<h2>Las carreras</h2>";
        html += "<div class='row'>";
        html += "<div class='col-md-4'>";
        $.each(respuesta.response, function (i, obj) {
            html += "<div class='card'> ";
            //html += "<img class='card-img-top' src='imagenes/imagen1.jpg' alt='card image cap'>";
            html += "<div class='card-body'>";
            html += "<h5 class='card-title'>" + obj.Nombre + " " + "(" + obj.Sigla + ")" + "</h5>";
            html += "<p class='card-text'>" + obj.Descripcion + "</p>";
            html += "<div class='justify-content-lg-between d-flex'>"
            html += "<a onClick='o(" + obj.Id + ")' class='btn btn-outline-info'>" + "Sus proyectos" + "</a>";
            html += "<a href='#' data-toggle='modal' data-target='#exampleModal' onclick='encontrarInformacion(" + obj.Id + ")' class='btn btn-primary'>" + "Información" + "</a>";
            html += "</div></div></div><br>";
        });
        html += "</div></div></div>";
        $("#contenedor-carreras").html(html);
    } else {
    }
}

function o(id) {
    localStorage.setItem("carrera", id);
    location.href = "/bth/homeProyect.html";
}

function encontrarInformacion(id) {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/index/' + id,
        'dataType': 'json',
        'success': modalInformacion
    });
}

function modalInformacion(respuesta) {
    var contenido = "";
    console.log(respuesta);
    if (respuesta.success) {
        $.each(respuesta.response, function (i, obj) {
            contenido += "<strong>" + obj.titulo + "</strong>";
            contenido += "<p>" + obj.descripcion + "</p>"
            contenido += "<hr>";
        });
        if (contenido === "") {
            contenido += "No hay ninguna información";
        }
    } else {
        contenido += respuesta.message;
    }
    $("#contenidoModal").html(contenido);
}
