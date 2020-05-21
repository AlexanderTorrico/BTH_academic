$(document).ready(function () {
    
});
loadListEstudents();

$(function () {
    
    $('[data-toggle="popover"]').popover();
            
    $('.btn-danger').popover({title: "Encabezado", content: "Animación en true", animation: true}); 
    $('.btn-warning').popover({title: "Encabezado", content: "Animación en false", animation: false}); 
});
        

$('.popover-dismiss').popover({
          trigger: 'focus'
        });


function loadListEstudents(){
    
    var data = {
        idEstudiantes_grupos:localStorage.getItem("grupo"),
        id:20
    };
    //getInfoGrupoInDropdowns();
    //document.getElementById("body").innerHTML = templateParametro2();
    fetch('http://localhost:36129/bth/api/pago/mesAPagar',{
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
                console.log("myjsonsdfghjk,");
                var count = Object.keys(myJson).length;;
                var html = templeteItemTitleDeudores();
                for(var i = 1; i<count;i++){
                    var mesesTxt = "";
                    
                    
                    for(var j = 0; j<myJson[i].mes; j++ ){
                        var num = myJson[0].mes-j;
                        mesesTxt+=meses(num)+" - ";
                    }
                    mesesTxt = mesesTxt.substring(0,mesesTxt.length-2);
                    html+=templeteItemDeudores(myJson[i].nombre, myJson[i].mes, mesesTxt);
                }
                document.getElementById("listDeudores").innerHTML = html;
                
            });
}

function meses(mes){
    var meses = "";
    if(mes==1){
        meses = "ENERO";
    }else if(mes==2){
        meses = "FEBRERO";
    }else if(mes==3){
        meses = "MARZO";
    }else if(mes==4){
        meses = "ABRIL";
    }else if(mes==5){
        meses = "MAYO";
    }else if(mes==6){
        meses = "JUNIO";
    }else if(mes==7){
        meses = "JULIO";
    }else if(mes==8){
        meses = "AGOSTO";
    }else if(mes==9){
        meses = "SEPTIEMBRE";
    }else if(mes==10){
        meses = "OBTUBRE";
    }else if(mes==11){
        meses = "NOVIEMBRE";
    }else if(mes==12){
        meses = "DICIEMBRE";
    }
    return meses;
}


function templeteItemTitleDeudores(){
    return   '   <li class="list-group-item list-group-item-action active d-flex justify-content-between align-items-center">  '  + 
 '                               Nombre de estudiantes  '  + 
 '                               <span class="badge badge-primary badge-pill">Deuda de meses</span>  '  + 
 '                          </li>  ' ; 
}


function templeteItemDeudores(nombre, numero, meses){
    return  '   <li class="list-group-item d-flex justify-content-between align-items-center">  '  + nombre +  
 '                               <span type="button" class="badge badge-danger badge-pill" data-toggle="popover" title="'+meses+'" data-content="Contenido del popover personalizado.">'+numero+'</span>    '  + 
 '                          </li>  ' ;
}