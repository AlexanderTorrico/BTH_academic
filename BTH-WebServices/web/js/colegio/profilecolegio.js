/* globals Chart:false, feather:false */
$(document).ready(function (){
//    var html = "esto es una prueba";
//    $("#titulo").html(html);

    var testObject = {id: 1, nombre: "Col. Don Bosco"};
    localStorage.setItem('objColegio', JSON.stringify(testObject));
//    $(location).attr('href', 'index.html');

    if (localStorage.getItem('objColegio')){
        var objColegio = JSON.parse(localStorage.getItem('objColegio'));
        $("#tituloPerfil").html(objColegio.nombre);
    } else {
        // si estuviera en otra pagina, redirigir al index
    }
});
(function () {
    'use strict'
    feather.replace()
    // Graphs
    var ctx = document.getElementById('myChart')
    // eslint-disable-next-line no-unused-vars
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: [
                'Sunday',
                'Lunes',
                'Tuesday',
                'Wednesday',
                'Thursday',
                'Friday',
                'Saturday'
            ],
            datasets: [{
                    data: [
                        15339,
                        21345,
                        18483,
                        24003,
                        23489,
                        24092,
                        12034
                    ],
                    lineTension: 0,
                    backgroundColor: 'transparent',
                    borderColor: '#007bff',
                    borderWidth: 4,
                    pointBackgroundColor: '#007bff'
                }]
        },
        options: {
            scales: {
                yAxes: [{
                        ticks: {
                            beginAtZero: false
                        }
                    }]
            },
            legend: {
                display: false
            }
        }
    })

    document.getElementById("contenido").innerHTML = '<object type="text/html" data="marcelo.html" ></object>';
}())

function pruebahome() {
    var html = "Bienvenido";
    $("#contenido").html(html);
}
function listacarrera() {
    var html = "Lista de carrera";
    $("#titulo").html(html);
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/colegio/listacarreras/habilitadas/' + 1,
        'dataType': 'json',
        'success': procesarListaCarrerasHabilitadas
    });
}
function registroestudiante() {
    var html = "Registrar estudiante";
    $("#titulo").html(html);
    $("#contenido").load("regestudiante.html");
}
function planpagos() {
    var html = "Plan de pagos";
    $("#titulo").html(html);
    $("#contenido").load("pagos.html");
}
function adminpaginas() {
    var html = "Administradores de la pagina";
    var table = "";
    table += "<table class='table table-striped table-sm'><thead><tr><th>Nombre completo</th><th>Correo</th><th>Estado</th></tr></thead><tbody>";
    $("#titulo").html(html);
    table += "<tr>";
    table += " <td> Jose Perez </td>";
    table += " <td> jose@gmail.com</td>";
    table += " <td><div class='form-check form-check-inline'><input class='form-check-input' type='radio' name='inlineRadioOptions' id='inlineRadio1' value='option1'><label class='form-check-label' for='inlineRadio1'>Activar</label></div><div class='form-check form-check-inline'><input class='form-check-input' type='radio' name='inlineRadioOptions' id='inlineRadio1' value='option1'><label class='form-check-label' for='inlineRadio1'>Desactivar</label></div></td>";
    table += " </tr>";
    table += "<tr>";
    table += " <td> Marcelo Villca </td>";
    table += " <td> marce@gmail.com</td>";
    table += " <td><div class='form-check form-check-inline'><input class='form-check-input' type='radio' name='inlineRadioOptions' id='inlineRadio1' value='option1'><label class='form-check-label' for='inlineRadio1'>Activar</label></div><div class='form-check form-check-inline'><input class='form-check-input' type='radio' name='inlineRadioOptions' id='inlineRadio1' value='option1'><label class='form-check-label' for='inlineRadio1'>Desactivar</label></div></td>";
    table += " </tr>";
    table += "</table>";
    $("#contenido").html(table);
}
function reportenotascarrera() {
    var html = "Reporte de notas por carrera";
    $("#titulo").html(html);
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/reporte/nota/carrera/' + 1,
        'dataType': 'json',
        'success': procesarReporte
    });
}
function reportenotasgrupo() {
    var html = "Reporte de notas por grupo";
    $("#titulo").html(html);
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/reporte/nota/grupo/' + 1,
        'dataType': 'json',
        'success': procesarReporte
    });
}
function reportepagoscarrera() {
    var html = "Reporte de pagos por carrera";
    $("#titulo").html(html);
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/reporte/carrera/' + 1,
        'dataType': 'json',
        'success': procesarReporte
    });
}
function reportepagosgrupo() {
    var html = "Reporte de pagos por  grupo";
    $("#titulo").html(html);
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': 'api/reporte/grupo/' + 1,
        'dataType': 'json',
        'success': procesarReporte
    });
}

function procesarReporte(respuesta) {
    if (respuesta.success) {
        var nombre;
        var html;
        var grupo = 1;
        switch (respuesta.message) {
            case '1':
                $.each(respuesta.response, function (i, obj) {
                    if (!nombre) {
                        html += "<table table id='tabla'  class='table table-striped table-sm'><tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr><tr><th>Fecha</th><th>Nombre</th><th>Monto</th><th>Mes</th></tr>";
                        nombre = obj.carrera;
                    }
                    else if (nombre == obj.carrera) {
                        html += "<tr>";
                        html += " <td>" + obj.fecha + "</th>";
                        html += " <td>" + obj.nombre + "</th>";
                        html += " <td>" + obj.monto + "</th>";
                        html += " <td>" + obj.mes + "</th>";
                        html += " </tr>";
                    } else {
                        html += "<tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr>";
                        html += "</tr><tr><th>Fecha</th><th>Nombre</th><th>Monto</th><th>Mes</th></tr>";
                        html += "<tr>";
                        html += " <td>" + obj.fecha + "</th>";
                        html += " <td>" + obj.nombre + "</th>";
                        html += " <td>" + obj.monto + "</th>";
                        html += " <td>" + obj.mes + "</th>";
                        html += " </tr>";
                        nombre = obj.carrera;
                    }
                });
                $("#contenido").html(html);
                break;
            case '2':


                $.each(respuesta.response, function (i, obj) {
                    if (!nombre) {
                        html += "<table table id='tabla'  class='table table-striped table-sm'><tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr><tr><th colspan='5'>Grupo:  " + obj.grupo + "</th> </tr><tr><th>Fecha</th><th>Nombre</th><th>Monto</th><th>Mes</th></tr>";
                        nombre = obj.carrera;
                    } else if (obj.grupo <= grupo) {
                        html += "<tr>";
                        html += " <td>" + obj.fecha + "</th>";
                        html += " <td>" + obj.nombre + "</th>";
                        html += " <td>" + obj.monto + "</th>";
                        html += " <td>" + obj.mes + "</th>";
                        html += " </tr>";
                    } else {

                        html += "<tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr><tr><th colspan='5'>Grupo:  " + obj.grupo + "</th> </tr>";
                        html += "</tr><tr><th>Fecha</th><th>Nombre</th><th>Monto</th><th>Mes</th></tr>";
                        html += "<tr>";
                        html += " <td>" + obj.fecha + "</th>";
                        html += " <td>" + obj.nombre + "</th>";
                        html += " <td>" + obj.monto + "</th>";
                        html += " <td>" + obj.mes + "</th>";
                        html += " </tr>";
                        grupo++;
                    }
                });
                $("#contenido").html(html);
                break;
            case '3':

                $.each(respuesta.response, function (i, obj) {
                    if (!nombre) {
                        html += "<table table id='tabla'  class='table table-striped table-sm'><tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr><tr><th>NOMBRE</th><th>1er Trimestre</th><th>2do Trimestre</th><th>3er Trimestre</th><th>Promedio final</th></tr>";
                        nombre = obj.carrera;
                    }
                    else if (nombre == obj.carrera) {
                        html += "<tr>";
                        html += " <td>" + obj.nombre + "</th>";
                        html += " <td>" + obj.nota + "</th>";
                        html += " </tr>";
                    } else {
                        html += "<tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr>";
                        html += "</tr><tr><th>NOMBRE</th><th>NOTA</th>></tr>";
                        html += "<tr>";
                        html += " <td>" + obj.nombre + "</th>";
                        html += " <td>" + obj.nota + "</th>";
                        html += " </tr>";
                        nombre = obj.carrera;
                    }
                });
                $("#contenido").html(html);
                break;
            case '4':

                $.each(respuesta.response, function (i, obj) {
                    if (!nombre) {
                        html += "<table id='tabla' class='table table-striped table-sm'><tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr><tr><th colspan='5'>Grupo:  " + obj.grupo + "</th> </tr><tr><th>Nombre</th><th>1er Trimestre</th><th>2do Trimestre</th><th>3er Trimestre</th><th>Promedio final</th></tr>";
                        nombre = obj.carrera;
                    } else if (obj.grupo <= grupo) {
                        html += "<tr>";
                        html += " <td>" + obj.nombre + "</th>";
                        html += " <td>" + obj.nota + "</th>";
                        html += " </tr>";
                    } else {
                        html += "<tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr><tr><th colspan='5'>Grupo:  " + obj.grupo + "</th> </tr>";
                        html += "</tr><tr><th>Nombre</th><th>Nota</th></tr>";
                        html += "<tr>";
                        html += " <td>" + obj.nombre + "</th>";
                        html += " <td>" + obj.nota + "</th>";
                        html += " </tr>";
                        grupo++;
                    }
                });
                $("#contenido").html(html);
                break;
            default:
                console.log('Lo lamentamos, por el momento no disponemos de ' + expr + '.');
        }
    }
}
function procesarListaCarrerasHabilitadas(respuesta) {
    if (respuesta.success) {

        var table = "";
        table += "<table class='table table-striped table-sm'><thead><tr><th>Nombre Carrera</th><th>Costo</th><th>Fecha inicio</th><th>Fecha fin</th><th>Horario</th><th>Duracion</th></tr></thead><tbody>";
        $.each(respuesta.response, function (i, obj) {
            table += "<tr>";
            table += " <td>" + obj.nombre + "</th>";
            table += " <td>" + obj.costo + "Bs</th>";
            table += " <td>" + obj.fechainicio + "</th>";
            table += " <td>" + obj.fechafin + "</th>";
            table += " <td>" + obj.horainicio + " - " + obj.horafin + "</th>";
            table += " <td>" + obj.duracion + " Meses</th>";
        });
        table += "</table>";
        $("#contenido").html(table);
    }




}