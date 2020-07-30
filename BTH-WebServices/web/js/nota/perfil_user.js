function procesarTodo(respuesta) {
    if (respuesta.success) {
        var list = respuesta.response;
        for (var i in list) {
            if (localStorage.getItem('idDocente') == list[i].id) {
                console.log(list[i].roles);
                localStorage.setItem('roles', list[i].roles);
            }
        }
    } else {
        alert(respuesta.message);
    }
}

$(document).ready(function () {
    if (localStorage.getItem('idDocente') == null || localStorage.getItem('nombre') == null) {
        window.location.href = "/bth/index.html";
    } else {
        localStorage.removeItem("rol");
        localStorage.removeItem("idColegio");
        localStorage.removeItem("objColegio");
        jQuery.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'type': 'GET',
            'url': 'api/Usuario/Listado',
            'dataType': 'json',
            'success': procesarTodo
        });
    }
});

if (localStorage.getItem("idAux")) {
    localStorage.setItem("idDocente", localStorage.getItem("idAux"));
    localStorage.removeItem("idAux");
}

init();

function init() {
    existDataUserRole();
}

function existDataUserRole() {
    var data = {
        id: localStorage.getItem("idDocente")
    };
    fetch("/bth/api/user-roles/exist", {
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
                console.log(json);
                if (json["success"]) {
                    getRoles();
                    document.getElementById("btnConvertBTHAdmin").style.visibility = "hidden";
                    document.getElementById("adminBTH").style.display = "none";
                } else {
                    document.getElementById("btnConvertBTHAdmin").style.visibility = "none";
                }
            });
}


function getRoles() {
    var data = {
        id: localStorage.getItem("idDocente")
    };
    fetch("/bth/api/user-roles/get", {
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
                console.log(json);
                var array = [];
                json = json["response"];
                var html = "";
                for (var i in json) {
                    //html += templateCard(json[i].carrera, json[i].colegio, json[i].nivel, grupo, json[i].id);
                    if (json[i].idRol == 1) {

                        html += templateDocente(json[i]);
                    } else if (json[i].idRol == 2) {
                        html += templateColegio(json[i]);
                    } else if (json[i].idRol == 3) {
                        html += templateAdmBTH();
                    }
                }
                document.getElementById("body").innerHTML = html;
            });
}

function getIdDocente() {
    var data = {
        id: localStorage.getItem("idDocente")
    };
    fetch("/bth/api/user-roles/getIddocente", {
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
                console.log(json);
                localStorage.setItem("idDocente", json[0].id);
            });
}

function convertUserInBTHAdmin() {
    var data = {
        id: localStorage.getItem("idDocente")
    };
    
    fetch("/bth/api/user-roles/convertAdmBTH", {
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
                alert("El usuario se haa convertido en AdminBTH");
                document.getElementById("btnConvertBTHAdmin").style.visibility = "hidden";
                getRoles();
            });
}

function btnDocnete() {
    localStorage.setItem("idAux", localStorage.getItem("idDocente"));
    localStorage.setItem("rol", "Docente");
    getIdDocente();
    location.href = "/bth/docentegrupos.html";
}

function btnColegio(obj) {
    localStorage.setItem("idColegio", obj);
    localStorage.setItem("rol", "Administrador");
    location.href = "/bth/perfilcolegio.html";
}

function btnAdminBTH() {
    localStorage.setItem("rol", "AdministradorBTH");
    location.href = "/bth/Perfilbth.html";
}

function templateDocente() {
    return  '   <div class="card mt-5">  ' +
            '                   <div class="card-header">  ' +
            '                       Docente Bachillerato técnico humanístico  ' +
            '                   </div>  ' +
            '                   <div class="card-body">  ' +
            '                       <div class="row">  ' +
            '                           <div class="col-9">  ' +
            '                               <h5 class="card-title">Docente BTH</h5>  ' +
            '                               <p class="card-text">Con esta cuenta se podrá tener un mejor control de los estudiantes, al poder controlar la nota y la asistencia.</p>  ' +
            '                               <a href="#" class="btn btn-primary" onClick="btnDocnete()">Ingresar</a>  ' +
            '                           </div>  ' +
            '                           <div class="col-2">  ' +
            '                               <img src="imagenes/bth/docente.png"  width="20" class="card-img" alt="...">  ' +
            '                           </div>  ' +
            '                       </div>  ' +
            '                   </div>  ' +
            '              </div>  ';
}

function templateColegio(json) {
    var nombre = "Admin Colegio " + json["colegio"];
    localStorage.setItem("nombre", json["colegio"]);
    return  '   <div class="card mt-5">  ' +
            '                   <div class="card-header">  ' +
            '                       Administrador  de Colegios Bachillerato técnico humanístico  ' +
            '                   </div>  ' +
            '                   <div class="card-body">  ' +
            '                       <div class="row">  ' +
            '                           <div class="col-9">  ' +
            '                               <h5 class="card-title">' + nombre + '</h5>  ' +
            '                               <p class="card-text">Con esta cuenta se podrá registrar estudiantes y asignarlos a los diferentes grupo como también un control de pagos.</p>  ' +
            '                               <a href="#" class="btn btn-primary" onClick="btnColegio(' + json["idReferencia"] + ')">Ingresar</a>  ' +
            '                           </div>  ' +
            '                           <div class="col-2">  ' +
            '                               <img src="imagenes/bth/colegio.png"  width="20" class="card-img" alt="...">  ' +
            '                           </div>  ' +
            '                       </div>  ' +
            '                   </div>  ' +
            '              </div>  ';
}

function templateAdmBTH() {
    return  '   <div class="card mt-5">  ' +
            '                   <div class="card-header">  ' +
            '                       Administrador Bachillerato técnico humanístico  ' +
            '                   </div>  ' +
            '                   <div class="card-body">  ' +
            '                       <div class="row">  ' +
            '                           <div class="col-9">  ' +
            '                               <h5 class="card-title">Administrador BTH</h5>  ' +
            '                               <p class="card-text">Con esta cuenta se podrá administrar el bth como creación de grupos, carreras, colegio y control de los estudiantes.</p>  ' +
            '                               <a href="#" class="btn btn-primary" onClick="btnAdminBTH()">Ingresar</a>  ' +
            '                           </div>  ' +
            '                           <div class="col-2">  ' +
            '                               <img src="imagenes/bth/admin.png"  width="20" class="card-img" alt="...">  ' +
            '                           </div>  ' +
            '                       </div>  ' +
            '                   </div>  ' +
            '              </div>  ';
}