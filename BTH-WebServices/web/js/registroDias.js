$(document).ready(function () {

});
listOpcionesHora();

function dropdownsGrupo(json) {


    var menuHtml = '   <div class="dropdown">  ' +
            '                       <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButtonGrupo" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">  ' +
            '                           Seleccione el grupo  ' +
            '                       </button>  ' +
            '                      <div id="grupoDropdownGrupo" class="dropdown-menu" aria-labelledby="dropdownMenuButtonGrupo">  ';
    for (var i in json) {

        var nombre = "Grupo: " + json[i].id + "-" + json[i].sigla + " [Col: " + json[i].nombre + "]";
        menuHtml += '<a class="dropdown-item" href="#" data-carrera="' + json[i].carrera + '" data-id="' + json[i].id + '" data-nombre="' + json[i].nombre + '" data-nivel="' + json[i].nivel + '" data-sigla="' + json[i].sigla + '" onclick="saveGrupo(this)">' + nombre + '</a>';
    }
    menuHtml += '</div>' +
            '</div>';
    document.getElementById("grupo").innerHTML = menuHtml;
}
function abrirModal() {
    $("#CreateCursoModal").modal("show");
}



function listOpcionesHora() {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/horas/get',
        'dataType': 'json', // lo que se recibe 
        'success': procesarListadoHora
    });
}
function procesarListadoHora(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var list = respuesta.response;

        var html = "<option value='0'>Seleccione</option>";

        for (var i in list) {
            console.log(list[i]);
            html += "<option value='" + list[i].Id + "'>" + list[i].Inicio + "</option>";
        }
        document.getElementById("idHora").innerHTML = html;
        document.getElementById("HorasDia").innerHTML = html;
    } else {
        alert(respuesta.message);
    }
}

function registrarDias() {
    var dia = $("#dia").val();
    var idGrupo = $("#idGrupo").val();
    var idHora = $("#idHora").val();

   // var d = new Date();
    var obj = new Object();
    obj.dia = dia;
    obj.idGrupo = idGrupo;
    obj.idHora = idHora;


    fetch("api/dias/registrar", {
        method: 'POST',
        body: JSON.stringify(obj),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(function (request) {
        //console.log(request);
        return request.json();
    })
            .then(function (json) {
                //console.log(json);
                alert("Se registro Dias");
                getListadoDias();
            });
}
function procesarRegistro(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        alert("El Dia fue registrado");
        limpiarDatosDias();
        getListadoDias();
//        $(location).attr('href', 'index.html');
    } else {
        console.log(respuesta);
        alert(respuesta.message);
    }
}
function limpiarDatosDias() {
    var dias = $("#dia").val("");
    var idGrupo = $("#idGrupo").val("");
    var idHoras = $("#idHora").val("");

    $('#diaDia').val('');
    $('#idDias').val('');
    $('#GruposDia').val('');
    $('#HorasDia').val('');
}
function eliminarGrupos(idDias) {
    if (!confirm('Realmente desea eliminar el Dia?')) {
        return;
    }
    fetch("api/dias/" + idDias, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(function (request) {
        return request.json();
    })
            .then(function (json) {
                alert(json.message);
                getListadoDias();

            });
}
function ProcesoEliminarDias(respuesta) {
    if (respuesta.success) {
// actualiza la tabla y quita la fila
    } else {
        alert("hubo un error al eliminar la Carrera");
    }

}
