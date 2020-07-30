$(document).ready(function () {

});

getGrupo();


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

function getGrupoHorario() {
   
    var data = {
        id:localStorage.getItem("idDocente")
    };
    fetch("/bth/api/grupo/horario", {
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
                json = json["response"];
                var html = '';
                
                //var html = "";
                for (var i in json) {
                    
                    html += '<li class="list-item col-4 border-top py-2">' + json[i].dia + '</li>';
                    html += '<li class="list-item col-4 border-top py-2">' + json[i].inicio + '</li>';
                    html += '<li class="list-item col-4 border-top py-2">' + json[i].fin + '</li>';
                
                    document.getElementById("grupo-"+json[i].id).innerHTML= document.getElementById("grupo-"+json[i].id).innerHTML+html;
                    html="";
                }
                
                

            });

}

function save_NotaidGrupo(id){
    document.location.href = "/bth/nota.html";
    localStorage.setItem("grupo",id);
    localStorage.setItem("aaccion","showNote");
    
}

function save_DeudaidGrupo(id){
    document.location.href = "/bth/mensualidadesShowDocente.html";
    localStorage.setItem("grupo",id);
}

function save_Assistances(id){
    document.location.href = "/bth/asistenciaDocente.html";
    localStorage.setItem("grupo",id);
}



function templateCard(carrera, colegio, nivel, grupo, id) {
    return  '   <div class="card col-5 ml-5" mt-5 style="width: 18rem;">  ' +
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
            '                           <a href="#" class="btn btn-primary" onClick="save_NotaidGrupo('+id+')">Nota</a>  ' +
            '                           <a href="#" class="btn btn-primary" onClick="save_DeudaidGrupo('+id+')">Deuda</a>  ' +
            '                           <a href="#" class="btn btn-primary" onClick="save_Assistances('+id+')">Asistencia</a>  ' +
            '                       </div>  ' +
            '                  </div>  ';
}