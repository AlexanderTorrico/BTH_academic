$(document).ready(function () {

});
getListadoGrupos();

function getListadoGrupos() {

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/grupos/get',
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
            html += templateitemGrupos(list[i]);
        }
        document.getElementById("listGruposAdm").innerHTML = html;
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}


function templateitemGrupos(objGrupos) {
    return  '   <tr>  ' +
            '                                               <td>' + objGrupos.Nivel + '</td>  ' +
            '                                               <td>' + objGrupos.Gestion + '</td>  ' +
            '                                               <td>' + objGrupos.Colegio + '</td>  ' +
            '                                               <td>' + objGrupos.Carrera + '</td>  ' +
            '                                               <td>' + objGrupos.Docente + '</td>  ' +
            '                                               <td>' + objGrupos.Costo + '</td>  ' +
            '                                               <td>' + objGrupos.FechaInicio + '</td>  ' +
            '                                               <td>' + objGrupos.FechaFin + '</td>  ' +
            '                                               <td>  ' +
            '                                               <a href="#" data-toggle="modal" data-target="#modalGrupos" onclick=informacion(' + objGrupos.Id + ')><i class="fas fa-edit"></i></a> | <a href="#"  onclick=(eliminarGrupos(' + objGrupos.Id + '))><i class="fas fa-trash-alt"></i></a>  ' +
            '                                               </td>  ' +
            '                                          </tr>  ';
}