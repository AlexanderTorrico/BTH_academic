$(document).ready(function () {

});

getListaPermisos()

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
        var list = respuesta.response;
        var html = "";
        var cont = 1;
        for (var i in list) {
            if (i == 0 || i % 3 == 0) {
                console.log(list[i]);
                var x = parseFloat(i) + parseFloat(1);
                var y = parseFloat(i) + parseFloat(2);
                html += templateitemPermiso(cont, list[i].nombre, list[i].tipo, list[x].tipo, list[y].tipo);
                cont++;
            }
        }
        document.getElementById("listaPermisos").innerHTML = html;
    } else {
        alert(respuesta.message);
    }
}

function templateitemPermiso(id, nombre, tipo1, tipo2, tipo3) {
    return  '<tr>' +
            '   <th scope="row">' + id + '</th>  ' +
            '   <td>' + nombre + '</td>  ' +
            '   <td>' +
            '       <div class="form-check">' +
            '           <input type="radio"  name="' + nombre + '" class="form-check-input" id="' + nombre.concat(tipo1) + '">' +
            '       </div>' +
            '   </td>' +
            '   <td>' +
            '       <div class="form-check">' +
            '           <input type="radio"  name="' + nombre + '" class="form-check-input" id="' + nombre.concat(tipo2) + '">' +
            '       </div>' +
            '   </td>' +
            '   <td>' +
            '       <div class="form-check">' +
            '           <input type="radio"  name="' + nombre + '" class="form-check-input" id="' + nombre.concat(tipo3) + '">' +
            '       </div>' +
            '   </td>' +
            '</tr>';
}