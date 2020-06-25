
var x = true;
var btnTexto = document.getElementById("botonEditar");

var lblNombre = document.getElementById("nombreColegio");
var lblSigep = document.getElementById("sigepColegio");
var lblDirector = document.getElementById("directorColegio");
var lblTelefono = document.getElementById("telefonoColegio");
var lblDireccion = document.getElementById("direccionColegio");
var txtNombre = document.getElementById("editNombreColegio");
var txtSigep = document.getElementById("editSigepColegio");
var txtDirector = document.getElementById("editDirectorColegio");
var txtTelefono = document.getElementById("editTelefonoColegio");
var txtDireccion = document.getElementById("editDireccionColegio");
getColegio();

function editar() {
    if (x) {
        editarInformacion();
    } else {
        guardarInformacion();
    }
}

function editarInformacion() {
    lblNombre.style.display = "none";
    lblSigep.style.display = "none";
    lblDirector.style.display = "none";
    lblTelefono.style.display = "none";
    lblDireccion.style.display = "none";
    txtNombre.style.display = "block";
    txtSigep.style.display = "block";
    txtDirector.style.display = "block";
    txtTelefono.style.display = "block";
    txtDireccion.style.display = "block";
    x = false;
    btnTexto.textContent = "Guardar";
    txtNombre.value = lblNombre.textContent;
    txtSigep.value = lblSigep.textContent;
    txtDirector.value = lblDirector.textContent;
    txtTelefono.value = lblTelefono.textContent;
    txtDireccion.value = lblDireccion.textContent;
}

function guardarInformacion() {
    lblNombre.style.display = "block";
    lblSigep.style.display = "block";
    lblDirector.style.display = "block";
    lblTelefono.style.display = "block";
    lblDireccion.style.display = "block";
    txtNombre.style.display = "none";
    txtSigep.style.display = "none";
    txtDirector.style.display = "none";
    txtTelefono.style.display = "none";
    txtDireccion.style.display = "none";
    x = true;
    btnTexto.textContent = "Editar";
    txtNombre.innerHTML = lblNombre.value;
    txtSigep.innerHTML = lblSigep.value;
    txtDirector.innerHTML = lblDirector.value;
    txtTelefono.innerHTML = lblTelefono.value;
    txtDireccion.innerHTML = lblDireccion.value;
}

var loadFile = function (event) {
    var image = document.getElementById('imgPerfil');
    image.src = URL.createObjectURL(event.target.files[0]);
};


function getColegio() {
    var idColegio = getParameterByName('id');
    if (idColegio === "") {
        
    } else {
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
            },
            'type': 'GET',
            'url': 'api/colegio/' + idColegio,
            'dataType': 'json', // lo que se recibe 
            'success': rellenarFormulario
        });
    }
}
function rellenarFormulario(respuesta) {
    console.log(respuesta);
    if (!respuesta.success) {
        alert(respuesta.message);
    }
    var obj = respuesta.response;
    console.log(obj);
    lblNombre.innerHTML = obj.nombre;
    lblSigep.innerHTML = obj.sigep;
    lblDirector.innerHTML = obj.director;
    lblTelefono.innerHTML = obj.telefono;
    lblDireccion.innerHTML = obj.direccion;
    document.title = 'Colegio ' + obj.nombre;
}

function actualizarColegio() {
    var obj = new Object();
    obj.nombre = nombre;
    obj.descripcion = descripcion;
    obj.idCarrera = getParameterByName('id');
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

}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
