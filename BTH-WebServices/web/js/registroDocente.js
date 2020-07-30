$(document).ready(function () {
    if (localStorage.getItem('idDocente') == null || localStorage.getItem('nombre') == null) {

    } else {
        window.location.href = "/bth/perfil-user.html";
    }
});

function registrarDocente() {
    var usuario = $("#nombre").val();
    var apaterno = $("#apaterno").val();
    var amaterno = $("#amaterno").val();
    var correo = $("#correo").val();
    var username = $("#username").val();
    var contrasena = $("#contrasena").val();
    if (!usuario) {
        alert("debe ingresar el nombre de usuario");
        return;
    }
    var obj = new Object();
    obj.nombre = usuario;
    obj.apaterno = apaterno;
    obj.amaterno = amaterno;
    obj.correo = correo;
    obj.username = username;
    obj.contrasenia = contrasena;
// validar que no esten vacios
    if (!usuario) {
        alert("Debe ingresar el nombre del Docente.");
        return;
    }
    if (!apaterno) {
        alert("Debe ingresar el Apellido Paterno.");
        return;
    }
    if (!amaterno) {
        alert("Debe ingresar el Apellido Materno.");
        return;
    }
    if (!correo) {
        alert("Debe ingresar el Correo.");
        return;
    }
    if (!username) {
        alert("Debe ingresar el Username.");
        return;
    }
    if (!contrasena) {
        alert("Debe ingresar la Contrasena.");
        return;
    }

    fetch("api/docente/registro", {
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
                if (json["success"]) {
                    alert("Se envió un mensaje a su correo, por favor confirmar para poder ingresar.");
                    location.href = "/bth/loginbth.html";
                    return;
                }
                alert(json["message"]);
                //getListadoDocentes();
            });
}

function procesarRegistro(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        alert("Su registro fue exitoso, se le envió un mensaje a su correo para verificar su cuenta.");
        getListadoDocentes();
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}

function eliminarGrupos(idGrupos) {
    if (!confirm('¿Realmente desea eliminar docente?')) {
        return;
    }
    fetch("api/docente/" + idGrupos, {
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
                getListadoDocentes();
            });
}
function ProcesoEliminarGrupos(respuesta) {
    if (respuesta.success) {
// actualiza la tabla y quita la fila
    } else {
        alert("Hubo un error al eliminar Grupo.");
    }

}
