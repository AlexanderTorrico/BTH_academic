$(document).ready(function () {

});

function registrarEstudiante() {
    var ci = $("#ci").val();
    var nombre = $("#nombre").val();
    var apaterno = $("#apaterno").val();
    var amaterno = $("#amaterno").val();
    var telefono = $("#telefono").val();
    var nacimiento = $("#nacimiento").val();
    var estado = $("#estado").val();
    var idCarrera = $("#carrera").val();

    // validar que no esten vacios
  
    var obj = new Object();
    obj.ci = ci;
    obj.nombre = nombre;
    obj.apaterno = apaterno;
    obj.amaterno = amaterno;
    obj.contrasenia = "root";
    obj.telefono = telefono;
    obj.genero = "n";
    obj.nacimiento = nacimiento;
    obj.estado = estado;
    obj.idColegio = 1;
    obj.idCarrera = parseInt(idCarrera);
    

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/estudiante/registro',
        'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarRegistro
    });
}
function procesarRegistro(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        localStorage.setItem('objUsuario', JSON.stringify(usuario));
        alert("el estudiante fue registrado");
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}

