$(document).ready(function () {
});
listOpcionesCarrera();
//listOpcionesGestion();
listOpcionesColegio();
listOpcionesDocente();

function abrirModal() {
    $("#CreateCursoModal").modal("show");
}
function listOpcionesCarrera() {
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

        var html = "<option value='0'>Seleccione</option>";

        for (var i in list) {
            console.log(list[i]);
            html += "<option value='" + list[i].Id + "'>" + list[i].Nombre + "</option>";
        }
        document.getElementById("carrera").innerHTML = html;
        document.getElementById("carreraGrupo").innerHTML = html;
    } else {
        alert(respuesta.message);
    }
}
//function listOpcionesGestion() {
//    jQuery.ajax({
//        headers: {
//            'Accept': 'application/json',
//            'Content-Type': 'application/json'
//        },
//        'type': 'GET',
//        'url': 'api/carrera/get',
//        'dataType': 'json', // lo que se recibe 
//        'success': procesarListadoGestion
//    });
//}
//function procesarListadoGestion(respuesta) {
//    if (respuesta.success) { // if (respuesta.success == true)
//        var list = respuesta.response;
//
//        var html = "<option value='0'>Seleccione</option>";
//
//        for (var i in list) {
//            console.log(list[i]);
//            html += "<option value='" + list[i].Id + "'>" + list[i].Mesapagar + "</option>";
//        }
//        document.getElementById("gestion").innerHTML = html;
//        document.getElementById("gestionGrupo").innerHTML = html;
//    } else {
//        alert(respuesta.message);
//    }
//}
function listOpcionesColegio() {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/colegio/get',
        'dataType': 'json', // lo que se recibe 
        'success': procesarListadoColegio
    });
}
function procesarListadoColegio(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var list = respuesta.response;

        var html = "<option value='0'>Seleccione</option>";

        for (var i in list) {
            html += "<option value='" + list[i].id + "'>" + list[i].nombre + "</option>";
        }
        document.getElementById("colegio").innerHTML = html;
        document.getElementById("colegioGrupo").innerHTML = html;
    } else {
        alert(respuesta.message);
    }
}
function listOpcionesDocente() {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/docente/get',
        'dataType': 'json', // lo que se recibe 
        'success': procesarListadoDocente
    });
}
function procesarListadoDocente(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var list = respuesta.response;

        var html = "<option value='0'>Seleccione</option>";

        for (var i in list) {
            console.log(list[i]);
            html += "<option value='" + list[i].id + "'>" + list[i].nombre + "</option>";
        }
        document.getElementById("docente").innerHTML = html;
        document.getElementById("docenteGrupo").innerHTML = html;
    } else {
        alert(respuesta.message);
    }
}
function registrarGrupos() {
    var nivel = $("#nivel").val();
    var estado = $("#estado").val();
    var idGestion = $("#gestion").val();
    var idColegio = $("#colegio").val();
    var idCarrera = $("#carrera").val();
    var idDocente = $("#docente").val();
    var costo = $("#costo").val();
    var inicio = $("#inicio").val() + "";
    var fin = $("#fin").val() + "";



    var obj = new Object();
    obj.nivel = nivel;
    obj.estado = estado;
    obj.idGestion = idGestion;
    obj.idColegio = idColegio;
    obj.idCarrera = idCarrera;
    obj.idDocente = idDocente;
    obj.costo = costo;
    obj.fechaInicio = inicio;
    obj.fechaFin = fin;

    // validar que no esten vacios
    if (!nivel) {
        alert("debe ingresar el nivel");
        return;
    }
    if (!estado) {
        alert("debe ingresar el estado");
        return;
    }

    if (!idGestion) {
        alert("debe ingresar la gestion");
        return;
    }

    if (idColegio == 0) {
        alert('debe selecionar un colegio');
        return;
    }
    if (idCarrera == 0) {
        alert('debe selecionar una Carrera');
        return;
    }
    if (idDocente == 0) {
        alert('debe selecionar un Docente');
        return;
    }
    if (!costo) {
        alert("debe ingresar el costo");
        return;
    }
    if (inicio == 0) {
        alert('debe selecionar una fecha inicio');
        return;
    }
    if (fin == 0) {
        alert('debe selecionar una fecha fin');
        return;
    }


    fetch("api/grupos/registrar", {
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
                alert('Se registro Grupo');
                getListadoGrupos();
            });
}

function procesarRegistro(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        alert("El grupo fue registrado");
        limpiarDatosGrupos();
        getListadoGrupos();
        // $(location).attr('href', 'index.html');
    } else {
        console.log(respuesta);
        alert(respuesta.message);
    }
}
function eliminarGrupos(idGrupos) {
    if (!confirm('Realmente desea eliminar el grupo?')) {
        return;
    }
    fetch("api/grupos/" + idGrupos, {
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
                getListadoGrupos();

            });
}
function ProcesoEliminarGrupos(respuesta) {
    if (respuesta.success) {
// actualiza la tabla y quita la fila
    } else {
        alert("hubo un error al eliminar Grupo");
    }

}
function limpiarDatosGrupos() {
    var nivel = $("#nivel").val("");
    var estado = $("#estado").val("");
    var idGestion = $("#gestion").val("");
    var idColegio = $("#colegio").val("");
    var idCarrera = $("#carrera").val("");
    var idDocente = $("#docente").val("");



    $('#nivelGrupo').val('');
    $('#estadoGrupo').val('');
    $('#gestionGrupo').val('');
    $('#colegioGrupo').val('');
    $('#carreraGrupo').val('');
    $('#docenteGrupo').val('');
    $('#costoGrupo').val('');
    $('#inicioGrupo').val('');
    $('#finGrupo').val('');



}
function informacion(id) {
    fetch("api/grupos/" + id, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(function (request) {
        return request.json();
    })
            .then(function (json) {
                console.log(json.response.FechaInicio);
                $('#estadoGrupo').val(json.response.Estado);
                $('#idGrupos').val(json.response.Id);
                $('#carreraGrupo').val(json.response.IdCarrera);
                $('#colegioGrupo').val(json.response.IdColegio);
                $('#docenteGrupo').val(json.response.IdDocente);
                $('#gestionGrupo').val(json.response.IdGestion);
                $('#nivelGrupo').val(json.response.Nivel);
                $('#costoGrupo').val(json.response.Costo);
                var d = new Date(json.response.FechaInicio);
                d = d.toJSON().slice(0, 10);
                $('#inicioGrupo').val(d);
                var d = new Date(json.response.FechaFin);
                d = d.toJSON().slice(0, 10);
                $('#finGrupo').val(d);



            });
}
function editarGrupos() {
    var idGrupos = $('#idGrupos').val();
    var nivel = $("#nivelGrupo").val();
    var estado = $("#estadoGrupo").val();
    var idGestion = $("#gestionGrupo").val();
    var idColegio = $("#colegioGrupo").val();
    var idCarrera = $("#carreraGrupo").val();
    var idDocente = $("#docenteGrupo").val();
    var costo = $("#costoGrupo").val();
    var inicio = $("#inicioGrupo").val() + "";
    var fin = $("#finGrupo").val() + "";



    var obj = new Object();
    obj.nivel = nivel;
    obj.estado = estado;
    obj.idGestion = idGestion;
    obj.idColegio = idColegio;
    obj.idCarrera = idCarrera;
    obj.idDocente = idDocente;
    obj.id = idGrupos;
    obj.costo = costo;
    obj.fechaInicio = inicio;
    obj.fechaFin = fin;


    fetch("api/grupos/", {
        method: 'PUT',
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
                //procesarRegistro(json)
                alert('se realizo con exito la operacion');
                getListadoGrupos();
            });

}
