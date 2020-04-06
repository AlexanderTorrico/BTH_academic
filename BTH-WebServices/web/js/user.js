$(document).ready(function () {

});
function saludo(){
    alert("Hola mundo");
}
function sendEmail() {
    
    var correo = $("#email").val();
    
    ;

    // validar que no esten vacios
    if (!correo) {
        alert("debe ingresar el nombre de usuario");
        return;
    }
    if(!validar_email(correo)){
        alert("El correo insertado no es valido");
        return;
    }

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/user/sendVerificacion',
        //'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarRegistro
    });    
}

function validateChangePass(){
    var password = $("#password").val();
    var repeat = $("#repeat").val();
  
    alert(password);
    alert(repeat);
    if(password.length < 6){
        alert("La contraseña deve tener al menos 6 caracteres");
        return;
    }
    if(password!=repeat){
        alert("Las contraseñas no coinciden");
        return;
    }
  
}

function procesarRegistro(respuesta){
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        alert("Confirme el cambio de su contraseña a partir de su correo electroncio");
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}

function validar_email( email ) 
{
    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email) ? true : false;
}



