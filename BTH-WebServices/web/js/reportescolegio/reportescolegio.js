$(document).ready(function () {

});
function getComboA(selectObject) {
    var value = selectObject.value;
    var link;
    switch (value) {
        case '1':
            link = 'api/reporte/carrera/'
            break;
        case '2':
            link = 'api/reporte/grupo/'
            break;
        case '3':
            link = 'api/reporte/nota/carrera/'
            break;
        case '4':
            link = 'api/reporte/nota/grupo/'
            break;
        default:
            console.log('Lo lamentamos, por el momento no disponemos de ' + expr + '.');
    }
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
        },
        'type': 'GET',
        'url': link + 1,
        'dataType': 'json',
        'success': procesarReportes
    });

}
function procesarReportes(respuesta) {
    if (respuesta.success) {
        var nombre;
        var html;
        var grupo = 1;
        switch (respuesta.message) {
            case '1':
                $.each(respuesta.response, function (i, obj) {
                    if (!nombre) {
                        html += "<table table id='tabla'  class='table table-dark''><tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr><tr><th>Fecha</th><th>Nombre</th><th>Monto</th><th>Mes</th></tr>";
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
                
                $("#mis-repotes").html(html);
                break;
            case '2':


                $.each(respuesta.response, function (i, obj) {
                    if (!nombre) {
                        html += "<table table id='tabla'  class='table table-dark''><tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr><tr><th colspan='5'>Grupo:  " + obj.grupo + "</th> </tr><tr><th>Fecha</th><th>Nombre</th><th>Monto</th><th>Mes</th></tr>";
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
            
                $("#mis-repotes").html(html);
                break;
            case '3':

                $.each(respuesta.response, function (i, obj) {
                    if (!nombre) {
                        html += "<table table id='tabla'  class='table table-dark''><tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr><tr><th>NOMBRE</th><th>NOTA</th></tr>";
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
              
                $("#mis-repotes").html(html);

                break;
            case '4':

                $.each(respuesta.response, function (i, obj) {
                    if (!nombre) {
                        html += "<table id='tabla' class='table table-dark''><tr><th colspan='5'>Carrera:  " + obj.carrera + "</th> </tr><tr><th colspan='5'>Grupo:  " + obj.grupo + "</th> </tr><tr><th>Nombre</th><th>Nota</th></tr>";
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
                

                $("#mis-repotes").html(html);
                break;
            default:
                console.log('Lo lamentamos, por el momento no disponemos de ' + expr + '.');
        }
    }
}
