$(document).ready(function () {

});
getPagos();

function getPagos() {
    /*var data = {
     
     };
     fetch('api/reportesPago',{
     method:"POST",
     body: JSON.stringify(data),
     headers: {
     'Accept': 'application/json, text/plain',
     'Content-Type': 'application/json;charset=UTF-8'
     }
     })
     .then(function (response) {
     return response.json();
     })
     .then(function (myJson) {
     
     console.log(myJson);
     });
     */
    var obj = new Object();
    obj.idCarrera = '0';
    obj.idColegio = '0';
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'data': JSON.stringify(obj), // lo que se envia
        'url': 'api/reportesPago',
        'dataType': 'json', // lo que se recibe 
        'success': procesargetagos
    });
}
function procesargetagos(respuesta) {
   console.log(respuesta);

    if (respuesta.success) { // if (respuesta.success == true)
        var listaobj = respuesta.response;
        //   localStorage.setItem('objUsuario', JSON.stringify(usuario));

        var nombre;
        var html;
        for (var i in listaobj) {
            if (!nombre) {
                        html += "<table table id='tabla'  class='table table-dark''><tr><th colspan='4'>Colegio:  " +listaobj[i].NombreColegio + "</th></tr><tr><th>Estado</th><th>Carrera</th><th>Costo</th></tr>";
                        nombre = listaobj[i].NombreColegio;
                    }
                    else if (nombre == listaobj[i].NombreColegio) {
                        html += "<tr>";
                        html += " <td>" + listaobj[i].Estado + "</th>";
                        html += " <td>" + listaobj[i].NombreCarrera + "</th>";
                        html += " <td>" + listaobj[i].Costo + "</th>";
                        html += " </tr>";
                    } else {
                        html += "<tr><th colspan='5'>Carrera:  " + listaobj[i].NombreColegio + "</th> </tr>";
                        html += "</tr><tr><th>Estado</th><th>Carrera</th><th>Costo</th></tr>";
                        html += "<tr>";
                        html += " <td>" + listaobj[i].Estado + "</th>";
                        html += " <td>" + listaobj[i].NombreCarrera + "</th>";
                        html += " <td>" + listaobj[i].Costo + "</th>";
                        html += " </tr>";
                        nombre = listaobj[i].NombreColegio;
                    }
            
        }
      $("#mis-repotes").html(html);
        
       
//        $(location).attr('href', 'index.html');
    } else {
        alert(respuesta.message);
    }
}

function  buscar() {
    var obj = new Object();
    obj.idCarrera = $('#carrera').val();
    obj.idColegio = $('#colegio').val();
    console.log(obj);
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'data': JSON.stringify(obj), // lo que se envia
        'url': 'api/reportesPago',
        'dataType': 'json', // lo que se recibe 
        'success': procesargetagos
    });
}
function  cerrar() {
    var bandera = $('#panelBusqueda').hide();

    $('#carrera').val('0');
    $('#colegio').val('0');
    buscar();
}

function edit(obj) {
    alert(obj.dataset.id);
}
//function templateitemCarrera(numero, obj) {
//    console.log(obj);
//    return  '   <tr>  ' +
//            '                                               <th scope="row">' + numero + '</th>  ' +
//            '                                               <td>' + obj.Estado + '</td>  ' +
//            '                                               <td>' + obj.NombreColegio + '</td>  ' +
//            '                                               <td>' + obj.NombreCarrera + '</td>  ' +
//            '                                               <td>' + obj.Costo + ' Bs.</td>  ' +
//            '                                               <td>  ' +
//            '                                               <a href="#" data-toggle="modal" data-target="#exampleModal" onclick=(informacion(' + obj.IdGrupo + '))><i class="fas fa-eye"></i></a> ' +
//            '                                               </td>  ' +
//            '                                          </tr>  ';
//}
function  abrirBusqueda() {
    var bandera = $('#panelBusqueda').show();
    listOpcionesCarrera();
    listOpcionesColegio();

}
function listOpcionesCarrera() {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/carrera/get',
        'dataType': 'json', // lo que se recibe 
        'success': procesarListadoCarrera
    });
}
function procesarListadoCarrera(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var list = respuesta.response;

        var html = "<option value='0'>Seleccione</option>";

        for (var i in list) {
            console.log(list[i]);
            html += "<option value='" + list[i].Id + "'>" + list[i].Nombre + "</option>";
        }
        document.getElementById("carrera").innerHTML = html;
    } else {
        alert(respuesta.message);
    }
}
function listOpcionesColegio() {
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'GET',
        'url': 'api/colegio/get',
        'dataType': 'json', // lo que se recibe 
        'success': procesarListadoColegio
    });
}
function procesarListadoColegio(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var list = respuesta.response;

        var html = "<option value='0'>Seleccione</option>";

        for (var i in list) {
            html += "<option value='" + list[i].id + "'>" + list[i].nombre + "</option>";
        }
        document.getElementById("colegio").innerHTML = html;
    } else {
        alert(respuesta.message);
    }
}







