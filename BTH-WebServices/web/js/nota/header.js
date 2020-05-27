userDocente();

function cerrar(){
    var msg = confirm("Estas seguro de cerrar su sesion.");
    if(msg){
        location.href="/bth/loginbth.html";
    }
}

function grupo(){
    location.href="/bth/docentegrupos.html";
}

function userDocente(){
    document.getElementById("usernamedocente").innerText = "Doc: "+localStorage.getItem("nombre");
}