$(document).ready(function () {

});

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
        alert(respuesta.message);
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
        html += '       <td>' +
                '           <div class="form-check">' +
                '               <input type="checkbox" onclick="checkRoles(' + "'" + listaRoles[i].nombre + "-" + id + "'" + ')" name="' + "rol".concat(nombre) + '" class="form-check-input" id=rol' + listaRoles[i].nombre + "-" + id + '>' +
                '           </div>' +
                '       </td>';
    }
    return  html;
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

        if(losDatos[0]=="Docente"){
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
            //if(!dato.res) {
            alert(dato.message);
            //}
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