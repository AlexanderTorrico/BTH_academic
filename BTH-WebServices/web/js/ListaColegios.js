$(document).ready(function () {

});
getListadoColegio();

function getListadoColegio() {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/colegio/get',
        'dataType': 'json', // lo que se recibe 
        'success': procesarRegistro
    });
}
function procesarRegistro(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var list = respuesta.response;
        //   localStorage.setItem('objUsuario', JSON.stringify(usuario));

        var html = "";

        //    Nombre,Sigep,Directo,Direccion,Telefono,EsModulo,Correo,Username,Contrasena,Estad

        for (var i in list) {
            console.log(list[i]);
            html += templateitemColegios(list[i]);
        }
        document.getElementById("listColegioAdm").innerHTML = html;
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}


function templateitemColegios(objColegios) {
    return  '   <tr>  ' +
            '                                               <td>' + objColegios.nombre + '</td>  ' +
            '                                               <td>' + objColegios.sigep + '</td>  ' +
            '                                               <td>' + objColegios.director + '</td>  ' +
            '                                               <td>' + objColegios.direccion + '</td>  ' +
            '                                               <td>' + objColegios.telefono + '</td>  ' +
            '                                               <td>' + objColegios.correo + '</td>  ' +
            '                                               <td>  ' +
            '                                               <a href="#" data-toggle="modal" data-target="#modalGrupos" onclick=informacion(' + objColegios.id + ')><i class="fas fa-edit"></i></a> | <a href="#"  onclick=(eliminarGrupos(' + objColegios.id + '))><i class="fas fa-trash-alt"></i></a>  ' +
            '                                               </td>  ' +
            '                                          </tr>  ';
}