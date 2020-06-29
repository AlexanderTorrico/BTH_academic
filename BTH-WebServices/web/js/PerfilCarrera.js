var x = true;
var btnTexto = document.getElementById("botonEditar");

var lblNombre = document.getElementById('nombreCarrera');
var lblSigla = document.getElementById('siglasCarrera');
var lblJefe = document.getElementById('jefeCarrera');
var lblDescripcion = document.getElementById('descripcionCarrera');
var txtNombre = document.getElementById('editNombreCarrera');
var txtSigla = document.getElementById('editSiglasCarrera');
var txtJefe = document.getElementById('editJefeCarrera');
var txtDescripcion = document.getElementById('editDescripcionCarrera');
getCarrera();


function editar() {
    if (x) {
        editarInformacion();
    } else {
        guardarInformacion();
    }
}

function editarInformacion() {
    lblNombre.style.display = "none";
    lblSigla.style.display = "none";
    lblJefe.style.display = "none";
    lblDescripcion.style.display = "none";
    txtNombre.style.display = "block";
    txtSigla.style.display = "block";
    txtJefe.style.display = "block";
    txtDescripcion.style.display = "block";
    btnTexto.textContent = "Guardar";
    txtNombre.value = lblNombre.textContent;
    txtSigla.value = lblSigla.textContent;
    txtJefe.value = lblJefe.textContent;
    txtDescripcion.value = lblDescripcion.textContent;
    x = false;
}

function guardarInformacion() {
    lblNombre.style.display = "block";
    lblSigla.style.display = "block";
    lblJefe.style.display = "block";
    lblDescripcion.style.display = "block";
    txtNombre.style.display = "none";
    txtSigla.style.display = "none";
    txtJefe.style.display = "none";
    txtDescripcion.style.display = "none";
    btnTexto.textContent = "Editar";
    lblNombre.innerHTML = txtNombre.value;
    lblSigla.innerHTML = txtSigla.value;
    lblJefe.innerHTML = txtJefe.value;
    lblDescripcion.innerHTML = txtDescripcion.value;
    x = true;
}

var loadFile = function (event) {
    var image = document.getElementById('imgPerfil');
    image.src = URL.createObjectURL(event.target.files[0]);
};

function getCarrera() {
    var idCarrera = getParameterByName('id');
    if (idCarrera === "") {

    } else {
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
            },
            'type': 'GET',
            'url': 'api/carrera/' + idCarrera,
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
    lblNombre.innerHTML = obj.Nombre;
    lblSigla.innerHTML = obj.Sigla;
    lblJefe.innerHTML = obj.Id;
    lblDescripcion.innerHTML = obj.Descripcion;
    document.title = 'Carrera de ' + obj.nombre;
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
