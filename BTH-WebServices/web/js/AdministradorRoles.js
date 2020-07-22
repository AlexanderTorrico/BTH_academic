$(document).ready(function () {

});

getListadoUsuarios()

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
            console.log(list[i]);
            html += templateitemRol(list[i].id, list[i].nombre, list[i].apPaterno, list[i].apMaterno, list[i].roles);
        }
        document.getElementById("listaUsuarios").innerHTML = html;
        checkInfo(list);
    } else {
        alert(respuesta.message);
    }
}

function templateitemRol(id, nombre, apPaterno, apMaterno, roles) {
    var docente = "'Docente-" + id + "'";
    var administrador = "'Administrador-" + id + "'";
    var administradorBTH = "'AdministradorBTH-" + id + "'";
    return  '<tr>' +
            '   <th scope="row">' + id + '</th>  ' +
            '   <td>' + nombre.concat(' ', apPaterno, ' ', apMaterno) + '</td>  ' +
            '   <td>' +
            '       <div class="form-check">' +
            '           <input type="checkbox" onclick="checkRoles(' + docente + ')" name="' + "rol".concat(nombre) + '" class="form-check-input" id="' + "rolDocente".concat("-", id) + '">' +
            '       </div>' +
            '   </td>' +
            '   <td>' +
            '       <div class="form-check">' +
            '           <input type="checkbox" onclick="checkRoles(' + administrador + ')" name="' + "rol".concat(nombre) + '" class="form-check-input" id="' + "rolAdministrador".concat("-", id) + '">' +
            '       </div>' +
            '   </td>' +
            '   <td>' +
            '       <div class="form-check">' +
            '           <input type="checkbox" onclick="checkRoles(' + administradorBTH + ')" name="' + "rol".concat(nombre) + '" class="form-check-input" id="' + "rolAdministradorBTH".concat("-", id) + '">' +
            '       </div>' +
            '   </td>' +
            '</tr>';
}

function checkInfo(list) {
    for(var i in list) {
        if(list[i].roles.length != 0) {
            for(var j in list[i].roles) {
                if(list[i].roles[j] == 1) {
                    document.getElementById("rolDocente".concat("-", list[i].id)).checked = true;
                } else if(list[i].roles[j] == 2) {
                    document.getElementById("rolAdministrador".concat("-", list[i].id)).checked = true;
                } else if(list[i].roles[j] == 3) {
                    document.getElementById("rolAdministradorBTH".concat("-", list[i].id)).checked = true;
                }
            }
        }
    }
}

function checkRoles(dato) {
    var losDatos = dato.split("-");
    console.log(losDatos);
    var inf = document.getElementById("rol".concat(dato)).checked;
    var url = "";
    if(inf) {
        //Insertando
        url = "api/Usuario/Insertar";
    } else {
        //Eliminando
        url = "api/Usuario/Eliminar";
    }
    console.log(inf);
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
        'success': function(dato) {
            //if(!dato.res) {
                alert(dato.message);
            //}
        }
    });
}




