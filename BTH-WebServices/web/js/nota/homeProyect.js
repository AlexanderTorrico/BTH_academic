$(document).ready(function () {
    
});

getListByGroup();
var arrayIdProyects = [];

function getListByGroup() {


    var data = {
        id: localStorage.getItem("carrera"),
        idgestion: 20
    };
    fetch("/bth/api/proyect/getListByGroupEstado", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
            .then(function (request) {
                return request.json();
            })
            .then(function (json) {
                
                
                
                var html = "";
                json = json["response"];
                document.getElementById("titulo").innerText = "Aun no se tienen proyectos en esta carrrera";
                for (i in json) {
                    document.getElementById("titulo").innerText = json[0]["nombreCarrera"];
                    html += templateItemGetListProyect(json[i]);
                }

                document.getElementById("cuerpo").innerHTML = html;

                for (j in arrayIdProyects) {
                    addPartaker(arrayIdProyects[j]);
                }
                
            });
}

function addPartaker(idProyecto) {
    var data = {
        id: idProyecto
    };
    fetch("/bth/api/participante/getListByProyect", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
            .then(function (request) {
                return request.json();
            })
            .then(function (json) {
                var array = [];
                json = json["response"];
                console.log(json);
                var html = "";
                for (var i in json) {
                    html += templateItemParticipante(json[i]);
                }

                document.getElementById("proyect_" + idProyecto).innerHTML = html;
            });
}

function templateItemGetListProyect(obj) {
    arrayIdProyects.push(obj.id);
    var img = obj.img;
    console.log(obj.img.length);
    if (img.length < 20) {
        img = '<img src="imagenes/imagen4.jpg" class="card-img" alt="...">';
    }
    return  '   <div class="card col-12 mt-3">  ' +
            '                       <div class="row no-gutters">  ' +
            '                           <div class="col-md-3">  ' +
            img +
            '                           </div>  ' +
            '                           <div class="col-5">  ' +
            '                               <div class="card-body">  ' +
            '                                   <h5 class="card-title">' + obj.nombre + '</h5>  ' +
            '                                   <p class="card-text">' + obj.descripcion + '</p>  ' +
            
            '                                   <p class="card-text"><small class="text-muted"><b>Colegio: </b>' + obj.nombreColegio + '</small></p>  ' +
            '                               </div>  ' +
            '                           </div>  ' +
            '     ' +
            '                           <div class="col-3 offset-1">  ' +
            '                               <div class="card-body">  ' +
            '                                   <h5>Integrantes</h5>  ' +
            '                                   <div id="idParticipantes">  ' +
            '                                       <ul id="proyect_' + obj.id + '">  ' +
            '     '+
            '     ' +
            '                                       </ul>  ' +
            '                                   </div>  ' +
            '                               </div>  ' +
            
            '                           </div>  ' +
            '     ' +
            '                       </div>  ' +
            '                  </div>  ';
}

function templateItemParticipante(obj) {
    return '<li>' + obj.cadena + '</li>  ';
}