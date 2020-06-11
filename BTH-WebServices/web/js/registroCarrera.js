$(document).ready(function () {

});

function abrirModal() {
    $("#CreateCursoModal").modal("show");
}
function registrarCarrera() {
    var nombre = $("#nombre").val();
    var sigla = $("#sigla").val();
    var descripcion = $("#descripcion").val();
    var img = $("#img").val();
    if (img == 0) {
        alert('selecione una opcione')
        return;
    }
    var obj = new Object();
    obj.nombre = nombre;
    obj.sigla = sigla;
    obj.descripcion = descripcion;
    obj.img = img;

    if (nombre == "") {
        alert("debe ingresar el nombre de Carrera");
        return;
    }

    if (sigla == "") {
        alert("debe ingresar la sigla de Carrera");
        return;
    }

    if (descripcion == "") {
        alert("debe ingresar la descripcion de Carrera");
        return;
    }

    if (img == "") {
        alert("debe ingresar la area de Carrera");
        return;
    }


    fetch("api/carrera/registrar", {
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

function procesarRegistro(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        alert("La carrera fue registrado");
        limpiarDatosCarrera();
        getListadoCarreras();
//        $(location).attr('href', 'index.html');
    } else {
        console.log(respuesta);
        alert(respuesta.message);
    }
}

function limpiarDatosCarrera() {
    var nombre = $("#nombre").val("");
    var sigla = $("#sigla").val("");
    var descripcion = $("#descripcion").val("");
    var img = $("#img").val("");

    $('#descripcionCarrera').val('');
    $('#idCarrera').val('');
    $('#imgCarrera').val('');
    $('#nombreCarrera').val('');
    $('#siglaCarrera').val('');
}

function eliminarCarrera(idCarrera) {
    if (!confirm('Realmente desea eliminar la Carrera?')) {
        return;
    }
    fetch("api/carrera/" + idCarrera, {
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
                getListadoCarreras();

            });
}
function ProcesoEliminarCarrera(respuesta) {
    if (respuesta.success) {
// actualiza la tabla y quita la fila
    } else {
        alert("hubo un error al eliminar la Carrera");
    }

}
function informacion(id) {
    fetch("api/carrera/" + id, {
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
                $('#descripcionCarrera').val(json.response.Descripcion);
                $('#idCarrera').val(json.response.Id);
                $('#imgCarrera').val(json.response.Img);
                $('#nombreCarrera').val(json.response.Nombre);
                $('#siglaCarrera').val(json.response.Sigla);
            });

}
function editarCarrera() {
    var idCarrera = $('#idCarrera').val();
    var nombre = $("#nombreCarrera").val();
    var sigla = $("#siglaCarrera").val();
    var descripcion = $("#descripcionCarrera").val();
    var img = $("#imgCarrera").val();

    if (img == 0) {
        alert('selecione una opcione')
        return;
    }
    // validar que no esten vacios
    if (!nombre) {
        alert("debe ingresar el nombre de usuario");
        return;
    }
    var obj = new Object();
    obj.nombre = nombre;
    obj.sigla = sigla;
    obj.descripcion = descripcion;
    obj.img = img;
    obj.id = idCarrera;

    fetch("api/carrera/", {
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
                limpiarDatosCarrera();
                getListadoCarreras();
            });

}