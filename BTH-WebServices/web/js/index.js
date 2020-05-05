$(document).ready(function() {
    if (localStorage.getItem('objUsuario')) {
        var us = JSON.parse(localStorage.getItem('objUsuario'));
        $("#login-btn").hide();
        $("#registro-btn").hide();
        $("#perfil-btn").show();
        $("#cerrarSecion-btn").show();
        $('#cambiContracena').show();
        alert(localStorage.getItem('objUsuario'));
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
        'url': 'api/cancion/', 
        'dataType': 'json', // lo que se recibe 
        'success': procesarCanciones
    });
});

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
