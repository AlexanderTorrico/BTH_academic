$(document).ready(function () {
    if (localStorage.getItem('objUsuario')) {
        var objUsuario = JSON.parse(localStorage.getItem('objUsuario'));
        $("#nombreDeUsuario").html(objUsuario.nombreCompleto);

        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
            },
            'type': 'GET',
            'url': 'api/cancion/usuario/' + objUsuario.usuarioId,
            'dataType': 'json',
            'success': procesarCanciones
        });
     
    } else {
        // si estuviera en otra pagina, redirigir al index
    }
});

function procesarCanciones(respuesta) {
    if (respuesta.success) {
        var html = "<table id='tabla'><tr><th>Titulo</th><th>Genero</th><th colspan='2'>operaciones</th></tr>";

        $.each(respuesta.response, function (i, obj) {

            html += "<tr>";
            html += "<td>" + obj.titulo + "</th>";
            html += " <td>" + obj.nombreGenero + "</th>";
            html += " <td>  <button onclick='editarCancion(" + obj.cancionId + ")'>editar</button></td>";
            html += " <td>  <button onclick='eliminarCancion(" + obj.cancionId + ")'>eliminar</button></td>";
            html += " </tr>";
        });

        $("#mis-canciones").html(html);
    } else {

    }
}

function editarCancion(idCancion) {
    $(location).attr('href', 'editarLetra.html?id=' + idCancion);
}

function eliminarCancion(idCancion) {

    var obj = new Object();
    obj.cancionId = idCancion

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/cancion/eliminar',
        'data': JSON.stringify(obj),
        'dataType': 'json', 
        'success': procesarEliminarCancion
    });
}
function procesarEliminarCancion(respuesta){
    if (respuesta.success) {
        // actualiza la tabla y quita la fila
    } else {
        alert("hubo un error al eliminar la cancion");
    }
    
}

function crearCancion(){
     $(location).attr('href', 'editarLetra.html');
}

