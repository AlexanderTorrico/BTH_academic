import 'package:bth_movil/Utils/constants.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class AsistenciaPage extends StatefulWidget {
  @override
  _AsistenciaPageState createState() => _AsistenciaPageState();
}

class _AsistenciaPageState extends State<AsistenciaPage> {
  @override
  Widget build(BuildContext context) {
    print("Estadivarius");
    getPagoFuture();
    return Scaffold(
      appBar: AppBar(
        title: Text("Asistencia"),
        backgroundColor: Constantes.colorEstandar,
      ),
      body: ListView(
        children: <Widget>[
          itemCardAsistencia(1, '02-05-2020', 'Profe estoy resfriado', 'h')
        ],
      ),
    );
  }

  Widget itemCardAsistencia(int trimestre, String fecha, String motivo, String tipo){
    //Widget _title = Text("$fecha");
    
    return Card(
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: <Widget>[
          ListTile(
            leading: Icon(Icons.album),
            title: Text(fecha),
            subtitle: Text(motivo),
          ),
          ButtonBar(
            children: <Widget>[
              FlatButton(
                child: const Text('Enviar motivo'),
                onPressed: () {
                  _neverSatisfied();
                },
              ),
              
            ],
          ),
        ],
      ),
    );
  }


  Future<String> getPagoFuture() async{
    
      try{
        final url =Constantes.url+"bth_beta/src/all/pago.php/?idEstudiante=2&idGrupo=2";
        final response = 
          await http.get(url);
      
        print(response.body);
      }catch(e){
        print(e);
      }
    
    return null;
  }

  Future<void> _neverSatisfied() async {
    return showDialog<void>(
      context: context,
      barrierDismissible: false, // user must tap button!
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Enviar motivo'),
          content: SingleChildScrollView(
            child: ListBody(
              children: <Widget>[
                Text('Una vez enviado el motivo de la observacion no se podra agregar otro motivo ni actualizar dicha informacion'),
                TextFormField(),
              ],
            ),
          ),
          actions: <Widget>[
            FlatButton(
              child: Text('Enviar'),
              onPressed: () {
                Navigator.of(context).pop();
                _confirm();
              },
            ),
          ],
        );
      },
    );
  }

  Future<void> _confirm() async {
    return showDialog<void>(
      context: context,
      barrierDismissible: false, // user must tap button!
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Rewind and remember'),
          content: SingleChildScrollView(
            child: ListBody(
              children: <Widget>[
                Text('You will never be satisfied.'),
                Text('You\’re like me. I’m never satisfied.'),
              ],
            ),
          ),
          actions: <Widget>[
            FlatButton(
              child: Text('Regret'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }
}
