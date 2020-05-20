$(document).ready(function () {

});

getGrupo();


function getGrupo() {
    var data = {
        id: 1 // ----------------------------- Id del docete modificar
    };
    fetch("http://localhost:36129/bth/api/grupo/grupo", {
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

                    html += templateCard(json[i].carrera, json[i].colegio, json[i].nivel, grupo, "grupo-" + json[i].id);
                }

                document.getElementById("body").innerHTML = html;

                getGrupoHorario(array);
            });
}

function getGrupoHorario(array) {
    var num = 1;
    for (var n in array) {
        var num = 1;
        alert(num);
        var data = {
            id:num  // ----------------------------- Id del docete modificar
        };
        fetch("http://localhost:36129/bth/api/grupo/horario", {
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
                    for (var i in json) {
                        html += '<li class="list-item col-4 border-top py-2">' + json[i].dia + '</li>';
                        html += '<li class="list-item col-4 border-top py-2">' + json[i].inicio + '</li>';
                        html += '<li class="list-item col-4 border-top py-2">' + json[i].fin + '</li>';
                    }
                    document.getElementById("grupo-" + num).innerHTML = html;
                });
    }
}




function templateCard(carrera, colegio, nivel, grupo, id) {
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
            '                               <ul id="' + id + '" class="list-unstyled row">  ' +
            '                                   <li class="list-item col-4 border-top py-2"><strong>Dia</strong></li>  ' +
            '                                   <li class="list-item col-4 border-top py-2"><strong>Inicio</strong></li>  ' +
            '                                   <li class="list-item col-4 border-top py-2"><strong>Fin</strong></li>  ' +
            '                                    ' +
            '                               </ul>  ' +
            '                          </div>  ' +
            '                           <br>  ' +
            '                           <a href="#" class="btn btn-primary">Nota</a>  ' +
            '                           <a href="#" class="btn btn-primary">Asistencia</a>  ' +
            '                       </div>  ' +
            '                  </div>  ';
}