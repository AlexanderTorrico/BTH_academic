$(document).ready(function () {

});
var puerto= "36129";
var contacts = [
    "1: Primer Trimestre",
    "2: Segundo Trimestre",
    "3: Tercer Trimestre"
];

getInfoGrupoInDropdowns();
dropdownsTrimestre();

function dropdownsTrimestre() {
    localStorage.removeItem("trimestre");
    var menuHtml = '   <div class="dropdown">  ' +
            '                       <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButtonTrimestre" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">  ' +
            '                           Timestre  ' +
            '                       </button>  ' +
            '                      <div id="grupoDropdownTrimestre" class="dropdown-menu" aria-labelledby="dropdownMenuButtonTrimestre">  ';
    for (i = 0; i < contacts.length; i++) {
        var numero = i + 1;
        menuHtml += '<a class="dropdown-item" data-id="' + numero + '" href="#" onclick="saveTrimetre(this)">' + contacts[i] + '</a>';
    }
    menuHtml += '</div>' +
            '</div> ';
    document.getElementById("trimestre").innerHTML = menuHtml;
}


function saveTrimetre(obj) {
    localStorage.setItem("trimestre", obj.dataset.id);
    if(localStorage.getItem("grupo")){
        loadParametro();
    }
}
function saveGrupo(tupla) {
    localStorage.setItem("grupo",tupla.dataset.id);
    var txt = "Carrera: " + tupla.dataset.carrera+"      Grado: "+tupla.dataset.nivel+"°        Colegio: " + tupla.dataset.nombre;
    document.getElementById("divInfoGrupo").innerText = txt;
    if(localStorage.getItem("trimestre")){
        loadParametro();
    }
    
    
}
function dropdownsGrupo(json) {
    localStorage.removeItem("grupo");
    
    var menuHtml = '   <div class="dropdown">  ' +
            '                       <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButtonGrupo" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">  ' +
            '                           Seleccione el grupo  ' +
            '                       </button>  ' +
            '                      <div id="grupoDropdownGrupo" class="dropdown-menu" aria-labelledby="dropdownMenuButtonGrupo">  ';
    for (var i in json) {
        
        var nombre = "Grupo: "+json[i].id+"-"+json[i].sigla+" [Col: "+json[i].nombre+"]";
        menuHtml += '<a class="dropdown-item" href="#" data-carrera="'+json[i].carrera+'" data-id="'+json[i].id+'" data-nombre="'+json[i].nombre+'" data-nivel="'+json[i].nivel+'" data-sigla="'+json[i].sigla+'" onclick="saveGrupo(this)">' + nombre + '</a>';
    }
    menuHtml += '</div>' +
            '</div>';
    document.getElementById("grupo").innerHTML = menuHtml;
}


$(".dropdown-menu a").click(function () {
    $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
    $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
});

$("#grupoDropdownTrimestre a").click(function () {
    $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
    $(this).parents(".dropdown").find('.btn').val($(this).data('value'));

});

// Boton parametro



function loadVerNota() {
    var html = document.getElementById("verNotaTemplate").innerHTML;
    document.getElementById("body").innerHTML = html;
}

function loadParametro() {
    var data = {
        idGrupo:localStorage.getItem("grupo"),
        trimestre:localStorage.getItem("trimestre")
    };
    //getInfoGrupoInDropdowns();
    document.getElementById("body").innerHTML = templateParametro2();
    fetch('http://localhost:'+puerto+'/bth/api/parametro/gbg',{
        method:"Post",
        body: JSON.stringify(data),
        headers: {
            'Accept': 'application/json, text/plain',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    })
            .then(function (response) {
                return response.json();
            })
            .then(function (myJson) {

                var htmlHaber = "";
                var htmlHacer = "";
                var htmlSaber = "";

                for (i in myJson.response) {
                    if (myJson.response[i].tipo == "h") {
                        htmlHaber += templateParametroChildren(myJson.response[i].id, myJson.response[i].nombre);
                    } else if (myJson.response[i].tipo == "c") {
                        htmlHacer += templateParametroChildren(myJson.response[i].id, myJson.response[i].nombre);
                    } else if (myJson.response[i].tipo == "s") {
                        htmlSaber += templateParametroChildren(myJson.response[i].id, myJson.response[i].nombre);
                    }
                }

                document.getElementById("parametroHaber").innerHTML = htmlHaber;
                document.getElementById("parametroHacer").innerHTML = htmlHacer;
                document.getElementById("parametroSaber").innerHTML = htmlSaber;
                //dropdownsTrimestre();
            });
}


// Boton add parametro ##############################


function titleMoldal(obj) {
    document.getElementById("exampleModalCenterTitle").innerHTML = obj.dataset.name;
}

function insertParam() {
    var input = document.getElementById("txtModalParam");
    var title = document.getElementById("exampleModalCenterTitle").innerText;

    if (title == "Haber") {
        title = "h";
    } else if (title == "Hacer") {
        title = "c";
    } else if (title == "Saber") {
        title = "s";
    }

    var data = {
        nombre: input.value,
        tipo: title,
        trimestre: localStorage.getItem("trimestre"),
        idGrupo: localStorage.getItem("grupo")
    };

    input.value = "";
    fetch("http://localhost:"+puerto+"/bth/api/parametro/i", {
        method: 'POST',
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
                //console.log(json);
                loadParametro()
            });
}

function deleteParam(obj) {
    if (confirm("Seguro al eliminar el parametro")) {
        fetch("http://localhost:"+puerto+"/bth/api/parametro/eliminar/" + obj)
                .then(function (request) {
                    return request.json();
                })
                .then(function (json) {
                    
                    loadParametro();
                });
    }
}
function nameTitleModalUpdate(obj, id) {
    document.getElementById("exampleModalCenterTitle2").innerHTML = obj.dataset.name;
    localStorage.setItem('idParametro', id);
}
function updateParam() {
    var input = document.getElementById("txtModalUpdateParam");

    var data = {
        id: localStorage.getItem('idParametro'),
        nombre: input.value
    };
    fetch("http://localhost:"+puerto+"/bth/api/parametro/update", {
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
                loadParametro();
                localStorage.removeItem("idParametro");
            });

}

function getInfoGrupoInDropdowns() {
    var data = {
        id: 1,
        idDocente: 2 // ----------------------------- Id del docete modificar
    };
    fetch("http://localhost:"+puerto+"/bth/api/grupo/grupoInfo", {
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

// Templates #########################################
function templateParametro2() {
    //Boton activa el modal 

    return (
            '   <div class="modal-content mt-3">  ' +
            '                   <div class="modal-header">  ' +
            '                       <h5 class="modal-title">HABER</h5>  ' +
            '                       <button type="button" class="close" aria-label="Close" data-target="#exampleModalCenter" data-toggle="modal" data-name="Haber" onclick="titleMoldal(this)">  ' +
            '                           <svg class="bi bi-file-earmark-plus" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">  ' +
            '                               <path d="M9 1H4a2 2 0 00-2 2v10a2 2 0 002 2h5v-1H4a1 1 0 01-1-1V3a1 1 0 011-1h5v2.5A1.5 1.5 0 0010.5 6H13v2h1V6L9 1z"/>  ' +
            '                               <path fill-rule="evenodd" d="M13.5 10a.5.5 0 01.5.5v2a.5.5 0 01-.5.5h-2a.5.5 0 010-1H13v-1.5a.5.5 0 01.5-.5z" clip-rule="evenodd"/>  ' +
            '                               <path fill-rule="evenodd" d="M13 12.5a.5.5 0 01.5-.5h2a.5.5 0 010 1H14v1.5a.5.5 0 01-1 0v-2z" clip-rule="evenodd"/>  ' +
            '                           </svg>  ' +
            '                       </button>  ' +
            '                   </div>  ' +
            '                   <div id="parametroHaber" class="modal-body">  ' +
            '                         ' +
            '                   </div>  ' +
            '               </div>  ' +
            '                 ' +
            '               <div class="modal-content mt-3">  ' +
            '                   <div class="modal-header">  ' +
            '                       <h5 class="modal-title">HACER</h5>  ' +
            '                       <button type="button" class="close" aria-label="Close" data-target="#exampleModalCenter" data-toggle="modal" data-name="Hacer" onclick="titleMoldal(this)">  ' +
            '                           <svg class="bi bi-file-earmark-plus" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">  ' +
            '                               <path d="M9 1H4a2 2 0 00-2 2v10a2 2 0 002 2h5v-1H4a1 1 0 01-1-1V3a1 1 0 011-1h5v2.5A1.5 1.5 0 0010.5 6H13v2h1V6L9 1z"/>  ' +
            '                               <path fill-rule="evenodd" d="M13.5 10a.5.5 0 01.5.5v2a.5.5 0 01-.5.5h-2a.5.5 0 010-1H13v-1.5a.5.5 0 01.5-.5z" clip-rule="evenodd"/>  ' +
            '                               <path fill-rule="evenodd" d="M13 12.5a.5.5 0 01.5-.5h2a.5.5 0 010 1H14v1.5a.5.5 0 01-1 0v-2z" clip-rule="evenodd"/>  ' +
            '                           </svg>  ' +
            '                       </button>  ' +
            '                   </div>  ' +
            '                   <div id="parametroHacer" class="modal-body">  ' +
            '                         ' +
            '                   </div>  ' +
            '               </div>  ' +
            '                 ' +
            '               <div class="modal-content mt-3">  ' +
            '                   <div class="modal-header">  ' +
            '                       <h5 class="modal-title">SABER</h5>  ' +
            '                       <button type="button" class="close" aria-label="Close" data-target="#exampleModalCenter" data-toggle="modal" data-name="Saber" onclick="titleMoldal(this)">  ' +
            '                           <svg class="bi bi-file-earmark-plus" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">  ' +
            '                               <path d="M9 1H4a2 2 0 00-2 2v10a2 2 0 002 2h5v-1H4a1 1 0 01-1-1V3a1 1 0 011-1h5v2.5A1.5 1.5 0 0010.5 6H13v2h1V6L9 1z"/>  ' +
            '                               <path fill-rule="evenodd" d="M13.5 10a.5.5 0 01.5.5v2a.5.5 0 01-.5.5h-2a.5.5 0 010-1H13v-1.5a.5.5 0 01.5-.5z" clip-rule="evenodd"/>  ' +
            '                               <path fill-rule="evenodd" d="M13 12.5a.5.5 0 01.5-.5h2a.5.5 0 010 1H14v1.5a.5.5 0 01-1 0v-2z" clip-rule="evenodd"/>  ' +
            '                           </svg>  ' +
            '                       </button>  ' +
            '                   </div>  ' +
            '                   <div id="parametroSaber" class="modal-body">  ' +
            '                         ' +
            '                   </div>  ' +
            '              </div>  '
            );
}
function templateParametroChildren(id, nombre) {
    return ('                             <div class="d-flex bd-highlight mb-3 mr-5 ml-5 mt-1 mb-1">  ' +
            '                               <div class="mr-auto p-2 bd-highlight">' + nombre + '</div>  ' +
            '     ' +
            '                               <button class="btn btn-warning bd-highlight" data-name="' + nombre + '" data-target="#exampleModalCenter2" data-toggle="modal" onclick="nameTitleModalUpdate(this,' + id + ')">  ' +
            '                                   <svg class="bi bi-pen" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor"  ' +
            '                                        xmlns="http://www.w3.org/2000/svg">  ' +
            '                                   <path fill-rule="evenodd"  ' +
            '                                         d="M5.707 13.707a1 1 0 01-.39.242l-3 1a1 1 0 01-1.266-1.265l1-3a1 1 0 01.242-.391L10.086 2.5a2 2 0 012.828 0l.586.586a2 2 0 010 2.828l-7.793 7.793zM3 11l7.793-7.793a1 1 0 011.414 0l.586.586a1 1 0 010 1.414L5 13l-3 1 1-3z"  ' +
            '                                         clip-rule="evenodd" />  ' +
            '                                   <path fill-rule="evenodd"  ' +
            '                                         d="M9.854 2.56a.5.5 0 00-.708 0L5.854 5.855a.5.5 0 01-.708-.708L8.44 1.854a1.5 1.5 0 012.122 0l.293.292a.5.5 0 01-.707.708l-.293-.293z"  ' +
            '                                         clip-rule="evenodd" />  ' +
            '                                   <path d="M13.293 1.207a1 1 0 011.414 0l.03.03a1 1 0 01.03 1.383L13.5 4 12 2.5l1.293-1.293z" />  ' +
            '                                   </svg>  ' +
            '                               </button>  ' +
            '                               <button class="btn btn-danger bd-highlight ml-3" onclick="deleteParam(' + id + ')">  ' +
            '                                   <svg class="bi bi-x-circle" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor"  ' +
            '                                        xmlns="http://www.w3.org/2000/svg">  ' +
            '                                   <path fill-rule="evenodd" d="M8 15A7 7 0 108 1a7 7 0 000 14zm0 1A8 8 0 108 0a8 8 0 000 16z"  ' +
            '                                         clip-rule="evenodd" />  ' +
            '                                   <path fill-rule="evenodd"  ' +
            '                                         d="M11.854 4.146a.5.5 0 010 .708l-7 7a.5.5 0 01-.708-.708l7-7a.5.5 0 01.708 0z" clip-rule="evenodd" />  ' +
            '                                   <path fill-rule="evenodd" d="M4.146 4.146a.5.5 0 000 .708l7 7a.5.5 0 00.708-.708l-7-7a.5.5 0 00-.708 0z"  ' +
            '                                         clip-rule="evenodd" />  ' +
            '                                   </svg>  ' +
            '                               </button>  ' +
            '                          </div>  ');
}

function templateDropdownsGrupos(data) {
    var txt = "Grupo: " + data.sigla + "-" + data.id + " Col: " + data.nombre + " ";
    return '<a class="dropdown-item" href="#">' + txt + '</a><br>';
}