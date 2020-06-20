$(document).ready(function () {
    loadDate();
});

function addNewAsistencia(){
    
    
    var data = {
        id:localStorage.getItem("grupo"), //---------------------------idGrupo
        fecha:new Date(document.getElementById("date").value)
    };
    
    fetch('/bth/api/asistencia/insertByGroup',{
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
                
                
                alert(myJson["message"]);
                loadDate();
            });
            
}


function loadDate() {
    
    var data = {
        id:localStorage.getItem("grupo") //---------------------------idGrupo
    };
    
    fetch('/bth/api/asistencia/getDateByGroup',{
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
                var formateDate = "'date'";
                var formatetext = "'text'";
                var html = '<input placeholder="Nueva Asistencia" class="text-center" type="text" onfocus="(this.type = '+formateDate+' )" onblur="(this.type = '+formatetext+')" id="date" onchange="addNewAsistencia()" />';
                
                myJson = myJson["response"];
                for(i in myJson){
                    html+="<button type='button' class='btn btn-secondary' data-fecha='"+myJson[i]["fecha"]+"' onClick='loadListAssistance(this)'>"+myJson[i]["fecha"]+"</button>";
                }
                html+="<button type='button' class='btn btn-secondary' onClick='loadListFinal()'>Informe</button>";
                document.getElementById("btn_fechas").innerHTML= html;
            });
}

function loadListFinal(){
    
    document.getElementById("title").innerText = "Informe lista de aistencia";
    var data = {
        id:localStorage.getItem("grupo")
    };
    
    fetch('/bth/api/asistencia/getListFinal',{
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
                if(!myJson["success"]){
                    console.log(myJson);
                    return;
                }
                var html = "";
                
                myJson = myJson["response"];
                var count = 0;
                for(i in myJson){
                    
                    count =parseInt(count)+parseInt(1);
                    html+=templateItemAssistanceFinal(count, myJson[i]["nombre"], myJson[i]["presente"], myJson[i]["falta"], myJson[i]["retraso"] );
                }
                
                document.getElementById("table_assistance").innerHTML= html;
            });
}


function loadListAssistance(obj) {
    var event = new Date(obj.dataset.fecha);
    var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    document.getElementById("title").innerText = event.toLocaleDateString(undefined, options);
    var data = {
        id:localStorage.getItem("grupo"),
        fecha:(new Date(obj.dataset.fecha))
    };
    
    fetch('/bth/api/asistencia/getListAssistanceByGroup',{
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
                if(!myJson["success"]){
                    console.log(myJson);
                    return;
                }
                var html = "";
                
                
                myJson = myJson["response"];
                var count = 0;
                for(i in myJson){
                    
                    count =parseInt(count)+parseInt(1);
                    html+=templateItemAssistance(count, myJson[i]["id"], myJson[i]["nombre"], myJson[i]["tipo"] );
                }
                
                document.getElementById("table_assistance").innerHTML= html;
            });
}

function acctionPresente(obj){
    
    var data = {
        id:obj.dataset.id,
        tipo:obj.dataset.tipo
    };
    
    fetch('/bth/api/asistencia/update',{
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
                
            });
}



function templateItemAssistance(count,id, nombre, tipo){
    var check_presente="", check_retraso="", check_falta="";
    if(tipo=="p"){
        check_presente="checked";
    }else if(tipo=="r"){
        check_retraso="checked";
    }else if(tipo=="f"){
        check_falta="checked";
    }
    
    return  '   <tr>  '  + 
 '                                       <th scope="row">'+count+'</th>  '  + 
 '                                       <td>'+nombre+'</td>  '  + 
 '                                       <td><input type="radio" class="checks_asist" name="'+id+'" value="4" data-id="'+id+'" data-tipo="p" onClick="acctionPresente(this)" '+check_presente+'></td>  '  + 
 '                                       <td><input type="radio" class="checks_asist" name="'+id+'" value="4" data-id="'+id+'" data-tipo="r" onClick="acctionPresente(this)" '+check_retraso+'></td>  '  + 
 '                                       <td><input type="radio" class="checks_asist" name="'+id+'" value="4" data-id="'+id+'" data-tipo="f" onClick="acctionPresente(this)" '+check_falta+'></td>  '  + 
 '                                  </tr>  ' ;
}

function templateItemAssistanceFinal(count, nombre, presente, falta, retraso){
    
    return  '   <tr>  '  + 
 '                                       <th scope="row">'+count+'</th>  '  + 
 '                                       <td>'+nombre+'</td>  '  + 
 '                                       <td>'+presente+'</td>  '  + 
 '                                       <td>'+retraso+'</td>  '  + 
 '                                       <td>'+falta+'</td>  '  + 
 '                                  </tr>  ' ;
}