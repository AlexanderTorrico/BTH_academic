$(document).ready(function() {
    
});

function registrarse() {
   var usuario = $("#usuario").val();
    var clave = $("#clave").val();
    var nombre = $("#nombreCompleto").val();
    
    // validar que no esten vacios
    if (!usuario) {
        alert("debe ingresar el nombre de usuario");
        return;
    }
    
    var obj = new Object();
    obj.username = usuario;
    obj.password = clave;
    obj.nombreCompleto = nombre;
    
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/usuario/registro', 
        'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarRegistro
    });
}

function procesarRegistro(respuesta){
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}
