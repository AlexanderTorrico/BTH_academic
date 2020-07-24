$(document).ready(function () {

});
getListadoHora();

function getListadoHora() {

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/horas/get',
        'dataType': 'json', // lo que se recibe 
        'success': procesarRegistro
    });
}
function procesarRegistro(respuesta) {
  if (respuesta.success) { // if (respuesta.success == true)
        var list = respuesta.response;
        //   localStorage.setItem('objUsuario', JSON.stringify(usuario));

        var html = "";

        for (var i in list) {
            console.log(list[i]);
            html += templateitemHora(i, list[i].Inicio, list[i].Fin, list[i].Id);
        }
        document.getElementById("listHoraAdm").innerHTML = html;
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}


function templateitemHora(numero, Inicio, Fin, Id) {
    return  '   <tr>  ' +
            '                                               <th scope="row">' + numero + '</th>  ' +
            '                                               <td>' + Inicio + '</td>  ' +
            '                                               <td>' + Fin + '</td>  ' +
            '                                               <td>  ' +
            '                                               <a href="#" data-toggle="modal" data-target="#exampleModal" onclick=(informacion(' + Id + '))></a> <a href="#"  onclick=(eliminarGrupos(' + Id + '))><i class="fas fa-trash-alt"></i></a> | <a href="#" onclick="abrirVentana(' + Id + ')" ><i class="fas fa-eye"></i></a>  ' +
            '                                               </td>  ' +
            '                                          </tr>  ';
}