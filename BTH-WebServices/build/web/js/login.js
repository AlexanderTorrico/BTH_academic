$(document).ready(function() {
    
});

function ingresar() {
    var usuario = $("#usuario").val();
    var clave = $("#clave").val();
    
    // validar que no esten vacios
    
    var obj = new Object();
    obj.username = usuario;
    obj.contrasenia = clave;
    
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/user/login', 
        'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarLogin
    });
}

function procesarLogin(respuesta){
    if (respuesta.success) { // respuesta.success == true
        var usuario = respuesta.response; 
        
        alert(respuesta.message);
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        $(location).attr('href','index.html');
        
        
    } else {
        alert(respuesta.message);
    }
}