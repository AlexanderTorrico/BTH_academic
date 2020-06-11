$(document).ready(function () {

});

function registrarColegio() {
    var usuario = $("#nombre").val();
    var sigep = $("#sigep").val();
    var director = $("#director").val();
    var direccion = $("#direccion").val();
    var telefono = $("#telefono").val();
    var correo = $("#correo").val();
    var username = $("#username").val();
    var contrasena = $("#contrasena").val();

    // validar que no esten vacios
    if (!usuario) {
        alert("debe ingresar el nombre de usuario");
        return;
    }



    var obj = new Object();
    obj.nombre = usuario;
    obj.sigep = sigep;
    obj.director = director;
    obj.direccion = direccion;
    obj.telefono = telefono;
    obj.correo = correo;
    obj.username = username;
    obj.contrasenia = contrasena;

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/colegio/registro',
        'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarRegistro
    });
}

function procesarRegistro(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        limpiarDatosColegio();
        getListadoColegio();
        //  alert("su registro fu exitoso. se le envio un mensaje a su correo para verificar su cuenta");
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}

function eliminarColegio(idColegio) {
    if (!confirm('Realmente desea eliminar el colegio?')) {
        return;
    }
    fetch("api/colegio/" + idColegio, {
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
                getListadoColegio();

            });
}
function ProcesoEliminarColegio(respuesta) {
    if (respuesta.success) {
// actualiza la tabla y quita la fila
    } else {
        alert("hubo un error al eliminar Colegio");
    }

}
function limpiarDatosColegio() {
    var nombre = $("#nombre").val("");
    var sigep = $("#sigep").val("");
    var director = $("#director").val("");
    var direccion = $("#direccion").val("");
    var telefono = $("#telefono").val("");
    var correo = $("#correo").val("");
    var username = $("#username").val("");


    $('#nombreColegio').val('');
    $('#sigepColegio').val('');
    $('#directorColegio').val('');
    $('#direccionColegio').val('');
    $('#telefonoColegio').val('');
    $('#CorreoColegio').val('');
    $('#UsernameColegio').val('');



}


function informacion(id) {
    fetch("api/colegio/" + id, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(function (request) {
        return request.json();
    })
            .then(function (json) {
                //     console.log(json);

                $('#correoColegio').val(json.response.correo);
                $('#direccionColegio').val(json.response.direccion);
                $('#directorColegio').val(json.response.director);
                $('#esModuloColegio').val(json.response.esModulo);
                $('#EstadoColegio').val(json.response.estado);
                $('#idColegio').val(json.response.id);
                $('#nombreColegio').val(json.response.nombre);
                $('#sigepColegio').val(json.response.sigep);
                $('#telefonoColegio').val(json.response.telefono);
                $('#UsernameColegio').val(json.response.username);

            });

}
function editarColegio() {
    var idColegio = $('#idColegio').val();
    var nombre = $("#nombreColegio").val();
    var sigep = $("#sigepColegio").val();
    var director = $("#directorColegio").val();
    var direccion = $("#direccionColegio").val();
    var telefono = $("#telefonoColegio").val();
    var esmodulo = $("#esModuloColegio").val();
    var correo = $("#correoColegio").val();
    var username = $("#UsernameColegio").val();
    var estado = $("#EstadoColegio").val();


    if (!nombre) {
        alert("debe ingresar el nombre de usuario");
        return;
    }

    if (!sigep) {
        alert("debe ingresar el nombre de usuario");
        return;
    }

    if (!director) {
        alert("debe ingresar el nombre de usuario");
        return;
    }
    if (!direccion) {
        alert("debe ingresar el nombre de usuario");
        return;
    }
    if (!telefono) {
        alert("debe ingresar el nombre de usuario");
        return;
    }
    if (!esmodulo) {
        alert("debe ingresar el nombre de usuario");
        return;
    }

    var obj = new Object();
    obj.nombre = nombre;
    obj.sigep = sigep;
    obj.director = director;
    obj.direccion = direccion;
    obj.telefono = telefono;
    obj.esmodulo = esmodulo;
    obj.correo = correo;
    obj.username = username;
    obj.estado = estado;

    obj.id = idColegio;

    fetch("api/colegio/", {
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
                limpiarDatosColegio();
                getListadoColegio();
            });

}
