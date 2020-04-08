$(document).ready(function () {

});
function saludo(){
    alert("Hola mundo");
}
function sendEmail() {
    var correo = $("#email").val();
    

    // validar que no esten vacios
    if (!correo) {
        alert("debe ingresar el nombre de usuario");
        return;
    }
    if(!validar_email(correo)){
        alert("El correo insertado no es valido");
        return;
    }
    
    var obj = new Object();
    obj.correo = correo;
    obj.tipo = "docente";
    
    console.log(JSON.stringify(obj));
    //location.href="http://www.fento.com.mx";
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        
        'url': 'api/user/recuperar',
        'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarRequest
    });    
}

function validateChangePass(){
    var password = $("#password").val();
    var repeat = $("#repeat").val();
    var correo = getParameterByName("correo");
    var token = getParameterByName("token");
    
    
    if(password.length < 6){
        alert("La contraseña deve tener al menos 6 caracteres");
        return;
    }
    if(password!=repeat){
        alert("Las contraseñas no coinciden");
        return;
    }
    var obj = new Object();
    obj.correo = correo;
    obj.tipo = "c";
    obj.username = token;
    obj.contrasenia = password;
    
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        
        'url': 'api/user/cambiarPass',
        'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarRequest
    });  
    
    
}

function procesarRequest(respuesta){
    if(respuesta.success){
        alert(respuesta.message);
        if(respuesta.message = "Se envieo el enlace del cambio e contraseña a su correo"){
            location.href="/bth/index.html";
        }else{
            location.href="/bth/login.html";
        }
        
    }else{
        alert(respuesta.message);
    }
    
}

function validar_email( email ) 
{
    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email) ? true : false;
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

