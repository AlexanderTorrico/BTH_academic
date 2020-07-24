$(document).ready(function () {

});

getListadoRoles();
var listaRoles;

function getListadoRoles() {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/Usuario/ListadoRoles',
        'dataType': 'json',
        'success': procesarListadoRoles
    });
}

function procesarListadoRoles(respuesta) {
    if (respuesta.success) {
        var list = respuesta.response;
        listaRoles = list;
        var html = '<th scope="col">Id</th>' +
                '<th scope="col">Nombre</th>';
        for (var i in list) {
            console.log(list[i]);
            html += templateitemRol(list[i].id, list[i].nombre);
        }
        document.getElementById("listaRoles").innerHTML = html;
    } else {
        alert(respuesta.message);
    }
    console.log("Cantidad de Roles:".concat(listaRoles.length));
}

function templateitemRol(id, nombre) {
    return  '<th scope="col" id="' + "rol".concat(id,nombre) + '" onclick="redireccion(' + id + ')">' + nombre + '</th>';
}

function redireccion(id) {
    window.location.href = "http://localhost:8080/bth/AdministradorPermisos.html?" + id;
}
