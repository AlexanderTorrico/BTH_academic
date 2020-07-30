$(document).ready(function () {

});

var numeroUser;

getListadoUsuarios();

function getListadoUsuarios() {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/Usuario/Listado',
        'dataType': 'json',
        'success': procesarListadoUsuarios
    });
}

function procesarListadoUsuarios(respuesta) {
    if (respuesta.success) {
        var list = respuesta.response;
        var html = "";
        for (var i in list) {
            html += templateitemUsuarios(list[i].id, list[i].nombre, list[i].apPaterno, list[i].apMaterno, list[i].roles);
        }
        document.getElementById("listaUsuarios").innerHTML = html;
        checkInfo(list);
    } else {
    }
}

function templateitemUsuarios(id, nombre, apPaterno, apMaterno, roles) {
    var html = '<tr>' +
            '   <th scope="row">' + id + '</th>  ' +
            '       <td>' +
            '           <a href=perfil-user.html?id=' + id + ' target="_blank"> ' +
            nombre.concat(' ', apPaterno, ' ', apMaterno) +
            '           </a>' +
            '       </td>';
    for (var i in listaRoles) {
        rol = "rol" + listaRoles[i].nombre + "-" + id;
        html += '       <td>' +
                '           <div class="form-check">' +
                '               <input type="checkbox" onclick="checkRoles(' + "'" + listaRoles[i].nombre + "-" + id + "'" + ');listaColegios(' + id + ", '" + rol + "'" + ');" name="' + "rol".concat(nombre) + '" class="form-check-input ' + listaRoles[i].nombre + '" id=' + rol + '>' +
                '           </div>' +
                '       </td>';
    }
    return  html;
}

function listaColegios(id, nombreRol) {
    numeroUser = id;
    if ($("#" + nombreRol).is(':checked')) {
        $("#exampleModalCenter").modal('show');
    }
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/colegio/get',
        'dataType': 'json',
        'success': colegioModal
    });
}

function colegioModal(respuesta) {
    var lista = respuesta.response;
    var html = "";
    for (var i in lista) {
        var aux = parseFloat(i) + parseFloat(1);
        console.log(aux);
        html += '<label class="radio-inline">' +
                '   <input type="radio" name="inlineRadioOptions" id="inlineRadio3" value="option3" onclick="colegioReferencia(' + aux + ')"> ' + lista[i].nombre +
                '</label><br>'
    }
    $(".modal-body").html(html);
}

function colegioReferencia(id) {
    console.log("Id de colegio = " + id);
    console.log("id de usuario = " + numeroUser);
    var obj = new Object();
    obj.idRol = id;
    obj.idUsuario = numeroUser;
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/Usuario/AsignarColegio',
        'data': JSON.stringify(obj),
        'dataType': 'json',
        'success': function () {
            alert("Se logró asignar el colegio");
        }
    });

}

function checkInfo(list) {
    for (var i in list) {
        if (list[i].roles.length !== 0) {
            for (var j in list[i].roles) {
                for (var k = 1; k <= listaRoles.length; k++) {
                    if (list[i].roles[j] === k) {
                        document.getElementById("rol".concat(listaRoles[k - 1].nombre, "-", list[i].id)).checked = true;
                    }
                }
            }
        }
    }
}

function checkRoles(dato) {
    var losDatos = dato.split("-");
    var inf = document.getElementById("rol".concat(dato)).checked;
    var url = "";
    if (inf) {
        //Insertando
        url = "api/Usuario/Asignar";

        if (losDatos[0] == "Docente") {
            alert("vamos");
            saveTldocente(losDatos[1]);

        }
    } else {
        //Eliminando
        url = "api/Usuario/Eliminar";
    }
    //console.log(inf);
    var obj = new Object();
    obj.nombre = losDatos[0];
    obj.id = losDatos[1];
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': url,
        'data': JSON.stringify(obj),
        'dataType': 'json',
        'success': function (dato) {
        }
    });
}

function IngresarRol() {
    var elemento = document.getElementById("nombreRolNuevo").value;
    var obj = new Object();
    obj.nombre = elemento;
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/Usuario/InsertarRol',
        'data': JSON.stringify(obj),
        'dataType': 'json',
        'success': anadirPermisos
    });
}

function anadirPermisos() {
    largo = listaRoles.length;
    largo++;
    location.href = "AdministradorPermisos.html?".concat(largo);
}






/* Parche para que funcione el Rol de docente [Alexander Torrico]*/

function saveTldocente(idUser) {

    var data = {
        id: idUser
    };
    fetch("/bth/api/user-roles/saveDocente", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
            .then(function (request) {
                return request.json();
            })
            .then(function (json) {
                console.log(json);
            });
}