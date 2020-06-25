$(document).ready(function () {

});
localStorage.removeItem("grupo");
var paticipantesCount = 0;
var arrayIdProyects = [];

getListByGroup();

function addFormCreateProyect() {
    document.getElementById("cuerpo").innerHTML = templateCreatePoyect();
    getInfoGrupoInDropdowns();
}

function getProyect() {
    document.getElementById("cuerpo").innerHTML = templateGetPoyect();

}

function baseCreate() {
    document.getElementById("contenedor").innerHTML = templateBaseCreate();
}

function getInfoGrupoInDropdowns() {
    var data = {
        //idDocente: localStorage.getItem("idDocente")
        idDocente: localStorage.getItem("idDocente")
    };
    fetch("/bth/api/grupo/grupoInfo", {
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

                dropdownsGrupo(json.response);
                //document.getElementById("grupoDropdown").innerHTML = html;

            });
}

function saveGrupo(tupla) {
    localStorage.setItem("grupo", tupla.dataset.id);
    var txt = "Carrera: " + tupla.dataset.carrera + "      Grado: " + tupla.dataset.nivel + "°        Colegio: " + tupla.dataset.nombre;
    document.getElementById("divInfoGrupo").innerText = txt;
    if (localStorage.getItem("trimestre")) {
        if (localStorage.getItem("aaccion") != "showNote") {
            loadParametro();
        }
    }
}
function dropdownsGrupo(json) {


    var menuHtml = '   <div class="dropdown">  ' +
            '                       <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButtonGrupo" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">  ' +
            '                           Seleccione el grupo  ' +
            '                       </button>  ' +
            '                      <div id="grupoDropdownGrupo" class="dropdown-menu" aria-labelledby="dropdownMenuButtonGrupo">  ';
    for (var i in json) {

        var nombre = "Grupo: " + json[i].id + "-" + json[i].sigla + " [Col: " + json[i].nombre + "]";
        menuHtml += '<a class="dropdown-item" href="#" data-carrera="' + json[i].carrera + '" data-id="' + json[i].id + '" data-nombre="' + json[i].nombre + '" data-nivel="' + json[i].nivel + '" data-sigla="' + json[i].sigla + '" onclick="saveGrupo(this)">' + nombre + '</a>';
    }
    menuHtml += '</div>' +
            '</div>';
    document.getElementById("grupo").innerHTML = menuHtml;
}

function getGrupo() {
    var data = {
        id: localStorage.getItem("idDocente")
    };
    fetch("/bth/api/grupo/grupo", {
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
                var html = "";
                for (var i in json) {
                    array.push(json[i].id);
                    var grupo = json[i].sigla + "-" + json[i].id + "." + json[i].idGestion;

                    html += templateCard(json[i].carrera, json[i].colegio, json[i].nivel, grupo, json[i].id);
                }

                document.getElementById("body").innerHTML = html;

                getGrupoHorario();
            });
}




function getListByGroup() {


    var data = {
        id: localStorage.getItem("idDocente"),
        idgestion: 20
    };
    fetch("/bth/api/proyect/getListByGroup", {
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
                console.log("comienzo");
                console.log(json);
                var html = "";
                json = json["response"];

                for (i in json) {

                    html += templateItemGetListProyect(json[i]);
                }

                document.getElementById("cuerpo").innerHTML = html;

                
            });
}

function saveProyect() {
    if (document.getElementById("idname").value == "") {
        alert("El proyecto deve tener un nombre");
        return;
    }
    if (document.getElementById("iddescript").value == "") {
        alert("El proyecto deve tener una breve descripcion");
        return;
    }
    if (document.getElementById("iddescript").value == "") {
        alert("El proyecto deve tener una breve descripcion");
        return;
    }
    if (!localStorage.getItem("grupo")) {
        alert("Deves seleccionar un grupo para añadir participantes");
        return;
    }


    var data = {
        nombre: document.getElementById("idname").value,
        descripcion: document.getElementById("iddescript").value,
        img: document.getElementById("imgTest").innerHTML,
        idCarrera: 1
    };
    fetch("/bth/api/proyect/insert2", {
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
                //console.log(json["response"]);

                addParticipantes();
                $('#staticBackdrop').modal('show');
            });
}

function addParticipantes() {
    var data = {
        id: localStorage.getItem("grupo") //-------------------------------------------------------------------------
    };
    fetch("/bth/api/grupo/list/", {
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
                console.log(json["response"]);
                var html = "";
                json = json["response"];
                for (i in json) {
                    var j = parseInt(i) + 1;
                    var nombre = json[i]["apaterno"] + " " + json[i]["amaterno"] + " " + json[i]["nombre"];
                    html += templateItemTabla(j, nombre, json[i]["id"]);
                }

                document.getElementById("tblModalParticipantes").innerHTML = html;
            });
}


function checkSelected() {
    var checks = document.getElementsByClassName("checks");

    var p = "";

    for (var i = 0; i < paticipantesCount; i++) {
        if (checks[i].checked) {
            p += checks[i].value + "-";
        }
    }

    var data = {
        cadena: p
    };
    fetch("/bth/api/participante/insert", {
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

                alert("Se an añadido participantes al proyecto");
                getListByGroup();
            });

}

function deleteProyect(idProyect) {
    var r = confirm("Seguro que deseas eliminar el proyecto!");
    if (!r) {
        return;
    }
    var data = {
        id: idProyect
    };
    fetch("/bth/api/proyect/delete", {
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

                alert(json["message"]);
                getListByGroup();
            });
}

function publicar(idProyect){
    var isEstado;
    if(document.getElementById("cmn-toggle-"+idProyect).checked){
        isEstado = true;
    }else{
        isEstado = false;
    }
    
    
    var data = {
        id: idProyect,
        estado:isEstado
    };
    fetch("/bth/api/proyect/active", {
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

                
                
            });
    
    
}

function templateItemTabla(n, nombre, id) {
    paticipantesCount += 1;
    return  '   <tr>  ' +
            '                                           <th scope="row">' + n + '</th>  ' +
            '                                           <td>' + nombre + '</td>  ' +
            '                                           <td><input type="checkbox" class="checks" aria-label="Checkbox for following text input" value="' + id + '"></td>  ' +
            '                                             ' +
            '                                      </tr>  ';
}


function templateForm() {
    return  '   <div class="card col-5 ml-5" style="width: 18rem;">  ' +
            '                       <div class="card-header">' + carrera + '</div>  ' +
            '                       <div class="card-body">  ' +
            '                           <h5 class="card-title">Grupo: ' + grupo + '</h5>' +
            '                           <div class="row ml-2">  ' +
            '                               <p class="card-text">Colegio: ' + colegio + '</p>  ' +
            '                           </div>  ' +
            '                           <div class="row ml-2">  ' +
            '                               <p class="card-text">Nivel: ' + nivel + '°</p>  ' +
            '                           </div>  ' +
            '<h5 class="card-title text-center">Horario</h5>' +
            '   <div class="col-12 container">  ' +
            '                               <ul id="grupo-' + id + '" class="list-unstyled row">  ' +
            '                                   <li class="list-item col-4 border-top py-2"><strong>Dia</strong></li>  ' +
            '                                   <li class="list-item col-4 border-top py-2"><strong>Inicio</strong></li>  ' +
            '                                   <li class="list-item col-4 border-top py-2"><strong>Fin</strong></li>  ' +
            '                                    ' +
            '                               </ul>  ' +
            '                          </div>  ' +
            '                           <br>  ' +
            '                           <a href="#" class="btn btn-primary" onClick="save_NotaidGrupo(' + id + ')">Nota</a>  ' +
            '                           <a href="#" class="btn btn-primary" onClick="save_DeudaidGrupo(' + id + ')">Deuda</a>  ' +
            '                           <a href="#" class="btn btn-primary">Asistencia</a>  ' +
            '                       </div>  ' +
            '                  </div>  ';
}

function templateBaseCreate() {
    return  '   <div class="form-group">  ' +
            '                   <label for="exampleInputEmail1">Nombre del proyecto</label>  ' +
            '                   <input  class="form-control" id="idname" aria-describedby="emailHelp">  ' +
            '     ' +
            '               </div>  ' +
            '               <div class="form-group">  ' +
            '                   <label for="exampleInputPassword1">Descripcion</label>  ' +
            '                   <input  class="form-control" id="iddescript">  ' +
            '               </div>  ' +
            '     ' +
            '               <div id="grupo" class="col-2"></div>  ' +
            '               <div class="col-12">  ' +
            '                   <div class="alert alert-light m-3" id="divInfoGrupo" role="alert">  ' +
            '                       Seleccione el grupo y el trimestre  ' +
            '                   </div>  ' +
            '               </div>  ' +
            '     ' +
            '     ' +
            '               <input id="inputFileToLoad" type="file" onchange="encodeImageFileAsURL();" />  ' +
            '               <div id="imgTest"></div>  ' +
            '     ' +
            '               <br><br><br>  ' +
            '               <input id="btnsaveProyect" type="button" class="btn btn-success col-4 offset-4" data-toggle="modal" value="Guardar Proyecto" onclick="saveProyect()">  ' +
            '     ' +
            '    ';
}


function templateCreatePoyect() {
    return  '   <div class="form-group">  ' +
            '                       <label for="exampleInputEmail1">Nombre del proyecto</label>  ' +
            '                       <input  class="form-control" id="idname" aria-describedby="emailHelp">  ' +
            '     ' +
            '                   </div>  ' +
            '                   <div class="form-group">  ' +
            '                       <label for="exampleInputPassword1">Descripcion</label>  ' +
            '                       <input  class="form-control" id="iddescript">  ' +
            '                   </div>  ' +
            '     ' +
            '                   <div id="grupo" class="col-2"></div>  ' +
            '                   <div class="col-12">  ' +
            '                       <div class="alert alert-light m-3" id="divInfoGrupo" role="alert">  ' +
            '                           Seleccione el grupo y el trimestre  ' +
            '                       </div>  ' +
            '                   </div>  ' +
            '     ' +
            '     ' +
            '                   <input id="inputFileToLoad" type="file" onchange="encodeImageFileAsURL();" />  ' +
            '                   <div id="imgTest"></div>  ' +
            '     ' +
            '                   <br><br><br>  ' +
            '                  <input id="btnsaveProyect" type="button" class="btn btn-success col-4 offset-4" data-toggle="modal" value="Guardar Proyecto" onclick="saveProyect()">  ';
}

function templateGetPoyect() {
    return "<h1>Listado de proyectos</h1>";
}

function templateItemGetListProyect(obj) {
    arrayIdProyects.push(obj.id);
    var img = obj.img;
    console.log(obj.img.length);
    if (img.length < 20) {
        img = '<img src="imagenes/imagen4.jpg" class="card-img" alt="...">';
    }
    
    var check = "";
    if(obj.estado){
        
        check = "checked";
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
            '                                   <p class="card-text"><small class="text-muted"><b>Carrera: </b>' + obj.nombreCarrera + '</small></p>  ' +
            '                                   <p class="card-text"><small class="text-muted"><b>Colegio: </b>' + obj.nombreColegio + '</small></p>  ' +
            '                               </div>  ' +
            '                           </div>  ' +
            '     ' +
            '                           <div class="col-3 offset-1">  ' +
            '                               <div class="card-body">  ' +
            '                                   <h5>Publicar</h5>  ' +
            '                                   <div id="idParticipantes">  ' +
             '   <div class="switch mt-5">  '  + 
 '                   <input id="cmn-toggle-'+obj.id+'" class="cmn-toggle cmn-toggle-round"  type="checkbox" onClick="publicar('+obj.id+')" '+check+'>  '  + 
 '                   <label for="cmn-toggle-'+obj.id+'"></label>  '  + 
 '              </div>  '+
            '                                   </div>  ' +
            '                               </div>  ' +
            
            '                           </div>  ' +
            '     ' +
            '                       </div>  ' +
            '                  </div>  ';
}
