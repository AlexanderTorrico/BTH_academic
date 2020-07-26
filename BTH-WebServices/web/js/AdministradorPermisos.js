$(document).ready(function () {

});
console.log(window.location.search.substr(4));
var user = window.location.search.substr(4);
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
            '   <td>' +
            '       <a href=' + nombre + '.html target="_blank">' +
                        nombre +
            '        </a>' +
            '   </td>  ' +
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

var listaEspacios = [,];

function checkInfo(rolesPermisos) {
    lista = rolesPermisos.response;
    for (var i in list + 1) {
        for (var j in lista) {
            if (lista[j].idPermiso == i) {
                var x = devuelvePosicion(i);
                var nombreActual = list[i - 1].nombre.concat(x);
                listaEspacios.push([nombreActual, true]);
                if (lista[j].idRol == user) {
                    document.getElementById(nombreActual).checked = true;
                    document.getElementById(nombreActual).disabled = true;
                }
            }
        }
    }
    for(var i in list + 1) {
        console.log(listaEspacios[i]);
    }
}

function devuelvePosicion(x) {
    while (x > 3) {
        x -= 3;
    }
    return x;
}

function verificarCheck(nombre, id) {
    for (var i = 0; i < list.length; i++) {
        if (nombre === list[i].nombre) {
            var nro2 = parseFloat(id) + parseFloat(i);
            var nro = parseFloat(nro2) - parseFloat(i);
            i = list.length;
            console.log("P1 = " + nro2);
            console.log("P2 = " + nro);
            console.log(nombre);
            if(nro === 1) {
                document.getElementById(nombre.concat(1)).disabled = true;
                document.getElementById(nombre.concat(2)).checked = false;
                document.getElementById(nombre.concat(2)).disabled = false;
                document.getElementById(nombre.concat(3)).checked = false;
                document.getElementById(nombre.concat(3)).disabled = false;
            } else if(nro === 2) {
                document.getElementById(nombre.concat(1)).checked = false;
                document.getElementById(nombre.concat(1)).disabled = false;
                document.getElementById(nombre.concat(2)).disabled = true;
                document.getElementById(nombre.concat(3)).disabled = false;
                document.getElementById(nombre.concat(3)).checked = false;
            } else if(nro === 3) {
                document.getElementById(nombre.concat(1)).checked = false;
                document.getElementById(nombre.concat(1)).disabled = false;
                document.getElementById(nombre.concat(2)).checked = false;
                document.getElementById(nombre.concat(2)).disabled = false;
                document.getElementById(nombre.concat(3)).disabled = true;
            }
            checkPermisos(nombre, nro);
        }
    }
}

function checkPermisos(nombre, id) {
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
        'success': function dato() {
            uncheckPermisos(obj)
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