import 'package:flutter/material.dart';

class Constantes{
  
  static final Color colorEstandar = Color.fromRGBO(109, 0, 122, 1.0);
  static final Color colorBtn = Color.fromRGBO(109, 0, 122, 1.0);
  
  static final url = 'http://192.168.43.89:36129/bth/api/';

  static String id;
  static String idEg;

  static String getMesTxt(String i){
    if(i=="1"){
      return 'Enero';
    }else if(i=="2"){
      return 'Febrero';
    }else if(i=="3"){
      return 'Marzo';
    }else if(i=="4"){
      return 'Abril';
    }else if(i=="5"){
      return 'Mayo';
    }else if(i=="6"){
      return 'Junio';
    }else if(i=="7"){
      return 'Julio';
    }else if(i=="8"){
      return 'Agosto';
    }else if(i=="9"){
      return 'Septiembre';
    }else if(i=="10"){
      return 'Octubre';
    }else if(i=="11"){
      return 'Noviembre';
    }else{
      return 'Diciembre';
    }
  }
}