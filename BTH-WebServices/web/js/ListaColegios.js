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
            html += templateitemColegios(i, list[i].nombre, list[i].sigep, list[i].director, list[i].direccion, list[i].telefono, list[i].esModulo, list[i].correo, list[i].username, list[i].estado, list[i].id);


        }
        document.getElementById("listColegioAdm").innerHTML = html;
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}

function edit(obj) {
    alert(obj.dataset.id);
}

function templateitemColegios(numero, nombre, sigep, director, direccion, telefono, esModulo, correo, username, estado, id) {

    return  '   <tr>  ' +
            '                                               <th scope="row">' + numero + '</th>  ' +
            '                                               <th>' + nombre + '</th>  ' +
            '                                               <td>' + sigep + '</td>  ' +
            '                                               <td>' + director + '</td>  ' +
            '                                               <td>' + direccion + '</td>  ' +
            '                                               <td>' + telefono + '</td>  ' +
            '                                               <td>' + esModulo + '</td>  ' +
            '                                               <td>' + correo + '</td>  ' +
            '                                               <td>' + username + '</td>  ' +
            '                                               <td>' + estado + '</td>  ' +
            '                                               <td>  ' +
            '                                               <a href="#" data-toggle="modal" data-target="#modalColegio" onclick=informacion(' + id + ')><i class="fas fa-edit"></i></a> | <a href="#"  onclick=(eliminarColegio(' + id + '))><i class="fas fa-trash-alt"></i></a> | <a href="#" onclick="abrirVentana(' + id +')" ><i class="fas fa-eye"></i></a>  ' +
            '                                               </td>  ' +
            '                                          </tr>  ';
}
function abrirVentana(id) {
    window.location="PerfilColegio.html?id=" + id;
    
}

