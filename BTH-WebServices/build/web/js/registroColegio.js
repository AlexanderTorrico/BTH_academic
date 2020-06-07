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

     fetch("api/colegio/registro", {
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
        alert("su registro fu exitoso. se le envio un mensaje a su correo para verificar su cuenta");
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}
