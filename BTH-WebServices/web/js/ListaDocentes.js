$(document).ready(function () {

});
getListadoDocentes();

function getListadoDocentes() {

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/docente/get',
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

            html += templateitemDocentes(list[i]);
        }
        document.getElementById("listDocentesAdm").innerHTML = html;
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}


function templateitemDocentes(objGrupos) {
    return  '   <tr>  ' +
            '                                               <td>' + objGrupos.nombre + '</td>  ' +
            '                                               <td>' + objGrupos.apaterno + '</td>  ' +
            '                                               <td>' + objGrupos.amaterno+ '</td>  ' +
            '                                               <td>' + objGrupos.correo + '</td>  ' +
            '                                               <td>' + objGrupos.username + '</td>  ' +
            '                                               <td>  ' +
            '                                               <a href="#" data-toggle="modal" data-target="#modalGrupos" onclick=informacion(' + objGrupos.id + ')><i class="fas fa-edit"></i></a> | <a href="#"  onclick=(eliminarGrupos(' + objGrupos.id + '))><i class="fas fa-trash-alt"></i></a>  ' +
            '                                               </td>  ' +
            '                                          </tr>  ';
}