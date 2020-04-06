$(document).ready(function () {

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

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/docente/registro',
        'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarRegistro
    });
}


function procesarRegistro(respuesta){
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        alert("su registro fu exitoso. se le envio un mensaje a su correo para verificar su cuenta");
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}

