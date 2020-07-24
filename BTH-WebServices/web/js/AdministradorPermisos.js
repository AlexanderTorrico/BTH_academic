$(document).ready(function () {

});
var user = window.location.search[1];
getListaPermisos();

var list;
var lista;

function getListaPermisos() {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/Permisos/Listado',
        'dataType': 'json',
        'success': procesarListadoPermisos
    });
}

function procesarListadoPermisos(respuesta) {
    if (respuesta.success) {
        list = respuesta.response;
        var html = "";
        var cont = 1;
        for (var i in list) {
            if (i === 0 || i % 3 === 0) {
                html += templateitemPermiso(cont, list[i].nombre);
                cont++;
            }
        }
        document.getElementById("listaPermisos").innerHTML = html;
    } else {
        
    }
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/Permisos/RolPermisos',
        'dataType': 'json',
        'success': checkInfo
    });
}

function templateitemPermiso(id, nombre) {
    return  '<tr>' +
            '   <th scope="row">' + id + '</th>  ' +
            '   <td>' + nombre + '</td>  ' +
            '   <td>' +
            '       <div class="form-check">' +
            '           <input type="radio" onclick=verificarCheck("' + nombre + '",1) id="' + nombre.concat(1) + '" class="form-check-input">' +
            '       </div>' +
            '   </td>' +
            '   <td>' +
            '       <div class="form-check">' +
            '           <input type="radio" onclick=verificarCheck("' + nombre + '",2) id="' + nombre.concat(2) + '" class="form-check-input">' +
            '       </div>' +
            '   </td>' +
            '   <td>' +
            '       <div class="form-check">' +
            '           <input type="radio" onclick=verificarCheck("' + nombre + '",3) id="' + nombre.concat(3) + '" class="form-check-input">' +
            '       </div>' +
            '   </td>' +
            '</tr>';
}

function checkInfo(rolesPermisos) {
    lista = rolesPermisos.response;
    for (var i in list) {
        for (var j in lista) {
            if (lista[j].idPermiso == i) {
                var x = i;
                while (x > 3) {
                    x -= 3;
                }
                if (lista[j].idRol == user) {
                    document.getElementById(list[i - 1].nombre.concat(x)).checked = true;
                }
            }
        }
    }
}

function verificarCheck(nombre, id) {
    for (var i = 0; i < list.length; i++) {
        if (nombre === list[i].nombre) {
            var nro2 = parseFloat(id) + parseFloat(i);
            var nro = parseFloat(nro2) - parseFloat(i);
            i = list.length;
            checkPermisos(nombre, nro, nro2);
        }
    }
}

function checkPermisos(nombre, id, idrol) {
    var url = "api/Permisos/Asignar";
    var obj = new Object();
    obj.nombre = nombre;
    if (id === 1) {
        obj.tipo = "SoloEscritura";
    } else if (id === 2) {
        obj.tipo = "SoloLectura";
    } else if (id === 3) {
        obj.tipo = "EscrituraLectura";
    }
    obj.idRol = user;
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
            alert(dato.message);
            uncheckPermisos(obj);
        }
    });
}

function uncheckPermisos(obj) {
    var url = "api/Permisos/Designar";
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