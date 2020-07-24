$(document).ready(function () {

});
getListadoGestion();

function getListadoGestion() {

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/gestiones/get',
        'dataType': 'json', // lo que se recibe 
        'success': procesarListadoGestiones
    });
}
function procesarListadoGestiones(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var list = respuesta.response;
        //   localStorage.setItem('objUsuario', JSON.stringify(usuario));

        var html = "";

        for (var i in list) {
            console.log(list[i]);
            html += templateitemGestion(i, list[i].MesAPagar, list[i].Id);
        }
        document.getElementById("listGestionesAdm").innerHTML = html;
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}


function edit(obj) {
    alert(obj.dataset.id);
}
function templateitemGestion(numero, mesapagar, id) {
    return  '   <tr>  ' +
            '                                               <th scope="row">' + numero + '</th>  ' +
            '                                               <td>' + mesapagar + '</td>  ' +
            '                                               <td>  ' +
            '                                               <a href="#" data-toggle="modal" data-target="#exampleModal" onclick=(informacion(' + id + '))><i class="fas fa-edit"></i></a> | <a href="#"  onclick=(eliminarGestion(' + id + '))><i class="fas fa-trash-alt"></i></a>  ' +
            '                                               </td>  ' +
            '                                          </tr>  ';
}


