$(document).ready(function () {
    var titulo = getParameterByName("titulo");

    if (titulo) { // if idCancion != null o idCancion != 0
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
            },
            'type': 'GET',
            'url': 'api/cancion/buscar/'+titulo,
            'dataType': 'json', // lo que se recibe 
            'success': procesarCanciones
        });
    } else {
        $(location).attr('href','index.html');
    }
});

function procesarCanciones(respuesta){
    if (respuesta.success){
        var html = "<table id='tabla'><tr><th>Titulo</th><th>Genero</th><th colspan='2'>operaciones</th></tr>";
        $.each(respuesta.response, function (i, obj) {
            html += "<tr>";
            html += "<td>" + obj.titulo + "</th>";
            html += " <td>" + obj.nombreGenero + "</th>";
            html += " <td>  <button onclick='verCancion(" +obj.cancionId+")'>ver</button></td>";
            html += " </tr>";         
        });
       
        $("#mis-canciones").html(html);
    } else {
         html += "<h4>" +respuesta.message + "</h4>";
          $("#mis-canciones").html(html);
    }
}

function getParameterByName(name, url){
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

function verCancion(idCancion){
    $(location).attr('href','contenido.html?id=' + idCancion);
}


