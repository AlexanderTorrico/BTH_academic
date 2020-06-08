$(document).ready(function () {
    if (localStorage.getItem('objUsuario')) {
        var us = JSON.parse(localStorage.getItem('objUsuario'));
        $("#login-btn").hide();
        $("#registro-btn").hide();
        $("#perfil-btn").show();
        $("#cerrarSecion-btn").show();
        $('#cambiContracena').show();
        $("#btnCrearProyecto").show();
        
        $(".opEditarProyecto").show();
        $(".opEliminarProyecto").show();
        $('#cambiContracena').attr('href', 'changePassword.html?id=' + us.id);
    } else {
        $("#btnCrearProyecto").hide();
        $(".opEditarProyecto").hide();
        $(".opEliminarProyecto").hide();
        $("#perfil-btn").hide();
        $("#login-btn").show();
        $("#registro-btn").show();
        $("#perfil-btn").hide();
        $("#cerrarSecion-btn").hide();
        $('#cambiContracena').hide();
    }
    var prodId = getParameterByName('nombre');
    if (prodId === "") {
        prodId = "Todos";
        getList(0);
    } else {
        prodId = "de " + getParameterByName('nombre');
        getList(getParameterByName('id'));
    }
    $("#tituloProyecto").html("Proyectos " + prodId);

});
function getList(id) {
    var url = 'api/proyectos/carrera/';
    if (id !== 0) {
        url += id;
    }
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': url,
        'dataType': 'json', // lo que se recibe 
        'success': procesarList
    });
}
function procesarList(respuesta) {

    var html = '';
    $.each(respuesta.response, function (i, obj) {
        html += '<tr>';
        html += '<td>' + obj.nombre + '</td>';
        if (localStorage.getItem('objUsuario')) {
        html += "<td><button type='button' data-toggle='modal' data-target='#exampleModalProyecto' onclick='actualizar(" + obj.id + ")' class='btn btn-primary'>Editar</button></td>";
        html += "<td><button type='button' onclick='eliminar(" + obj.id + ")' class='btn btn-danger'>Eliminar</button></td>";
        }
        html += '<td>' + obj.nombreCarrera + '</td>';
        html += '</tr>';

    });
    if (html === "") {
        html += "No hay proyectos";
        $("#tabla").html("");

        $("#mensajeTabla").html(html);

    } else {
        $("#opcionesTabla").html(html);
    }

}
function insert() {
    var nombre = $("#nombreProyecto").val();
    var idProyecto = $("#idProyecto").val();
    var descripcion = $("#descripcionProyecto").val();
    if (nombre.trim() === '' || descripcion.trim() === '') {
        alert('complete los datos');
        return;
    }
    var obj = new Object();
    obj.nombre = nombre;
    obj.descripcion = descripcion;
    obj.idCarrera = getParameterByName('id');

    if (idProyecto !== 0) {
        obj.id = idProyecto;
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'PUT',
            'url': 'api/proyectos/',
            'data': JSON.stringify(obj), // lo que se envia
            'dataType': 'json', // lo que se recibe 
            'success': procesarRegistro
        });
        return;
    }

    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/proyectos/',
        'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarRegistro
    });

}
function procesarRegistro(respuesta) {
    alert(respuesta.message);
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        location.reload();
        alert("se realizo exitosamente la operacion!!!");
    } else {
        alert(respuesta.message);
    }
}
function actualizar(id) {
    $("#idProyecto").val(id);
    var url = 'api/proyectos/' + id;
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': url,
        'dataType': 'json', // lo que se recibe 
        'success': procesarActualizacion
    });
}
function procesarActualizacion(respuesta) {
    var obj = respuesta.response;
    $("#nombreProyecto").val(obj.nombre);
    $("#descripcionProyecto").val(obj.descripcion);
}
function eliminar(id) {
    if (confirm("Estas seguro que desea Eliminarlo")) {
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
            },
            'type': 'DELETE',
            'url': 'api/proyectos/' + id,
            'dataType': 'json', // lo que se recibe 
            'success': mensajeEstado
        });
    }
}
function mensajeEstado(respuesta) {
    alert(respuesta.message);
    location.reload();
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


