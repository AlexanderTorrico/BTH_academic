$(document).ready(function () {
});

function abrirModal() {
    $("#CreateCursoModal").modal("show");
}
function registrarHora() {
    var inicio = document.getElementById("inicio").value + ":00";
    var fin = document.getElementById("fin").value + ":00";
    //console.log(inicio);
    //console.log(fin);
    var obj = new Object();

    obj.inicio = inicio;
    obj.fin = fin;

    fetch("api/horas/registrar", {
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
                alert("Se registro Hora")
                getListadoHora();
            });
}
function procesarRegistro(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        alert("La hora fue registrado");
        getListadoHora();
//        $(location).attr('href', 'index.html');
    } else {
        console.log(respuesta);
        alert(respuesta.message);
    }
}
function impiarDatosHora() {
    var inicio = $("#inicio").val("");
    var fin = $("#fin").val("");

    $('#InicioHora').val('');
    $('#FinHora').val('');
    $('#idHora').val('');
}
function eliminarGrupos(idHora) {
    if (!confirm('Realmente desea eliminar la Hora?')) {
        return;
    }
    fetch("api/horas/" + idHora, {
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
                getListadoHora();

            });
}
function ProcesoEliminarHora(respuesta) {
    if (respuesta.success) {
// actualiza la tabla y quita la fila
    } else {
        alert("hubo un error al eliminar la hora");
    }

}
function informacion(id) {
    fetch("api/horas/" + id, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(function (request) {
        return request.json();
    })
            .then(function (json) {
                console.log(json);
                $('#InicioHora').val(json.response.Inicio);
                $('#FinHora').val(json.response.Fin);
                $('#idHora').val(json.response.Id);
            });
}
function editarHora() {
    // var idHora = document.getElementById("idHora").value;
    //var inicio = document.getElementById("InicioHora").value;
    //var fin = document.getElementById("FinHora").value;
    //var d = new Date();
    // var n = new d.getTime();

    var idHora = document.getElementById("idHora").value;
    var inicio = document.getElementById("InicioHora").value;
    var fin = document.getElementById("FinHora").value;

    var obj = new Object();

    obj.inicio = inicio;
    obj.fin = fin;
    obj.id = idHora;

    fetch("api/horas/", {
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
                impiarDatosHora();
                getListadoHora();
            });

}