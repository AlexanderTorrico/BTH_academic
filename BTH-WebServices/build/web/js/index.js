$(document).ready(function() {
    if (localStorage.getItem('objUsuario')) {
        var us = JSON.parse(localStorage.getItem('objUsuario'));
        $("#login-btn").hide();
        $("#registro-btn").hide();
        $("#perfil-btn").show();
        $("#cerrarSecion-btn").show();
        $('#cambiContracena').show();
        //alert(localStorage.getItem('objUsuario'));
        $('#cambiContracena').attr('href','changePassword.html?id=' + us.id);
    } else {
        $("#login-btn").show();
        $("#registro-btn").show();
        $("#perfil-btn").hide();
        $("#cerrarSecion-btn").hide();
        $('#cambiContracena').hide();
    }
    
    // por ahora, voy a conseguir todas las canciones
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/index/',
        'dataType': 'json', // lo que se recibe 
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
            html += "<a onClick='o("+obj.Id+")' class='btn btn-outline-info'>" + "Sus proyectos" + "</a>";
            html += "<a href='#' data-toggle='modal' data-target='#exampleModal' onclick='encontrarInformacion(" + obj.Id + ")' class='btn btn-primary'>" + "Informacion" + "</a>";
            html += "</div></div></div><br>";
        });
        html += "</div></div></div>";
        $("#contenedor-carreras").html(html);
    } else {
    }
}

function o(id){
    localStorage.setItem("carrera",id);
    location.href="/bth/homeProyect.html";
}
function encontrarInformacion(id) {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/index/'+id,
        'dataType': 'json', // lo que se recibe 
        'success': modalInformacion
    });
}
function modalInformacion(respuesta) {
    var html = "";
    var contenido = "";
    console.log(respuesta);
    if(respuesta.success) {
        $.each(respuesta.response, function (i, obj) {
            contenido += "<strong>"+obj.titulo+"</strong>";
            contenido += "<p>" + obj.descripcion + "</p>"
            contenido += "<hr>";
        });
        if (contenido === "") {
            contenido += "no hay ninguna informacion";
        }
    } else {
        contenido += respuesta.message;
    }
    
    $("#contenidoModal").html(contenido);

    
}

function procesarCanciones(respuesta){
    if (respuesta.success) {
        var html = "";

        $.each(respuesta.response, function(i, obj){
            
            html += "<article class='info' onclick='verCancion(" + obj.cancionId + ")'>";
            html += "<h4>" + obj.titulo + "</h4>";
            html += "<img src='imagenes/imagen1.jpg' alt=''>";
            html += "</article>";
        });
         
        $("#contenedor-canciones").html(html);
    } else {       
    }
}
function verCancion(idCancion){
    $(location).attr('href','contenido.html?id=' + idCancion);
}

function cerrarSesion(){
    localStorage.clear();
}

function buscarCancion(){
    var titulo = $("#buscar").val();
   $(location).attr('href','buscador.html?titulo='+titulo);
}
