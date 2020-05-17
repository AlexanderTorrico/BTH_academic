$(document).ready(function () {

});


function loadNota(){
    dropdownsTipo();
    localStorage.setItem("aaccion","showNote");
    if(localStorage.getItem("gurpo")&&localStorage.getItem("trimestre")&&localStorage.getItem("tipo")){
        
        createTableShowNota();
    }
    
}

/*  Cargar combo Box   */
var puerto= "36129";
var contacts = [
    "Haber",
    "Hacer",
    "Saber"
];
var rubrica = [
    "h",
    "c",
    "s"
];



function dropdownsTipo() {
    
    var menuHtml = '   <div class="dropdown">  ' +
            '                       <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButtonTipo" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">  ' +
            '                           Tipo  ' +
            '                       </button>  ' +
            '                      <div id="grupoDropdownTipo" class="dropdown-menu" aria-labelledby="dropdownMenuButtonTipo">  ';
    for (i = 0; i < contacts.length; i++) {
        menuHtml += '<a class="dropdown-item" href="#" onclick="saveTipo('+i+')">' + contacts[i] + '</a>';
    }
    menuHtml += '</div>' +
            '</div> ';
    document.getElementById("tipo").innerHTML = menuHtml;
}


$("#grupoDropdownTipo a").click(function () {
    $(this).parents(".dropdown").find('.btn').html($(this).text() + ' <span class="caret"></span>');
    $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
    
});


function saveTipo(obj) {
    localStorage.setItem("tipo", rubrica[obj]);
    
    createTableShowNota();
    if(localStorage.getItem("grupo") && localStorage.getItem("trimestre")){
        
    }
}

/*  CArgar la tabla */
function createTableShowNota() {
    
    var data = {
        idGrupo:localStorage.getItem("grupo"),
        tipo:localStorage.getItem("tipo"),
        trimestre:localStorage.getItem("trimestre")
    };

    
    fetch("http://localhost:"+puerto+"/bth/api/parametro/showTable", {
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
                if(json["success"]){
                    json = json["response"];
                    console.log(json);
                    var html = '<th scope="col">#</th>';
                    html+= '<th scope="col">Nombre completo</th>';
                    document.getElementById("body").innerHTML = templateTabla();
                    for(i in json){
                        html+= ' <th scope="col" class="text-center">'+json[i].nombre+'<br><button type="button" data-name="'+json[i].nombre+'" class="btn btn-warning p-1" onClick="updateNotaInModal(this)" data-toggle="modal" data-target="#exampleModalScrollableSN">'+
                                '   <svg class="bi bi-chevron-compact-down" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">  '  + 
                                '     <path fill-rule="evenodd" d="M1.553 6.776a.5.5 0 01.67-.223L8 9.44l5.776-2.888a.5.5 0 11.448.894l-6 3a.5.5 0 01-.448 0l-6-3a.5.5 0 01-.223-.67z" clip-rule="evenodd"/>  '  + 
                                '  </svg>  ' +
                        '</button></th> ';
                    }
                    html+= '<th scope="col">Nota</th>';
                    document.getElementById("tablaHeadShowNota").innerHTML = html;
                    
                    
                    llenarTableShowNota();
                }else{
                    alert("Error al traer los datos");
                }
            });
}

function llenarTableShowNota() {
    
    var data = {
        idGrupo:localStorage.getItem("grupo"),
        tipo:localStorage.getItem("tipo"),
        trimestre:localStorage.getItem("trimestre")
    };

    
    fetch("http://localhost:"+puerto+"/bth/api/nota/getList", {
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
                
                
                json = json["response"];
                
                localStorage.setItem("infoNota",JSON.stringify(json));
                var count = Object.keys(json).length;
                var arrayNombre = [];
                
                arrayNombre.push(json[0]["nombre"]);
                
                for(var i = 1; i<count; i++){
                    if(json[0]["nombre"] == json[i]["nombre"] ){
                        break;
                    }else{
                        arrayNombre.push(json[i]["nombre"]);
                    }
                }
                var count = 1;
                var html="";
                
                for(i in arrayNombre){
                    var promedio = 0;
                    var nota = 0;
                    var item="";
                    var nombre="";
                    for(j in json){
                        if(arrayNombre[i] == json[j]["nombre"]){
                            nombre = '<tr><th scope="row">'+count+'</th><td>'+json[j]["nombre"]+'</td>';
                            item+= '<td class="text-center">'+json[j]["nota"]+'</td>';
                            nota+=json[j]["nota"];
                            promedio++;
                        }
                    }
                    nota = nota/promedio;
                    item+= '<td class="text-center">'+nota+'</td>';
                    html+=nombre+item;
                    count = count+1;
                }
                document.getElementById("tablaBodyShowNota").innerHTML = html+"</tr>";
            });
}


function updateNotaInModal(obj){
    document.getElementById("exampleModalScrollableTitleSN").innerHTML = obj.dataset.name;
    var list = [];
    var html = "";
    var json = localStorage.getItem("infoNota");
    json = JSON.parse(json);
    
    for(i in json){
        if(json[i].parametro == obj.dataset.name){
            var count = parseInt(i);
            count+=1;
            
            html+='<tr><td >'+count+'</td><td>'+json[i].nombre+'</td><td><input data-id="'+json[i].id+'" type="number" value="'+json[i].nota+'" max="100" min="0" class="form-control" id="idInput'+json[i].id+'"/></td></tr>';
            
        }
    }
    
    document.getElementById("modal-bodySN").innerHTML = templateModalUpdateNote();
    document.getElementById("tableModalUpdateNote").innerHTML = html;
    
}

function saveUpdateNote(){
    var tipo = document.getElementById("exampleModalScrollableTitleSN").innerText;
    var arrayNombre = [];
    var data = [];
    var json = JSON.parse(localStorage.getItem("infoNota"));
    var count = Object.keys(json).length;
    
    for(var i = 0; i<count; i++){
        if(json[i]["parametro"] == tipo ){
            var nameId = "idInput"+json[i]["id"];
            var obj = {
              "id":  json[i]["id"],
              "nota": parseInt(document.getElementById(nameId).value)
            };
            data.push(obj);
        }
    }
    
    
    fetch('http://localhost:36129/bth/api/nota/update',{
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
                llenarTableShowNota();
                
            });
}


function templateTabla(){
    var html = "";
    var inicio = '   <div class="col-12 mt-5">  '  + 
 '                   <table class="col-12 table table-striped table-responsive-md">  '  + 
 '                   <thead id="tablaHeadShowNota" class="thead-dark">  '  + 
 '                   <tr>  ';
    
    return   inicio+
 '                   </tr>  '  + 
 '                   </thead>  '  + 
 '                   <tbody id="tablaBodyShowNota">  '  + 
 
 '                   </tbody>  '  + 
 '                   </table>  '  + 
 '                  </div>  ' ; 
}

function templateItemTabla(){
    return  '   <tr>  '  + 
 '                   <th scope="row">3</th>  '  + 
 '                   <td>Larry</td>  '  + 
 '                   <td>the Bird</td>  '  + 
 '                   <td>@twitter</td>  '  + 
 '                  </tr>  ' ; 
}

function templateModalUpdateNote(){
    return  '   <table class="table table-borderless">  '  + 
 '     <thead>  '  + 
 '       <tr>  '  + 
 '         <th scope="col">#</th>  '  +
 '         <th scope="col">Nombre completo</th>  '  + 
 '         <th scope="col class="text-center">Nota</th>  '  + 
 
 '       </tr>  '  + 
 '     </thead>  '  + 
 '     <tbody id="tableModalUpdateNote">  '  + 
 
 
 '     </tbody>  '  + 
 '  </table>  ' ; 
}
