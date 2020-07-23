$(document).ready(function () {

});
getListadoDias();

function getListadoDias() {

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/dias/get',
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
       
            html += templateitemDias(list[i]);
        }
        document.getElementById("listDiasAdm").innerHTML = html;
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}


function templateitemDias(objGrupos) {
    return  '   <tr>  ' +
            '                                               <td>' + objGrupos.Dia + '</td>  ' +
            '                                               <td>' + objGrupos.IdGrupo + '</td>  ' +
            '                                               <td>' + objGrupos.Hora + '</td>  ' +
            '                                               <td>  ' +
            '                                               <a href="#" data-toggle="modal" data-target="#modalGrupos" onclick=informacion(' + objGrupos.Id + ')><i class="fas fa-edit"></i></a> | <a href="#"  onclick=(eliminarGrupos(' + objGrupos.Id + '))><i class="fas fa-trash-alt"></i></a>  ' +
            '                                               </td>  ' +
            '                                          </tr>  ';
}