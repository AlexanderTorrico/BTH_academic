$(document).ready(function () {

});
function loadParametro() {
    var html = document.getElementById("parametroTemplate").innerHTML; 
    document.getElementById("body").innerHTML = html;
}

function loadVerNota() {
  var html = document.getElementById("verNotaTemplate").innerHTML; 
  document.getElementById("body").innerHTML = html;
}

function loadAddNota() {
   
  fetch('http://localhost:43169/bth/api/parametro/gbg/1')
    .then(function(response) {
      return response.json();
    })
    .then(function(myJson) {
      console.log(myJson.response);
    });
}

function saludo(){
  console.log("Estaviones d afdsjl");
}

function procesarRegistro(respuesta){
  console.log(respuesta);
}


function templateParametro(){
    return "";
}