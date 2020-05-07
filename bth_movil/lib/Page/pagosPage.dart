import 'dart:convert';

import 'package:bth_movil/Utils/constants.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;


class PagosPage extends StatefulWidget {
  @override
  _PagosPageState createState() => _PagosPageState();
}

class _PagosPageState extends State<PagosPage>{

  @override
  Widget build(BuildContext context) {
    
    getPagoFuture();

    return Scaffold(
      appBar: AppBar(
        title: Text('Pagos'),
        backgroundColor: Constantes.colorEstandar,
      ),
      body: FutureBuilder<List>(
        future: getPagoFuture(),
        builder: (context, snapshot){
          if(snapshot.hasError) print(snapshot.error);
          return snapshot.hasData
            ? new ItemList(
              list: snapshot.data,
            ): new Center(
              child: new CircularProgressIndicator(),
            );
        },
      ),
    );
  }


  Widget itemPagoWidget(int mes, String fecha, double monto){
    Widget _icon;
    Widget _subTitle;

    if(fecha!=null){
      _icon =Icon(Icons.monetization_on, color: Colors.greenAccent,);
      _subTitle = Row(
        children: <Widget>[
          Text('Fecha: ',style: TextStyle(fontWeight: FontWeight.bold),),
          Text(fecha),
          Text('   Monto: ', style: TextStyle(fontWeight: FontWeight.bold),),
          Text('$monto Bs'),
        ],
      );
    }else{
      _icon =Icon(Icons.money_off, color: Colors.redAccent,);
      _subTitle = Row(
        children: <Widget>[
          Text('Fecha: ',style: TextStyle(fontWeight: FontWeight.bold),),
          Text('Pendiente'),
          Text('   Monto: ', style: TextStyle(fontWeight: FontWeight.bold),),
          Text('--'),
        ],
      );
    }

    return Card(
      child: ListTile(
        leading: _icon,
        //title: Text(mtdMesTxt(mes)),
        subtitle: _subTitle,
      ),
    );
  }

  
  Future<List> getPagoFuture() async{
    
      try{
        
        final String _id = Constantes.id;
        final String _idEg = Constantes.idEg;

        final url ="http://192.168.43.150:36129/bth/api/pago/byGroup";
        print(_id +"  "+ _idEg+"-----------------------");
        /*final response = 
          await http.get(url);*/

        final response = await http.post(url, 
            headers: {"Content-Type": "application/json"},
            body: jsonEncode({'id': _id, 'idGrupo': _idEg}));  

        print(url);

        print(response.body);
        return json.decode(response.body);
        
      }catch(e){
        print(e);
        return null;
      }
    
  }
  
}

class ItemList extends StatelessWidget {
  final List list;
  ItemList({this.list});

  @override
  Widget build(BuildContext context) {
    final i =list == null ? 0 : list.length;
    if(i==0){
      return getRequuestVoid();
    }else{
      return getListPagos(context, i);
    }
    
  }

  Widget getRequuestVoid(){
    return Center(child: Text("No se a realizado ningun pago"));
  }

  Widget getListPagos(context, i){
    return new ListView.builder(
      itemCount: i,
      
      itemBuilder: (context, i){
        
        return Container(
          padding:  const EdgeInsets.all(10.0),
          child:  new GestureDetector(
            onTap: () => null,
            child: Card(
              child: ListTile(
                title: Text(' ${Constantes.getMesTxt(list[i]["mes"])}',
                  style: TextStyle(fontWeight: FontWeight.bold),
                ),
                leading: new Icon(
                  Icons.people,
                  size: 40.0,
                  color: Colors.black87,
                ),
                subtitle: Row(
                  children: <Widget>[
                    Text('Fecha: ',style: TextStyle(fontWeight: FontWeight.bold),),
                    Text(list[i]["fecha"]),
                    Text('   Monto: ', style: TextStyle(fontWeight: FontWeight.bold),),
                    Text(list[i]["monto"]),
                  ],
                ),
              ),
            ),
          ),

        );
      }
      
    );
  }
}