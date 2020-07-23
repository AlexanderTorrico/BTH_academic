$(document).ready(function () {
});
function buscarEstudiante() {
    var ci = $("#registroci").val();
    if (ci) {
        // con un ajax, obtenes la cancion por id
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
            },
            'type': 'GET',
            'url': 'api/pago/' + ci,
            'dataType': 'json', // lo que se recibe 
            'success': procesarObtenerEstudiante
        });
    }
    return false;
}
function procesarObtenerEstudiante(respuesta) {
    if (respuesta.success)
    {
        var codigo = 0;
        var imput = "<div class='form-group col-md-2'><label for='inputEmail4'>Pagar Cuota</label><input type='number' class='form-control' id='monto'>";
        imput += "<button type='submit' class='btn btn-info btn-lg' onclick='registrarPago()'>Registrar</button>";
        imput += "</div>";
        var table = "";
        table += "<table  class='table table-striped table-sm'><thead><tr><th>Nombre Completo</th><th>Cuota</th><th>pago</th><th>Saldo</th><th>Fecha</th><th>Duracion</th><th>Costo materia</th></tr></thead><tbody>";
        $.each(respuesta.response, function (i, obj) {
            table += "<tr>";
            table += " <td>" + obj.nombre + "</th>";
            table += " <td>" + obj.cuota + "Bs</th>";
            table += " <td>" + obj.pago + "Bs</th>";
            table += " <td>" + obj.saldo + "Bs</th>";
            table += " <td>" + obj.fecha + "</th>";
            table += " <td>" + obj.duracion + "</th>";
            table += " <td>" + obj.costomateria + "</th>";
            codigo = obj.codigo;
        });
        table += "</table>";
        $("#inputdiv").html(imput);
        $("#tablaplan").html(table);


        var objEstudiante = {idCodigo: codigo};
        localStorage.setItem('objEstudiante', JSON.stringify(objEstudiante));


    }
    return false;
}
function registrarPago() {
   
    if (localStorage.getItem('objEstudiante')){
        var objColegio = JSON.parse(localStorage.getItem('objEstudiante'));
        codigo = objColegio.idCodigo;
    } else {
        // si estuviera en otra pagina, redirigir al index
    }
    var currentdate = new Date(); 
    var monto = $("#monto").val();
    var obj = new Object();
    obj.monto = monto;
    obj.mes = "1";
    obj.fecha = "20200723";
    obj.idEstudiantes_grupos = codigo;
    jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': 'api/pago/registrar',
        'data': JSON.stringify(obj), // lo que se envia
        'dataType': 'json', // lo que se recibe 
        'success': procesarPago
    });
}
function procesarPago(respuesta) {
    if (respuesta.success) { // if (respuesta.success == true)
        var usuario = respuesta.response;
        alert("pago de mensualidad exitoso");
    } else {
        alert(respuesta.message);
    }
}
function getParameterByName(name, url) {
    if (!url)
        url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
    if (!results)
        return null;
    if (!results[2])
        return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}