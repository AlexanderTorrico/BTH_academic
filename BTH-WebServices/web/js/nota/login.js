$(document).ready(function () {
    
});
var puerto= "36129";

function validateloginQuery(){
    if(document.getElementById("username").value && document.getElementById("pass").value){
        loginQuery();
    }else{
        alert("Debes completar todos los campos");
    }
}
function loginQuery() {
   
    var data = {
        username: document.getElementById("username").value,
        password: document.getElementById("pass").value
    };
    fetch("/bth/api/login/docente", {
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
                if(json["success"]){
                    json=json["response"];
                    if(json["estado"]==1){
                        localStorage.setItem("idDocente",json["id"]);
                        localStorage.setItem("nombre",json["nombreCompleto"]);
                        location.href="/bth/docentegrupos.html";
                    }else{
                        alert("El docente no ha confirmado su cuenta");
                    }
                    
                }else{
                    alert("Usuario o Contraseña incorrepta");
                }
            });

}

function saludo(){
    alert("sdfghj,.");
}