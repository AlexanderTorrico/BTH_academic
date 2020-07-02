$(document).ready(function () {

});

function abrirModal() {
    $("#CreateCursoModal").modal("show");
}
function registrarGestion() {
    var MesAPaga = $("#mespagar").val();
    var obj = new Object();
    obj.mes = mes;
    
    if (mes == "") {
        alert("debe ingresar el Mes");
        return;
        var obj = new Object();
        obj.mespagar = MesAPagar;
        fetch("api/gestiones/registrar", {
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
                    procesarRegistro(json)
                });
    }
}
function procesarRegistro(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        alert("La Gestion fue registrado");
        limpiarDatosGestion();
        getListadoGestion();
//        $(location).attr('href', 'index.html');
    } else {
        console.log(respuesta);
        alert(respuesta.message);
    }
}

function limpiarDatosGestion() {
    var mes = $("#mespagar").val("");
    $('#mesGestion').val('');
    $('#idGestion').val('');
}
function eliminarGestion(idGestion) {
    if (!confirm('Realmente desea eliminar la Gestion?')) {
        return;
    }
    fetch("api/gestiones/" + idGestion, {
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
                getListadoGestion()();

            });
}
function ProcesoEliminarGestion(respuesta) {
    if (respuesta.success) {
// actualiza la tabla y quita la fila
    } else {
        alert("hubo un error al eliminar la Gestion");
    }

}
function informacion(id) {
    fetch("api/gestiones/" + id, {
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
                $('#idGestion').val(json.response.Id);
                $('#mesGestion').val(json.response.MesAPagar);
            });
}
function editarGestiones() {
    var idGestion = $('#idGestion').val();
    var mes = $("#mesGestion").val();

    if (!mes) {
        var obj = new Object();
        obj.nombre = mes;
        obj.id = idGestion;
        alert("debe ingresar el nombre de usuario");
        return;
    }
    var obj = new Object();
    obj.nombre = mes;
    obj.id = idGestion;

    fetch("api/gestiones/", {
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
                limpiarDatosGestion();
                getListadoGestion();
            });

}