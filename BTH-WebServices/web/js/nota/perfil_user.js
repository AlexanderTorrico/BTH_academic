$(document).ready(function () {

});

getRoles();

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
                
                var array = [];
                json = json["response"];
                var html = "";
                for (var i in json) {
                    console.log(json[i]);
                    //html += templateCard(json[i].carrera, json[i].colegio, json[i].nivel, grupo, json[i].id);
                    if(json[i].idRol==1){
                        
                        html+=templateDocente();
                    }else if(json[i].idRol==2){
                        html+=templateColegio(json[i]);
                    }else if(json[i].idRol==3){
                        html+=templateAdmBTH();
                    }
                }

                document.getElementById("body").innerHTML = html;
            });

}
function btnDocnete(){
    
}
function btnColegio(idColegio){
    localStorage.setItem("objColegio", idColegio);
    location.href = "/bth/PerfilColegio.html";
}
function btnAdminBTH(){
    location.href = "/bth/Perfilbth.html";
}
function templateDocente(){
    return  '   <div class="card mt-5">  '  + 
 '                   <div class="card-header">  '  + 
 '                       Docente Bachillerato tecnico humanistico  '  + 
 '                   </div>  '  + 
 '                   <div class="card-body">  '  + 
 '                       <div class="row">  '  + 
 '                           <div class="col-9">  '  + 
 '                               <h5 class="card-title">Docente BTH</h5>  '  + 
 '                               <p class="card-text">Con esta cuenta se podra tener un mejor control de los estudiantes al poder controlar la nota, asistencia.</p>  '  + 
 '                               <a href="#" class="btn btn-primary">Ingresar</a>  '  + 
 '                           </div>  '  + 
 '                           <div class="col-2">  '  + 
 '                               <img src="imagenes/bth/docente.png"  width="20" class="card-img" alt="...">  '  + 
 '                           </div>  '  + 
 '                       </div>  '  + 
 '                   </div>  '  + 
 '              </div>  ' ;
}

function templateColegio(json){
    var nombre = "Admin Colegio "+json["colegio"];
    return  '   <div class="card mt-5">  '  + 
 '                   <div class="card-header">  '  + 
 '                       Administrador  de Colegios Bachillerato tecnico humanistico  '  + 
 '                   </div>  '  + 
 '                   <div class="card-body">  '  + 
 '                       <div class="row">  '  + 
 '                           <div class="col-9">  '  + 
 '                               <h5 class="card-title">'+nombre+'</h5>  '  + 
 '                               <p class="card-text">Con esta cuenta se podra registrar estudiantes y asignarlos a los diferentes grupo como tabien un control de pagos</p>  '  + 
 '                               <a href="#" class="btn btn-primary" onClick="btnColegio('+json["idReference"]+')">Ingresar</a>  '  + 
 '                           </div>  '  + 
 '                           <div class="col-2">  '  + 
 '                               <img src="imagenes/bth/colegio.png"  width="20" class="card-img" alt="...">  '  + 
 '                           </div>  '  + 
 '                       </div>  '  + 
 '                   </div>  '  + 
 '              </div>  ' ; 
}

function templateAdmBTH(){
    return  '   <div class="card mt-5">  '  + 
 '                   <div class="card-header">  '  + 
 '                       Administrador Bachillerato tecnico humanistico  '  + 
 '                   </div>  '  + 
 '                   <div class="card-body">  '  + 
 '                       <div class="row">  '  + 
 '                           <div class="col-9">  '  + 
 '                               <h5 class="card-title">Administrador BTH</h5>  '  + 
 '                               <p class="card-text">Con esta cuenta se podra administrar el bth como creacon de grupos, carreras, colegio y control de los estudiantes.</p>  '  + 
 '                               <a href="#" class="btn btn-primary" onClick="btnAdminBTH()">Ingresar</a>  '  + 
 '                           </div>  '  + 
 '                           <div class="col-2">  '  + 
 '                               <img src="imagenes/bth/admin.png"  width="20" class="card-img" alt="...">  '  + 
 '                           </div>  '  + 
 '                       </div>  '  + 
 '                   </div>  '  + 
 '              </div>  ' ; 
}
