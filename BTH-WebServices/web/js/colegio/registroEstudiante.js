

function registrarEstudiante() {

    var ci = $("#ci").val();
    var nombre = $("#nombre").val();
    var apaterno = $("#apaterno").val();
    var amaterno = $("#amaterno").val();
    var telefono = $("#telefono").val();
    var nacimiento = $("#nacimiento").val();
    var sexo = document.registroform.gender.value;
    var ids = document.getElementById("seleccion").value;
    var sub = ids.split(",");
    var idcarrera = parseInt(sub[0]);
    var idgrupo = parseInt(sub[1]);
    var obj = new Object();
    if (localStorage.getItem('objColegio')) {
        var objColegio = JSON.parse(localStorage.getItem('objColegio'));
        var idColegio = objColegio.id;
    } else {
        // si estuviera en otra pagina, redirigir al index
    }
    obj.ci = ci;
    obj.nombre = nombre;
    obj.apaterno = apaterno;
    obj.amaterno = amaterno;
    obj.contrasenia = "root";
    obj.telefono = telefono;
    obj.genero = sexo;
    obj.nacimiento = nacimiento;
    obj.estado = "a";
    obj.idColegio = idColegio;
    obj.idCarrera = parseInt(idcarrera);
    obj.idGrupo = parseInt(idgrupo);

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
        alert("el estudiante fue registrado " + usuario.nombre);
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}

