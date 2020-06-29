$(document).ready(function () {

});
getListadoCarreras();

function getListadoCarreras() {

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/carrera/get',
        'dataType': 'json', // lo que se recibe 
        'success': procesarListadoCarrera
    });
}
function procesarListadoCarrera(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var list = respuesta.response;
        //   localStorage.setItem('objUsuario', JSON.stringify(usuario));

        var html = "";

        for (var i in list) {
            console.log(list[i]);
            html += templateitemCarrera(i, list[i].Nombre, list[i].Sigla, list[i].Descripcion, list[i].Img, list[i].Id);
        }
        document.getElementById("listCarreraAdm").innerHTML = html;
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}


function edit(obj) {
    alert(obj.dataset.id);
}
function templateitemCarrera(numero, carrera, sigla, descripcion, img, id) {
    return  '   <tr>  ' +
            '                                               <th scope="row">' + numero + '</th>  ' +
            '                                               <td>' + carrera + '</td>  ' +
            '                                               <td>' + sigla + '</td>  ' +
            '                                               <td>' + descripcion + '</td>  ' +
            '                                               <td>' + img + '</td>  ' +
            '                                               <td>  ' +
            '                                               <a href="#" data-toggle="modal" data-target="#exampleModal" onclick=(informacion(' + id + '))><i class="fas fa-edit"></i></a> | <a href="#"  onclick=(eliminarCarrera(' + id + '))><i class="fas fa-trash-alt"></i></a> | <a href="#" onclick="abrirVentana(' + id + ')" ><i class="fas fa-eye"></i></a>  ' +
            '                                               </td>  ' +
            '                                          </tr>  ';
}

function abrirVentana(id) {
    window.location = "PerfilCarrera.html?id=" + id;

}