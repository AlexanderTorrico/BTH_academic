(document).ready(function () {

});

function registrarDocente() {
    var usuario = $("#nombre").val();
    var apaterno = $("#apaterno").val();
    var amaterno = $("#amaterno").val();
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
    obj.apaterno = apaterno;
    obj.amaterno = amaterno;
    obj.correo = correo;
    obj.username = username;
    obj.contrasenia = contrasena;

// validar que no esten vacios
    if (!usuario) {
        alert("debe ingresar el nombre del Docente");
        return;
    }
    if (!apaterno) {
        alert("debe ingresar el Apellido Paterno");
        return;
    }
    if (!amaterno) {
        alert("debe ingresar el Apellido Materno");
        return;
    }
    if (!correo) {
        alert("debe ingresar el Correo");
        return;
    }
    if (!username) {
        alert("debe ingresar el Username");
        return;
    }
    if (!contrasena) {
        alert("debe ingresar la contrasena");
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
                
                if(json["success"]){
                    alert("Se envio un mensaje a su correo. Por favor confirmar para poder ingresar");
                    
                    location.href="/bth/loginbth.html";
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
        alert("su registro fu exitoso. se le envio un mensaje a su correo para verificar su cuenta");
        getListadoDocentes();
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}


function eliminarGrupos(idGrupos) {
    if (!confirm('Realmente desea eliminar docente?')) {
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
        alert("hubo un error al eliminar Grupo");
    }

}
